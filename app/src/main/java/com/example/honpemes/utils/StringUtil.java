package com.example.honpemes.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.honpemes.MyApplication.getContext;
import static com.example.honpemes.utils.StringUtil.DoubleToString;


/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 13:47
 * @Author: 李熙祥
 * @Description: java类作用描述 字符串工具
 */
public class StringUtil {

    /**
     * 复制字符串
     *
     * @param context
     * @param text
     */
    public static void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    public static void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        getContext().startActivity(intent);
    }

    /**
     * 获取所有中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 获取汉字拼音的方法。如： 张三 --> zhangsan
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param hanzi 汉子字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    public static String getHanziPinYin(String hanzi) {
        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    // 把第几声这个数字给去掉
                    sb.append(stringArray[0].replaceAll("\\d", ""));
                }
            }
            if (sb.length() > 0) {
                result = sb.toString();
            }
        }
        return result;
    }

    /**
     * 获取全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] chars = null;
        chars = src.toCharArray();
        String[] strings = new String[chars.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String result = "";
        int t0 = chars.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    strings = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                    result += strings[0];
                } else {
                    result += Character.toString(chars[i]);
                }
            }
            return result;
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 截取部分字分串
     *
     * @param sourceString
     * @param objects
     * @return
     */
    public static String replace(String sourceString, Object[] objects) {
        String temp = sourceString;
        for (int i = 0; i < objects.length; i++) {
            String[] result = (String[]) objects[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }

    /**
     * 截取字符串设定长度
     *
     * @param str
     * @param cutCount 设定长度，字节数
     * @return
     */
    public static String getSubStr(String str, int cutCount) {
        if (str == null)
            return "";

        String resultStr = "";
        char[] ch = str.toCharArray();
        int count = ch.length;
        int strBLen = str.getBytes().length;
        int temp = 0;
        for (int i = 0; i < count; i++) {
            resultStr += ch[i];
            temp = resultStr.getBytes().length;
            if (temp >= cutCount && temp < strBLen) {
                resultStr += "";
                break;
            }
        }
        return resultStr;
    }

    public static String replaceT(String sourceString) {
        String[][] object = {new String[]{"T", " "}};
        String temp = sourceString;
        for (int i = 0; i < object.length; i++) {
            String[] result = (String[]) object[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }

    /**
     * @param sourceString
     * @param change
     * @return
     */
    public static String replaceSpace(String sourceString, String change) {
        String[][] object = {new String[]{change, " "}};
        String temp = sourceString;
        for (int i = 0; i < object.length; i++) {
            String[] result = (String[]) object[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }

    /**
     * List 集合转换为String
     *
     * @param list
     * @param separator
     * @return 开发中会用到把 List<string>  的内容拼接成以逗号分隔的字符串的形式,现总结如下：
     */
    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 去除数组空元素
     *
     * @param strArray
     * @return
     */
    public static String[] removeArrayEmptyTextBackNewArray(String[] strArray) {
        List<String> strList = Arrays.asList(strArray);
        List<String> strListNew = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            if (strList.get(i) != null && !strList.get(i).equals("")) {
                strListNew.add(strList.get(i));
            }
        }
        String[] strNewArray = strListNew.toArray(new String[strListNew.size()]);
        return strNewArray;
    }

    // 辅助方法：将int[]转换为Integer[]
    public static Integer[] toIntegerArray(int[] intArray) {
        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = intArray[i];
        }
        return result;
    }


    //1. 循环list中的所有元素然后删除重复
    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }



    //2. 通过HashSet踢除重复元素
    public static List removeDuplicate2(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    //3. 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate " + list);
    }

    //4.把list里的对象遍历一遍，用list.contain()，如果不存在就放入到另外一个list集合中
    public static List<?> removeDuplicate4(List<?> list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 正则表达式去掉所有中括号【】
     */
    public static String removeBrackets(String str) {
        return str.replaceAll("[\\[\\]]", "");
    }


    /**
     * 字符串不区分大小写
     */
    public static boolean igonreCaseEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    //返回一个网页 没有带前缀
    public static String[] returnImageUrlsFromHtml2(String data) {
        List<String> imageSrcList = new ArrayList<String>();
        //  String htmlCode = returnExampleHtml();
        String htmlCode = data;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }


    /**
     * 是否有邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 是否有密码
     *
     * @param pass
     * @return
     */
    public static boolean isPass(String pass) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    /**
     * 是否有电话
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhone(String phoneNumber) {
        String pho = "^1[0-9]{10}$";
        Pattern p = Pattern.compile(pho);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 判断字符串是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //=========================================================================

    /**
     * 检查数组是否包含某个值
     */
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public static String useList2(String[] arr, String targetValue) {
        boolean b = Arrays.asList(arr).contains(targetValue);
        if (b) {
            return targetValue;
        } else {
            return "没有这个字符";
        }
    }

    //使用Set
    public static boolean useSet(String[] arr, String targetValue) {
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        return set.contains(targetValue);
    }

    //使用循环判断
    public static boolean useLoop(String[] arr, String targetValue) {
        for (String s : arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }

    //查找有序数组中是否包含某个值的用法
    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
        int a = Arrays.binarySearch(arr, targetValue);
        if (a > 0)
            return true;
        else
            return false;
    }

    //=========================================================================
    public static String returnExampleHtml() {

        return "<img src=\"/uploadfile/image/20170628/20170628155301_17333.jpg\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20170628/20170628155301_84385.jpg\" alt=\"\" />";
    }

    /**
     * 代码实现editText提示文字
     */
    public static SpannableString editHint(String hint) {
        SpannableString s = new SpannableString(hint);
        return s;
    }


    /**
     * editText hint文字大小
     *
     * @param et
     * @param content
     */
    public static void HintUtil(EditText et, CharSequence content) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(content);
        // 新建 一个属性对象,设置文字的大小,
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(14, true);
        //附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置hint
        et.setHint(new SpannableString(ss)); // 一定要进行转换,否则属性会消失
    }

    public static void HintUtil(TextView et, CharSequence content, int size) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(content);
        // 新建 一个属性对象,设置文字的大小,
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        //附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置hint
        et.setHint(new SpannableString(ss)); // 一定要进行转换,否则属性会消失
    }

    public static void HintUtil(EditText et, CharSequence content, int size) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(content);
        // 新建 一个属性对象,设置文字的大小,
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        //附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置hint
        et.setHint(new SpannableString(ss)); // 一定要进行转换,否则属性会消失
    }

    /**
     * 删除集合中重复的元素保留维一一个元素
     *
     * @param list
     */
    public static void DeleteArrayRepeatStr(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
    }

    /**
     * 将数据按照名称分组
     *
     * @param list 需要被改变的json
     * @param name 按照name来提取
     * @return
     */
    public static List group(List<Map> list, String name) {
        List<Map> newList = new ArrayList<>();
        /*
         *第一次外循环与内循环完成以name为比较准则的封装
         */
        for (int i = 0; i < list.size(); i++) {
            //用map接收list中一个键值对
            Map<String, Object> dt_i = list.get(i);
            if (!dt_i.containsKey(name))
                continue;
            String date1 = dt_i.get(name).toString();

            Map<String, Object> res = new HashMap<String, Object>();
            res.put(name, date1);

            //用于存放第i次的比较后的结果
            List lst_1 = new ArrayList();

            for (int j = 0; j < list.size(); j++) {
                Map<String, Object> dt_j = list.get(j);
                if (!dt_j.containsKey(name))
                    continue;
                String date2 = dt_j.get(name).toString();

                if (date1 == date2 || date1.equals(date2)) {

                    //深拷贝当前第j条的json数组
                    Map<String, Object> dt1 = new HashMap<String, Object>();
                    dt1.putAll(dt_j);
                    dt1.remove(name);

                    //将数据按照json的形式存储，方便前端解析
                    String dataJson = new Gson().toJson(dt1);
                    lst_1.add(dataJson);
                }

            }
            res.put("data", lst_1);
            newList.add(res);
        }

        /*
         * 将封装后的结果进行去重复
         */
        for (int i = 0; i < newList.size(); i++) {
            Map<String, Object> dt_i = newList.get(i);
            if (!dt_i.containsKey(name))
                continue;
            String date1 = dt_i.get(name).toString();
            for (int j = newList.size() - 1; j > i; j--) {
                Map<String, Object> dt_j = newList.get(j);
                if (!dt_j.containsKey(name))
                    continue;
                String date2 = dt_j.get(name).toString();
                if (date1 == date2 || date1.equals(date2)) {
                    newList.remove(j);
                }
            }
        }
        return newList;
    }


    public static String md5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 数组转集合
     */
    public static List<String> ArrToList(String[] strArr) {
        return Arrays.asList(strArr);
    }


    /**
     * 集合转数组
     */
    public static String[] ListToArr(List<String> list) {
        return list.toArray(new String[]{});
    }

    /**
     * 去掉字符串最后一位字任
     *
     * @param str
     * @param index 去掉后面几位字符
     * @return
     */
    public static String deleteLastStr(String str, int index) {
        return str.substring(0, str.length() - index);
    }

    /**
     * 获得字符串最后一位字符
     *
     * @return
     */
    public static String getLastStr(String str) {
        return str.substring(str.length() - 1, str.length());
    }

    /**
     * 去掉从前面去字符
     *
     * @param str
     * @return
     */
    public static String deleteStrBefore(String str, int index) {
        if (str == null || str.length() < index) {
            return null;
        }
        char[] arr = str.toCharArray();
        char[] ret = new char[arr.length - index];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i + index];
        }
        return String.copyValueOf(ret);
    }

    /**
     * 没有订单时
     */
    public static void getUnOrder(TextView tvTitle, TextView tvTips, TextView tvAddOrder, String strTitle, String strTips, String strAdd) {
        tvTitle.setText(strTitle);
        tvTips.setText(strTips);
        tvAddOrder.setText(strAdd);
    }

    /**
     * textView 显示文字某几个字改变颜色
     *
     * @param str   需要修改的字符串
     * @param color 改变字符的颜色
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableString changeFontColor(String str, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan
                (getContext().getResources().getColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    public static SpannableString changeFontSize(String str, int size, int start, int end){
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(size,true),start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }



    public static SpannableString changeFontColorSize(String str, int size, int start, int end,int color,int startColor,int endColor){
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(size,true),start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan
                (color), startColor, endColor, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString changeFontBold(SpannableString spannableString, int startBold,int endBold){
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, startBold, endBold, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString changeMoreFontSizeBold(String str, int size, int[] start, int[] end){
        SpannableString spannableString = new SpannableString(str);
        for (int i = 0; i < start.length; i++) {
            spannableString.setSpan(new AbsoluteSizeSpan(size,true),start[i],end[i],Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),start[i],end[i], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public static SpannableString changeMoreFontSizeBold(String str, int size, int start, int end){
        SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new AbsoluteSizeSpan(size,true),start,end,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
    /**
     * @param str              需要修改的字符串
     * @param color            改变字符的颜色
     * @param start            开始位置
     * @param end              结束位置
     * @param changeClickStart 颜色开始
     * @param changeClickEnd   颜色结束
     * @return
     */
    public static SpannableString changeFontClickColor(TextView tv, String str, int color, int start, int end, int changeClickStart, int changeClickEnd) {
        SpannableString spannableString = new SpannableString(str);
        tv.setMovementMethod(LinkMovementMethod.getInstance()); //点击需要加上，否则点击无效
        spannableString.setSpan(new ForegroundColorSpan
                (getContext().getResources().getColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new MyClickText(str), changeClickStart, changeClickEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    /**
     * textView 显示文字某几个字改变颜色
     *
     * @param str   需要修改的字符串
     * @param color 改变字符的颜色
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableString changeFontColor(Context context, String str, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(color))
                , start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 按中文首字母排序
     *
     * @param list
     */
    public static void initialOrder(List list) {
        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(list, com);
    }


    /**
     * 对TextView设置不同状态时其文字颜色
     *
     * @param normal
     * @param pressed
     * @param focused
     * @param unable
     * @return
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    /**
     * java中double类型如果小数点后为零则显示整数否则保留两位小数
     *
     * @param d
     * @return
     */
    public static String DoubleToString(double d) {
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        double num = bg.doubleValue();
        if (Math.round(num) - num == 0) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }

    //把字符串转换为Double 类型
    public static Double StringToDouble(String value) {
        Double doubleValue = new Double("0.00");
        if (value != null && !"".equals(value))
            doubleValue = new Double(value.replaceAll(",", ""));

        return doubleValue;
    }

    /**
     * 字符串转数组
     */
    public static String[] stringToArr(String str) {
        return str.split(",");
    }

    /**
     * 字符串转列表
     *
     * @param str
     * @return
     */
    public static List<String> stringToList(String str) {
        return Arrays.asList(str.split(","));
    }

    /**
     * 数组转字符串
     */
    public static String arrToString(String[] arr) {
        StringBuffer str5 = new StringBuffer();
        for (String s : arr) {
            str5.append(s).append(",");
        }
        return str5.toString();
    }

    /**
     * String to uri
     *
     * @param netImage 网络图片链接
     * @return
     */
    public static Uri stringToUri(String netImage) {
        return Uri.parse(netImage);
    }

    /**
     * uri to bitmap
     *
     * @param uri
     * @return
     */
    public static Bitmap uriToBitmap(Uri uri) {
        try {
            return BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 位图 转 字符串
     * Bitmap to String
     */
    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bao);
        byte[] arr = bao.toByteArray();
        String result = Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }

    public static Bitmap BitMapJPGToString(Bitmap bitmap, int maxSize) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] b = bao.toByteArray();
        String result = Base64.encodeToString(b, Base64.DEFAULT);
        //将字节换成KB
        double mid = b.length / 1024;
        //判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            //获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            //开始压缩 此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitmap = zoomImage(bitmap, bitmap.getWidth() / Math.sqrt(i),
                    bitmap.getHeight() / Math.sqrt(i));

        }
        return bitmap;
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
// 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
// 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
// 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
// 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }


    public static Bitmap decodeBitmapFromFilePath(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//如此，无法decode bitmap
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;//如此，方可decode bitmap

        return BitmapFactory.decodeFile(path, options);
    }

    /*
     * 计算采样率
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 字符串 转 位图
     * String to Bitmap
     */
    public static Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * 根据路径 创建文件
     *
     * @param pathFile
     * @return
     * @throws IOException
     */
    public static File createFile(String pathFile) throws IOException {
        File fileDir = new File(pathFile.substring(0, pathFile.lastIndexOf(File.separator)));
        File file = new File(pathFile);
        if (!fileDir.exists()) fileDir.mkdirs();
        if (!file.exists()) file.createNewFile();
        return file;
    }

    /**
     * 字符串改变某个字的颜色
     *
     * @return
     */
    public static SpannableString changeStrColor(String string, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(color)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 字符串改变多个字的颜色
     */
    public static SpannableString changeStrMultiColor(String string, int color, int start, int end, int start2, int end2) {
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(color)), start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(color)), start2, end2, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 改变字符串某一个字的大小
     *
     * @param label 字符
     * @param type  选择哪种类型
     * @param size  字符大小
     * @return
     */
    public static SpannableString changeStrSize(String label, int size, int type) {
        SpannableString spannableString = new SpannableString(label);
        if (type == 0) {
            spannableString.setSpan(new AbsoluteSizeSpan(size), label.length() - 2, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(size), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (type == 1) {
            spannableString.setSpan(new AbsoluteSizeSpan(20), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF000000")), 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(30), 6, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0006")), 5, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (type == 2) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0006")), 7, label.length() - 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (type == 3) {
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0006")), 5, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else if (type == 4) {
            spannableString.setSpan(new AbsoluteSizeSpan(size), 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(size), 11, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 改变文字大小及颜色
     */
    public static SpannableString changeStrSize(String label, int color, int size, int start, int end) {
        SpannableString spannableString = new SpannableString(label);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(color)), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString changeStrSize(String label, int size, int start, int end) {
        SpannableString spannableString = new SpannableString(label);
        spannableString.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(size), start, label.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 去掉字符串相同的字符
     */
    public static String removeChar(String str) {
        Set<Character> set = new HashSet<Character>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        for (Character c : set) {
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 获取字符串的值以逗号隔开的值 我，是  如是要取我就 s[0]
     *
     * @param string
     * @return
     */
    public static String[] getStringArrValue(String string) {
        String[] s = string.split(",|,");
        return s;
    }


    /**
     * 获取第一个字的拼音首字母
     *
     * @param chinese
     * @return
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pinYinBF = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char curChar : arr) {
            if (curChar > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curChar, defaultFormat);
                    if (temp != null) {
                        pinYinBF.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinYinBF.append(curChar);
            }
        }
        return pinYinBF.toString().replaceAll("\\W", "").trim();
    }

    /**
     * java正则表达式判断字符串是否仅含有数字和字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        boolean isDigitLetter = false;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {
                isLetter = true;
            }
        }

        if (isDigit && isLetter) {
            isDigitLetter = true;
        } else {
            isDigitLetter = false;
        }
        return isDigitLetter;
    }
//把String转化为float

    public static float convertToFloat(String number, float defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;

        }

        try {
            return Float.parseFloat(number);

        } catch (Exception e) {
            return defaultValue;

        }

    }

//把String转化为double

    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;

        }

        try {
            return Double.parseDouble(number);

        } catch (Exception e) {
            return defaultValue;

        }

    }

//把String转化为int

    public static int convertToInt(String number, int defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;

        }

        try {
            return Integer.parseInt(number);

        } catch (Exception e) {
            return defaultValue;

        }

    }

    /**
     * 本地图片直接使用
     */
    public static int ResourcesImg(String defType, String strIcon) {
        return getContext().getResources().getIdentifier(strIcon, defType, getContext().getPackageName());
    }

    /**
     * String数组转int数组
     */
    public static int[] strArrTointArr(String[] arr) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = Integer.parseInt(arr[i]);
        }
        return array;
    }

    public static class MyClickText extends ClickableSpan {
        private String newStr;

        public MyClickText(String newStr) {
            this.newStr = newStr;
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(true);

        }

        /**
         * Performs the click action associated with this span.
         *
         * @param widget
         */
        @Override
        public void onClick(@NonNull View widget) {

        }
    }

    /**
     * java中double类型如果小数点后为零则显示整数否则保留两位小数
     *
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        double num = bg.doubleValue();
        if (Math.round(num) - num == 0) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }

    /**
     * 生成随机数
     *
     * @param max 最大值
     * @param min 最小值
     * @return
     */
    public static int random(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 提取URL中的文件名
     *
     * @param url
     * @return
     */
    public static String extractFileNameWithSuffix(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 提取URL中的路径
     * /storage/emulated/legacy/Download/sample.pptx = /storage/emulated/legacy/Download
     *
     * @param url String of a URL
     * @return the path of URL without the file separator
     */
    public static String extractPathWithoutSeparator(String url) {
        return url.substring(0, url.lastIndexOf("/"));
    }

    private static final Double THOUSAND = 1000.0;
    private static final Double MILLIONS = 1000000.0;
    private static final Double BILLION = 1000000000.0;
    private static final String THOUSAND_UNIT = "K";
    private static final String MILLION_UNIT = "M";
    private static final String BILLION_UNIT = "B";

    /**
     * 数字超过1000为k 数字超过一万时用 m 将数字转换成以万为单位或者以亿为单位，因为在前端数字太大显示有问题
     *
     * @return
     */
    public static String changeNumUnit(double amount) {
        //最终返回的结果值
        String result = String.valueOf(amount);
        //四舍五入后的值
        double value = 0;
        //转换后的值
        double tempValue = 0;
        //余数
        double remainder = 0;
        //大于1000小于1百万

        if (amount > THOUSAND && amount <= MILLIONS) {
            tempValue = amount / THOUSAND;
            remainder = amount % THOUSAND;
            //余数小于500则不进行四舍五入
            if (remainder < (THOUSAND / 2)) {
                value = formatNumber(tempValue, 2, false);
            } else {
                value = formatNumber(tempValue, 2, true);
            }
            //如果值刚好是1000000，则要变成1m
            if (value == THOUSAND) {
                result = DoubleToString(value / THOUSAND) + MILLION_UNIT;
            } else {
                result = DoubleToString(value) + THOUSAND_UNIT;
            }
        } else if (amount > MILLIONS && amount <= BILLION) {//大于1百万小于10亿
            tempValue = amount / MILLIONS;
            remainder = amount % MILLIONS;
            Logger.d("tempValue=" + tempValue + ", remainder=" + remainder);

            //余数小于500000则不进行四舍五入
            if (remainder < (MILLIONS / 2)) {
                value = formatNumber(tempValue, 2, false);
            } else {
                value = formatNumber(tempValue, 2, true);
            }
            //如果值刚好是10000万，则要变成1亿
            if (value == MILLIONS) {
                result = DoubleToString(value / MILLIONS) + BILLION_UNIT;
            } else {
                result = DoubleToString(value) + MILLION_UNIT;
            }
        }
        //大于10亿
        else if (amount > BILLION) {
            tempValue = amount / BILLION;
            remainder = amount % BILLION;
            Logger.d("tempValue=" + tempValue + ", remainder=" + remainder);

            //余数小于50000000则不进行四舍五入
            if (remainder < (BILLION / 2)) {
                value = formatNumber(tempValue, 2, false);
            } else {
                value = formatNumber(tempValue, 2, true);
            }
            result = DoubleToString(value) + BILLION_UNIT;
        } else {
            result = DoubleToString(amount);
        }
        return result;
    }

    /**
     * 对数字进行四舍五入，保留2位小数
     *
     * @param number   要四舍五入的数字
     * @param decimal  保留的小数点数
     * @param rounding 是否四舍五入
     * @return
     */
    public static Double formatNumber(double number, int decimal, boolean rounding) {
        BigDecimal bigDecimal = new BigDecimal(number);

        if (rounding) {
            return bigDecimal.setScale(decimal, RoundingMode.HALF_UP).doubleValue();
        } else {
            return bigDecimal.setScale(decimal, RoundingMode.DOWN).doubleValue();
        }
    }


    /**
     * 将字符串转成unicode
     *
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String convert(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * 将unicode 字符串
     *
     * @param str 待转字符串
     * @return 普通字符串
     */
    public static String revert(String str) {
        str = (str == null ? "" : str);
        if (str.indexOf("\\u") == -1)//如果不是unicode码则原样返回
            return str;

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i <= str.length() - 6; ) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }

    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTextReturnStr(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName + ".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
// throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "";
    }

    //删除重复的字符
    public static String delRepeat(String s) {

        char buf[] = new char[s.length()];//创建一个辅助的空数组
        int sub = 0;
        boolean find = false;
        for (int l = 0; l < s.length(); l++) {
            find = false;
            //从头到尾搜索辅助的数组里有没有出现重复的字符
            for (int i = 0; i < l; i++) {
                if (buf[i] == s.charAt(l)) {
                    find = true;//标记为找到，后面不进行填充
                    break;//找到重复的，跳出循环
                }
            }
            //如果在辅助数组没有找到重复的字符，填充该字符
            if (find == false) buf[sub++] = s.charAt(l);
        }
        return String.valueOf(buf).trim();
    }





}























