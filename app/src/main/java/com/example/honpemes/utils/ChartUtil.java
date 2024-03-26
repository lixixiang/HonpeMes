package com.example.honpemes.utils;

import static com.example.honpemes.MyApplication.getContext;
import static com.example.honpemes.api.DataClass.COLORS;
import static com.example.honpemes.api.DataClass.COLORS2;
import static com.example.honpemes.api.DataClass.COLORS3;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.honpemes.MyApplication;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.bean.OrderStatBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter.OrderFormAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.OrderFormBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.bean.MeterManagerBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item7.bean.SRBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceUserBean;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.bean.SendOutBean;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;
import com.example.honpemes.widget.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Lixixiang on 2023/2/4 15:17
 */
public class ChartUtil {
    static ArrayList<BarEntry> mBarEntryList = new ArrayList<>();
    static ArrayList<BarEntry> mBarEntryList2 = new ArrayList<>();

    static ArrayList<PieEntry> mPieEntryList = new ArrayList<>();
    static ArrayList<Entry> mEntryList = new ArrayList<>();
    static ArrayList<Entry> mEntryList1 = new ArrayList<>();
    static ArrayList<Entry> mEntryList2 = new ArrayList<>();
    static ArrayList<Entry> mEntryList3 = new ArrayList<>();
    static int sValue1, sValue2, sValue3, sValue4;
    static String strValue;
    static List<IBarDataSet> dataSets = new ArrayList<>();

    public static void setBarChart1(BarChart chart) {
        sValue1 = 0;
        sValue2 = 0;
        sValue3 = 0;
        sValue4 = 0;

        if (mBarEntryList.size() > 0) {
            mBarEntryList.clear();
        }
        for (int i = 0; i < DataClass.testStart.length; i++) {
            sValue1 = sValue1 + DataClass.testStart[i];
            sValue2 = sValue2 + DataClass.testRun[i];
            sValue3 = sValue3 + DataClass.testStop[i];
            sValue4 = sValue4 + DataClass.testClose[i];
        }

        int[] arr = {sValue1, sValue2, sValue3, sValue4};

        for (int i = 0; i < arr.length; i++) {
            mBarEntryList.add(new BarEntry(i, arr[i]));
        }

        //2，BarDataSet 柱子
        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "");

        LatteLogger.d("testListData", GsonBuildUtil.GsonBuilder(mBarEntryList));
        //3，把BarDataSet数据添加到IBarDataSet集合
//        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
//        iBarDataSets.add(barDataSet);

        //设置x、y轴
        XAxis xAxis = chart.getXAxis();//获取X轴

