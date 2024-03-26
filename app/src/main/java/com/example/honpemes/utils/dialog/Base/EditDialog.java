package com.example.honpemes.utils.dialog.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.honpemes.R;
import com.example.honpemes.utils.dialog.Base.BaseDialog;
import com.example.honpemes.widget.DJEditText;
import com.flyco.roundview.RoundTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/1 16:16
 * EditText 对话框
 */
public class EditDialog extends BaseDialog {
    private static final String TAG = "EditDialog";
    @BindView(R.id.tv_title)
    RoundTextView mTvTitle;
    @BindView(R.id.et_content)
    DJEditText etContent;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_sure)
    TextView mTvSure;


    public EditDialog(Context context) {
        super(context);
    }

    public EditDialog(Context context, String title, String cancelName, String sureName) {
        super(context);
        setCancelName(cancelName);
        setSureName(sureName);
        setTitleName(title);
    }


    @Override
    protected int getDialogStyleId() {
        return R.style.Dialog_Common;
    }

    @Override
    protected View getView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null);
        ButterKnife.bind(this, view);

        return view;
    }

    /**
     * 设置取消名字
     *
     * @param string
     * @return
     */
    public BaseDialog setCancelName(String string) {
        mTvCancel.setText(string);
        return this;
    }

    /**
     * 设置确定名字
     *
     * @param string
     * @return
     */
    public BaseDialog setSureName(String string) {
        mTvSure.setText(string);
        return this;
    }


    /**
     * 设置标题名字
     *
     * @param string
     * @return
     */
    public BaseDialog setTitleName(String string) {
        mTvTitle.setText(string);
        return this;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
//                if (baseDialogListener != null) {
//                    baseDialogListener.negative();
//                }
                dismiss();
                break;
            case R.id.tv_sure:
//                if (baseDialogListener != null) {
//                    baseDialogListener.positive();
//                }
                if (ediInterfaceListener != null) {
                    ediInterfaceListener.InputContent(etContent.getText().toString());
                }
                dismiss();
                break;
        }
    }

    EdiInterfaceListener ediInterfaceListener;

    public void setOnEditInterfaceLister(EdiInterfaceListener listener) {
        ediInterfaceListener =listener;
    }

    public interface EdiInterfaceListener {
        void InputContent(String content);
    }

}




































