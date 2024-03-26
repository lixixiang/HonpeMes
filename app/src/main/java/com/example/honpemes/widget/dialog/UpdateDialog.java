package com.example.honpemes.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honpemes.MyApplication;
import com.example.honpemes.R;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.LatteLogger;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;

import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportActivity;

import static com.example.honpemes.api.Constants.APPDownload;
import static com.example.honpemes.api.FinalClass.REQUEST_CODE_APP_INSTALL;


/**
 * FileName: UpdateDialog
 * Author: asus
 * Date: 2020/8/16 13:00
 * Description: 检测更新对话框
 */
public class UpdateDialog extends Dialog implements View.OnClickListener {
    TextView tvTitle;
    TextView tvDes;
    Button btnVersionDialogCommit;
    NumberProgressBar numberProgressBar;
    TextView tvProgressNumBer;
    TextView tvProgressPercent;
    LinearLayout llDownload;
    TextView icUpdateCancel;

    private String mProgressNumberFormat;
    private NumberFormat mProgressPercentFormat;
    private long oldBytesRead;
    private Disposable disposable; //网络请求会返回Disposable对象，方便取消网络请求
    Bundle bundle;
    private boolean isGetSize;


    protected Activity _mActivity;
    protected String PATH_APP_DOWNLOAD;

    public UpdateDialog(Activity activity, Bundle bundle) {
        super(activity, R.style.custom_dialog_background);
        this.bundle = bundle;
        _mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.css_update_dialog);
        tvTitle = findViewById(R.id.tv_title);
        tvDes = findViewById(R.id.tv_des);
        btnVersionDialogCommit = findViewById(R.id.btn_version_dialog_commit);
        numberProgressBar = findViewById(R.id.number_progress_bar);
        tvProgressNumBer = findViewById(R.id.tv_progress_number);
        tvProgressPercent = findViewById(R.id.tv_progress_percent);
        llDownload = findViewById(R.id.ll_download);
        icUpdateCancel = findViewById(R.id.ic_update_cancel);
        tvDes.setText(bundle.getString("des").replace("\\r\\n", "\n"));
        llDownload.setVisibility(View.GONE);

        btnVersionDialogCommit.setOnClickListener(this);
        icUpdateCancel.setOnClickListener(this);

        String PATH_APP_ROOT = FileUtils.getAppRootPath(MyApplication.getContext()).getAbsolutePath() + File.separator ;
        PATH_APP_DOWNLOAD = PATH_APP_ROOT + File.separator + "Download";
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_version_dialog_commit:
                // 所有权限都已被授予，执行需要权限的操作
                btnVersionDialogCommit.setVisibility(View.GONE);
                llDownload.setVisibility(View.VISIBLE);
                getRequestDownload();
                break;
            case R.id.ic_update_cancel: //取消网络请求
                if (disposable != null && !disposable.isDisposed()) {
                    EasyHttp.cancelSubscription(disposable);
                }
                dismiss();
                break;
        }
    }

    private void getRequestDownload() {
        disposable = EasyHttp.downLoad(APPDownload)
                .savePath(PATH_APP_DOWNLOAD)
                .saveName("mes.apk")
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void onStart() {
                        LatteLogger.d("path", Thread.currentThread().getName());

                    }

                    @Override
                    public void onError(ApiException e) {
                       dismiss();
                        LatteLogger.d("path", e.getMessage());
                    }

                    @Override
                    public void update(final long bytesRead, final long contentLength, final boolean done) {

                        _mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LatteLogger.d("path", bytesRead + "=============" + contentLength + "=============" + done);
                                if (!isGetSize) {
                                    tvDes.setText(tvDes.getText().toString() + "\n总共：" + contentLength / (1024 * 1024) + "M");
                                    isGetSize = true;
                                }

                                final int progress = (int) (bytesRead * 100 / contentLength);
                                double dProgress = (double) bytesRead / (double) (1024 * 1024);
                                double dMax = (double) contentLength / (double) (1024 * 1024);
                                initFormats();
                                if (mProgressNumberFormat != null) {
                                    String format = mProgressNumberFormat;
                                    tvProgressNumBer.setText(String.format(format, dProgress, dMax));
                                } else {
                                    tvProgressNumBer.setText("");
                                }

                                if (oldBytesRead != 0) {
                                    long NetWorkSpeek = bytesRead - oldBytesRead;
                                    double newSpeek = (double) NetWorkSpeek / (double) (1024);
                                    BigDecimal bg = new BigDecimal(newSpeek);
                                    newSpeek = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                    LatteLogger.d("NetWorkSpeek", NetWorkSpeek + "  " + bytesRead + "  " + oldBytesRead + "  " + newSpeek);
                                    tvProgressPercent.setText(String.valueOf(newSpeek) + "KB/s");
                                }
                                oldBytesRead = bytesRead;
                                LatteLogger.d("progress", progress + "    " + String.valueOf(contentLength) + "   " + done);
                                numberProgressBar.setProgress((int) progress);
                            }
                        });
                    }

                    @Override
                    public void onComplete(String path) {
                        LatteLogger.d("onComplete", path);
                        Event<String> event = new Event<String>(REQUEST_CODE_APP_INSTALL, path);
                        EventBusUtil.sendEvent(event);
                        dismiss();

                    }
                });
    }

    private void initFormats() {
        mProgressNumberFormat = "%1.2fM/%2.2fM";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }


}





