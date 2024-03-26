package com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.bean.DetailBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.flyco.roundview.RoundLinearLayout;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/21 10:44
 * 注释：
 */
public class DetailAdapter extends BaseMultiItemQuickAdapter<DetailBean.DataBean, BaseViewHolder> {
    private String strStartTime, strEndTime, differ;

    public DetailAdapter() {
        addItemType(ProcessingRecordBean.DataBean.TYPE_1, R.layout.item_detal_css1);
        addItemType(ProcessingRecordBean.DataBean.TYPE_2, R.layout.item_detal_css2);
        addItemType(ProcessingRecordBean.DataBean.TYPE_3, R.layout.item_detal_css3);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DetailBean.DataBean bean) {
        switch (holder.getItemViewType()) {
            case ProcessingRecordBean.DataBean.TYPE_1:
                String createTime = bean.dataBean.createTime.replace("T", " ");
                String ymd = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, createTime));

                holder.setText(R.id.item_1, bean.dataBean.workName)
                        .setText(R.id.item_1_1, "星期" + DateUtil.getWeek(ymd, DateUtil.ymd) + " " + ymd)
                        .setText(R.id.item_2, "机台编号：" + bean.dataBean.机台编号)
                        .setText(R.id.item_2_1,"机台类型：" +  bean.dataBean.机台类型)
                        .setText(R.id.item_3, "加工数量：" + bean.dataBean.workpieceCount)
                        .setText(R.id.item_3_1, "待机数量：" + bean.dataBean.待机数量)
                        .setText(R.id.item_4, "使用部门：" + bean.dataBean.使用部门)
                        .setText(R.id.item_4_1, "制作组别：" + bean.dataBean.制作组别);

                break;
            case ProcessingRecordBean.DataBean.TYPE_2:
                holder.setText(R.id.item_1,"运行时长：" +DateUtil.getTimePoor(bean.dataBean.dailyRunTime))
                        .setText(R.id.item_2,"待料时长：" +DateUtil.getTimePoor(bean.dataBean.dailyWaitTime))
                        .setTextColorRes(R.id.item_2,R.color.orange_l)
                        .setText(R.id.item_3,"加工时长：" +DateUtil.getTimePoor(bean.dataBean.diffTime/1000))
                        .setTextColorRes(R.id.item_3,R.color.green_l)
                        .setTextColorRes(R.id.item_3_1,R.color.blue_l)
                        .setText(R.id.item_3_1,
                                "机床利用率：" +
                                        StringUtil.formatDouble(((double) bean.dataBean.dailyCutTime / bean.dataBean.dailyRunTime) * 100) + "%");
                break;
            case ProcessingRecordBean.DataBean.TYPE_3:
                strStartTime = bean.startTime.replace("T", " ");
                strEndTime = bean.endTime.replace("T", " ");
                differ = DateUtil.getTimePoor(DateUtil.setDate(DateUtil.ymdhms, strEndTime),
                        DateUtil.setDate(DateUtil.ymdhms, strStartTime));
                RoundLinearLayout ll = holder.getView(R.id.ll_item3_bg);
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatteLogger.d("setOnClickListener",holder.getAdapterPosition());
                    }
                });

                holder.setText(R.id.tv_start_time, strStartTime)
                        .setText(R.id.tv_end_time, strEndTime)
                        .setText(R.id.tv_time_differ, differ);
//                if (holder.getAdapterPosition() == 2) {
//                    holder.setGone(R.id.fl_item, false);
//                } else {
//                    holder.setGone(R.id.fl_item, true);
//                }
                break;
        }
    }
}

