        //设置X轴的值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    return "开机";
                } else if (value == 1) {
                    return "运行";
                } else if (value == 2) {
                    return "暂停";
                } else if (value == 3) {
                    return "关机";
                } else {
                    return "";
                }
            }
        });

        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴
        leftAxis.setDrawAxisLine(true);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        // 图表交互
        common_bar_chart(chart, barDataSet, xAxis, leftAxis, COLORS2);
    }


    private static void common_bar_chart(BarChart chart, BarDataSet barDataSet, XAxis xAxis, YAxis leftAxis, int[] colors) {
        barDataSet.setColors(colors);
        barDataSet.setDrawValues(false);

        //4,柱状集所有设置 BarData 中 可以完成
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.2f); //柱状图宽度
        barData.setDrawValues(true);
        barData.setValueTextColor(Color.BLACK);
        barData.setValueTextSize(12f);
        chart.setData(barData);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.black));

        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(300);
        leftAxis.setAxisMinimum(0);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        chart.setExtraOffsets(10, 0, 0, 10);
        //不显示图表网格
        chart.setDrawGridBackground(false);
        //背景阴影
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志

        chart.animateXY(2000, 2000);
        chart.invalidate();
    }


    public static void setMeterBarDetailLineChart(LineChart chart, ArrayList<MeterManagerBean.DataBean> meterDataList) {
        mEntryList.clear();
        if (chart.getData() != null) {
            chart.getData().clearValues();
            chart.getData().notifyDataChanged();
        }

        int monthLastDay = Integer.parseInt(DateUtil.getCurrentLastDayOfMonth(DateUtil.dd).replace("0", ""));

        ArrayList<MeterManagerBean.DataBean> contentList = new ArrayList<>();
        contentList.clear();

        for (int i = 0; i < meterDataList.size(); i++) {
            MeterManagerBean.DataBean dataBean = meterDataList.get(i);
            if (dataBean.getItemType() == 4) {
                contentList.add(dataBean);
            }
        }

        int curLastDay = Integer.parseInt(contentList.get(0).getUsingtime());

        LatteLogger.d("tefwfwfewf", monthLastDay + "    " + GsonBuildUtil.GsonBuilder(contentList));


        for (int k = 0; k < curLastDay; k++) { //0-30 即31天
            mEntryList.add(new Entry(k, 0));

            for (int i = 0; i < contentList.size(); i++) {
                MeterManagerBean.DataBean bean = contentList.get(i);
                int usingDay = Integer.parseInt(bean.getUsingtime());

                if ((k + 1) == usingDay) {
                    LatteLogger.d("tewffedfw", usingDay);

                    mEntryList.set(k, new Entry(k, bean.getAdd_up()));
                }
            }
        }

        LatteLogger.d("contentList", mEntryList.size() + "    " + GsonBuildUtil.GsonBuilder(mEntryList));

        LineDataSet set1 = new LineDataSet(mEntryList, "");
        set1.setDrawIcons(false);
        set1.enableDashedLine(10f, 5f, 0f);

        set1.setDrawCircles(false);

        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);

        set1.setDrawCircleHole(false);
        set1.setDrawValues(false);

        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.0f);
        set1.setValueTextSize(9f);

        set1.enableDashedHighlightLine(10f, 5f, 0f);

        set1.setDrawFilled(true);
        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
            set1.setFillDrawable(drawable);
        } else {
            set1.setFillColor(Color.BLACK);
        }

        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);
        chart.setPinchZoom(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.clear();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // set data
        chart.setData(data);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        //背景阴影
        chart.setTouchEnabled(true);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(true); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志
        chart.animateXY(2000, 2000);
        chart.getAxisRight().setEnabled(false);
        chart.setExtraOffsets(10, 10, 10, 10);


        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextSize(12);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.black));
        int labelCount = curLastDay;

        xAxis.setLabelCount(labelCount);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(monthLastDay - 1);
        xAxis.setYOffset(5);
        xAxis.setXOffset(5);

        //  xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(0f);//设置x轴标签的旋转角度
        xAxis.setGranularity(1f);//设置是否一个格子显示一条数据，如果不设置这个属性，就会导致X轴数据重复并且错乱的问题

        // 设置右侧坐标轴
        YAxis leftYAxis = chart.getAxisLeft();
        leftYAxis.setGranularityEnabled(true);
        leftYAxis.setAxisMinimum(1f);
        leftYAxis.setDrawGridLines(true);//设置是否绘制轴内的横线
        leftYAxis.setDrawAxisLine(false);//设置是否绘制Y轴
        //是否绘制等0线
        leftYAxis.setDrawZeroLine(true);
        leftYAxis.setGranularityEnabled(false);


        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                if (value == 1) {
                    return "1日";
                } else if (value == 7) {
                    return "7日";
                } else if (value == 14) {
                    return "14日";
                } else if (value == 21) {
                    return "21日";
                } else if (value == 28) {
                    return "";
                } else if (value == monthLastDay - 1) {
                    return monthLastDay + "日";
                } else {
                    return "";
                }
            }
        });
    }

    /**
     * 入库看板
     * @param chart
     */
    public static void setLineBoard(LineChart chart, BoardBean bean) {
        mEntryList.clear();
        mEntryList1.clear();
        mEntryList2.clear();
        mEntryList3.clear();
        for (int i = 0; i < bean.nums1.length; i++) {
            mEntryList.add(new Entry(i, Float.parseFloat(bean.nums1[i]), "采购入库"));
            mEntryList1.add(new Entry(i,  Float.parseFloat(bean.nums2[i]), "调拔入库"));
            mEntryList2.add(new Entry(i,  Float.parseFloat(bean.nums3[i]), "盘盈入库"));
            mEntryList3.add(new Entry(i,  Float.parseFloat(bean.nums4[i]), "供应商受赠入库"));
        }

        //数据集
        LineDataSet lineDataSet = getLineDataSet(mEntryList, R.color.orange_l);
        LineDataSet lineDataSet1 = getLineDataSet(mEntryList1, R.color.green_l);
        LineDataSet lineDataSet2 = getLineDataSet(mEntryList2, R.color.red_l);
        LineDataSet lineDataSet3 = getLineDataSet(mEntryList3, R.color.blue_l);

        //设置点击交叉线
        lineDataSet.setHighlightEnabled(true);//是否禁用点击高亮线

        List<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.clear();

        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);

        LineData lineData = new LineData(lineDataSet, lineDataSet1, lineDataSet2, lineDataSet3);

        // 设置底侧坐标轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.black));
        xAxis.setLabelCount(bean.nums1.length);
        //  xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(15f);//设置x轴标签的旋转角度
        xAxis.setGranularity(1f);
        if (bean.months == null) {
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return "";
                }
            });
        } else {
            // 如果有月份数据，则设置 X 轴的值格式化器
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    // 根据 value 获取对应位置的月份字符串，然后返回
                    int index = (int) value;
                    if (index >= 0 && index < bean.months.length) {
                        return bean.months[index];
                    } else {
                        return ""; // 如果超出了数组长度，返回空字符串
                    }
                }
            });
        }

        // 设置右侧坐标轴
        YAxis leftYAxis = chart.getAxisLeft();
        leftYAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftYAxis.setGranularityEnabled(true);
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setDrawGridLines(true);//设置是否绘制轴内的横线
        leftYAxis.setDrawAxisLine(false);//设置是否绘制Y轴
        //是否绘制等0线
        leftYAxis.setDrawZeroLine(true);
        leftYAxis.setGranularityEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        legend.setTextSize(10);
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setTextColor(Color.BLACK);
//        legend.setWordWrapEnabled(true); //自动换行


        //x轴所在位置
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setDrawGridBackground(false);
        //设置描述
        Description description = new Description();//设置描述
        description.setText("");
        chart.setDescription(description);

        //轴偏移

        // 图表交互
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
//        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
//        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
//        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
//        chart.setNoDataText("没有数据");//没有数据时显示的文字
//        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
//        chart.setLogEnabled(true);//打印日志
        chart.setData(lineData);
    }

    public static void setLineChart(LineChart chart, List<OrderFormBean.DataBean.销售年月接单数Bean> list) {
        mEntryList.clear();
        mEntryList1.clear();
        mEntryList2.clear();
        mEntryList3.clear();
        for (int i = 0; i < list.size(); i++) {
            mEntryList.add(new Entry(i, list.get(i).get订单数量(), "订单数量"));
            mEntryList1.add(new Entry(i, list.get(i).get完成数量(), "完成数量"));
            mEntryList2.add(new Entry(i, list.get(i).get延期数量(), "延期数量"));
            mEntryList3.add(new Entry(i, list.get(i).get报废数量(), "报废数量"));
            sValue1 = sValue1 + list.get(i).get订单数量();
            sValue2 = sValue2 + list.get(i).get完成数量();
            sValue3 = sValue3 + list.get(i).get延期数量();
            sValue4 = sValue4 + list.get(i).get报废数量();
        }

        //数据集
        LineDataSet lineDataSet = getLineDataSet(mEntryList, R.color.blue_l);
        LineDataSet lineDataSet1 = getLineDataSet(mEntryList1, R.color.yellow_l);
        LineDataSet lineDataSet2 = getLineDataSet(mEntryList2, R.color.green_l);
        LineDataSet lineDataSet3 = getLineDataSet(mEntryList3, R.color.red_l);
        //设置点击交叉线
        lineDataSet.setHighlightEnabled(true);//是否禁用点击高亮线

        List<LineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.clear();

        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);

        LineData lineData = new LineData(lineDataSet, lineDataSet1, lineDataSet2, lineDataSet3);

        // 设置底侧坐标轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.black));
        xAxis.setLabelCount(list.size());
        //  xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(15f);//设置x轴标签的旋转角度
        xAxis.setGranularity(1f);


        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return list.get((int) value).get销售年月().substring(list.get((int) value).get销售年月().length() - 2) + "月";
            }
        });


        // 设置右侧坐标轴
        YAxis leftYAxis = chart.getAxisLeft();
        leftYAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftYAxis.setGranularityEnabled(true);
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setDrawGridLines(true);//设置是否绘制轴内的横线
        leftYAxis.setDrawAxisLine(false);//设置是否绘制Y轴
        //是否绘制等0线
        leftYAxis.setDrawZeroLine(true);
        leftYAxis.setGranularityEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(true);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTextSize(10);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextColor(Color.BLACK);
        legend.setWordWrapEnabled(true); //自动换行

        LegendEntry entry = new LegendEntry("订单 " + sValue1, Legend.LegendForm.CIRCLE, 10f,
                10f, null, getContext().getResources().getColor(R.color.blue_l));
        LegendEntry entry1 = new LegendEntry("完成 " + sValue2, Legend.LegendForm.CIRCLE, 10f,
                10f, null, getContext().getResources().getColor(R.color.yellow_l));
        LegendEntry entry2 = new LegendEntry("延期 " + sValue3, Legend.LegendForm.CIRCLE,
                10f, 10f, null, getContext().getResources().getColor(R.color.green_l));
        LegendEntry entry3 = new LegendEntry("报废 " + sValue4, Legend.LegendForm.CIRCLE,
                10f, 10f, null, getContext().getResources().getColor(R.color.red_l));

        LegendEntry[] legendEntries = {entry, entry1, entry2, entry3};
        legend.setCustom(legendEntries);

        chart.setExtraOffsets(10, 10, 10, 10);
        chart.setDragEnabled(true);
        chart.setDragDecelerationFrictionCoef(105f);

        //x轴所在位置
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setDrawGridBackground(false);
        //设置描述
        Description description = new Description();//设置描述
        description.setText("");
        chart.setDescription(description);

        //轴偏移

        // 图表交互
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
//        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
//        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
//        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
//        chart.setNoDataText("没有数据");//没有数据时显示的文字
//        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
//        chart.setLogEnabled(true);//打印日志
        chart.setData(lineData);
    }


    @NotNull
    private static LineDataSet getLineDataSet(ArrayList<Entry> mEntryList, int Color) {
        LineDataSet lineDataSet = new LineDataSet(mEntryList, "");
        lineDataSet.setLineWidth(1.5f);//设置线的宽度
        lineDataSet.setValueTextSize(9f);//设置显示值的文字大小
        lineDataSet.setColors(getContext().getResources().getColor(Color)); //设置线的颜色
        lineDataSet.setCircleRadius(3.5f); //设置焦点圆心的大小
        lineDataSet.setDrawCircles(false);
        lineDataSet.setValueTextColor(android.R.color.transparent);
        lineDataSet.setCircleColors(getContext().getResources().getColor(Color));// 设置焦点圆心的颜色
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        // 设置曲线下面的填充色
//        lineDataSet.setDrawFilled(true);
//        lineDataSet.setFillColor(getContext().getResources().getColor(Color));
        return lineDataSet;
    }


    public static void setDeviceStatus(PieChart chart, ArrayList<PieEntry> mPieEntryList, int[] colors) {

        PieDataSet pieDataSet = new PieDataSet(mPieEntryList, "加工机台状态");
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(0);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0);


        chart.setDrawCenterText(false);

        chart.setData(pieData);
        chart.setHighlightPerTapEnabled(false);//设置piecahrt图表点击Item高亮是否可用
        chart.setDrawCenterText(true); //设置中心圆文字
        chart.setEntryLabelTextSize(0);
        chart.setHoleRadius(85f); //改变圆环大小通过设置内圆

        Description description = new Description();//设置描述
        description.setText("");
        chart.setDescription(description);

        chart.setTransparentCircleAlpha(0);
        chart.setRotationEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setYOffset(-30f);
