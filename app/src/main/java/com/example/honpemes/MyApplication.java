package com.example.honpemes;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.honpemes.api.TableClass;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zhouyou.http.EasyHttp;

import java.io.File;


/**
 * created by lxx at 2019/11/9 17:05
 * 描述:
 */
public class MyApplication extends Application {
    public static final String SP_NAME = "share_data";

    private static Context context;
    public static MyApplication instances;
    private int currentIndex;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Logger.addLogAdapter(new AndroidLogAdapter());

        instances = this;
        DBUtils.init(context, TableClass.TABLE_MENU);
        DBUtils.init(context, TableClass.TABLE_USER_INFO);

        EasyHttp.init(this);

        ApiTokenDelayedUtil.HttpLogin();
    }


    /**
     * 单例模式
     *
     * @return
     */
    public static MyApplication getInstances() {
        return instances;
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 全局上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }


    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    private boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

}

