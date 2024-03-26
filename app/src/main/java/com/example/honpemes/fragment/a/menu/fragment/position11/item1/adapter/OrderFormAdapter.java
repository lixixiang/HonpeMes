package com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.ItemFormStatusBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.OrderFormBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lixixiang on 2023/2/4 11:54
 */
public class OrderFormAdapter extends BaseQuickAdapter<OrderFormBean, BaseViewHolder> {
    public static String[] PieBarString = {"制作数量", "检修数量", "空闲数量"};
    public static int Sum, makeNum, repairNum, freeNum, totalNum1, totalNum2, totalNum3, totalNum4,SumAccept;
    private ItemOrderFormAdapter itemAdapter;
    private List<ItemFormStatusBean> mItemList = new ArrayList<>();
    private Bundle bundle = new Bundle();


    public OrderFormAdapter() {
        super(R.layout.item_chart_list);
        addChildClickViewIds(R.id.ll_card_chart_bg);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderFormBean bean) {
        PieChart mPieChart = holder.getView(R.id.m_Pie_chart);
        LinearLayout llCardChartBg=holder.getView(R.id.ll_card_chart_bg);
        LinearLayout llCardBarChartBg=holder.getView(R.id.ll_card_BarChart_bg);
        LinearLayout llLineChartBg=holder.getView(R.id.ll_card_LineChart_bg);
        RecyclerView itemRecycler = holder.getView(R.id.item_recyclerview);

        itemRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Sum = 0;
        makeNum = 0;
        repairNum = 0;
        freeNum = 0;
        mItemList.clear();
        totalNum1 = 0;
        totalNum2 = 0;
        totalNum3 = 0;
        totalNum4 = 0;
        SumAccept = 0;

        for (int i = 0; i < bean.getData().get加工机台状态().size(); i++) {
            Sum = Sum + bean.getData().get加工机台状态().get(i).getCn();
            if ("●" .equals(bean.getData().get加工机台状态().get(i).get机台状态())) { //制作数量
                makeNum = makeNum + bean.getData().get加工机台状态().get(i).getCn();
            } else if ("▲" .equals(bean.getData().get加工机台状态().get(i).get机台状态())) { //空闲数量
                repairNum = repairNum + bean.getData().get加工机台状态().get(i).getCn();
            } else if ("■" .equals(bean.getData().get加工机台状态().get(i).get机台状态())) { //检修数量
                freeNum = freeNum + bean.getData().get加工机台状态().get(i).getCn();
            }
        }
        if (Sum == 0) {
            llCardChartBg.setVisibility(View.GONE);
        } else {
            int[] arr = {makeNum, repairNum, freeNum};
            ChartUtil.setPieChart(mPieChart, arr, DataClass.COLORS);

            itemAdapter = new ItemOrderFormAdapter();
            itemRecycler.setAdapter(itemAdapter);

            for (int i = 0; i < arr.length; i++) {
                mItemList.add(new ItemFormStatusBean(String.valueOf(i + 1), PieBarString[i], String.valueOf(arr[i]), String.valueOf(Sum)));
            }
            holder.setText(R.id.tv_total, "机台数量：" + Sum);

            itemAdapter.setList(mItemList);
            itemAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                    bundle.putInt("position", position);
                    bundle.putString("name", itemAdapter.getData().get(position).getName());
                    EventBusUtil.sendEvent(new Event(FinalClass.EVENT, bundle));
                }
            });
        }

        RecyclerView mSalesRecycler = holder.getView(R.id.list_sales);
        mSalesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        SaleAdapter mSaleAdapter = new SaleAdapter();
        mSalesRecycler.setAdapter(mSaleAdapter);

        mSaleAdapter.setList(bean.getData().get订单统计分析());

        for (int i = 0; i < bean.getData().get订单统计分析().size(); i++) {
            totalNum1 = totalNum1 + bean.getData().get订单统计分析().get(i).get订单数量();
            totalNum2 = totalNum2 + bean.getData().get订单统计分析().get(i).get完成数量();
            totalNum3 = totalNum3 + bean.getData().get订单统计分析().get(i).get延期数量();
            totalNum4 = totalNum4 + bean.getData().get订单统计分析().get(i).get报废数量();
        }

        holder.setText(R.id.sum_1, totalNum1 + "")
                .setText(R.id.sum_2, totalNum2 + "")
                .setText(R.id.sum_3, totalNum3 + "")
                .setText(R.id.sum_4, totalNum4 + "");

        for (int i = 0; i < bean.getData().get近一年业务接单数().size(); i++) {
            SumAccept = SumAccept +bean.getData().get近一年业务接单数().get(i).get订单数量();
        }

       if (SumAccept == 0){
           llCardBarChartBg.setVisibility(View.GONE);
       }else {
           // 近一年业务接单数
           holder.setText(R.id.tv_bar_title,"近一年业务接单数")
                   .setText(R.id.tv_bar_total,"接单数合计："+SumAccept);
           BarChart barChart =holder.getView(R.id.m_bar_chart);
           ChartUtil.setBarChart(barChart,bean.getData().get近一年业务接单数());
       }

        holder.setText(R.id.tv_line_title,"销售年月接单数").setGone(R.id.tv_line_total,true);
        LineChart lineChart =holder.getView(R.id.m_line_chart);
        ChartUtil.setLineChart(lineChart,bean.getData().get销售年月接单数());
        LatteLogger.d("接单数", GsonBuildUtil.GsonBuilder(bean.getData().get销售年月接单数()));

    }


    public class SaleAdapter extends BaseQuickAdapter<OrderFormBean.DataBean.订单统计分析Bean, BaseViewHolder> {

        public SaleAdapter() {
            super(R.layout.item_form_detail);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, OrderFormBean.DataBean.订单统计分析Bean bean) {
            holder.setText(R.id.c_0, bean.get组别().substring(0, 2))
                    .setText(R.id.c_1, bean.get组别().substring(bean.get组别().length() - 3))
                    .setText(R.id.c_2, bean.get订单数量() + "")
                    .setText(R.id.c_3, bean.get完成数量() + "")
                    .setText(R.id.c_4, bean.get延期数量() + "")
                    .setText(R.id.c_5, bean.get报废数量() + "");
        }
    }
}



























