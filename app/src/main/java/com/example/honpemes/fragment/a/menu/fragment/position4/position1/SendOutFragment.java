package com.example.honpemes.fragment.a.menu.fragment.position4.position1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter.VerticalShowAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.adapter.SendOutLeftAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.adapter.SendOutRightAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.bean.SendOutBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.widget.HorizonScrollView;
import com.example.honpemes.widget.MListView;
import com.github.mikephil.charting.charts.BarChart;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/13 14:41
 * 注释：外发采购单
 */
public class SendOutFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;

    @BindView(R.id.tv_item)
    TextView tvItem;
    @BindView(R.id.tv_item0)
    TextView tvItem0;
    @BindView(R.id.tv_item1)
    TextView tvItem1;
    @BindView(R.id.tv_item2)
    TextView tvItem2;
    @BindView(R.id.tv_item3)
    TextView tvItem3;
    @BindView(R.id.tv_item4)
    TextView tvItem4;
    @BindView(R.id.tv_item5)
    TextView tvItem5;
    @BindView(R.id.tv_item6)
    TextView tvItem6;
    @BindView(R.id.tv_item7)
    TextView tvItem7;

    @BindView(R.id.rightTitleHorscrollView)
    HorizonScrollView rightTitleHorscrollView;
    @BindView(R.id.contentListViewLeft)
    MListView contentListViewLeft;
    @BindView(R.id.contentListViewRight)
    MListView contentListViewRight;
    @BindView(R.id.rightContentHorscrollView)
    HorizonScrollView rightContentHorscrollView;
    @BindView(R.id.contentLayout)
    LinearLayout contentLayout;
    @BindView(R.id.contentScrollView)
    ScrollView contentScrollView;

    @BindView(R.id.tv_bar_title)
    TextView tvBarTitle;
    @BindView(R.id.tv_bar_total)
    TextView tvBarTotal;
    @BindView(R.id.m_bar_chart)
    BarChart mBarChart;
    @BindView(R.id.ll_cardView)
    CardView mCardView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.re_chat_x_text)
    RecyclerView reChatXText;


    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;
    public int oneDay = 0; //这个初始日子
    private SendOutLeftAdapter mLeftAdapter;
    private SendOutRightAdapter mRightAdapter;
    private List<SendOutBean.DataBean> mList = new ArrayList<>();
    private int sWFOrderNumSum, sWFLinJianNumSum, sSHOrderNumSum, sSHOrderLinJianNumSum, sBaoJiaOrderNumSum;
    private double sBaoJiaSum;

    public static SendOutFragment newInstance(Bundle bundle) {
        SendOutFragment fragment = new SendOutFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_horizon_scroll_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd) + " 00:00:00";
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd) + " 23:59:59";
        curDate = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        rgDate.setVisibility(View.VISIBLE);
        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);

        rightTitleHorscrollView.setSyncView(rightContentHorscrollView);
        rightContentHorscrollView.setSyncView(rightTitleHorscrollView);

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }

        initTextView();
        getHttpData();
    }

    List<String> departList = new ArrayList<>();

    private void initTextView() {
        tvItem.setText("业务组别");
        tvItem0.setText("制作组别");
        tvItem1.setText("外发订单数");
        tvItem2.setText("外发零件数");
        tvItem3.setText("收货订单数");
        tvItem4.setText("收货零件数");
        tvItem5.setText("报价订单数");
        tvItem6.setText("报价金额");
        tvItem7.setVisibility(View.GONE);
        tvBarTitle.setText("报价金额（万元）");


        departList.add("国内业务部_汽车事业部");
        departList.add("国内业务部_机壳组");
        departList.add("国内业务部_数码组");
        departList.add("国内业务部_国际机壳组");
        departList.add("国内业务部_国内数码组");
        departList.add("国内业务部_国际数码组");
        departList.add("国内业务部_国际五金组");
        departList.add("国内业务部_日本机壳组");

        departList.add("国际业务部_五金组");
        departList.add("国际业务部_模具组");
        departList.add("国际业务部_数码组");
        departList.add("国际业务部_机壳组");
        departList.add("国际业务部_国际机壳组");

        departList.add("日本业务部_机壳组");
        departList.add("日本业务部_五金组");
        departList.add("日本业务部_国际数码组");
        departList.add("日本业务部_汽车事业部");
        departList.add("日本业务部_日本五金组");
        departList.add("日本业务部_日本机壳组");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(120,0,0,40);
        reChatXText.setLayoutParams(params);
        reChatXText.setLayoutManager(new GridLayoutManager(_mActivity,departList.size()));
        VerticalShowAdapter verticalShowAdapter = new VerticalShowAdapter();
        reChatXText.setAdapter(verticalShowAdapter);
        verticalShowAdapter.setList(departList);
    }


    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(oneDay);
        lastDay = DateUtil.getLastDayOfMonth(oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        tvBarTitle.setText("外发订单金额");

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getHttpData();

    }

    private List<SendOutBean.DataBean> dataBeans = new ArrayList<>();

    private void getHttpData() {
        EasyHttp.post(Constants.GetOutsourcingPurchase(firstDay, lastDay))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        SendOutBean bean = GsonBuildUtil.create().fromJson(s, SendOutBean.class);
                        LatteLogger.d("testgetHttpData", GsonBuildUtil.GsonBuilder(bean));

                        if (mList.size() > 0) {
                            mList.clear();
                        }
                        for (int i = 0; i < bean.data.size(); i++) {
                            mList.add(bean.data.get(i));
                            dataBeans.add(bean.data.get(i));
                            sWFOrderNumSum = sWFOrderNumSum + Integer.parseInt(bean.data.get(i).外发订单数);
                            sWFLinJianNumSum = sWFLinJianNumSum + Integer.parseInt(bean.data.get(i).外发零件数);
                            sBaoJiaOrderNumSum = sBaoJiaOrderNumSum + Integer.parseInt(bean.data.get(i).报价订单数);
                            sSHOrderNumSum = sSHOrderNumSum + Integer.parseInt(bean.data.get(i).确认收货订单数);
                            sSHOrderLinJianNumSum = sSHOrderLinJianNumSum + Integer.parseInt(bean.data.get(i).确认收货零件数);
                            sBaoJiaSum = sBaoJiaSum + bean.data.get(i).报价金额;
                        }

                        Collections.sort(mList, new Comparator<SendOutBean.DataBean>() {
                            @Override
                            public int compare(SendOutBean.DataBean o1, SendOutBean.DataBean o2) {
                                return o1.业务组别.compareTo(o2.业务组别);
                            }
                        });

                        SendOutBean.DataBean dataBean = new SendOutBean.DataBean();
                        dataBean.业务组别 = "";
                        dataBean.制作组别 = "合计";
                        dataBean.外发订单数 = String.valueOf(sWFOrderNumSum);
                        dataBean.外发零件数 = String.valueOf(sWFLinJianNumSum);
                        dataBean.确认收货订单数 = String.valueOf(sSHOrderNumSum);
                        dataBean.确认收货零件数 = String.valueOf(sSHOrderLinJianNumSum);
                        dataBean.报价订单数 = String.valueOf(sBaoJiaOrderNumSum);
                        dataBean.报价金额 = sBaoJiaSum;

                        mList.add(dataBean);

                        if (mLeftAdapter != null) {
                            mLeftAdapter = null;
                        }
                        if (mRightAdapter != null) {
                            mRightAdapter = null;
                        }

                        mLeftAdapter = new SendOutLeftAdapter(_mActivity, 0, mList);
                        mRightAdapter = new SendOutRightAdapter(_mActivity, 0, mList);
                        contentListViewLeft.setAdapter(mLeftAdapter);
                        contentListViewRight.setAdapter(mRightAdapter);

                        mCardView.setVisibility(View.VISIBLE);

                        ChartUtil.setWFBarChartOrderNum(mBarChart, bean.data);


                    }
                });

    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
            case R.id.tv_end:

                break;
        }
    }
}
