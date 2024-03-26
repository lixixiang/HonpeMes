/*
 * Copyright © 2018 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.honpemes.widget.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yan Zhenjie on 2016/7/7.
 */
public class FileUtils {

    public static File getAppRootPath(Context context) {
        if (sdCardIsAvailable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return context.getFilesDir();
        }
    }

    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else return false;
    }

    /**
     * Copy to clipboard.
     */
    public static boolean copyTextToClipboard(Context context, CharSequence content) {
        ClipboardManager clipboardManager =
          (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            ClipData clipData = ClipData.newPlainText(content, content);
            clipboardManager.setPrimaryClip(clipData);
            return true;
        }
        return false;
    }

    public static String getMimeType(String url) {
        String extension = getExtension(url);
        if (!MimeTypeMap.getSingleton().hasExtension(extension)) return "";

        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        return TextUtils.isEmpty(mimeType) ? "" : mimeType;
    }

    public static String getExtension(String url) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        return TextUtils.isEmpty(extension) ? "" : extension;
    }


    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /*
     * 获取Asset内的文件夹
     * @param fileName 必须是完整文件名（文件名+格式）
     */
    public static void getFileFromAsset(Context context,String fileName) {
        InputStream fileStream;
        try {
            //获取指定Assets文件流
            fileStream = context.getResources().getAssets().open(fileName);
            //转化为bitmap对象
            Bitmap bitmap = BitmapFactory.decodeStream(fileStream);
            saveInSdCard(context,fileName, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * 保存到sb卡内
     * @param fileName 必须是完整文件名（文件名+格式）
     * @param bitmap
     */
    public static  void saveInSdCard(Context context,String filename, Bitmap bitmap) throws IOException {
        //检查是否存在sd卡
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "请插入sd卡", Toast.LENGTH_LONG).show();
            return;
        }

        /*
         * 在Android中1.5、1.6的sdcard目录为/sdcard，而Android2.0以上都是/mnt/sdcard，因此如果我们在保存时直接写具体目录会不妥，因此我们可以使用:
         * Environment.getExternalStorageDirectory();获取sdcard目录；
         */
        String directory = Environment.getExternalStorageDirectory().toString() + "/";
        File rootFile = new File(directory);
        //如不存在文件夹，则新建文件夹
        if (!rootFile.exists())
            rootFile.mkdir();
        //在文件夹下加入获取的文件
        File file = new File(directory, filename);
        try {
            //文件输出流
            FileOutputStream out = new FileOutputStream(file);
            //bitmp压缩到本地，原图就100
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
