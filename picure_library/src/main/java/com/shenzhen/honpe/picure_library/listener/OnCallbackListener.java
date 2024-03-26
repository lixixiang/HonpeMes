package com.shenzhen.honpe.picure_library.listener;

/**
 * @author：luck
 * @date：2020/4/24 11:48 AM
 * @describe：OnCallbackListener
 */
public interface OnCallbackListener<T> {
    /**
     * @param data
     */
    void onCall(T data);
}