//        legend.setXOffset(15f);
//        legend.setFormToTextSpace(5f);//设置图例到饼状图的距离
//        legend.setForm(Legend.LegendForm.SQUARE);//设置图例的图形样式,默认为圆形
//        legend.setFormSize(15f);//设置图例的大小
//        legend.setTextSize(12);

//        chart.setExtraOffsets(60, 0, 60, 65);

        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.animateXY(1000, 1000);


        chart.invalidate();


    }

    public static void setPieChart(PieChart chart, int[] arr, int[] colors) {
        mPieEntryList.clear();

        for (int i = 0; i < arr.length; i++) {
            PieEntry pieEntry = new PieEntry(arr[i], OrderFormAdapter.PieBarString[i]);
            mPieEntryList.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(mPieEntryList, "加工机台状态");
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0);
        chart.setData(pieData);
        chart.setHighlightPerTapEnabled(false);//设置piecahrt图表点击Item高亮是否可用
        chart.setDrawCenterText(false);
        chart.setCenterTextSize(0);
        chart.setCenterTextColor(Color.BLACK);
        chart.setEntryLabelTextSize(0);
        chart.setHoleRadius(65f); //改变圆环大小通过设置内圆

        Description description = new Description();//设置描述
        description.setText("");
        chart.setDescription(description);

        chart.setTransparentCircleAlpha(0);
        chart.setRotationEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setYOffset(-30f);
//        legend.setXOffset(15f);
//        legend.setFormToTextSpace(5f);//设置图例到饼状图的距离
//        legend.setForm(Legend.LegendForm.SQUARE);//设置图例的图形样式,默认为圆形
//        legend.setFormSize(15f);//设置图例的大小
//        legend.setTextSize(12);

//        chart.setExtraOffsets(60, 0, 60, 65);

        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        //   chart.animateXY(1000, 1000);


        chart.invalidate();
    }

    public static void setPieChart2(PieChart chart, List<Integer> arr,int sum, int[] colors) {
        mPieEntryList.clear();
        sValue1 = 0;

        for (int i = 0; i < arr.size(); i++) {
            PieEntry pieEntry = new PieEntry(arr.get(i), "");
            mPieEntryList.add(pieEntry);
        }
        List<Integer> intList = Arrays.asList(StringUtil.toIntegerArray(colors));
        List<Integer> colors2 = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE);

        PieDataSet  pieDataSet = new PieDataSet (mPieEntryList, "加工机台状态");
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(0f);
        pieDataSet.setValueLineVariableLength(true);
        pieDataSet.setValueLineColor(Color.BLACK);
        pieDataSet.setValueLinePart1OffsetPercentage(100f); // 调整连接线第一部分的偏移百分比，起始位置在扇形的上方
        pieDataSet.setValueLinePart1Length(1.5f); // 调整连接线第一部分的长度，相对于饼图半径的比例
        pieDataSet.setValueLinePart2Length(0.7f); // 调整连接线第二部分的长度，相对于饼图半径的比例
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(13f);



        pieData.setValueTextColors(intList);

        List<String> additionalDataList = Arrays.asList(
                "●设备运行", "▲设备闲置", "■设备检修");

        pieData.setValueFormatter(new ValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                // 获取当前索引对应的附加内容
                String additionalData = additionalDataList.get(sValue1);

                // 构建最终字符串，使用 \n 来表示换行
                String formattedValue = additionalData +
                        StringUtil.DoubleToString(value) + "台\n\n占比" +
                        StringUtil.DoubleToString((double) (value / sum) * 100) + "%";

                // 更新索引，循环使用附加内容列表
                sValue1 = (sValue1 + 1) % additionalDataList.size();

                return formattedValue;
            }
        });


        chart.setHoleRadius(0f);

        chart.setData(pieData);
        chart.setHighlightPerTapEnabled(false);
        chart.setDrawCenterText(false); // Do not draw text in the center of the pie chart

        chart.getDescription().setEnabled(false);

        chart.setTransparentCircleAlpha(0);
        chart.setRotationEnabled(false);

        Description description = new Description();//设置描述
        description.setText("");
        chart.setDescription(description);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.highlightValues(null);
        chart.setTouchEnabled(true);
        chart.setDragDecelerationEnabled(true);
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。

        chart.setExtraOffsets(10f, 10f, 10f, 10f);

        // Animate the chart
        chart.animateXY(1000, 1000);
    }


    public static void setBarChart(BarChart chart, List<OrderFormBean.DataBean.近一年业务接单数Bean> list) {
        if (mBarEntryList.size() > 0) {
            mBarEntryList.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get组别名称().contains("国际")) {
                mBarEntryList.add(new BarEntry(0, list.get(i).get订单数量()));
            } else if (list.get(i).get组别名称().contains("国内")) {
                mBarEntryList.add(new BarEntry(1, list.get(i).get订单数量()));
            } else if (list.get(i).get组别名称().contains("日本")) {
                mBarEntryList.add(new BarEntry(2, list.get(i).get订单数量()));
            }
        }

        //2，BarDataSet 柱子
        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "");

        //3，把BarDataSet数据添加到IBarDataSet集合
