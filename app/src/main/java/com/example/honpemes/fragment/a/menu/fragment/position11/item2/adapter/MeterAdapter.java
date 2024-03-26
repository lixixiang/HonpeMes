package com.example.honpemes.fragment.a.menu.fragment.position11.item2.adapter;

import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.bean.MeterManagerBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.Util;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lixixiang on 2023/3/20 10:32
 */
public class MeterAdapter extends BaseMultiItemQuickAdapter<MeterManagerBean.DataBean, BaseViewHolder> {

    private String[] headMeterTitle = {"", "名称", "使用电量(度)", "电表累计(度)", "使用比率"};
    private String[] headMeterTitle2 = {"日期", "时间", "使用电量(度)", "电表累计(度)", "日合计(度)"};


    private TextView tvBarTotal;
    private ArrayList<MeterManagerBean.DataBean> dataList1 = new ArrayList<>();
    private ArrayList<MeterManagerBean.DataBean> dataList2 = new ArrayList<>();
    private ArrayList<MeterManagerBean.DataBean> dataList3 = new ArrayList<>();

    public MeterAdapter() {
        addItemType(HomeBean.TYPE_1, R.layout.item_bar_chart);
        addItemType(HomeBean.TYPE_2, R.layout.item_radio_list);


    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MeterManagerBean.DataBean bean) {
        switch (holder.getItemViewType()) {
            case HomeBean.TYPE_1:
                tvBarTotal = holder.getView(R.id.tv_bar_total);

                holder.setText(R.id.tv_bar_title, bean.getElename());
                BarChart barChart = holder.getView(R.id.m_bar_chart);
                LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dp2px(120));
                barChart.setLayoutParams(barParams);


                ChartUtil.setMeterBarChart(barChart, bean.getDataBeanArrayList());

                RecyclerView rvItemBar = holder.getView(R.id.item_bar_recyclerview);
                rvItemBar.setLayoutManager(new LinearLayoutManager(getContext()));
                MeterDetailAdapter mDetailAdapter = new MeterDetailAdapter();
                rvItemBar.setAdapter(mDetailAdapter);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, Util.dp2px(16), 0, Util.dp2px(16));
                rvItemBar.setLayoutParams(params);
                mDetailAdapter.setList(getDetail(bean.getDataBeanArrayList()));
                break;
            case HomeBean.TYPE_2:
                dataList1.clear();
                dataList2.clear();
                dataList3.clear();
                holder.setText(R.id.tv_bar_title, bean.getElename())
                        .setText(R.id.rb_one, "一排")
                        .setText(R.id.rb_two, "二排")
                        .setText(R.id.rb_three, "三排");
                tvBarTotal = holder.getView(R.id.tv_bar_total);
                LineChart lineChart = holder.getView(R.id.m_bar_chart);

                for (int i = 0; i < bean.getDataBeanArrayList().size(); i++) {
                    MeterManagerBean.DataBean dataBean = bean.getDataBeanArrayList().get(i);
                    if (dataBean.getElename().contains("一排")) {
                        dataList1.add(dataBean);
                    } else if (dataBean.getElename().contains("二排")) {
                        dataList2.add(dataBean);
                    } else if (dataBean.getElename().contains("三排")) {
                        dataList3.add(dataBean);
                    }
                }
                LatteLogger.d("tefefwe", dataList1.size());

                RadioGroup rg = holder.getView(R.id.rg);
                rg.check(R.id.rb_one);

