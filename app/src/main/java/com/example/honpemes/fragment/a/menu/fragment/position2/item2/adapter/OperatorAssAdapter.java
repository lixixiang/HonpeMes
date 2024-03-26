package com.example.honpemes.fragment.a.menu.fragment.position2.item2.adapter;


import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.bean.OperatorAssBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.utils.DateUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/21 09:19
 * 注释：
 */
public class OperatorAssAdapter extends BaseQuickAdapter<OperatorAssBean, BaseViewHolder> {


    public OperatorAssAdapter() {
        super(R.layout.item_process_record);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OperatorAssBean bean) {
        holder.setGone(R.id.ll_process_record_bg, false);
        holder.setText(R.id.item_1, bean.生产单号);
        holder.setText(R.id.item_2, bean.机台类型)
                .setText(R.id.item_3, bean.制作数量+"")
                .setText(R.id.item_4, bean.执行次数 + "" )
                .setText(R.id.item_5, bean.开料尺寸 + "");

        ImageView ivRight = holder.getView(R.id.iv_right);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setColorFilter(Color.BLACK);
        TextView tvItem1 =holder.getView(R.id.item_1);
        TextView tvItem2 =holder.getView(R.id.item_2);
        TextView tvItem3 =holder.getView(R.id.item_3);
        TextView tvItem4 =holder.getView(R.id.item_4);
        TextView tvItem5 =holder.getView(R.id.item_5);
        tvItem1.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        tvItem2.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        tvItem3.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        tvItem4.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        tvItem5.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);

        LinearLayout linearLayout =holder.getView(R.id.ll_process_record_bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,5);
        linearLayout.setLayoutParams(params);
    }
}









