//        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
//        iBarDataSets.add(barDataSet);

        //设置x、y轴
        XAxis xAxis = chart.getXAxis();//获取X轴

        //设置X轴的值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    return "国际业务部";
                } else if (value == 1) {
                    return "国内业务部";
                } else if (value == 2) {
                    return "日本业务部";
                } else {
                    return "";
                }
            }
        });

        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴

        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        // 图表交互
        common_bar_chart(chart, barDataSet, xAxis, leftAxis, COLORS);
    }

    private static List<String> departList = new ArrayList<>();



    public static void setBarChartOrderNum(BarChart chart, List<OrderStatBean.DataBean> list) {
        if (mBarEntryList.size() > 0) {
            mBarEntryList.clear();
        }
        if (departList.size() > 0) {
            departList.clear();
        }

        int glJkSum = 0;
        int rbJkSum = 0;
        int rbWJSum = 0;
        int gJJkSum = 0;
        int gjwjSum = 0;

        for (int i = 0; i < list.size(); i++) {
            if (!"0".equals(list.get(i).订单制作组别)) {
                String part = list.get(i).订单制作组别;
                String[] parts = part.split("\\.");
                if (parts[0].equals("国内业务部") && parts[1].contains("机壳组")) {
                    glJkSum = glJkSum + Integer.parseInt(list.get(i).订单数量);
                }
                if (parts[0].equals("国内业务部") && parts[1].equals("汽车事业部")) {
                    mBarEntryList.add(new BarEntry(1, Integer.parseInt(list.get(i).订单数量)));
                }
                if (parts[0].equals("国内业务部") && parts[1].equals("数码组")) {
                    mBarEntryList.add(new BarEntry(2, Integer.parseInt(list.get(i).订单数量)));
                }
                if (parts[0].equals("国内业务部") && parts[1].contains("五金组")) {
                    mBarEntryList.add(new BarEntry(3, Integer.parseInt(list.get(i).订单数量)));
                }
                if (parts[0].equals("日本业务部") && parts[1].contains("机壳组")) {
                    rbJkSum = rbJkSum + Integer.parseInt(list.get(i).订单数量);
                }
                if (parts[0].equals("日本业务部") && parts[1].contains("五金")) {
                    rbWJSum = rbWJSum + Integer.parseInt(list.get(i).订单数量);
                }
                if (parts[0].equals("国际业务部") && parts[1].contains("机壳组")) {
                    gJJkSum = gJJkSum + Integer.parseInt(list.get(i).订单数量);
                }

                if (parts[0].equals("国际业务部") && parts[1].equals("模具组")) {
                    mBarEntryList.add(new BarEntry(7, Integer.parseInt(list.get(i).订单数量)));
                }
                if (parts[0].equals("国际业务部") && parts[1].equals("数码组")) {
                    mBarEntryList.add(new BarEntry(8, Integer.parseInt(list.get(i).订单数量)));
                }
                if (parts[0].equals("国际业务部") && parts[1].contains("五金")) {
                    gjwjSum = gjwjSum + Integer.parseInt(list.get(i).订单数量);
                }
            }
        }
        departList.add("国内机壳组");
        departList.add("国内汽车部");
        departList.add("国内数码组");
        departList.add("国内五金组");
        departList.add("日本机壳组");
        departList.add("日本五金组");
        departList.add("国际机壳组");
        departList.add("国际模具组");
        departList.add("国际数码组");
        departList.add("国际五金组");

        mBarEntryList.add(new BarEntry(0, glJkSum));
        mBarEntryList.add(new BarEntry(4, rbJkSum));
        mBarEntryList.add(new BarEntry(5, rbWJSum));
        mBarEntryList.add(new BarEntry(6, gJJkSum));
        mBarEntryList.add(new BarEntry(9, gjwjSum));

        //2，BarDataSet 柱子
        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "");
        //设置x、y轴
        XAxis xAxis = chart.getXAxis();//获取X轴

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴

        common_bar_chart3(chart, barDataSet, xAxis, leftAxis, COLORS3, departList);
    }

    public static void setBarChart2(BarChart chart,List<SRBean.DataBean> chatList){
        if (mBarEntryList.size() > 0) {
            mBarEntryList.clear();
        }
        if (departList.size() > 0) {
            departList.clear();
        }
        LatteLogger.d("testChatList",GsonBuildUtil.GsonBuilder(chatList));

        for (int i = 0; i < chatList.size(); i++) {
            mBarEntryList.add(new BarEntry(i,chatList.get(i).count));
            departList.add(chatList.get(i).title);
        }


        //2，BarDataSet 柱子
        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "");
        barDataSet.setColors(getContext().getResources().getColor(R.color.red_l));
        barDataSet.setValueTextSize(12f);
        dataSets.add(barDataSet);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextColor(getContext().getResources().getColor(R.color.red_l));
        BarData data = new BarData(dataSets);

        data.setBarWidth(0.2f);
        chart.setData(data);

        XAxis xAxis = chart.getXAxis();//获取X轴

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;

                if (index >= 0 && index < departList.size()) {
                    String label = departList.get(index);
                    return label;
                }
                return String.valueOf(index);
            }
        });


        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        common_bar_chart5(chart,   xAxis, leftAxis,  departList);

    }

    private static void common_bar_chart5(BarChart chart,XAxis xAxis, YAxis leftAxis,  List<String> departList) {
        xAxis.setDrawLabels(true); // 隐藏标签
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setDrawAxisLine(true); // 隐藏轴线
        xAxis.setAxisLineWidth(1f);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.red_l));
        xAxis.setDrawGridLines(false); // 隐藏网格线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(departList.size());

