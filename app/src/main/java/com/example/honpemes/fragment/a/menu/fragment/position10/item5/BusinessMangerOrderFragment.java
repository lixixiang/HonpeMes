package com.example.honpemes.fragment.a.menu.fragment.position10.item5;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.datepickview.wheelpicker.DatePicker;
import com.example.datepickview.wheelpicker.contract.OnDatePickedListener;
import com.example.datepickview.wheelpicker.entity.DateEntity;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.com_adapter.MultiSelectPopAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.TeamBean;
import com.example.honpemes.fragment.a.menu.fragment.position10.item5.adapter.BusinessAdapter;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.utils.dialog.Base.DialogUtils;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.example.honpemes.widget.pop.CommonPopupWindow;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
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

import static com.example.honpemes.api.DataClass.btnTitles;
import static com.example.honpemes.api.DataClass.editTitles;
import static com.example.honpemes.api.DataClass.madeTeams;
import static com.example.honpemes.api.DataClass.orderStatus;
import static com.example.honpemes.api.DataClass.orderTeams;
import static com.example.honpemes.utils.ProgressUtils.deleteProgress;

/**
 * Created by Lixixiang on 2023/3/8 11:10
 * 营业管理-订单
 */
public class BusinessMangerOrderFragment extends BaseBackFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;

    private String startTime, endTime, NextMonthDay;

    public int oneDay = 0; //这个初始日子
    private BusinessAdapter mAdapter;
    private CommonPopupWindow popEvaluate;
    private List<OrderEntity.DataBean> mSearchList = new ArrayList<>();
    private List<OrderEntity.DataBean> mSaveList = new ArrayList<>();
    private Collection collection1 = new ArrayList();
    private Collection collection2 = new ArrayList();
    private Collection collection3 = new ArrayList();
    private DateEntity startDateEntity = new DateEntity();
    private DateEntity endDateEntity = new DateEntity();
    private String yearMonthDay, yearMonthDay2, curMonth;
    List<String> dateList = new ArrayList<>();
    private int index;

    public static BusinessMangerOrderFragment newInstance(Bundle bundle) {
        BusinessMangerOrderFragment fragment = new BusinessMangerOrderFragment();
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
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("筛选");
        tvTitle.setText(getArguments().getString(FinalClass.title));
        rgDate.setVisibility(View.VISIBLE);
        startTime = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        endTime = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd) ;
        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }

        getRequestJsonData();
        initRecyclerView();

        initPopSelector();
        refresh();
    }

    private void refresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                endTime = DateUtil.ymd.format(new Date());
                getRequestJsonData();
                startTime = DateUtil.ymd.format(new Date());
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                index++;
                startTime = DateUtil.getIndexDateBeforeLast(startTime, DateUtil.ymd, index);
                getRequestJsonData();

                refreshLayout.finishLoadMore();
            }
        });
    }

    private void initRecyclerView() {
        String strTeamData = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_TEAM, "");
        mTeamBean = GsonBuildUtil.create().fromJson(strTeamData, TeamBean.class);
        LatteLogger.d("etewfwfw", GsonBuildUtil.GsonBuilder(mTeamBean));
        mAdapter = new BusinessAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getRequestJsonData() {
        LatteLogger.d("apiToken",startTime + "   "+endTime);
        disposable = EasyHttp.post(Constants.OrderManager(apiToken, startTime, endTime))
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
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onSuccess(String s) {

                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                mSaveList.clear();
                                OrderEntity bean = GsonBuildUtil.create().fromJson(s, OrderEntity.class);
                           //     LatteLogger.d("getRequestJsonData", GsonBuildUtil.GsonBuilder(bean));

                                for (OrderEntity.DataBean bean1 : bean.getData()) {
                                    for (int i = 0; i < mTeamBean.getData().size(); i++) {
                                        if (bean1.get订单组别().equals(mTeamBean.getData().get(i).get组别代码())) {
                                            bean1.set订单组别(mTeamBean.getData().get(i).get组别名称());
                                        }
                                        if (bean1.get制作组别().equals(mTeamBean.getData().get(i).get组别代码())) {
                                            bean1.set制作组别(mTeamBean.getData().get(i).get组别名称());
                                        }
                                    }
                                }
                                mSaveList.addAll(bean.getData());



                                mAdapter.setList(bean.getData());
                            }else {
                                if (o.getString(FinalClass.Message).contains("失效")) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                index = 0;
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                index = 0;
                oneDay++;
                setOneMonth(oneDay);
                break;
            case R.id.tv_end:
                popEvaluate.showRightView(mToolbar, 0.5f);
                break;
        }
    }


    private void initPopSelector() {
        Integer[] widthAndHeight = Util.getWidthAndHeight(_mActivity.getWindow());

        popEvaluate = new CommonPopupWindow.Builder(_mActivity)
                .setView(R.layout.css_multi_selector)
                .setWidthAndHeight(widthAndHeight[0] * 5 / 6, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)//在外不可用手指取消
                .setAnimationStyle(R.style.RightAnimation)
                .create();

        RecyclerView mPopRecyclerView = popEvaluate.getContentView().findViewById(R.id.recyclerview);
        TextView tvTitle = popEvaluate.getContentView().findViewById(R.id.tv_title);
        TextView tvSure = popEvaluate.getContentView().findViewById(R.id.tv_sure);
        TextView tvReset = popEvaluate.getContentView().findViewById(R.id.tv_reset);
        tvTitle.setText(getString(R.string.selector));
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mPopRecyclerView.setLayoutManager(manager);

        MultiSelectPopAdapter mPopAdapter = new MultiSelectPopAdapter();
        mPopRecyclerView.setAdapter(mPopAdapter);
        mPopAdapter.setList(getPopData());

        mPopAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_start:
                        if (TextUtils.isEmpty(yearMonthDay)) {
                            String newDate = tvDate.getText().toString() + "-" + DateUtil.dd.format(new Date());
                            startDateEntity = DateEntity.target(DateUtil.setDate(newDate));
                        }

                        DatePicker datePicker = DialogUtils.onYearMonthDay(_mActivity, "开始时间", DateEntity.yearOnFuture(-7),
                                DateEntity.yearOnFuture(7), startDateEntity);
                        datePicker.setOnDatePickedListener(new OnDatePickedListener() {
                            @Override
                            public void onDatePicked(int year, int month, int day) {
                                yearMonthDay = year + "-" + month + "-" + day;
                                startDateEntity = DateEntity.target(year, month, day);
                                String startTime = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymd, yearMonthDay));

                                for (int i = 0; i < mPopAdapter.getData().size(); i++) {
                                    if ("按时间区间".equals(mPopAdapter.getData().get(i).getTitle())) {
                                        mPopAdapter.getData().get(i).setStartTime(startTime);
                                    }
                                }
                                mPopAdapter.notifyItemChanged(position);
                            }
                        });

                        datePicker.show();
                        break;
                    case R.id.tv_end:
                        if (TextUtils.isEmpty(yearMonthDay2)) {
                            String newDate = tvDate.getText().toString() + "-" + DateUtil.dd.format(new Date());
                            endDateEntity = DateEntity.target(DateUtil.setDate(newDate));
                        }

                        DatePicker datePicker2 = DialogUtils.onYearMonthDay(_mActivity, "结束时间", DateEntity.yearOnFuture(-7),
                                DateEntity.yearOnFuture(7), endDateEntity);
                        datePicker2.setOnDatePickedListener(new OnDatePickedListener() {
                            @Override
                            public void onDatePicked(int year, int month, int day) {
                                yearMonthDay2 = year + "-" + month + "-" + day;
                                endDateEntity = DateEntity.target(year, month, day);
                                String startTime = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymd, yearMonthDay2));

                                for (int i = 0; i < mPopAdapter.getData().size(); i++) {
                                    if ("按时间区间".equals(mPopAdapter.getData().get(i).getTitle())) {
                                        mPopAdapter.getData().get(i).setEndTime(startTime);
                                    }
                                }
                                mPopAdapter.notifyItemChanged(position);
                            }
                        });

                        datePicker2.show();
                        break;
                }
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mPopAdapter.getData().size(); i++) {
                    if (mPopAdapter.getData().get(i).getItemType() == HomeBean.TYPE_1) {
                        mPopAdapter.getData().get(i).setStartTime("");
                        mPopAdapter.getData().get(i).setEndTime("");
                    } else if (mPopAdapter.getData().get(i).getItemType() == HomeBean.TYPE_4) {
                        mPopAdapter.getData().get(i).setContent("");
                    } else if (mPopAdapter.getData().get(i).getItemType() == HomeBean.TYPE_2) {
                        for (int j = 0; j < mPopAdapter.getData().get(i).getMenuBeanList().size(); j++) {
                            mPopAdapter.getData().get(i).getMenuBeanList().get(j).setCheck(false);
                        }
                    }
                }
                mPopAdapter.notifyDataSetChanged();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchList.clear();
                collection1.clear();
                collection2.clear();
                collection3.clear();
                dateList.clear();
                String strName = "";
                String strOrderId = "";
                String strPerson = "";
                String strStartPopDate = "";
                String strEndPopDate = "";
                for (HomeBean homeBean : mPopAdapter.getData()) {
                    if (editTitles[0].equals(homeBean.getTitle())) {
                        strName = homeBean.getContent();
                    } else if (editTitles[1].equals(homeBean.getTitle())) {
                        strOrderId = homeBean.getContent();
                    } else if (editTitles[2].equals(homeBean.getTitle())) {
                        strPerson = homeBean.getContent();
                    } else if ("按时间区间".equals(homeBean.getTitle())) {
                        strStartPopDate = homeBean.getStartTime();
                        strEndPopDate = homeBean.getEndTime();
                        if (!TextUtils.isEmpty(strStartPopDate) && TextUtils.isEmpty(strEndPopDate)) {
                            strEndPopDate = strStartPopDate;
                        } else if (TextUtils.isEmpty(strStartPopDate) && !TextUtils.isEmpty(strEndPopDate)) {
                            strStartPopDate = strEndPopDate;
                        }

                        dateList = DateUtil.getDays(strStartPopDate, strEndPopDate);
                    } else if (btnTitles[0].equals(homeBean.getTitle())) {
                        for (int i = 0; i < homeBean.getMenuBeanList().size(); i++) {
                            if (homeBean.getMenuBeanList().get(i).isCheck()) {
                                collection1.add(homeBean.getMenuBeanList().get(i).getTitle());
                            }
                        }
                    } else if (btnTitles[1].equals(homeBean.getTitle())) {
                        for (int i = 0; i < homeBean.getMenuBeanList().size(); i++) {
                            if (homeBean.getMenuBeanList().get(i).isCheck()) {
                                collection2.add(homeBean.getMenuBeanList().get(i).getTitle());
                            }
                        }
                    }
                    if (btnTitles[2].equals(homeBean.getTitle())) {
                        for (int i = 0; i < homeBean.getMenuBeanList().size(); i++) {
                            if (homeBean.getMenuBeanList().get(i).isCheck()) {
                                collection3.add(homeBean.getMenuBeanList().get(i).getTitle());
                            }
                        }
                    }
                }

                if (mSaveList.size() > 0) {
                    for (int i = 0; i < mSaveList.size(); i++) {
                        boolean isEdit1 = false;
                        boolean isEdit2 = false;
                        boolean isEdit3 = false;
                        boolean isEdit4 = false;
                        boolean isEdit5 = false;
                        boolean isEdit6 = false;
                        boolean isEdit7 = false;

                        OrderEntity.DataBean dataBean = mSaveList.get(i);
                        if (!"".equals(strName)) {
                            if (dataBean.get产品名称().contains(strName)) {
                                isEdit1 = true;
                            }
                        } else {
                            isEdit1 = true;
                        }
                        if (!"".equals(strOrderId)) {
                            if (dataBean.get生产单号().contains(strOrderId)) {
                                isEdit2 = true;
                            }
                        } else {
                            isEdit2 = true;
                        }

                        if (!"".equals(strPerson)) {
                            if (dataBean.get下单人员().contains(strPerson)) {
                                isEdit3 = true;
                            }
                        } else {
                            isEdit3 = true;
                        }

                        if (dateList.size() > 0) {
                            for (String date : dateList) {
                                if (date.contains(DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhm, dataBean.get下单日期())))) {
                                    isEdit4 = true;
                                }
                            }
                        } else {
                            isEdit4 = true;
                        }

                        if (collection1.size() > 0) {
                            if (collection1.contains(dataBean.get订单状态())) {
                                isEdit5 = true;
                            }
                        } else {
                            isEdit5 = true;
                        }

                        if (collection2.size() > 0) {
                            if (collection2.contains(dataBean.get订单组别())) {
                                isEdit6 = true;
                            }
                        } else {
                            isEdit6 = true;
                        }

                        if (collection3.size() > 0) {
                            if (collection3.contains(dataBean.get制作组别())) {
                                isEdit7 = true;
                            }
                        } else {
                            isEdit7 = true;
                        }


                        if (isEdit1 && isEdit2 && isEdit3 && isEdit4 && isEdit5 && isEdit6 && isEdit7) {
                            mSearchList.add(dataBean);
                        }
                    }
                }

                if ("".equals(strName) && "".equals(strOrderId) && "".equals(strPerson)
                        && dateList.size() == 0 && collection1.size() == 0 && collection2.size() == 0 && collection3.size() == 0) {
                    mSearchList.addAll(mSaveList);
                }

                mAdapter.setList(mSearchList);
                mAdapter.notifyDataSetChanged();
                popEvaluate.dismiss();
            }
        });
    }

    List<HomeBean> mList = new ArrayList<>();

    private Collection<? extends HomeBean> getPopData() {
        for (int i = 0; i < editTitles.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(editTitles[i]);
            homeBean.setContent("");
            homeBean.setHint("请输入" + editTitles[i]);
            homeBean.setItemType(HomeBean.TYPE_4);
            mList.add(homeBean);
        }
        HomeBean homeBean = new HomeBean();
        homeBean.setTitle("按时间区间");
        homeBean.setStartTime("");
        homeBean.setEndTime("");
        homeBean.setItemType(HomeBean.TYPE_1);
        mList.add(homeBean);

        for (int i = 0; i < btnTitles.length; i++) {
            HomeBean homeBean2 = new HomeBean();
            homeBean2.setTitle(btnTitles[i]);
            homeBean2.setContent("");
            homeBean2.setItemType(HomeBean.TYPE_2);
            List<MenuBean> orderBeanList = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < orderStatus.length; j++) {
                    MenuBean orderBean = new MenuBean();
                    orderBean.setTitle(orderStatus[j]);
                    orderBeanList.add(orderBean);
                }
            } else if (i == 1) {
                for (int j = 0; j < orderTeams.length; j++) {
                    MenuBean orderBean = new MenuBean();
                    orderBean.setTitle(orderTeams[j]);
                    orderBeanList.add(orderBean);
                }
            } else if (i == 2) {
                for (int j = 0; j < madeTeams.length; j++) {
                    MenuBean orderBean = new MenuBean();
                    orderBean.setTitle(madeTeams[j]);
                    orderBeanList.add(orderBean);
                }
            }
            homeBean2.setMenuBeanList(orderBeanList);
            mList.add(homeBean2);
        }



        return mList;
    }


    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        startTime = DateUtil.getFirstDayOfMonth(oneDay);
        endTime = DateUtil.getLastDayOfMonth(oneDay) ;
        tvDate.setText(DateUtil.getLastMonth(oneDay));


        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getRequestJsonData();

    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EVENT:
                bundle = (Bundle) event.getData();
                sureHttp();
                break;

        }
    }


    private void sureHttp() {
        EasyHttp.post(Constants.PassAudit(apiToken, bundle.getString(FinalClass.ORDER_ID)))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                ToastUtil.getInstance().showToast("审批成功");
                                mAdapter.getData().remove(bundle.getInt(FinalClass.POSITION));
                                mAdapter.notifyItemRemoved(bundle.getInt(FinalClass.POSITION));
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void onDestroyView() {
        if (popEvaluate.isShowing()) {
            popEvaluate.dismiss();
        }

        deleteProgress();
        super.onDestroyView();
    }
}



