package com.example.honpemes.fragment.a.menu.fragment.position3.item4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.bean.WorkTimeBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item4.adapter.CheckRepairAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item4.bean.CheckRepairBean;
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
 * 作者：asus on 2023/12/25 16:19
 * 注释：
 */
public class CheckRepairFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.content_recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.ll_check_repair)
    LinearLayout llBg;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    public int oneDay = 0;

    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;
    private CheckRepairAdapter mAdapter;

    public static CheckRepairFragment newInstance(Bundle bundle) {
        CheckRepairFragment fragment = new CheckRepairFragment();
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
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        tvEnd.setText("筛选");
        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd) ;
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd) ;
        curDate = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        rgDate.setVisibility(View.VISIBLE);
        llBg.setVisibility(View.VISIBLE);

        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);

        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new CheckRepairAdapter();
        recyclerview.setAdapter(mAdapter);

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }

        getHttpData();
        initPopSelector();
    }

    private void initPopSelector() {

    }

    private void getHttpData() {
        EasyHttp.post(Constants.checkRepair(firstDay, lastDay, apiToken))
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
                                CheckRepairBean bean = GsonBuildUtil.create().fromJson(s, CheckRepairBean.class);
                                LatteLogger.d("testfewf", GsonBuildUtil.GsonBuilder(bean));
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
                //  popEvaluate.showDownView(toolbar, 0.5f);
                break;
        }
    }



}


























