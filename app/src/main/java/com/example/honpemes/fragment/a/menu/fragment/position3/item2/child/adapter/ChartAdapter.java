package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter;

import android.graphics.Typeface;
import android.text.SpannableString;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceUserBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Lixixiang on 2023/3/16 10:54
 */
public class ChartAdapter extends BaseMultiItemQuickAdapter<DeviceUserBean, BaseViewHolder> {

    public ChartAdapter() {
        addItemType(HomeBean.TYPE_1, R.layout.item_title_mp_bar);
        addItemType(HomeBean.TYPE_2, R.layout.item_pie_chart2);
        addItemType(HomeBean.TYPE_3, R.layout.item_line_chart2);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DeviceUserBean bean) {
        switch (holder.getItemViewType()) {
            case HomeBean.TYPE_1:
                holder.setText(R.id.tv_line_title, bean.getTitle())
                        .setText(R.id.tv_line_total, bean.getDes());
                break;
            case HomeBean.TYPE_2:
                PieChart pieChart = holder.getView(R.id.m_Pie_chart);
                String accountTime = "当前时长\n" + bean.getAccountTime();
                SpannableString sp = StringUtil.changeFontSize(accountTime, Util.sp2px(getContext(), 6), "当前时长\n".length(), accountTime.length());
                holder.setText(R.id.tv_des, StringUtil.changeFontBold(sp, "当前时长\n".length(), accountTime.length()));
                setStatusPieChart(pieChart, bean, holder.getAdapterPosition());
                break;
            case HomeBean.TYPE_3:
//                LineChart chart = holder.getView(R.id.m_line_chart);
//                ChartUtil.setStatusChart(chart, bean);
                break;
        }

    }

    private void setStatusPieChart(PieChart chart, DeviceUserBean bean, int position) {
        ArrayList<PieEntry> mPieEntryList = new ArrayList<>();
        mPieEntryList.clear();

        Typeface mTf = Typeface.createFromAsset(getContext().getAssets(), "font/pingfangsc_medium.ttf");
        PieEntry pieRunEntry1 = new PieEntry(bean.getTotalCount(), "");
        PieEntry pieRunEntry2 = new PieEntry(bean.getSum() - bean.getTotalCount(), "");

        mPieEntryList.add(pieRunEntry1);
        mPieEntryList.add(pieRunEntry2);

        PieDataSet pieDataSet = new PieDataSet(mPieEntryList, "加工机台状态");
        pieDataSet.setColors(bean.getColors());
        pieDataSet.setSliceSpace(0);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0);

        chart.setDrawCenterText(false);
        chart.setData(pieData);
        chart.setHighlightPerTapEnabled(false);
        chart.setDrawCenterText(true);
        chart.setEntryLabelTextSize(0);
        chart.setHoleRadius(85f);//改变圆环大小通过设置内圆
        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);

        chart.getLegend().setEnabled(false);


        chart.setCenterTextSize(10);
        String centerText = "使用率\n" + bean.getUserRate() + "\n近期活跃度\n" + bean.getLineRate();
        int[] start = {"使用率\n".length(), "使用率\n".length() + bean.getUserRate().length() + "\n近期活跃度\n".length()};
        int[] end = {"使用率\n".length() + bean.getUserRate().length(), centerText.length()};
        SpannableString sbUserRate = StringUtil.changeMoreFontSizeBold(centerText, Util.sp2px(getContext(), 14), start, end);
        chart.setCenterText(sbUserRate);
        chart.setCenterTextTypeface(mTf);


        LatteLogger.d("tefwefwfewfew", GsonBuildUtil.GsonBuilder(mPieEntryList) + "   " + GsonBuildUtil.GsonBuilder(chart.getData().getYValueSum()));

    }


}
