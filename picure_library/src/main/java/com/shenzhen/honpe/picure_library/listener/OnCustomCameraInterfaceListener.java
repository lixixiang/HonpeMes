package com.shenzhen.honpe.picure_library.listener;

import android.content.Context;

import com.shenzhen.honpe.picure_library.config.PictureSelectionConfig;

/**
 * @author：luck
 * @date：2020/4/27 3:24 PM
 * @describe：OnCustomCameraInterfaceListener
 */
public interface OnCustomCameraInterfaceListener {
    /**
     * Camera Menu
     *
     * @param context
     * @param config
     * @param type
     */
    void onCameraClick(Context context, PictureSelectionConfig config, int type);
}
