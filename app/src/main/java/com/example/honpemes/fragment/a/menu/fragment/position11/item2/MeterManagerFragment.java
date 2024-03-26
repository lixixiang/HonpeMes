package com.example.honpemes.fragment.a.menu.fragment.position11.item2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.com_adapter.ComTabAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.adapter.MeterAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.bean.MeterManagerBean;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/7 17:24
 * 电表管理
 */
public class MeterManagerFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;

    private ArrayList<MeterManagerBean.DataBean> meterDetailList = new ArrayList<>();
    private ArrayList<MeterManagerBean.DataBean> mList = new ArrayList<>();


    private MeterAdapter meterAdapter;
    private String firstDay, lastDay, curMonth;
    private int oneDay = 0; //这个初始日子
    private int count; //获取最后一次集合完整


    public static MeterManagerFragment newInstance(Bundle bundle) {
        MeterManagerFragment fragment = new MeterManagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_titleba_refresh_recyclerview;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd);
        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));

        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerview.setHasFixedSize(true);
        meterAdapter = new MeterAdapter();
        mRecyclerview.setAdapter(meterAdapter);

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }

        getInitData();
        super.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    protected void initView() {
        rgDate.setVisibility(View.VISIBLE);

    }



    private void getInitData() {
        EasyHttp.post(Constants.PowerManger(apiToken))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(getContext(),1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(getContext(),0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testInitData", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                MeterManagerBean bean = GsonBuildUtil.create().fromJson(s, MeterManagerBean.class);
                                count = 0;
                                count = bean.getData().size();
                                meterDetailList.clear();
                                for (int i = 0; i < bean.getData().size(); i++) {
                                    MeterManagerBean.DataBean dataBean = bean.getData().get(i);
                                    initData(dataBean.getElename());
                                }

                            } else {
                                if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    bundle.putString(FinalClass.title, tvTitle.getText().toString());
                                    startWithPop(LoginFragment.newInstance(bundle));
                                }
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void initData(String elename) {
        EasyHttp.post(Constants.PowerManagerDetail(apiToken, firstDay, lastDay, elename))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        count--;
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {

                                MeterManagerBean bean = GsonBuildUtil.create().fromJson(s, MeterManagerBean.class);
                                meterDetailList.addAll(bean.getData());
                                if (count == 0) {
                                    EventBusUtil.sendEvent(new Event(FinalClass.METER_DATA));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.METER_DATA:
                if (meterDetailList.size() > 0) {
                    mList.clear();

                    MeterManagerBean.DataBean dataBean1 = new MeterManagerBean.DataBean();
                    dataBean1.setItemType(HomeBean.TYPE_1);
                    dataBean1.setDataBeanArrayList(meterDetailList);
                    dataBean1.setElename(DateUtil.m.format(new Date()).replace("0", "") + "月电量汇总");
                    mList.add(dataBean1);

                    MeterManagerBean.DataBean dataBean2 = new MeterManagerBean.DataBean();
                    dataBean2.setItemType(HomeBean.TYPE_2);
                    dataBean2.setDataBeanArrayList(meterDetailList);
                    dataBean2.setElename("各排电量明细");
                    mList.add(dataBean2);

                    meterAdapter.setList(mList);

                }
                break;
        }
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth();
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth();
                break;
        }
    }

    private void setOneMonth() {
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
        getInitData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ProgressUtils.disLoadView(getContext(),0);
    }
}




























