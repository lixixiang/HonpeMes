package com.example.honpemes.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.honpemes.MyApplication.getContext;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 13:34
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class GsonBuildUtil {

    public static Gson create() {
        return GsonHolder.gson;
    }

    private static class GsonHolder {
        private static Gson gson=new Gson();
    }

    public static String GsonBuilder(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String result = gson.toJson(obj);
        return result;
    }

    public static <T> List<T> GsonListBuilder(String s,Class<T> cls){
        List<T> list = new ArrayList<T>();
        try {
            JsonArray array = new JsonParser().parse(s).getAsJsonArray();
            for(JsonElement jsonElement :array){
                list.add(GsonHolder.gson.fromJson(jsonElement,cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 在Assets中引用的json数据调用方法
     *
     * @param context
     * @param testJson
     * @return
     */
    public static String getAssetsJson(Context context, String testJson) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(testJson)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public static String GsonToString(Object o){
        Gson gson = new Gson();
        String result = gson.toJson(o);
        return result;
    }


    /**
     * 将JSON字符串转换为集合
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        List<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }


    //isjson用于判断JSON字符串是否是一个标准的JSON格式
    public static boolean isJson(String string) {

        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(string);
            LatteLogger.d("testjsonElement",GsonBuilder(jsonElement));

        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }

    public static JsonArray getAssetJson(String strJsonName) {
        JsonParser parser = new JsonParser();
        String strAll = GsonBuildUtil.getAssetsJson(getContext(), strJsonName);
        JsonArray jsonArray = parser.parse(strAll).getAsJsonArray();
        return jsonArray;
    }

}
