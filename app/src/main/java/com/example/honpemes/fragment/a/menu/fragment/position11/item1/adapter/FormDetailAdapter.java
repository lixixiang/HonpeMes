package com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.DetailFormBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.ItemFormStatusBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.github.mikephil.charting.charts.PieChart;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lixixiang on 2023/2/7 16:57
 * 适配子类
 */
public class FormDetailAdapter extends BaseQuickAdapter<DetailFormBean, BaseViewHolder> {
    private List<ItemFormStatusBean> mItemList = new ArrayList<>();
    public String[] PieBarString = {"制作数量", "检修数量", "空闲数量"};


    public FormDetailAdapter() {
        super(R.layout.item_pie_chart);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DetailFormBean bean) {
        if ("JK".equals(bean.getName())) {
            holder.setText(R.id.tv_title, "组别：机壳");
        } else if ("WJ".equals(bean.getName())) {
            holder.setText(R.id.tv_title, "组别：五金");
        } else if ("SM".equals(bean.getName())) {
            holder.setText(R.id.tv_title, "组别：数码");
        }

        LatteLogger.d("testdfewf", GsonBuildUtil.GsonBuilder(bean));

        holder.setText(R.id.tv_total, "机台数：" + bean.getSum());

        PieChart mPieChart = holder.getView(R.id.m_Pie_chart);
        int[] arr = {bean.getMakeNum(), bean.getRepairNum(), bean.getFreeNum()};
        ChartUtil.setPieChart(mPieChart, arr, DataClass.COLORS);

        RecyclerView itemRecycler = holder.getView(R.id.item_recyclerview);
        itemRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemOrderFormAdapter itemAdapter = new ItemOrderFormAdapter();
        itemRecycler.setAdapter(itemAdapter);
        mItemList.clear();
        for (int i = 0; i < arr.length; i++) {
            mItemList.add(new ItemFormStatusBean(String.valueOf(i + 1), PieBarString[i], String.valueOf(arr[i]), String.valueOf(bean.getSum())));
        }
        itemAdapter.setList(mItemList);
    }



}











































