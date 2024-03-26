package com.example.honpemes.utils;

import android.app.Activity;
import android.graphics.Color;


import com.example.honpemes.MyApplication;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.view.ScanLineView;

import static android.os.Build.VERSION_CODES.R;
import static cn.bertsir.zbar.QrConfig.SCANVIEW_TYPE_QRCODE;
import static cn.bertsir.zbar.QrConfig.TYPE_QRCODE;

/**
 * @author: asus
 * @date: 2022/10/27
 * @Description: 二维码扫描
 */
public class QrUtil {

    public static void ScanZxing(Activity activity){
        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText("将二维码/条形码放入框内，即可自动扫描")//扫描框下文字
                .setShowDes(true)//是否显示扫描框下面文字
                .setShowLight(true)//显示手电筒按钮
                .setShowTitle(true)//显示Title
                .setShowAlbum(true)//显示从相册选择按钮
                .setNeedCrop(true)//是否从相册选择后裁剪图片
                .setCornerColor(Color.parseColor("#E42E30"))//设置扫描框颜色
                .setLineColor(Color.parseColor("#E42E30"))//设置扫描线颜色
                .setLineSpeed(QrConfig.LINE_MEDIUM)//设置扫描线速度
                .setScanType(TYPE_QRCODE)//设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanViewType(SCANVIEW_TYPE_QRCODE)//设置扫描框类型（二维码还是条形码，默认为二维码）
                .setCustombarcodeformat(QrConfig.BARCODE_PDF417)//此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setPlaySound(true)//是否扫描成功后bi~的声音
             //   .setDingPath(R.r)//设置提示音(不设置为默认的Ding~)
                .setIsOnlyCenter(true)//是否只识别框中内容(默认为全屏识别)
                .setTitleText("扫一扫")//设置Tilte文字
                .setTitleBackgroudColor(Color.parseColor("#262020"))//设置状态栏颜色
                .setTitleTextColor(Color.WHITE)//设置Title文字颜色
                .setShowZoom(false)//是否开始滑块的缩放
                .setAutoZoom(false)//是否开启自动缩放(实验性功能，不建议使用)
                .setFingerZoom(true)//是否开始双指缩放
                .setDoubleEngine(false)//是否开启双引擎识别(仅对识别二维码有效，并且开启后只识别框内功能将失效)
                .setScreenOrientation(QrConfig.SCREEN_SENSOR)//设置屏幕方式
                .setOpenAlbumText("选择要识别的图片")//打开相册的文字
                .setLooperScan(false)//是否连续扫描二维码
                .setLooperWaitTime(5 * 1000)//连续扫描间隔时间
                .setScanLineStyle(ScanLineView.style_line)//扫描线样式
                .setAutoLight(true)//自动灯光
                .setShowVibrator(true)//是否震动提醒
                .create();

        QrManager.getInstance().init(qrConfig).startScan(activity, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(ScanResult result) {
                LatteLogger.e("QrManager", "onScanSuccess: " + GsonBuildUtil.GsonBuilder(result));
                EventBusUtil.sendEvent(new Event(FinalClass.ScanResult,result));

            }
        });
    }



}
