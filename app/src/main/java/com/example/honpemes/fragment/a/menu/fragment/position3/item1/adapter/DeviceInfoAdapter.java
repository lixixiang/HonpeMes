package com.example.honpemes.fragment.a.menu.fragment.position3.item1.adapter;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceInBean;
import com.example.honpemes.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/23 12:10
 * 注释：
 */
public class DeviceInfoAdapter extends BaseQuickAdapter<DeviceInBean, BaseViewHolder> {
    private String ljbm;

    public DeviceInfoAdapter() {
        super(R.layout.item_device_info);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DeviceInBean bean) {
        holder.setGone(R.id.ll_out_send_order, false);

        if (bean.零件编码.length() > 4) {
            ljbm = bean.零件编码.substring(0,4);
        } else {
            ljbm = "";
        }

        holder.setText(R.id.item_1, bean.机台编号 + "\n" + bean.机台类型.replaceAll("\\s", ""))
                .setText(R.id.item_2, bean.上机时间)
                .setText(R.id.item_3, bean.产品名称)
                .setText(R.id.item_4, bean.生产单号 + "\n" + ljbm)
                .setText(R.id.item_5, bean.使用部门 + "\n" + bean.制作组别);

        TextView tvItem1 = holder.getView(R.id.item_1);
        TextView tvItem2 = holder.getView(R.id.item_2);
        TextView tvItem3 = holder.getView(R.id.item_3);
        TextView tvItem4 = holder.getView(R.id.item_4);
        TextView tvItem5 = holder.getView(R.id.item_5);
        ImageView ivRight = holder.getView(R.id.iv_right);

        tvItem1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tvItem2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tvItem3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tvItem4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tvItem5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        ivRight.setVisibility(View.VISIBLE);

        if (bean.机台状态.contains("▲")) {
            tvItem1.setTextColor(getContext().getResources().getColor(R.color.orange_l));
            tvItem2.setTextColor(getContext().getResources().getColor(R.color.orange_l));
            tvItem3.setTextColor(getContext().getResources().getColor(R.color.orange_l));
            tvItem4.setTextColor(getContext().getResources().getColor(R.color.orange_l));
            tvItem5.setTextColor(getContext().getResources().getColor(R.color.orange_l));
            ivRight.setColorFilter(getContext().getResources().getColor(R.color.orange_l));
        } else if (bean.机台状态.contains("●")) {
            tvItem1.setTextColor(getContext().getResources().getColor(R.color.green_l));
            tvItem2.setTextColor(getContext().getResources().getColor(R.color.green_l));
            tvItem3.setTextColor(getContext().getResources().getColor(R.color.green_l));
            tvItem4.setTextColor(getContext().getResources().getColor(R.color.green_l));
            tvItem5.setTextColor(getContext().getResources().getColor(R.color.green_l));
            ivRight.setColorFilter(getContext().getResources().getColor(R.color.green_l));
        } else if (bean.机台状态.contains("■")) {
            tvItem1.setTextColor(getContext().getResources().getColor(R.color.red_l));
            tvItem2.setTextColor(getContext().getResources().getColor(R.color.red_l));
            tvItem3.setTextColor(getContext().getResources().getColor(R.color.red_l));
            tvItem4.setTextColor(getContext().getResources().getColor(R.color.red_l));
            tvItem5.setTextColor(getContext().getResources().getColor(R.color.red_l));
            ivRight.setColorFilter(getContext().getResources().getColor(R.color.red_l));
        }

    }
}


























