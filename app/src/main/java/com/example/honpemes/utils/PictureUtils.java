package com.example.honpemes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.example.honpemes.R;
import com.example.honpemes.listener.MyResultCallback;
import com.example.honpemes.utils.glide.GlideEngine;
import com.shenzhen.honpe.picure_library.PictureSelector;
import com.shenzhen.honpe.picure_library.style.PictureCropParameterStyle;
import com.shenzhen.honpe.picure_library.style.PictureParameterStyle;
import com.shenzhen.honpe.picure_library.style.PictureWindowAnimationStyle;

/**
 * FileName: PictureUtils
 * Author: asus
 * Date: 2022/1/17 17:12
 * Description: 用于获取本机相册图片
 */
public class PictureUtils {
    private static PictureWindowAnimationStyle mWindowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
    private static PictureParameterStyle mPictureParameterStyle;
    private static PictureCropParameterStyle mCropParameterStyle;



    public static void createPictureSelector(Activity _mActivity){
       PictureSelector.create(_mActivity)
                .themeStyle(R.style.picture_default_style) // xml设置主题
                .maxSelectNum(1)
                .minSelectNum(1)
                .setPictureWindowAnimationStyle(mWindowAnimationStyle)
                .setPictureCropStyle(mCropParameterStyle)
                .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                .isNotPreviewDownload(true)// 预览图片长按是否可以下载
                .isOpenClickSound(true)// 是否开启点击声音
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .forResult(new MyResultCallback());
    }



    public static PictureParameterStyle getDefaultStyle(Context context) {
        // 相册主题
         mPictureParameterStyle = new PictureParameterStyle();
        // 是否改变状态栏字体颜色(黑白切换)
        mPictureParameterStyle.isChangeStatusBarFontColor = false;
        // 是否开启右下角已完成(0/9)风格
        mPictureParameterStyle.isOpenCompletedNumStyle = false;
        // 是否开启类似QQ相册带数字选择风格
        mPictureParameterStyle.isOpenCheckNumStyle = false;
        // 相册状态栏背景色
        mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e");
        // 相册列表标题栏背景色
        mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e");
        // 相册父容器背景色
        mPictureParameterStyle.pictureContainerBackgroundColor = ContextCompat.getColor(context, R.color.black);
        // 相册列表标题栏右侧上拉箭头
        mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_arrow_up;
        // 相册列表标题栏右侧下拉箭头
        mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_arrow_down;
        // 相册文件夹列表选中圆点
        mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
        // 相册返回箭头
        mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back;
        // 标题栏字体颜色
        mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(context, R.color.picture_color_white);
        // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
        mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(context, R.color.picture_color_white);
        // 选择相册目录背景样式
        mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
        // 相册列表勾选图片样式
        mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
        // 相册列表底部背景色
        mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(context, R.color.picture_color_grey);
        // 已选数量圆点背景样式
        mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(context, R.color.picture_color_fa632d);
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(context, R.color.picture_color_white);
        // 相册列表已完成色值(已完成 可点击色值)
        mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(context, R.color.picture_color_fa632d);
        // 相册列表未完成色值(请选择 不可点击色值)
        mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(context, R.color.picture_color_white);
        // 预览界面底部背景色
        mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(context, R.color.picture_color_grey);
        // 外部预览界面删除按钮样式
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;
        // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_wechat_checkbox;
        // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(context, R.color.white);
        // 外部预览界面是否显示删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
        // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
        mPictureParameterStyle.pictureNavBarColor = Color.parseColor("#393a3e");
        // 文件夹字体颜色
        mPictureParameterStyle.folderTextColor = Color.parseColor("#4d4d4d");
        // 文件夹字体大小
        mPictureParameterStyle.folderTextSize = 16;
//        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "";
//        // 自定义相册完成文本内容
//        mPictureParameterStyle.pictureCompleteText = "";
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//
//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;

        //裁剪主题
        mCropParameterStyle = new PictureCropParameterStyle(
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                Color.parseColor("#393a3e"),
                ContextCompat.getColor(context, R.color.white),
                mPictureParameterStyle.isChangeStatusBarFontColor);
        return mPictureParameterStyle;
    }


}
























