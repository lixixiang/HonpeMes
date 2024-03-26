package com.example.honpemes.listener;

import android.os.Bundle;

import com.example.honpemes.api.FinalClass;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.shenzhen.honpe.picure_library.entity.LocalMedia;
import com.shenzhen.honpe.picure_library.listener.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lixixiang on 2023/3/23 14:03
 */
public  class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
    Bundle bundle = new Bundle();
    ArrayList<LocalMedia> mResult;

    public MyResultCallback() {
    }

    @Override
    public void onResult(List<LocalMedia> result) {
        mResult = (ArrayList<LocalMedia>) result;
        bundle.putSerializable(FinalClass.bean,mResult);
        EventBusUtil.sendEvent(new Event(FinalClass.PICTURE,bundle));
    }

    @Override
    public void onCancel() {

    }
}
