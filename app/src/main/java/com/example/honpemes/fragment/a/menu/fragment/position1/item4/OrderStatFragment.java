package com.example.honpemes.fragment.a.menu.fragment.position1.item4;

import android.os.Bundle;
import android.view.Gravity;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter.LeftAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter.RightAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter.VerticalShowAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.bean.OrderStatBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.HorizonScrollView;
import com.example.honpemes.widget.MListView;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/8 11:02
 * 注释：订单统计
 */
public class OrderStatFragment extends BaseBackFragment {
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.tv_item)
    TextView tvItem;
    @BindView(R.id.re_chat_x_text)
    RecyclerView reChatXText;


    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;
    public int oneDay = 0; //这个初始日子
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    private List<OrderStatBean.DataBean> mList = new ArrayList<>();
    private int sOrderNumSum, sFinishNumSum, sDelayedNumSum, sScrapNum;

    public static OrderStatFragment newInstance(Bundle bundle) {
        OrderStatFragment fragment = new OrderStatFragment();
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

        getHttpData();
        getHttpEveryDayData();
        initChat();
    }

    private void getHttpEveryDayData() {
        String endDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd);
        endDay = endDay.substring(endDay.length() - 2);
        LatteLogger.d("endDayendDay", endDay);
        for (int i = 0; i < Integer.parseInt(endDay); i++) {

        }
    }

    List<String> departList = new ArrayList<>();

    private void initChat() {
        tvBarTitle.setText("各部门订单数");

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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(140,0,0,40);
        reChatXText.setLayoutParams(params);
        reChatXText.setLayoutManager(new GridLayoutManager(_mActivity,departList.size()));
        VerticalShowAdapter verticalShowAdapter = new VerticalShowAdapter();
        reChatXText.setAdapter(verticalShowAdapter);
        verticalShowAdapter.setList(departList);
    }

    List<OrderStatBean.DataBean> dataBeans = new ArrayList<>();


    private void getHttpData() {
        EasyHttp.post(Constants.GetPorductData(firstDay, lastDay))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt(FinalClass.Tag) == 0) {
                                if (object.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(object.getString(FinalClass.Message));
                                }
                            } else {
                                OrderStatBean bean = GsonBuildUtil.create().fromJson(s, OrderStatBean.class);
                                LatteLogger.d("testgetHttpData", GsonBuildUtil.GsonBuilder(bean));
                                if (mList.size() > 0) {
                                    mList.clear();
                                }
                                for (int i = 0; i < bean.data.size(); i++) {
                                    if (!"0".equals(bean.data.get(i).订单制作组别)) {
                                        mList.add(bean.data.get(i));
                                        dataBeans.add(bean.data.get(i));
                                        sOrderNumSum = sOrderNumSum + Integer.parseInt(bean.data.get(i).订单数量);
                                        sFinishNumSum = sFinishNumSum + Integer.parseInt(bean.data.get(i).完成数量);
                                        sDelayedNumSum = sDelayedNumSum + Integer.parseInt(bean.data.get(i).延迟数量);
                                        sScrapNum = sScrapNum + Integer.parseInt(bean.data.get(i).报废数量);
                                    }
                                }

                                Collections.sort(mList, new Comparator<OrderStatBean.DataBean>() {
                                    @Override
                                    public int compare(OrderStatBean.DataBean o1, OrderStatBean.DataBean o2) {
                                        String[] parts = o1.订单制作组别.split("\\.");
                                        String[] parts2 = o2.订单制作组别.split("\\.");

                                        return parts[0].compareTo(parts2[0]);
                                    }
                                });

                                OrderStatBean.DataBean dataBean = new OrderStatBean.DataBean();
                                dataBean.订单制作组别 = "合计";
                                dataBean.订单数量 = String.valueOf(sOrderNumSum);
                                dataBean.完成数量 = String.valueOf(sFinishNumSum);
                                dataBean.完成率 = StringUtil.formatDouble(Double.parseDouble(dataBean.完成数量) / Double.parseDouble(dataBean.订单数量) * 100);
                                dataBean.延迟数量 = String.valueOf(sDelayedNumSum);
                                dataBean.延迟率 = StringUtil.formatDouble((Double.parseDouble(dataBean.延迟数量) / Double.parseDouble(dataBean.订单数量)));
                                dataBean.报废数量 = String.valueOf(sScrapNum);
                                dataBean.报废率 = StringUtil.formatDouble((Double.parseDouble(dataBean.报废数量) / Double.parseDouble(dataBean.订单数量)) * 100);


                                mList.add(dataBean);

                                if (mLeftAdapter != null) {
                                    mLeftAdapter = null;
                                }
                                if (mRightAdapter != null) {
                                    mRightAdapter = null;
                                }

                                mLeftAdapter = new LeftAdapter(_mActivity, 0, mList);
                                mRightAdapter = new RightAdapter(_mActivity, 0, mList);
                                contentListViewLeft.setAdapter(mLeftAdapter);
                                contentListViewRight.setAdapter(mRightAdapter);

                                mCardView.setVisibility(View.VISIBLE);
                                ChartUtil.setBarChartOrderNum(mBarChart, bean.data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(oneDay);
        lastDay = DateUtil.getLastDayOfMonth(oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));


        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getHttpData();

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

