//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//                int index = (int) value;
//                LatteLogger.d("getAxisLabel",index);
//                if (index >= 0 && index < departList.size()) {
//                    String label = departList.get(index);
//                    return label;
//                }
//                return "";
//            }
//        });

        // 自定义X轴标签的绘制
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
//        leftAxis.setLabelCount(departList.size(), false);
//        leftAxis.setSpaceTop(100);
//        leftAxis.setAxisMinimum(0);
//        leftAxis.setAxisMaximum(leftAxis.getAxisMaximum() + 100);
        leftAxis.setGranularity(1f);
        leftAxis.setTextSize(11f);
        leftAxis.setTextColor(Color.BLACK);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        chart.setExtraOffsets(0, 50, 50, 50);
        //不显示图表网格
        chart.setDrawGridBackground(false);
        //背景阴影
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志

        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private static void common_bar_chart4(BarChart chart, XAxis xAxis, YAxis leftAxis) {
        xAxis.setDrawLabels(false); // 隐藏标签
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setDrawAxisLine(true); // 隐藏轴线
        xAxis.setAxisLineWidth(1f);
        xAxis.setDrawGridLines(false); // 隐藏网格线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        // 自定义X轴标签的绘制
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setLabelCount(10, false);
        leftAxis.setSpaceTop(300);
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(leftAxis.getAxisMaximum() + 10000);
        leftAxis.setGranularity(11f);
        leftAxis.setTextSize(10f);
        leftAxis.setTextColor(getContext().getResources().getColor(R.color.red_l));
        ValueFormatter yAxisFormatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                DecimalFormat format = new DecimalFormat("¥#,###");
                int intValue = (int) value;
                float valueInMillion = intValue / 10000f;
                return format.format(valueInMillion);
            }
        };

        leftAxis.setValueFormatter(yAxisFormatter);


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        chart.setExtraOffsets(10, 0, 10, 20);
//        不显示图表网格
        chart.setDrawGridBackground(false);
        //背景阴影
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(false); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(false); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志

        chart.animateXY(2000, 2000);
        chart.invalidate();
    }


    private static void common_bar_chart3(BarChart chart, BarDataSet barDataSet, XAxis xAxis, YAxis leftAxis, int[] colors, List<String> departList) {
        barDataSet.setColors(colors);
        barDataSet.setDrawValues(true);

        //4,柱状集所有设置 BarData 中 可以完成
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f); //柱状图宽度
        barData.setValueTextColor(Color.BLACK);
        barData.setValueTextSize(11f);
        chart.setData(barData);


