package com.example.honpemes.fragment.a.menu.fragment.position2.item5;

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
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.adapter.WorkTimerAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.bean.WorkTimeBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/13 11:39
 * 注释：操机工时
 */
public class WorkTimeFragment extends BaseBackFragment {
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


    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;

    public int oneDay = 0; //这个初始日子
    private String[] titles = {"生产部门", "生产组别", "操机员", "总完成数", "总工时"};
    private WorkTimerAdapter mAdapter;

    public static WorkTimeFragment newInstance(Bundle bundle) {
        WorkTimeFragment fragment = new WorkTimeFragment();
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

    private void initRecyclerview() {
        llHeadBg.setVisibility(View.VISIBLE);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new WorkTimerAdapter();
        contentRecyclerView.setAdapter(mAdapter);

    }

    private void getHttpData() {
        EasyHttp.post(Constants.GetDigitalWorkingBoard(firstDay, lastDay))
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
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt("Tag") == 1) {
                                WorkTimeBean bean = GsonBuildUtil.create().fromJson(s, WorkTimeBean.class);
                                LatteLogger.d("testData", GsonBuildUtil.GsonBuilder(bean));
                                mAdapter.setList(bean.data);
                            } else {
                                if (object.getString(FinalClass.Message).contains("失效")) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                }
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




























