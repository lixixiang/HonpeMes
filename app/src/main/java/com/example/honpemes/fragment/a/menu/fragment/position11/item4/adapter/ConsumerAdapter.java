package com.example.honpemes.fragment.a.menu.fragment.position11.item4.adapter;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.bean.ConsumerBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2024/1/2 10:11
 * 注释：
 */
public class ConsumerAdapter extends BaseQuickAdapter<ConsumerBean.DataBean, BaseViewHolder> {
    private String firstDay;

    public ConsumerAdapter() {
        super(R.layout.item_consumer);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ConsumerBean.DataBean bean) {
        holder.setText(R.id.tv_content, bean.生产单号)
                .setText(R.id.tv_content2, bean.产品名称)
                .setText(R.id.tv_content3, bean.投诉问题)
                .setText(R.id.tv_content4, bean.客诉要求)
                .setText(R.id.tv_content5, bean.客诉单号)
                .setText(R.id.tv_content6, bean.客户名称)
                .setText(R.id.tv_type5, "类型：" + bean.客诉类型).setTextColorRes(R.id.tv_type5, R.color.red_l)
                .setText(R.id.tv_type6, "投诉日期：" + bean.投诉日期);


    }


}
