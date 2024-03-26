package com.example.honpemes.fragment.b.setting.app_download;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.utils.Util;

import butterknife.BindView;
import butterknife.OnLongClick;
import cn.bertsir.zbar.utils.QRUtils;

/**
 * Created by Lixixiang on 2023/3/1 15:31
 * java类作用描述 二维码下载APK
 */
public class DownloadQRCodeFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_version_num)
    TextView tvVersionNum;
    /**
     * 生成的二维码图片存储的URI
     */
    private Uri imageFileUri;
    private Bitmap qrCode;

    public static DownloadQRCodeFragment newInstance(Bundle bundle) {
        DownloadQRCodeFragment fragment = new DownloadQRCodeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_download_qr;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText(getArguments().getString(FinalClass.title));

        tvVersionNum.setText("当前版本号:V" + Util.getVersionDes(_mActivity));
        String content = Constants.APPDownload;
        qrCode = QRUtils.getInstance().createQRCodeAddLogo(content, 300, 300,
                BitmapFactory.decodeResource(getResources(), R.mipmap.log));
        ivQrCode.setImageBitmap(qrCode);
    }

    @OnLongClick(R.id.iv_qr_code)
    boolean img_code() {
        imageFileUri = Util.BitmapToUri(getContext(), qrCode);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageFileUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
        return true;
    }
}