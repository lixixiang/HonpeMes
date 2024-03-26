package com.example.honpemes.fragment.a.menu.fragment.position4.position3.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position4.position3.bean.AboutRunOutBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;

public class AboutRunOutAdapter extends BaseQuickAdapter<AboutRunOutBean.DataBean, BaseViewHolder> {


    public AboutRunOutAdapter() {
        super(R.layout.item_about_run_out);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, AboutRunOutBean.DataBean bean) {
        holder.setText(R.id.tv_order_id, bean.get生产单号())
                .setText(R.id.tv_order_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymd, bean.get下单日期())))
                .setText(R.id.tv_delivery_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymd, bean.get要求交货日期())))
                .setText(R.id.tv_buyer, "数量：" + bean.get数量())
                .setText(R.id.tv_center, "报价金额：" + StringUtil.formatDouble(bean.get报价金额()))
                .setText(R.id.tv_team, "采购分类：" + bean.get采购分类())
                .setText(R.id.tv_item3_left, "申请人：" + bean.get申请人())
                .setText(R.id.tv_item3_right, "申请部门：" + bean.get申请部门() + "_" + bean.get申请组别());

        String strEvent = "特殊事项：" + bean.get特殊事项();
        String strSupplierName = "供应商名称：" + bean.get供应商名称();

        holder.setText(R.id.tv_event, StringUtil.changeStrColor(strEvent, R.color.orange_l, "特殊事项：".length(), strEvent.length()));

        holder.setText(R.id.tv_supplier_name, StringUtil.changeStrColor(strSupplierName, R.color.blue_l, "供应商名称：".length(), strSupplierName.length()));

        if ("0".equals(bean.getTips())) {
            holder.setText(R.id.tv_status,"今天收货")
                    .setTextColorRes(R.id.tv_status,R.color.orange_l);
        } else if (Integer.parseInt(bean.getTips()) > 0) {
            holder.setText(R.id.tv_status,bean.getTips()+"天后收货")
                    .setTextColorRes(R.id.tv_status,R.color.black_l);
        } else  {
            holder.setText(R.id.tv_status,"延期"+bean.getTips()+"天")
                    .setTextColorRes(R.id.tv_status,R.color.red_l);
        }

        if ("".equals(bean.get特殊事项())) {
            holder.setGone(R.id.tv_event, true);
        } else {
            holder.setGone(R.id.tv_event, false);
        }
        if ("".equals(bean.get供应商名称())) {
            holder.setGone(R.id.tv_supplier_name, true);
        } else {
            holder.setGone(R.id.tv_supplier_name, false);
        }

    }
}
