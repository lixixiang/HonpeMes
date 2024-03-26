package com.example.honpemes.fragment.a.menu.fragment.position1.item1.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.TeamBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.dialog.Base.DialogUtils;
import com.flyco.roundview.RoundTextView;

import org.jetbrains.annotations.NotNull;

import cn.bertsir.zbar.utils.QRUtils;

/**
 * Created by Lixixiang on 2023/3/7 8:39
 */
public class OrderItemAdapter extends BaseQuickAdapter<OrderEntity.DataBean, BaseViewHolder> {
    String orderTeam, madeTeam;
    TeamBean mTeamBean;
    Bundle bundle = new Bundle();

    public OrderItemAdapter(TeamBean teamBean) {
        super(R.layout.item_order_detail_1);
        mTeamBean = teamBean;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderEntity.DataBean bean) {
        for (int i = 0; i < mTeamBean.getData().size(); i++) {
            if (bean.get订单组别().equals(mTeamBean.getData().get(i).get组别代码())) {
                orderTeam = mTeamBean.getData().get(i).get组别名称();
            }
            if (bean.get制作组别().equals(mTeamBean.getData().get(i).get组别代码())) {
                madeTeam = mTeamBean.getData().get(i).get组别名称();
            }
        }

        String name2 = "生产单号：" + bean.get生产单号();
        String name3 = "订单组别：" + orderTeam;
        String name4 = "下单人员：" + bean.get下单人员();

        holder.setText(R.id.tv_content, bean.get产品名称())
                .setText(R.id.tv_title2, StringUtil.changeStrColor(name2, R.color.black, "生产单号：".length(), name2.length()))
                .setText(R.id.tv_title3, StringUtil.changeStrColor(name3, R.color.black, "订单组别：".length(), name3.length()))
                .setText(R.id.tv_title4, StringUtil.changeStrColor(name4, R.color.black, "下单人员：".length(), name4.length()))
                .setText(R.id.tv_content3, "制作组别：" + madeTeam)
                .setText(R.id.tv_content4, "下单日期：" + DateUtil.ymd_hm.format(DateUtil.setDate(DateUtil.ymdhm, bean.get下单日期())))
                .setText(R.id.tv_kd, "快递：" + bean.get是否快递())
                .setText(R.id.tv_bg, "报关：" + bean.get是否报关())
                .setText(R.id.tv_fee, "收费：" + bean.get是否收费())
                .setText(R.id.tv_reason, bean.get订单备注().equals("") ? "备注：无" : "备注："+bean.get订单备注());

        RoundTextView rt2 = holder.getView(R.id.tv_content2);

        if (bean.isShengPi()) {
            rt2.getDelegate().setBackgroundColor(R.color.grey_l);
            rt2.getDelegate().setBackgroundPressColor(R.color.green_alpha);
            rt2.getDelegate().setCornerRadius(8);
            rt2.setTextColor(Color.WHITE);
            rt2.setPadding(10,8,10,8);
            rt2.setText("已审批");
        } else {
            rt2.getDelegate().setBackgroundColor(R.color.green_l);
            rt2.getDelegate().setBackgroundPressColor(R.color.green_alpha);
            rt2.getDelegate().setCornerRadius(8);
            rt2.setTextColor(Color.WHITE);
            rt2.setPadding(10,8,10,8);
            rt2.setText("待审核");
        }

        ImageView imageView = holder.getView(R.id.iv_director);
        ImageView ivScan = holder.getView(R.id.iv_scan);
        Bitmap qrCode = QRUtils.getInstance().createQRCode(bean.get生产单号());
        ivScan.setImageBitmap(qrCode);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isExpanded()) {
                    bean.setExpanded(false);
                    ViewCompat.animate(imageView).setDuration(200)
                            .setInterpolator(new DecelerateInterpolator())
                            .rotation(0)
                            .start();
                } else {
                    bean.setExpanded(true);
                    ViewCompat.animate(imageView).setDuration(200)
                            .setInterpolator(new DecelerateInterpolator())
                            .rotation(-180f)
                            .start();
                }
                holder.setGone(R.id.ll_show, bean.isExpanded() ? false : true);
            }
        });

        rt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString(FinalClass.ORDER_ID,bean.get生产单号());
                bundle.putInt(FinalClass.POSITION,holder.getAdapterPosition());
                DialogUtils.tipsDialog(getContext(),"审核","是否审核订单"+bean.get生产单号(),bundle);
            }
        });
    }
}





















