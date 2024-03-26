package com.example.honpemes.fragment.a.menu.fragment.position2.item6;

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
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.adapter.WorkTimerAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.bean.WorkTimeBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item6.adapter.QualityAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item6.bean.QualityBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/13 14:18
 * 注释：产品品质
 */
public class QualityFragment extends BaseBackFragment {
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

    @BindView(R.id.content_recyclerView)
    RecyclerView contentRecyclerView;
    @BindView(R.id.ll_head_bg)
    LinearLayout llHeadBg;
    @BindView(R.id.tv_part)
    TextView tv1;
    @BindView(R.id.tv_team)
    TextView tv2;
    @BindView(R.id.tv_operator)
    TextView tv3;
    @BindView(R.id.tv_finish_num)
    TextView tv4;
    @BindView(R.id.tv_work_time)
    TextView tv5;

    private String curMonth, firstDay, lastDay;

    public int oneDay = 0; //这个初始日子
    private String[] titles = {"部门名称","生产组别","零件数","良品数","良品率"};
    private QualityAdapter mAdapter;

    public static QualityFragment newInstance(Bundle bundle) {
        QualityFragment fragment = new QualityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_content_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd) + " 00:00:00";
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd) + " 23:59:59";
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

    private void initRecyclerview() {
        llHeadBg.setVisibility(View.VISIBLE);
        tv1.setText(titles[0]);
        tv2.setText(titles[1]);
        tv3.setText(titles[2]);
        tv4.setText(titles[3]);
        tv5.setText(titles[4]);

        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new QualityAdapter();
        contentRecyclerView.setAdapter(mAdapter);


    }


    private void getHttpData() {
        EasyHttp.post(Constants.GetProductQuality(firstDay, lastDay))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        QualityBean bean = GsonBuildUtil.create().fromJson(s, QualityBean.class);
                        LatteLogger.d("testData", GsonBuildUtil.GsonBuilder(bean));
                        mAdapter.setList(bean.data);
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






















