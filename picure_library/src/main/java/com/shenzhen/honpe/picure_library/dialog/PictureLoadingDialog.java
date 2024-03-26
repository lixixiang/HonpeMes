package com.shenzhen.honpe.picure_library.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.shenzhen.honpe.picure_library.R;


public class PictureLoadingDialog extends Dialog {

    public PictureLoadingDialog(Context context) {
        super(context, R.style.Picture_Theme_AlertDialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.PictureThemeDialogWindowStyle);
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_alert_dialog);
    }
}