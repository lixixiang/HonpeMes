package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.datepickview.wheelpicker.DatePicker;
import com.example.datepickview.wheelpicker.contract.OnDatePickedListener;
import com.example.datepickview.wheelpicker.entity.DateEntity;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.com_adapter.ComTabAdapter;
import com.example.honpemes.fragment.a.com_adapter.MultiSelectPopAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.TeamBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter.ChartAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter.DetailDeviceStatusAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceStatusBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceUserBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.dialog.Base.DialogUtils;
import com.example.honpemes.widget.pop.CommonPopupWindow;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.honpemes.api.DataClass.strHeads;

/**
 * Created by Lixixiang on 2023/2/13 17:55
 * 设备状态详情界面
 */
public class DeviceDetailFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_head_recyclerView)
    RecyclerView mHeadRecyclerView;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_wait_num)
    TextView tvWaitNum;
    @BindView(R.id.tv_speed_top)
    TextView tvSpeedTop;
    @BindView(R.id.tv_speed_mid)
    TextView tvSpeedMid;
    @BindView(R.id.tv_speed_down)
    TextView tvSpeedDown;
    @BindView(R.id.tv_depart)
    TextView tvDepart;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.tv_operator)
    TextView tvOperator;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.tv_current_status)
    TextView tvCurrentStatus;
    @BindView(R.id.tv_current_type)
    TextView tvCurrentType;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_chart)
    RecyclerView rvChart;

    @BindView(R.id.tv_times)
    TextView tvTimes;

    private DetailDeviceStatusAdapter mAdapter;

    private ComTabAdapter headAdapter;
    private ChartAdapter mChartAdapter;
    private String strOperator, startTime, endTime, strPopStart, strPopEnd, curStartDay, curEndDay;
    private String yearMonthDay;
    private DateEntity startDateEntity = new DateEntity();

    private CommonPopupWindow popEvaluate;
    private List<HomeBean> mPopList = new ArrayList<>();
    private MultiSelectPopAdapter popAdapter;
    private int downIndex, upIndex, downUp;
    private List<DeviceStatusBean.DataBean> mStatusList1 = new ArrayList<>();
    private List<DeviceStatusBean.DataBean> mStatusList2 = new ArrayList<>();
    private List<DeviceStatusBean.DataBean> mStatusList3 = new ArrayList<>();
    private List<DeviceStatusBean.DataBean> mStatusList4 = new ArrayList<>();
    private long sum1, sum2, sum3, sum4;

    private DeviceStatusBean.DataBean dataBean;
    private List<DeviceUserBean> mDeviceUserList = new ArrayList<>();
    private List<DeviceUserBean> mPieList = new ArrayList<>();
    private TeamBean mTeamBean;

    private void clearList() {
        mStatusList1.clear();
        mStatusList2.clear();
        mStatusList3.clear();
        mStatusList4.clear();
        mDeviceUserList.clear();
        mPieList.clear();
    }

    public static DeviceDetailFragment newInstance(Bundle bundle) {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        startTime = DateUtil.ymd.format(new Date()) + " 00:00:00";
        endTime = DateUtil.ymdhms.format(new Date());
        ivSearch.setVisibility(View.VISIBLE);
        tvTimes.setVisibility(View.VISIBLE);

        LatteLogger.d("testStrTime", startTime + "\n" + endTime);
        dataBean = (DeviceStatusBean.DataBean) getArguments().getSerializable(FinalClass.bean);

        initHeadContent();

        initToolbarNav(llBack);

        tvTitle.setText(dataBean.get机台编码() + "详情");
        tvTimes.setText(DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, startTime)));
        initHeadRecycler();
        initRecyclerView();
        initPopSelector();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.css_coordinator_layout;
    }


    @Override
    protected void initView() {

    }

    private void initHeadContent() {
        if (!"".equals(dataBean.get加工人员())) {
            strOperator = dataBean.get加工人员();
        } else if (!"".equals(dataBean.get操机员())) {
            strOperator = dataBean.get操机员();
        } else if (!"".equals(dataBean.get操机员ID())) {
            strOperator = dataBean.get操机员ID();
        } else {
            strOperator = dataBean.get操机员();
        }
        tvWaitNum.setText(dataBean.get待机数量() == 0 ? "--" : dataBean.get待机数量() + "");
        tvSpeedTop.setText(dataBean.get主轴转速() == "" ? "--" : dataBean.get主轴转速());
        tvSpeedMid.setText(dataBean.get切削速度() == "" ? "--" : dataBean.get切削速度());
        tvSpeedDown.setText(dataBean.get机器行程() == "" ? "--" : dataBean.get机器行程());
        tvDepart.setText(dataBean.get使用部门() == "" ? "--" : dataBean.get使用部门());
        tvTeam.setText(dataBean.get制作组别() == "" ? "--" : dataBean.get制作组别());



        String time = DateUtil.hm.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(dataBean.get入库时间())));
        tvCurrentTime.setText(time == "" ? "--" : time);
        tvOperator.setText(strOperator == "" ? "--" : strOperator);

        if ("运行".equals(dataBean.get状态())) {
            dataBean.set状态("运行");
            tvCurrentStatus.setTextColor(DataClass.COLORS2[1]);
        } else if ("暂停".equals(dataBean.get状态())) {
            dataBean.set状态("暂停");
            tvCurrentStatus.setTextColor(DataClass.COLORS2[2]);
        } else if ("手工报警".equals(dataBean.get状态())) {
            dataBean.set状态("报警");
            tvCurrentStatus.setTextColor(DataClass.COLORS2[3]);
        } else if ("关机".equals(dataBean.get状态())) {
            dataBean.set状态("关机");
            tvCurrentStatus.setTextColor(DataClass.COLORS2[4]);
        } else {
            dataBean.set状态("--");
        }
        tvCurrentStatus.setText(dataBean.get状态());

        tvCurrentType.setText(dataBean.get机台类型() == "" ? "--" : dataBean.get机台类型());
    }

    String strDes;

    private void initPieChat(List<DeviceStatusBean.DataBean> mDataList) {
        clearList();

        for (int i = 0; i < mDataList.size(); i++) {
            if (mDataList.get(i).get状态().equals(strHeads[1])) {//运行"
                mStatusList1.add(mDataList.get(i));
            } else if (mDataList.get(i).get状态().equals(strHeads[2])) {//暂停"
                mStatusList2.add(mDataList.get(i));
            } else if (mDataList.get(i).get状态().contains(strHeads[3])) {//报警"
                mStatusList3.add(mDataList.get(i));
            } else if (mDataList.get(i).get状态().equals(strHeads[4])) {//关机"
                mStatusList4.add(mDataList.get(i));
            }
        }


        for (int i = 0; i < strHeads.length; i++) {
            DeviceUserBean deviceUserBean = new DeviceUserBean();
            deviceUserBean.setSum(mDataList.size());
            deviceUserBean.setItemType(HomeBean.TYPE_2);
            deviceUserBean.setSpanSize(1);
            if (i == 1) {
                deviceUserBean.setTotalCount(mStatusList1.size()); //外环的那个颜色 使用率
                deviceUserBean.setUserRate(StringUtil.formatDouble(getRate(mStatusList1, mDataList)) + "%");
                deviceUserBean.setLineRate(getDesString(getRate(mStatusList1, mDataList)));
                deviceUserBean.setColors(DataClass.COLORS_RUN_1);
                deviceUserBean.setAccountTime(DateUtil.CountTMS(sum1));
                mDeviceUserList.add(deviceUserBean);
            } else if (i == 2) {
                deviceUserBean.setTotalCount(mStatusList2.size());
                deviceUserBean.setUserRate(StringUtil.formatDouble(getRate(mStatusList2, mDataList)) + "%");
                deviceUserBean.setLineRate(getDesString(getRate(mStatusList2, mDataList)));
                deviceUserBean.setColors(DataClass.COLORS_RUN_2);
                deviceUserBean.setAccountTime(DateUtil.CountTMS(sum2));
                mDeviceUserList.add(deviceUserBean);
            } else if (i == 3) {
                deviceUserBean.setTotalCount(mStatusList3.size());
                deviceUserBean.setUserRate(StringUtil.formatDouble(getRate(mStatusList3, mDataList)) + "%");
                deviceUserBean.setLineRate(getDesString(getRate(mStatusList3, mDataList)));
                deviceUserBean.setColors(DataClass.COLORS_RUN_3);
                deviceUserBean.setAccountTime(DateUtil.CountTMS(sum3));
                mDeviceUserList.add(deviceUserBean);
            } else if (i == 4) {
                deviceUserBean.setTotalCount(mStatusList4.size());
                deviceUserBean.setUserRate(StringUtil.formatDouble(getRate(mStatusList4, mDataList)) + "%");
                deviceUserBean.setLineRate(getDesString(getRate(mStatusList4, mDataList)));
                deviceUserBean.setColors(DataClass.COLORS_RUN_4);
                deviceUserBean.setAccountTime(DateUtil.CountTMS(sum4));
                mDeviceUserList.add(deviceUserBean);
            }
        }

        String strDescription = getDesString(getRate(mStatusList1, mDataList));
        if ("高".equals(strDescription)) {
            strDes = "机台运行很好！非常棒";
        } else if ("正常".equals(strDescription)) {
            strDes = "机台运行正常，继续保持！";
        } else if ("偏低".equals(strDescription)) {
            strDes = "机台运行有点下降，看看什么原因！";
        } else if ("低".equals(strDescription)) {
            strDes = "机台运行下降严重，看看是否需要维护！";
        } else if ("停止".equals(strDescription)) {
            strDes = "机台已经停止工作！";
        }

        for (int i = 0; i < headAdapter.getData().size(); i++) {
            HomeBean homeBean = headAdapter.getData().get(i);
            if (i != 0) {
                homeBean.setTitle(strHeads[i] + "(" + mDeviceUserList.get(i - 1).getTotalCount() + ")");
                headAdapter.getData().set(i, homeBean);
            }
        }
        headAdapter.notifyDataSetChanged();
        mChartAdapter.setList(getChartData());

    }

    @NotNull
    private String getDesString(double rate) {
        String strDescription = "";
        if (rate >= 50) {
            strDescription = "高";
        } else if (50 > rate && rate >= 40) {
            strDescription = "正常";
        } else if (40 > rate && rate >= 20) {
            strDescription = "偏低";
        } else if (20 > rate && rate > 0) {
            strDescription = "低";
        } else {
            strDescription = "停止";
        }
        return strDescription;
    }

    private double getRate(List<DeviceStatusBean.DataBean> mStatusList, List<DeviceStatusBean.DataBean> mDataList) {
        return (double) mStatusList.size() / (double) mDataList.size() * 100;
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                downUp = 1;
                upIndex += 1;
                curStartDay = DateUtil.getIndexDateBeforeLast(startTime, DateUtil.ymdhms, upIndex);
                curEndDay = DateUtil.getIndexDateBeforeLast(endTime, DateUtil.ymdhms, upIndex);

                if (DateUtil.setDate(DateUtil.ymdhms, endTime).getTime() <= DateUtil.setDate(DateUtil.ymdhms, curEndDay).getTime()) {
                    initData(curStartDay, curEndDay);
                } else {
                    refreshLayout.finishRefreshWithNoMoreData();
                }

                LatteLogger.d("downIndex", curStartDay + "=====" + curEndDay + "=====" + upIndex);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                downIndex -= 1;
                downUp = 2;
                curStartDay = DateUtil.getIndexDateBeforeLast(startTime, DateUtil.ymdhms, downIndex);
                curEndDay = DateUtil.getIndexDateBeforeLast(endTime, DateUtil.ymdhms, downIndex);
                initData(curStartDay, curEndDay);

                LatteLogger.d("downIndex", curStartDay + "=====" + curEndDay + "=====" + downIndex);

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
        mAdapter = new DetailDeviceStatusAdapter();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        initData(startTime, endTime);
    }

    private void initData(String sTime, String eTime) {
        disposable = EasyHttp.post(Constants.PosERPDataTableRecord + apiToken + "&strSQL=" + Constants.DeviceDetail(sTime,
                eTime, dataBean.get机台编码()))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (downUp == 0)
                            ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        if (downUp == 0)
                            ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                DeviceStatusBean bean = GsonBuildUtil.create().fromJson(s, DeviceStatusBean.class);
                                if (downUp == 2) { //上拉加载
                                    if (bean.getData().size() > 0) {
                                        mAdapter.addData(getList(bean.getData()));
                                    }
                                    refreshLayout.finishLoadMore();

                                } else if (downUp == 1) { //下拉刷新

                                    if (bean.getData().size() > 0) {
                                        mAdapter.addData(0, getList(bean.getData()));
                                    }
                                    refreshLayout.finishRefresh();

                                } else {
                                    LatteLogger.d("testData", bean.getData().size());
                                    if (bean.getData().size() > 0)
                                        mAdapter.setList(getList(bean.getData()));
                                }


                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void initPopSelector() {
        popEvaluate = new CommonPopupWindow.Builder(_mActivity)
                .setView(R.layout.single_recyclerview)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .create();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView mPopRecyclerView = popEvaluate.getContentView().findViewById(R.id.m_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mPopRecyclerView.setPadding(20, 0, 20, 0);
        mPopRecyclerView.setLayoutManager(manager);
        mPopRecyclerView.setBackgroundResource(R.color.white);
        mPopRecyclerView.setLayoutParams(params);

        popAdapter = new MultiSelectPopAdapter();
        mPopRecyclerView.setAdapter(popAdapter);
        popAdapter.setList(getDeviceStatusData());
        popAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_content:
                        if (TextUtils.isEmpty(yearMonthDay)) {
                            yearMonthDay = startTime;
                            startDateEntity = DateEntity.target(new Date());
                        }


                        DatePicker picker = DialogUtils.onYearMonthDay(_mActivity, "选择日期", DateEntity.yearOnFuture(-1), DateEntity.today(), startDateEntity);
                        picker.setOnDatePickedListener(new OnDatePickedListener() {
                            @Override
                            public void onDatePicked(int year, int month, int day) {
                                String popStartTime = year + "-" + month + "-" + day;
                                popStartTime = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymd, popStartTime));
                                startDateEntity.setYear(year);
                                startDateEntity.setMonth(month);
                                startDateEntity.setDay(day);

                                for (HomeBean homeBean : popAdapter.getData()) {
                                    if (homeBean.getItemType() == HomeBean.TYPE_3) {
                                        homeBean.setContent(popStartTime);
                                    }
                                }

                                popAdapter.notifyItemChanged(position);
                            }
                        });
                        picker.show();
                        break;

                    case R.id.tv_sure:
                        for (int i = 0; i < popAdapter.getData().size(); i++) {
                            if (popAdapter.getData().get(i).getItemType() == HomeBean.TYPE_3) {
                                strPopStart = popAdapter.getData().get(i).getContent() + " 00:00:00";
                                strPopEnd = popAdapter.getData().get(i).getContent() + " 08:59:59";
                            }
                        }

                        downUp = 0;
                        downIndex = 0;
                        upIndex = 0;

                        if (!"".equals(strPopStart) && !"".equals(strPopEnd)) {
                            startTime = strPopStart;
                            endTime = strPopEnd;
                        } else if (!"".equals(strPopStart)) {
                            startTime = strPopStart;
                        } else if (!"".equals(strPopEnd)) {
                            endTime = strPopEnd;
                        }
                        tvTimes.setText(DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, startTime)));

                        LatteLogger.d("tefwfewfewf", startTime + "\n" + endTime);
                        initData(startTime, endTime);

                        popEvaluate.dismiss();
                        break;
                }
            }
        });
    }

    List<MenuBean> status = new ArrayList<>();

    private Collection<? extends HomeBean> getDeviceStatusData() {
        HomeBean homeBean = new HomeBean();
        homeBean.setTitle("日期查询");
        homeBean.setContent("");
        homeBean.setHint("选择需要查询的日期");
        homeBean.setItemType(HomeBean.TYPE_3);
        mPopList.add(homeBean);

        HomeBean homeBean1 = new HomeBean();
        homeBean1.setItemType(HomeBean.TYPE_2);
        homeBean1.setTitle("状态划分");
        for (int i = 0; i < strHeads.length; i++) {
            if (i != 0) {
                MenuBean menuBean = new MenuBean();
                menuBean.setTitle(strHeads[i]);
                status.add(menuBean);
            }
        }
        homeBean1.setMenuBeanList(status);
        mPopList.add(homeBean1);

        HomeBean homeBean6 = new HomeBean();
        homeBean6.setItemType(HomeBean.TYPE_6);
        mPopList.add(homeBean6);
        return mPopList;
    }

    List<HomeBean> HeadList = new ArrayList<>();

    private Collection<? extends HomeBean> getHeadData() {
        for (int i = 0; i < strHeads.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(strHeads[i]);
            homeBean.setSpanSize(1);

            HeadList.add(homeBean);

        }
        return HeadList;
    }

    public void initHeadRecycler() {
        mHeadRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 5));
        headAdapter = new ComTabAdapter(DataClass.COLORS2);
        mHeadRecyclerView.setAdapter(headAdapter);
        headAdapter.setList(getHeadData());

        rvChart.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        mChartAdapter = new ChartAdapter();
        rvChart.setBackgroundResource(R.color.white);
        rvChart.setAdapter(mChartAdapter);
        mChartAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                return mChartAdapter.getData().get(position).getSpanSize();
            }
        });
    }

    private ArrayList<DeviceStatusBean.DataBean> mList = new ArrayList<>();
    private List<DeviceStatusBean.DataBean> mSaveList = new ArrayList<>();
    private List<DeviceStatusBean.DataBean> httpData = new ArrayList<>();

    private Collection<? extends DeviceStatusBean.DataBean> getList(List<DeviceStatusBean.DataBean> dataList) {
        mList.clear();
        mSaveList.clear();
        httpData.clear();
        sum1 = 0;
        sum2 = 0;
        sum3 = 0;
        sum4 = 0;
        httpData.addAll(dataList);//搞线性是用时间进行区分
        Collections.sort(dataList, new Comparator<DeviceStatusBean.DataBean>() {
            @Override
            public int compare(DeviceStatusBean.DataBean o1, DeviceStatusBean.DataBean o2) {
                return StringUtil.replaceT(o2.get入库时间()).compareTo(StringUtil.replaceT(o1.get入库时间()));
            }
        });

        DeviceStatusBean.DataBean dataBean = dataList.get(0);
        mList.add(dataBean);


        for (int i = 0; i < dataList.size(); i++) {
            DeviceStatusBean.DataBean bean = dataList.get(i);
            //下一个时间
            if (bean.get状态().equals(strHeads[1])) {
                sum1 = sum1 + 10 * 1000;
            } else if (bean.get状态().equals(strHeads[2])) {
                sum2 = sum2 + 10 * 1000;
            } else if (bean.get状态().contains(strHeads[3])) {
                sum3 = sum3 + 10 * 1000;
            } else if (bean.get状态().equals(strHeads[4])) {
                sum4 = sum4 + 10 * 1000;
            }

            if (!dataBean.get状态().equals(bean.get状态())) {
                mList.add(bean);
            }
            dataBean = bean;
            //上一个时间
        }


        for (int i = 0; i < mList.size(); i++) {
            for (int j = 0; j < popAdapter.getData().get(1).getMenuBeanList().size(); j++) {
                if (popAdapter.getData().get(1).getMenuBeanList().get(j).isCheck()) {
                    if (mList.get(i).get状态().equals(popAdapter.getData().get(1).getMenuBeanList().get(j).getTitle())) {
                        mSaveList.add(mList.get(i));
                    }
                }
            }
        }

        if (mSaveList.size() == 0 && mList.size() != 0) {
            mSaveList.addAll(mList);
        } else if (mList.size() == 0) {
            mSaveList.add(dataBean);
        }

        StringUtil.DeleteArrayRepeatStr(mSaveList);
        if (mSaveList.size() > 0) {
            initPieChat(mSaveList);
        }

        LatteLogger.d("tewefefwf", mSaveList.size());


        return mSaveList;
    }


    @Override
    public boolean onBackPressedSupport() {
        EventBusUtil.sendEvent(new Event(FinalClass.DEVICE_TIME_STATUS));
        return super.onBackPressedSupport();
    }


    @OnClick(R.id.iv_search)
    public void onClick() {
        popEvaluate.showDownView(mToolbar, 1f);
    }


    private Collection<? extends DeviceUserBean> getChartData() {
        DeviceUserBean deviceUserBean = new DeviceUserBean();
        deviceUserBean.setItemType(HomeBean.TYPE_1);
        deviceUserBean.setTitle("机台效率");
        deviceUserBean.setSpanSize(4);
        deviceUserBean.setDes(strDes);
        mPieList.add(deviceUserBean);

        mPieList.addAll(mDeviceUserList);

//        DeviceUserBean deviceUserBean2 = new DeviceUserBean();
//        deviceUserBean2.setItemType(HomeBean.TYPE_1);
//        deviceUserBean2.setTitle("状态次数和时间划分");
//        deviceUserBean2.setSpanSize(4);
//        deviceUserBean2.setDes("");
//        mPieList.add(deviceUserBean2);
//
//        DeviceUserBean deviceUserBean3 = new DeviceUserBean();
//        deviceUserBean3.setItemType(HomeBean.TYPE_3);
//        deviceUserBean3.setSpanSize(4);
//        deviceUserBean3.setAllList(httpData);
//        deviceUserBean3.setSaveList(mStatusList1);
//        deviceUserBean3.setSaveList2(mStatusList2);
//        deviceUserBean3.setSaveList3(mStatusList3);
//        deviceUserBean3.setSaveList4(mStatusList4);
//        mPieList.add(deviceUserBean3);

        return mPieList;
    }

}
























