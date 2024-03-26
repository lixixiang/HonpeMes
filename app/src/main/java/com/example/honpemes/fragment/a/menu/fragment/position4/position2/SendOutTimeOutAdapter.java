package com.example.honpemes.fragment.a.menu.fragment.position4.position2;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus  on 2024/3/7 9:56
 * 注释：
 */
public class SendOutTimeOutAdapter extends BaseQuickAdapter<SendOutTimeOutBean.DataBean, BaseViewHolder> {

    public SendOutTimeOutAdapter() {
        super(R.layout.item_time_out_order);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SendOutTimeOutBean.DataBean bean) {

        holder.setText(R.id.tv_order_id, bean.申请单号)
                .setText(R.id.tv_order_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymd, bean.下单日期)))
                .setText(R.id.tv_delivery_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymd, bean.要求交货日期)))
                .setText(R.id.tv_buyer, "数量：" + bean.数量)
                .setText(R.id.tv_center, "报价金额：" + StringUtil.DoubleToString(bean.报价金额))
                .setText(R.id.tv_team, "采购分类：" + bean.采购分类).setGone(R.id.re_item1, false)
                .setText(R.id.tv_item1_left, "申请人：" + bean.申请人)
                .setText(R.id.tv_item1_right, "申请部门：" + bean.申请部门 + "_" + bean.申请组别)
                .setText(R.id.tv_status, bean.记账状态).setText(R.id.tv_out_time_date, "超时" + bean.收货超时天数 + "天")
                .setGone(R.id.tv_status, false).setGone(R.id.tv_out_time_date, false);


        if (!"".equals(bean.特殊事项)) {
            holder.setGone(R.id.re_item_3, false)
                    .setText(R.id.tv_item3_left, "特殊事项：")
                    .setText(R.id.tv_item3_right, bean.特殊事项);
        } else {
            holder.setGone(R.id.re_item_3, true);
        }

        if (!"".equals(bean.供应商名称)) {
            holder.setGone(R.id.re_item_4, false)
                    .setText(R.id.tv_item4_left, "供应商名称：" + bean.供应商名称)
                    .setTextColor(R.id.tv_item4_left, getContext().getResources().getColor(R.color.blue_l));
        } else {
            holder.setGone(R.id.re_item_4, true);
        }

    }
}
