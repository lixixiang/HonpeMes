package com.example.honpemes.utils;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honpemes.MyApplication;
import com.example.honpemes.R;


/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 13:30
 * @Author: 李熙祥
 * @Description: java类作用描述 吐司工具类
 */
public class ToastUtil {
    private volatile static ToastUtil newInstance = null;
    private int duration = 2000;
    private ToastUtil() {
    }

    private Toast toast;
    private TextView mTvToast;

    public static ToastUtil getInstance() {
        if (newInstance == null) {
            synchronized (ToastUtil.class) {
                if (newInstance == null) {
                    newInstance = new ToastUtil();
                }
            }
        }
        return newInstance;
    }

    @SuppressLint("WrongConstant")
    public void showToast(String text) {
        if (toast == null) {
            toast = new Toast(MyApplication.getContext());
            toast.setDuration(duration);
            View root = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_custom_toast, null);
            mTvToast = root.findViewById(R.id.toast_text);
            mTvToast.setText(text);
            toast.setView(root);
            toast.setGravity(Gravity.CENTER,0,0);
        }
        mTvToast.setText(text);
        toast.show();
    }

    @SuppressLint("WrongConstant")
    public void showToast2(String text,int gravity) {
        if (toast == null) {
            toast = new Toast(MyApplication.getContext());
            toast.setDuration(duration);
            View root = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_custom_toast, null);
            mTvToast = root.findViewById(R.id.toast_text);
            mTvToast.setText(text);
            toast.setView(root);
            toast.setGravity(gravity,0,0);
        }
        mTvToast.setText(text);
        toast.show();
    }

    public void showToast(int stringId){
        showToast(stringId+"");
    }
}

