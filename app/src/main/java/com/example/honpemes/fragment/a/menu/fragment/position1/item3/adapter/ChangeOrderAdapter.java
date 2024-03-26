package com.example.honpemes.fragment.a.menu.fragment.position1.item3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position1.item3.bean.ChangeOrderBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/3/7 10:59
 */
public class ChangeOrderAdapter extends BaseQuickAdapter<ChangeOrderBean.DataBean, BaseViewHolder> {

    public ChangeOrderAdapter() {
        super(R.layout.item_order_detail_2);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ChangeOrderBean.DataBean bean) {
        LatteLogger.d("tefewefwfwe", GsonBuildUtil.GsonBuilder(bean));

        holder.setText(R.id.tv_id, bean.get变更单号())
                .setText(R.id.tv_title1, "变更日期")
                .setText(R.id.tv_title2, "变更人")
                .setText(R.id.tv_title3, "确认日期")
                .setText(R.id.tv_title4, "确认人")
                .setText(R.id.tv_content1, bean.get变更日期())
                .setText(R.id.tv_content2, bean.get变更人())
                .setText(R.id.tv_content3, bean.get确认日期())
                .setText(R.id.tv_content4, bean.get确认人())
                .setText(R.id.tv_change_content, bean.get变更内容())
                .setText(R.id.tv_change_des, bean.get变更描述().replaceAll("\r|\n", "").replace("，",""));

    }
}













