package com.example.honpemes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.example.honpemes.MyApplication;
import com.example.honpemes.api.Constants;
import com.example.honpemes.bean.UpdateBean;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.http.ConnNetworkState;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import static com.example.honpemes.api.FinalClass.UPDATA_CLIENT;

/**
 * Created by Lixixiang on 2023/2/27 11:29
 */
public class Util {

    public static DisplayMetrics getMetrics(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 设置edit hint 大小
     *
     * @param edit
     * @param hintSize
     * @param content  设置里面的内容
     */
    public static void setEditTextHint(EditText edit, int hintSize, String content) {
        //设置hint 大小
        SpannableString ss = new SpannableString(content);
        AbsoluteSizeSpan as = new AbsoluteSizeSpan(hintSize, true);//设置字体大小 true表示单位是sp
        ss.setSpan(as, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit.setHint(new SpannableString(ss));
    }

    /**
     * 获取颜色
     * @param context 上下文
     * @param colorId 颜色ID
     * @return 颜色
     */
    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorId);
        }
        //noinspection deprecation
        return context.getResources().getColor(colorId);
    }

    /**
     * dp转px
     * @param dpVal dp 值
     * @return px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {//获取版本号(内部识别号)
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * TextView Size 适配大小
     */
    public static void setTextSize(TextView tv, int size){
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Util.changeFontSize(size));
    }

    /**
     * 文字大小
     *
     * @param textSize
     * @return
     */
    public static int changeFontSize(int textSize) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int rate = (int) (textSize * (float) screenHeight / 1920);
        return rate;
    }


    /**
     * 获取版本信息
     *
     * @param context
     * @return
     */
    public static String getVersionDes(Context context) {//获取版本号
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bitmap 转 uri 做分享用
     */
    public static Uri BitmapToUri(Context context, Bitmap bitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
        return uri;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void reverse(List<?> list){
         Collections.reverse(list);
    }

    public static Integer[] getWidthAndHeight(Window window) {
        if (window == null) {
            return null;
        }
        Integer[] integer = new Integer[2];
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        integer[0] = dm.widthPixels;
        integer[1] = dm.heightPixels;
        return integer;
    }

    /**
     * 设置弹出popup，背景alpha值
     *
     * @param bgAlpha 0f - 1f
     */
    public static void backgroundAlpha(Activity activity,float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }

    public static void NewVersionRequest(Context context) {
        LatteLogger.d("tewfewf", Constants.UPDATE_SIZE);
        EasyHttp.post(Constants.UPDATE_SIZE)
                .retryCount(0)
                .params("mes_ver", String.valueOf(Util.getVersionCode(context)))
                .params("mes_desc", String.valueOf(Util.getVersionCode(context)))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (e.getCode() == 1009) {
                            new ConnNetworkState(context).checkNetworkState();
                        } else {
                            ToastUtil.getInstance().showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("NewVersionRequest",result);
                        try {
                            JSONObject o = new JSONObject(result);

                            if (o.getInt("code") == 200) {
                                UpdateBean updateBean = GsonBuildUtil.create().fromJson(result, UpdateBean.class);

                                String version = updateBean.data.ver;
                                String description = updateBean.data.description;

                                LatteLogger.d("updateResult", GsonBuildUtil.GsonBuilder(updateBean)
                                        +getVersionCode(context) +"   "+ Integer.parseInt(version));
                                if (getVersionCode(context) > Integer.parseInt(version)) { //当前版本大于历史版本
                                    EventBusUtil.sendEvent(new Event(UPDATA_CLIENT));
                                }
                            } else {
                                ToastUtil.getInstance().showToast(o.getString("info"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

}
