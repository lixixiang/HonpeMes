package com.example.honpemes.fragment.a.menu.fragment.position1.item6.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/15 10:41
 * 注释：
 */
public class TimeOutOrderAdapter extends BaseQuickAdapter<ProduceProgressBean.DataBean, BaseViewHolder> {


    public TimeOutOrderAdapter() {
        super(R.layout.item_time_out_order);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ProduceProgressBean.DataBean bean) {
        holder.setText(R.id.tv_order_id, bean.生产单号)
                .setText(R.id.tv_order_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymdh, bean.下单日期)))
                .setText(R.id.tv_delivery_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymdh, bean.交货日期)))
                .setText(R.id.tv_buyer, "订单数量:" + bean.订单数量)
                .setText(R.id.tv_team, "组别:" + bean.订单组别 + "_" + bean.制作组别名称)
                .setText(R.id.tv_status, bean.订单状态).setText(R.id.tv_out_time_date, "延误" + bean.延误天数 + "天")
                .setGone(R.id.tv_status, false).setGone(R.id.tv_out_time_date, false);

        String strProName = "产品名称：" + bean.产品名称;

        holder.setGone(R.id.re_item_2, false);
        holder.setText(R.id.tv_item2_left, StringUtil.changeStrColor(strProName, R.color.blue_l, "产品名称：".length(), strProName.length()));

        if (!"".equals(bean.订单备注)) {
            holder.setGone(R.id.re_item_3, false)
                    .setText(R.id.tv_item3_left, "订单备注：")
                    .setText(R.id.tv_item3_right, bean.订单备注);
        } else {
            holder.setGone(R.id.re_item_3, true);
        }

    }
}























