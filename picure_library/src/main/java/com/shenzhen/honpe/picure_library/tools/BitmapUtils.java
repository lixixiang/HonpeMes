package com.shenzhen.honpe.picure_library.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.shenzhen.honpe.picure_library.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author：luck
 * @date：2020-01-15 18:22
 * @describe：BitmapUtils
 */
public class BitmapUtils {
    /**
     * 旋转Bitmap
     *
     * @param bitmap
     * @param angle
     * @return
     */
    public static Bitmap rotatingImage(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();

        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 判断拍照 图片是否旋转
     *
     * @param degree
     * @param path
     */
    public static void rotateImage(int degree, String path) {
        if (degree > 0) {
            try {
                // 针对相片有旋转问题的处理方式
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2;
                File file = new File(path);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
                bitmap = rotatingImage(bitmap, degree);
                if (bitmap != null) {
                    saveBitmapFile(bitmap, file);
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将bitmap转换成base64字符串
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 将base64转换成bitmap图片
     * @return
     */
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        byte[] bitmapArray;
        bitmapArray = Base64.decode(string, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,bitmapArray.length);
        return bitmap;
    }
    /**
     * 保存Bitmap至本地
     *
     * @param bitmap
     * @param file
     */
    public static void saveBitmapFile(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取旋转角度
     *
     * @param orientation
     * @return
     */
    public static int getRotationAngle(int orientation) {
        switch (orientation) {
            case 1:
                return 0;
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
        }
        return 0;
    }

    /**
     * 改变bitmap的大小
     *
     * @param mBitmamp
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap changeBitmapSize(Bitmap mBitmamp, int newWidth, int newHeight) {
        float scaleWidth = ((float) newWidth) / mBitmamp.getWidth();
        float scaleHeight = ((float) newHeight) / mBitmamp.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(mBitmamp, 0, 0, mBitmamp.getWidth(), mBitmamp.getHeight(), matrix, true);
        return bitmap;
    }

    /**
     * 本地图片转icon
     * @param context
     * @return
     */
    public static Bitmap LocationToBitmap(Context context,int icon) {
        Drawable drawable = context.getResources().getDrawable(icon);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }




}
