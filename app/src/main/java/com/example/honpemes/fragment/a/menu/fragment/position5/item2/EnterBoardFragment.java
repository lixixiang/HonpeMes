package com.example.honpemes.fragment.a.menu.fragment.position5.item2;

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

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.BoardHeadAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.BoardTreeAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;
import com.example.honpemes.utils.DateUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 产品入库
 */
public class EnterBoardFragment extends BaseBackFragment {
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
    private BoardHeadAdapter mHeadAdapter;

    private String[] headTitleList = {"日期", "入库单", "类型", "仓库", "入库员"};
    private List<BoardBean> headList = new ArrayList<>();

    private BoardTreeAdapter mContentAdapter;

    public static EnterBoardFragment newInstance(Bundle bundle) {
        EnterBoardFragment fragment = new EnterBoardFragment();
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
        mHeadAdapter = new BoardHeadAdapter();
        headHorizonRecyclerview.setAdapter(mHeadAdapter);

        mHeadAdapter.setList(initHeadData());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mContentAdapter = new BoardTreeAdapter();
        mRecyclerView.setAdapter(mContentAdapter);

        mContentAdapter.setList(getContentData());
    }

    private Collection<? extends BaseNode> getContentData() {
        List<BaseNode> mFirstList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<BaseNode> mChildList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                mChildList.add(new BoardBean.SecondBoarBean("MTQ8200-MS2F","200G-HDR-QSFP12",
                        "7","台"));
            }
            mFirstList.add(new BoardBean("2024-02-12", "RK240316-11", "采购入库", "2号仓", "戴克威", mChildList));
        }

        return mFirstList;
    }

    /**
     * 头部标题
     * @return
     */
    private Collection<? extends BoardBean> initHeadData() {
        BoardBean boardBean = new BoardBean();
        boardBean.date = headTitleList[0];
        boardBean.produceID = headTitleList[1];
        boardBean.produceType = headTitleList[2];
        boardBean.produceNum = headTitleList[3];
        boardBean.unit = headTitleList[4];
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

