                MeterDetailAdapter mLineAdapter = new MeterDetailAdapter();

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.rb_one:
                                mLineAdapter.setList(getLineData(dataList1));
                                ChartUtil.setMeterBarDetailLineChart(lineChart, mMeterList2);
                                break;
                            case R.id.rb_two:
                                mLineAdapter.setList(getLineData(dataList2));
                                ChartUtil.setMeterBarDetailLineChart(lineChart, mMeterList2);
                                break;
                            case R.id.rb_three:
                                mLineAdapter.setList(getLineData(dataList3));
                                ChartUtil.setMeterBarDetailLineChart(lineChart, mMeterList2);
                                break;
                        }

                        lineChart.animateXY(2000, 2000);
                        lineChart.invalidate();

                    }
                });

                RecyclerView mLineRecyclerView = holder.getView(R.id.item_line_recyclerview);
                mLineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mLineRecyclerView.setAdapter(mLineAdapter);

                if (mLineAdapter.getData().size() > 0) {
                } else {
                    mLineAdapter.setList(getLineData(dataList1));
                    ChartUtil.setMeterBarDetailLineChart(lineChart, mMeterList2);
                }

                break;
        }
    }

    ArrayList<MeterManagerBean.DataBean> mMeterList2 = new ArrayList<>();
    Map<String, ArrayList<MeterManagerBean.DataBean>> maps = new HashMap<>();

    private Collection<? extends MeterManagerBean.DataBean> getLineData(ArrayList<MeterManagerBean.DataBean> dataList) {
        mMeterList2.clear();
        maps.clear();
        maps.keySet().clear();
        MeterManagerBean.DataBean detailBean = new MeterManagerBean.DataBean();
        detailBean.setItemType(HomeBean.TYPE_3);
        detailBean.setHeadList(StringUtil.ArrToList(headMeterTitle2));
        mMeterList2.add(detailBean);

        for (int i = 0; i < dataList.size(); i++) {
            MeterManagerBean.DataBean dataBean = dataList.get(i);
            dataBean.setUsingtime(StringUtil.replaceT(dataBean.getUsingtime()));
            String strDate = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, dataBean.getUsingtime()));

            ArrayList<MeterManagerBean.DataBean> rowsEntities = maps.get(strDate);
            if (rowsEntities == null) {
                rowsEntities = new ArrayList<>();
            }

            rowsEntities.add(dataBean);
            maps.put(strDate, rowsEntities);
        }

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, ArrayList<MeterManagerBean.DataBean>>> list = new ArrayList<Map.Entry<String, ArrayList<MeterManagerBean.DataBean>>>(maps.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<MeterManagerBean.DataBean>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<MeterManagerBean.DataBean>> o1, Map.Entry<String, ArrayList<MeterManagerBean.DataBean>> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });


        List<MeterManagerBean.DataBean> addUpList = new ArrayList<>();
        addUpList.clear();
        String account = "";
        for (Map.Entry<String, ArrayList<MeterManagerBean.DataBean>> mapList : list) {
            MeterManagerBean.DataBean contentEntity = new MeterManagerBean.DataBean();
            contentEntity.setItemType(HomeBean.TYPE_4);
            contentEntity.setUsingtime(DateUtil.dd.format(DateUtil.setDate(mapList.getKey())));
            contentEntity.setUsingenery(mapList.getValue().get(mapList.getValue().size() - 1).getUsingenery());
            contentEntity.setCurrentenergy(mapList.getValue().get(mapList.getValue().size() - 1).getCurrentenergy());
            contentEntity.setAdd_up(0);

            for (int i = 0; i < mapList.getValue().size(); i++) {
                mapList.getValue().get(i).setItemType(HomeBean.TYPE_5);
                contentEntity.setTime(mapList.getValue().get(i).getUsingtime());
            }

            Collections.reverse(mapList.getValue());

            if (!account.equals(contentEntity.getCurrentenergy()) && !"".equals(account)) {
                MeterManagerBean.DataBean dataBean = new MeterManagerBean.DataBean();
                dataBean.setItemType(HomeBean.TYPE_4);
                dataBean.setAdd_up(Float.parseFloat(account) - Float.parseFloat(contentEntity.getCurrentenergy()));
                addUpList.add(dataBean);
                LatteLogger.d("tedataList", Float.parseFloat(account) + "\n" + Float.parseFloat(contentEntity.getCurrentenergy()));
            }

            if (mapList.getValue().size() == 1) {
                contentEntity.setDataBeanArrayList(mapList.getValue());
            } else if (mapList.getValue().size() > 1) {
                mapList.getValue().remove(0);
                contentEntity.setDataBeanArrayList(mapList.getValue());
            }

            account = contentEntity.getCurrentenergy();
            mMeterList2.add(contentEntity);
        }
        LatteLogger.d("addUpList", GsonBuildUtil.GsonBuilder(addUpList));
        LatteLogger.d("mMeterList2", GsonBuildUtil.GsonBuilder(mMeterList2));
        float totalCount = 0;

        for (int i = 0; i < addUpList.size(); i++) {
            totalCount = totalCount + addUpList.get(i).getAdd_up();

            if (mMeterList2.get(i + 1).getItemType() == addUpList.get(i).getItemType()) {
                mMeterList2.get(i + 1).setAdd_up(addUpList.get(i).getAdd_up());
            }
        }

        String strTotal = "日合计汇总："+StringUtil.formatDouble(totalCount)+" （度）";
        SpannableString spTotal = StringUtil.changeFontSize(strTotal, Util.sp2px(getContext(), 6), "日合计汇总：".length(), strTotal.length()-" （度）".length() );

        tvBarTotal.setText(spTotal);

        return mMeterList2;
    }

    ArrayList<MeterManagerBean.DataBean> mMeterList = new ArrayList<>();

    private Collection<? extends MeterManagerBean.DataBean> getDetail(ArrayList<MeterManagerBean.DataBean> dataList) {
        mMeterList.clear();

        MeterManagerBean.DataBean detailBean = new MeterManagerBean.DataBean();
        detailBean.setItemType(HomeBean.TYPE_1);
        detailBean.setHeadList(StringUtil.ArrToList(headMeterTitle));
        mMeterList.add(detailBean);

        String sOne = "";
        String sTwo = "";
        String sThree = "";
        String sValue1 = "";
        String sValue2 = "";
        String sValue3 = "";
        float sTotal1 = 0;
        float sTotal2 = 0;
        float sTotal3 = 0;

        //1，mBarEntryList 初始化数据
        for (int i = 0; i < dataList.size(); i++) {
            MeterManagerBean.DataBean dataBean = dataList.get(i);
            if (dataBean.getElename().contains("第一排")) {
                sOne = dataBean.getUsingenery();
                sValue1 = dataBean.getCurrentenergy();
                sTotal1 = sTotal1 + Float.parseFloat(dataBean.getUsingenery());
            } else if (dataBean.getElename().contains("第二排")) {
                sTwo = dataBean.getUsingenery();
                sValue2 = dataBean.getCurrentenergy();
                sTotal2 = sTotal2 + Float.parseFloat(dataBean.getUsingenery());
            } else if (dataBean.getElename().contains("第三排")) {
                sThree = dataBean.getUsingenery();
                sValue3 = dataBean.getCurrentenergy();
                sTotal3 = sTotal3 + Float.parseFloat(dataBean.getUsingenery());
            }
        }
        float strTotal =  Float.parseFloat(sOne) + Float.parseFloat(sTwo) + Float.parseFloat(sThree);
        float strTotal2 =  Float.parseFloat(sValue1) + Float.parseFloat(sValue2) + Float.parseFloat(sValue3);
        double strTotal3 = 0;
//        SpannableString spTotal = StringUtil.changeFontSize(strTotal, Util.sp2px(getContext(), 6), "使用合计：".length(), strTotal.length());
//
//        tvBarTotal.setText(spTotal + "度");


        for (int i = 0; i < 4; i++) {
            MeterManagerBean.DataBean dataBean = new MeterManagerBean.DataBean();
            dataBean.setItemType(HomeBean.TYPE_2);
            dataBean.setId(i + 1);
            dataBean.setElename("机壳电表");

            if (i == 0) {
                dataBean.setUsingenery(StringUtil.formatDouble(Double.parseDouble(sOne)));
                dataBean.setCurrentenergy(StringUtil.formatDouble(Double.parseDouble(sValue1)));
                dataBean.setUserRate(Float.parseFloat(sOne) / sTotal1);
                strTotal3 =strTotal3 + dataBean.getUserRate();
            } else if (i == 1) {
                dataBean.setUsingenery(StringUtil.formatDouble(Double.parseDouble(sTwo)));
                dataBean.setCurrentenergy(StringUtil.formatDouble(Double.parseDouble(sValue2)));
                dataBean.setUserRate(Float.parseFloat(sTwo) / sTotal2);
                strTotal3 =strTotal3 + dataBean.getUserRate();
            } else if (i == 2) {
                dataBean.setUsingenery(StringUtil.formatDouble(Double.parseDouble(sThree)));
                dataBean.setCurrentenergy(StringUtil.formatDouble(Double.parseDouble(sValue3)));
                dataBean.setUserRate(Float.parseFloat(sThree) / sTotal3);
                strTotal3 =strTotal3 + dataBean.getUserRate();
            } else {
                dataBean.setId(0);
                dataBean.setElename("合计");
                dataBean.setUsingenery(StringUtil.formatDouble(strTotal));
                dataBean.setCurrentenergy(StringUtil.formatDouble(strTotal2));
                dataBean.setUserRate(strTotal3);
            }


            mMeterList.add(dataBean);
        }

        return mMeterList;
    }

    public class MeterDetailAdapter extends BaseMultiItemQuickAdapter<MeterManagerBean.DataBean, BaseViewHolder> {

        public MeterDetailAdapter() {
            addItemType(HomeBean.TYPE_1, R.layout.item_weight_text);
            addItemType(HomeBean.TYPE_2, R.layout.item_weight_text);
            addItemType(HomeBean.TYPE_3, R.layout.item_weight_text2);
            addItemType(HomeBean.TYPE_4, R.layout.item_tow_type_top_title_down_list);
            addItemType(HomeBean.TYPE_5, R.layout.item_weight_text2);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, MeterManagerBean.DataBean dataBean) {
            switch (holder.getItemViewType()) {
                case HomeBean.TYPE_1:
                    TextView tv0 = holder.getView(R.id.tv_0);
                    tv0.setLayoutParams(new LinearLayout.LayoutParams(Util.dp2px(40), ViewGroup.LayoutParams.MATCH_PARENT));

                    holder.setText(R.id.tv_0, dataBean.getHeadList().get(0)).setTextColorRes(R.id.tv_0, R.color.grey_l_l).setBackgroundResource(R.id.tv_0, R.color.grey_alpha4)
                            .setText(R.id.tv_2, dataBean.getHeadList().get(1)).setTextColorRes(R.id.tv_2, R.color.grey_l_l).setBackgroundResource(R.id.tv_2, R.color.grey_alpha4)
                            .setText(R.id.tv_3, dataBean.getHeadList().get(2)).setTextColorRes(R.id.tv_3, R.color.grey_l_l).setBackgroundResource(R.id.tv_3, R.color.grey_alpha4)
                            .setText(R.id.tv_4, dataBean.getHeadList().get(3)).setTextColorRes(R.id.tv_4, R.color.grey_l_l).setBackgroundResource(R.id.tv_4, R.color.grey_alpha4)
                            .setText(R.id.tv_5, dataBean.getHeadList().get(4)).setTextColorRes(R.id.tv_5, R.color.grey_l_l).setBackgroundResource(R.id.tv_5, R.color.grey_alpha4);

                    break;
                case HomeBean.TYPE_2:
                    TextView c0 = holder.getView(R.id.tv_0);
                    if (dataBean.getId() == 0) {
                        holder.setVisible(R.id.tv_0, false);
                    } else {
                        holder.setVisible(R.id.tv_0, true);
                    }
                    c0.setLayoutParams(new LinearLayout.LayoutParams(Util.dp2px(40), ViewGroup.LayoutParams.MATCH_PARENT));
                    holder.setText(R.id.tv_0, dataBean.getId() + "排").setTextColorRes(R.id.tv_0, R.color.grey_l_l)
                            .setText(R.id.tv_2, dataBean.getElename()).setTextColorRes(R.id.tv_2, R.color.grey_l_l)
                            .setText(R.id.tv_3,dataBean.getUsingenery()).setTextColorRes(R.id.tv_3, R.color.black)
                            .setText(R.id.tv_4, dataBean.getCurrentenergy()).setTextColorRes(R.id.tv_4, R.color.black)
                            .setText(R.id.tv_5, StringUtil.formatDouble(dataBean.getUserRate() * 100) + "%").setTextColorRes(R.id.tv_5, R.color.black);
                    break;
                case HomeBean.TYPE_3:
                    holder.setText(R.id.tv_0, dataBean.getHeadList().get(0)).setText(R.id.tv_1, dataBean.getHeadList().get(1))
                            .setText(R.id.tv_2, dataBean.getHeadList().get(2)).setText(R.id.tv_3, dataBean.getHeadList().get(3))
                            .setText(R.id.tv_4, dataBean.getHeadList().get(4));
                    break;
                case HomeBean.TYPE_4:
                    holder.setText(R.id.tv_0, dataBean.getUsingtime() + "日").setTextColorRes(R.id.tv_0, R.color.black)
                            .setText(R.id.tv_1, DateUtil.hm.format(DateUtil.setDate(DateUtil.ymdhms, dataBean.getTime())))
                            .setTextColorRes(R.id.tv_1, R.color.black).setTextColorRes(R.id.tv_2, R.color.black)
                            .setTextColorRes(R.id.tv_3, R.color.black).setTextColorRes(R.id.tv_4, R.color.black)
                            .setText(R.id.tv_2, StringUtil.formatDouble(Double.parseDouble(dataBean.getUsingenery())))
                            .setText(R.id.tv_3, StringUtil.formatDouble(Double.parseDouble(dataBean.getCurrentenergy())))
                            .setText(R.id.tv_4, StringUtil.formatDouble(dataBean.getAdd_up()));

                    LinearLayout llBG = holder.getView(R.id.ll_bg);
                    LinearLayout llListDetail = holder.getView(R.id.ll_list_detail);

                    RecyclerView mRecycler = holder.getView(R.id.m_recyclerView);
                    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    MeterDetailAdapter mMeterAdapter = new MeterDetailAdapter();
                    mRecycler.setAdapter(mMeterAdapter);
                    mMeterAdapter.setList(dataBean.getDataBeanArrayList());

                    llBG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dataBean.isOpen()) {
                                dataBean.setOpen(false);
                            } else {
                                dataBean.setOpen(true);
                            }
                            llListDetail.setVisibility(dataBean.isOpen() ? View.VISIBLE : View.GONE);
                        }
                    });
                    break;
                case HomeBean.TYPE_5:
                    LatteLogger.d("tefwffwefe", GsonBuildUtil.GsonBuilder(dataBean.getDataBeanArrayList()));

                    holder.setText(R.id.tv_1, DateUtil.hm.format(DateUtil.setDate(DateUtil.ymdhms, dataBean.getUsingtime())))
                            .setText(R.id.tv_2, StringUtil.formatDouble(Double.parseDouble(dataBean.getUsingenery())))
                            .setText(R.id.tv_3, StringUtil.formatDouble(Double.parseDouble(dataBean.getCurrentenergy())))
                            .setVisible(R.id.tv_0, true).setText(R.id.tv_4, "");
                    break;
            }
        }
    }
}

























