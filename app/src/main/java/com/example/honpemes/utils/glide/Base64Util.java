package com.example.honpemes.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.ValueCallback;

import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: NetUtil
 * Author: asus
 * Date: 2021/3/10 13:41
 * Description:与网络有关的工具
 */
public class Base64Util {
    private static String PREFIX_UNICODE = "\\u";

    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX_UNICODE);
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX_UNICODE, begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    private static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
        }
        if (!PREFIX_UNICODE.equals(str.substring(0, 2))) {
            throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        // 将十六进制转为十进制
        int code = Integer.parseInt(tmp, 16) << 8; // 转为高位，后与地位相加
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16); // 与低8为相加
        return (char) code;
    }

    /**
     * 用于上传图片给 web 端
     * String bitmap转为base64
     *
     * @param path
     * @return
     */
    public static String BitMapIconToString(String path) {
        try {
            Bitmap cameraBitmap = BitmapFactory.decodeFile(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            cameraBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();
            byte[] byteArray = stream.toByteArray();
            return Base64.encodeToString(byteArray, 0, byteArray.length, Base64.DEFAULT);
        } catch (IOException e) {
            ToastUtil.getInstance().showToast(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 文件转byte数组
     * @param filePath 文件路径
     * @return
     */
    public static byte[] readFile(String filePath) {
        File file = new File(filePath);
        // 需要读取的文件，参数是文件的路径名加文件名
        if (file.isFile()) {
            // 以字节流方法读取文件
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // 设置一个，每次 装载信息的容器
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                // 开始读取数据
                int len = 0;// 每次读取到的数据的长度
                while ((len = fis.read(buffer)) != -1) {// len值为-1时，表示没有数据了
                    // append方法往sb对象里面添加数据
                    outputStream.write(buffer, 0, len);
                }
                // 输出字符串
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在！");
        }
        return null;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String BitToString(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();
            byte[] byteArray = stream.toByteArray();
            return Base64.encodeToString(byteArray, 0, byteArray.length, Base64.NO_WRAP);
        } catch (Exception e) {
            ToastUtil.getInstance().showToast(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Base64字符串转换成图片
     *
     * @param string
     * @return
     */
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 图片转换成base64字符串
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imgBytes = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    /**
     * 字符串转义特殊处理
     *
     * @param str 源字符串
     * @param sr  替换成哪个字符
     * @param sd  需要替换的字符
     * @return 目标字符串
     */
    public static String stringReplace(String str, String sr, String sd) {
        String regEx = sr;
        Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        str = m.replaceAll(sd);
        return str;
    }

    /**
     * 字符串相对路径转 bitMap
     *
     * @return
     */
    public static Bitmap strPathToBitMap(String path) {
        return BitmapFactory.decodeFile(path);
    }

    /**
     * 得到服务器返回的值
     */
    public static String strContent(String s) throws JSONException {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject(s);
        return jsonObject.getString("value");
    }


    /**
     * 图片转化为base 64字符串
     *
     * @param imgUrl
     * @return
     */
    public static String imgToBase64(String imgUrl) {
        LatteLogger.d("testDDDD", imgUrl);
        byte[] bytes = new byte[0];

        try {
            FileInputStream fis = new FileInputStream(imgUrl);
            bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * 解码base64字符串
     *
     * @param imgBase64Str
     * @return
     */
    public static byte[] decodeImg(String imgBase64Str) {
        byte[] bytes = new byte[0];
        bytes = Base64.decode(imgBase64Str, Base64.DEFAULT);
        return bytes;
    }


    /**
     * bitmap 转化为Base64字符串
     *
     * @param bitmap
     * @return
     */
    public static String Bitmap2Base64Str(Bitmap bitmap) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baos.flush();
            byte[] bitmapBytes = baos.toByteArray();
            return Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * byte转化为Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap bytes2Bitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * 本地图片
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.NO_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static String FileToBase64(String path) {
        try {
            File file = new File(path);
            FileInputStream  inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取本地文件并转换为byte数组
     *
     * @param filePath
     * @return 文件转字节数组
     * @throws IOException
     */
    public static byte[] FileToByte(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();
        return data;
    }

    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * decoderBase64File:(将base64字符解码保存文件). <br/>
     *
     * @param base64Code 编码后的字串
     * @param savePath   文件保存路径
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static void Base64ToFile(String base64Code, String savePath) {

        byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(savePath);
            out.write(buffer);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * decoderBase64File:(将base64字符解码保存文件). <br/>
     *
     * @param base64Code 编码后的字串
     * @param savePath   文件保存路径
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static boolean IsBase64ToFile(String base64Code, String savePath) {
        byte[] data = Base64.decode(base64Code, Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
            FileOutputStream out = new FileOutputStream(savePath);
            out.write(buffer);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }




    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static Bitmap LocalPicToBitmap(String picturePath){
        Bitmap bitmap=BitmapFactory.decodeFile(picturePath,getBitmapOption(2));
        return bitmap;
    }
    private static BitmapFactory.Options getBitmapOption(int inSampleSize)

    {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }


}













