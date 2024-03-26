package com.example.honpemes.fragment.a.menu.fragment.position5.item3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.adapter.OutBoardAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.adapter.OutBoardHeadAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.bean.OutBoardBean;
import com.example.honpemes.utils.DateUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 出库看板
 */
public class OutBoardFragment extends BaseBackFragment {
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
    @BindView(R.id.head_horizon_recyclerview)
    RecyclerView headHorizonRecyclerview;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private String curMonth, firstDay, lastDay, curDate;
    public int oneDay = 0; //这个初始日子
    private OutBoardHeadAdapter mHeadAdapter;

    private String[] headTitleList = {"日期", "调出仓", "数量", "产品", "编码", "仓管"};
    private List<OutBoardBean> headList = new ArrayList<>();

    private OutBoardAdapter mContentAdapter;

    public static OutBoardFragment newInstance(Bundle bundle) {
        OutBoardFragment fragment = new OutBoardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
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

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        initRecyclerview();
        getHttpData();
    }


    private void getHttpData() {

    }

    private void initRecyclerview() {
        headHorizonRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mHeadAdapter = new OutBoardHeadAdapter();
        headHorizonRecyclerview.setAdapter(mHeadAdapter);

        mHeadAdapter.setList(initHeadData());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mContentAdapter = new OutBoardAdapter();
        mRecyclerView.setAdapter(mContentAdapter);

        mContentAdapter.setList(getContentData());
    }

    private Collection<? extends OutBoardBean> getContentData() {
        List<OutBoardBean> mFirstList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mFirstList.add(new OutBoardBean("2024-02-12", "3号仓", "27", "MTQB200-HS2F65", "CPO0075", "左明微"));
        }
        return mFirstList;
    }

    /**
     * 头部标题
     *
     * @return
     */
    private Collection<? extends OutBoardBean> initHeadData() {
        OutBoardBean boardBean = new OutBoardBean(headTitleList[0],headTitleList[1],headTitleList[2],headTitleList[3],headTitleList[4],headTitleList[5]);
        headList.add(boardBean);
        return headList;
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

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(oneDay) + " 00:00:00";
        lastDay = DateUtil.getLastDayOfMonth(oneDay) + " 23:59:59";
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
}