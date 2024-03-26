package com.example.honpemes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * FileName: ScreenAdapterUtils
 * Author: asus
 * Date: 2021/3/26 14:19
 * Description:屏幕适配类
 */
public class ScreenAdapterUtils {

    /***手机屏幕相关信息**/
    public static void getPhoneScreen(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();//获取屏幕分辨率
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int width = metrics.widthPixels; //宽度px
        int height = metrics.heightPixels; //高度px
        float density = metrics.density; //密度 (0.75/1.0/1.5)
        int densityDpi = metrics.densityDpi; //密度DPI(120/160/240)
        //屏幕宽度算法：屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density); //屏幕宽度（DP）
        int screenHeight = (int) (height / density); //屏幕高度（DP）
        Log.d("testesgt", "宽度:" + width + " 高度:" + height + " 密度:" + density + " 密度DPI:" + densityDpi
                + "\r\n屏幕dp宽度：" + screenWidth + " 屏幕dp高度：" + screenHeight);
    }

    /**屏幕宽***/
    public static int getScreenWith(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();//获取屏幕分辨率
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        return metrics.widthPixels;
    }
    /**屏幕高***/
    public static int getScreenHeight(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();//获取屏幕分辨率
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    private static int getInternalDimensionSize(Context context, String key) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier(key, "dimen", "android");
            if (resourceId > 0) {
                result = Math.round(context.getResources().getDimensionPixelSize(resourceId) *
                        Resources.getSystem().getDisplayMetrics().density /
                        context.getResources().getDisplayMetrics().density);
            }
        } catch (Resources.NotFoundException ignored) {
            return 0;
        }
        return result;
    }

    public static int getStatusBarHeight(Context context){
        return getInternalDimensionSize(context, "status_bar_height");
    }

    public static int getNavigationBarHeight(Context context) {
        boolean mInPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar((Activity) context)) {
                String key;
                if (mInPortrait) {
                    key = "navigation_bar_height";
                } else {
                    key = "navigation_bar_height_landscape";
                }
                return getInternalDimensionSize(context, key);
            }
        }
        return result;
    }



    private static boolean hasNavBar(Activity activity) {
        //判断小米手机是否开启了全面屏,开启了，直接返回false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Settings.Global.getInt(activity.getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
                return false;
            }
        }
        //其他手机根据屏幕真实高度与显示高度是否相同来判断
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }
}