//        xAxis.setLabelRotationAngle(90f); // 调整标签的旋转角度
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setEnabled(true);
//        xAxis.setDrawAxisLine(true);
//        xAxis.setDrawGridLines(false);
//        xAxis.setLabelCount(departList.size(),false);
//
//        xAxis.setTextSize(11f);
////        xAxis.setGranularityEnabled(false);
////        xAxis.setGranularity(1f);
//        xAxis.setYOffset(8f);
//        xAxis.setXOffset(-8f);
//
//
//        xAxis.setTextColor(getContext().getResources().getColor(R.color.black));
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//                int index = (int) value;
//                if (index >= 0 && index < departList.size()) {
//                    String label = departList.get(index);
//                    return  label;
//                }
//                return "";
//            }
//        });

        xAxis.setDrawLabels(false); // 隐藏标签
        xAxis.setDrawAxisLine(true); // 隐藏轴线
        xAxis.setAxisLineWidth(1f);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setDrawGridLines(false); // 隐藏网格线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // 自定义X轴标签的绘制
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setLabelCount(departList.size(), false);
        leftAxis.setSpaceTop(300);
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(leftAxis.getAxisMaximum() + 100);
        leftAxis.setGranularity(11f);
        leftAxis.setTextSize(11f);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

//        chart.setExtraOffsets(10, 0, 0, 10);
        //不显示图表网格
        chart.setDrawGridBackground(false);
        //背景阴影
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志

        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    static double glSum, glSum2, glSum3, glSum4, glSum5, glSum6, glSum7, glSum8;
    static double gfSum, gfSum2, gfSum3, gfSum4, gfSum5;
    static double jpSum, jpSum2, jpSum3, jpSum4, jpSum5, jpSum6;

    public static void setWFBarChartOrderNum(BarChart chart, List<SendOutBean.DataBean> list) {
        if (mBarEntryList.size() > 0) {
            mBarEntryList.clear();
        }
        if (departList.size() > 0) {
            departList.clear();
        }


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("汽车事业部")) {
                glSum = glSum + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("机壳组")) {
                glSum2 = glSum2 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("数码组")) {
                glSum3 = glSum3 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("国际机壳组")) {
                glSum4 = glSum4 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("国内数码组")) {
                glSum5 = glSum5 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("国际数码组")) {
                glSum6 = glSum6 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("国际五金组")) {
                glSum7 = glSum7 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国内业务部") && list.get(i).制作组别.equals("日本机壳组")) {
                glSum8 = glSum8 + list.get(i).报价金额;
            }

            if (list.get(i).业务组别.equals("国际业务部") && list.get(i).制作组别.equals("五金组")) {
                gfSum = gfSum + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国际业务部") && list.get(i).制作组别.equals("模具组")) {
                gfSum2 = gfSum2 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国际业务部") && list.get(i).制作组别.equals("数码组")) {
                gfSum3 = gfSum3 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国际业务部") && list.get(i).制作组别.equals("机壳组")) {
                gfSum4 = gfSum4 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("国际业务部") && list.get(i).制作组别.equals("国际机壳组")) {
                gfSum5 = gfSum5 + list.get(i).报价金额;
            }

            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("机壳组")) {
                jpSum = jpSum + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("五金组")) {
                jpSum2 = jpSum2 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("国际数码组")) {
                jpSum3 = jpSum3 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("汽车事业部")) {
                jpSum4 = jpSum4 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("日本五金组")) {
                jpSum5 = jpSum5 + list.get(i).报价金额;
            }
            if (list.get(i).业务组别.equals("日本业务部") && list.get(i).制作组别.equals("日本机壳组")) {
                jpSum6 = jpSum6 + list.get(i).报价金额;
            }
        }
        mBarEntryList.add(new BarEntry(0, (float) glSum));
        mBarEntryList.add(new BarEntry(1, (float) glSum2));
        mBarEntryList.add(new BarEntry(2, (float) glSum3));
        mBarEntryList.add(new BarEntry(3, (float) glSum4));
        mBarEntryList.add(new BarEntry(4, (float) glSum5));
        mBarEntryList.add(new BarEntry(5, (float) glSum6));
        mBarEntryList.add(new BarEntry(6, (float) glSum7));
        mBarEntryList.add(new BarEntry(7, (float) glSum8));

        mBarEntryList.add(new BarEntry(8, (float) gfSum));
        mBarEntryList.add(new BarEntry(9, (float) gfSum2));
        mBarEntryList.add(new BarEntry(10, (float) gfSum3));
        mBarEntryList.add(new BarEntry(11, (float) gfSum4));
        mBarEntryList.add(new BarEntry(12, (float) gfSum5));

        mBarEntryList.add(new BarEntry(13, (float) jpSum));
        mBarEntryList.add(new BarEntry(14, (float) jpSum2));
        mBarEntryList.add(new BarEntry(15, (float) jpSum3));
        mBarEntryList.add(new BarEntry(16, (float) jpSum4));
        mBarEntryList.add(new BarEntry(17, (float) jpSum5));
        mBarEntryList.add(new BarEntry(18, (float) jpSum6));

        //2，BarDataSet 柱子
        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "");
        barDataSet.setColors(getContext().getResources().getColor(R.color.red_l));
        barDataSet.setValueTextSize(10f);
        dataSets.add(barDataSet);
        barDataSet.setDrawValues(false);
        BarData data = new BarData(dataSets);

        data.setBarWidth(0.9f);
        chart.setData(data);


        //设置x、y轴
        XAxis xAxis = chart.getXAxis();//获取X轴


        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴

        XYMarkerView mv = new XYMarkerView(getContext(), R.color.white, R.color.black);

        mv.setChartView(chart);
        chart.setMarker(mv);

        common_bar_chart4(chart, xAxis, leftAxis);


    }


    static String[] deviceTimes = {"00:00:00", "03:00:00", "06:00:00", "09:00:00", "12:00:00", "15:00:00", "18:00:00", "21:00:00", "23:59:59"};

    /**
     * 设备状态次数和时间
     *
     * @param chart
     * @param bean
     */
    public static void setStatusChart(LineChart chart, DeviceUserBean bean) {
        LineData data = chart.getData();
        if (data == null) {
            data = new LineData();
            chart.setData(data);
        }
        ILineDataSet set = data.getDataSetByIndex(0);
        if (set == null) {
            set = getLineDataSet(null, Color.BLACK);
            data.addDataSet(set);
        }

        for (int i = 0; i < bean.getAllList().size(); i++) {


        }
    }

    /**
     * 双柱状图
     *
     * @param chart
     * @param dataList
     */
    public static void setMeterBarChart(BarChart chart, ArrayList<MeterManagerBean.DataBean> dataList) {
        Typeface mTf = Typeface.createFromAsset(getContext().getAssets(), "font/pingfangsc_medium.ttf");

        mBarEntryList.clear();
        mBarEntryList2.clear();
        String sValue1 = "";
        String sValue2 = "";
        String sValue3 = "";

        float sOne = 0;
        float sTwo = 0;
        float sThree = 0;
        int max = 0;

        //1，mBarEntryList 初始化数据
        for (int i = 0; i < dataList.size(); i++) {
            MeterManagerBean.DataBean dataBean = dataList.get(i);
            if (dataBean.getElename().contains("第一排")) {
                sOne = Float.parseFloat(dataBean.getUsingenery());
                sValue1 = dataBean.getCurrentenergy();

            } else if (dataBean.getElename().contains("第二排")) {
                sTwo = Float.parseFloat(dataBean.getUsingenery());
                sValue2 = dataBean.getCurrentenergy();
            } else if (dataBean.getElename().contains("第三排")) {
                sThree = Float.parseFloat(dataBean.getUsingenery());
                sValue3 = dataBean.getCurrentenergy();
            }
        }
        mBarEntryList.add(new BarEntry(0, sOne));
        mBarEntryList.add(new BarEntry(1, sTwo));
        mBarEntryList.add(new BarEntry(2, sThree));

        for (int i = 0; i < mBarEntryList.size(); i++) {
            if (max < mBarEntryList.get(i).getY()) {
                max = (int) mBarEntryList.get(i).getY();
            }
        }

        BarDataSet barDataSet = new BarDataSet(mBarEntryList, "使用电量");

        //设置x、y轴
        XAxis xAxis = chart.getXAxis();//获取X轴
        //设置X轴的值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    return "1排";
                } else if (value == 1) {
                    return "2排";
                } else if (value == 2) {
                    return "3排";
                } else {
                    return "";
                }
            }
        });

        barDataSet.setColor(MyApplication.getContext().getResources().getColor(R.color.blue_l_l));
        barDataSet.setDrawValues(false);

        //4,柱状集所有设置 BarData 中 可以完成
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.2f); //柱状图宽度
        barData.setDrawValues(false);
        chart.setData(barData);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(getContext().getResources().getColor(R.color.grey_l_l));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setTypeface(mTf);
        xAxis.setTextColor(getContext().getResources().getColor(R.color.grey_l_l));
        xAxis.setTextSize(12f);

        YAxis leftAxis = chart.getAxisLeft();// 获取Y轴
        leftAxis.setEnabled(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        // chart.setExtraOffsets(10, 0, 0, 10);
        //不显示图表网格
        chart.setDrawGridBackground(false);
        chart.getLegend().setEnabled(false);
        //背景阴影
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(false);// 设置是否可以触摸
        chart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认为true
        chart.setDragEnabled(false); // 是否可以拖拽
        chart.setHighlightPerDragEnabled(true); // 能否拖拽高亮线(数据点与坐标的提示线)，默认为true
        chart.setDragDecelerationEnabled(true); // 拖拽滚动时，手放开是否会持续滚动，默认为true（false：拖到哪是哪，true：停止拖拽之后还会有缓冲）
        chart.setDragDecelerationFrictionCoef(0.99f);// 与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        chart.setNoDataText("没有数据");//没有数据时显示的文字
        chart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        chart.setLogEnabled(true);//打印日志

        chart.animateXY(2000, 2000);
        chart.invalidate();
    }


}





























