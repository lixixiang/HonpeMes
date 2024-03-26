package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceStatusBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/3/2 16:06
 */
public class DeviceStatusHomeAdapter extends BaseQuickAdapter<DeviceStatusBean.DataBean, BaseViewHolder> {


    public DeviceStatusHomeAdapter() {
        super(R.layout.item_device_status);
        addChildClickViewIds(R.id.tv_4);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DeviceStatusBean.DataBean bean) {


        holder.setText(R.id.item_title, bean.get机台编号())
                .setText(R.id.item_title_1, bean.get机台型号())
                .setText(R.id.tv_2, bean.get待机数量() + "")
                .setText(R.id.tv_3, bean.get状态());


        if (bean.get状态().contains("运行")) {
            holder.setTextColor(R.id.tv_3, DataClass.COLORS2[1]);
        } else if (bean.get状态().contains("暂停")) {
            holder.setTextColor(R.id.tv_3, DataClass.COLORS2[2]);
        } else if (bean.get状态().contains("报警")) {
            holder.setText(R.id.tv_3, "报警").setTextColor(R.id.tv_3, DataClass.COLORS2[3]);
        } else if (bean.get状态().contains("关机")) {
            holder.setTextColor(R.id.tv_3, DataClass.COLORS2[4]);
        } else {
            holder.setText(R.id.tv_3, bean.get状态()).setTextColorRes(R.id.tv_3, R.color.black_l);
        }


    }
}


























