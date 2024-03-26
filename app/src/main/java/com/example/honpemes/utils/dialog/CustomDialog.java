package com.example.honpemes.utils.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.honpemes.R;

/**
 * 作者：asus  on 2024/3/8 12:05
 * 注释：
 */
public class CustomDialog extends AlertDialog {

    private Context context;
    private String message,sure,cancel;
    private OnDialogClickListener onDialogClickListener;

    public interface OnDialogClickListener {
        void onPositiveClick();
        void onNegativeClick();
    }

    public CustomDialog(Context context, String message,String sure,String cancel, OnDialogClickListener onDialogClickListener) {
        super(context);
        this.context = context;
        this.message = message;
        this.sure = sure;
        this.cancel = cancel;
        this.onDialogClickListener = onDialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_dialog_layout, null);
        setContentView(view);

        TextView messageTextView = view.findViewById(R.id.tv_content);
        TextView tvSure = view.findViewById(R.id.tv_sure);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        messageTextView.setText(message);
        tvSure.setText(sure);
        tvCancel.setText(cancel);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onPositiveClick();
                }
                dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onNegativeClick();
                }
                dismiss();
            }
        });
    }
}































