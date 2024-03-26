package com.example.honpemes.widget.pinyin;



import com.example.honpemes.fragment.b.setting.mob.bean.UseInfoBean;
import com.example.honpemes.utils.StringUtil;

import java.util.Comparator;

/**
 * FileName: PinyinComparator
 * Author: asus
 * Date: 2021/12/3 9:23
 * Description:
 */
public class PinyinComparator implements Comparator<UseInfoBean.DataBean> {

    @Override
    public int compare(UseInfoBean.DataBean o1, UseInfoBean.DataBean o2) {
        return StringUtil.getPingYin(o1.getRealname()).compareTo(StringUtil.getPingYin(o2.getRealname()));
    }
}
