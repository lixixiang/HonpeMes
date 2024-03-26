package com.example.honpemes.fragment.a.menu.fragment.position1.item6;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item6.adapter.TimeOutOrderAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item1.bean.OutSendOrderBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.utils.selectorData.pickView.builder.TimePickerBuilder;
import com.example.honpemes.utils.selectorData.pickView.listener.OnTimeSelectListener;
import com.example.honpemes.utils.selectorData.pickView.view.TimePickerView;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.example.honpemes.widget.pop.CommonPopupWindow;
import com.example.honpemes.widget.pop.adapter.SelectorAdapter;
import com.example.honpemes.widget.pop.bean.SelectorBean;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/15 09:21
 * 注释：超时订单
 */
public class OutTimeOrderFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_end)
    TextView tvEnd;
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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public int oneDay = 0;

    private String curMonth, firstDay, lastDay, strOrderId, strStartTime,strEndTime;
    private TimeOutOrderAdapter mAdapter;
    private List<ProduceProgressBean.DataBean> mList;
    private CommonPopupWindow popEvaluate;
    private List<ProduceProgressBean.DataBean> mSaveList = new ArrayList<>();
    private List<ProduceProgressBean.DataBean> mSearchList = new ArrayList<>();
    private SelectorAdapter mSelectorAdapter;
    private List<SelectorBean> mPopList = new ArrayList<>();
    private TimePickerView pvTime1, pvTime2;
    private int oldPos;
    private List<String> timeList = new ArrayList<>();
    private Collection collection1 = new ArrayList();
    private Collection collection2 = new ArrayList();
    private Collection collection3 = new ArrayList();

    public static OutTimeOrderFragment newInstance(Bundle bundle) {
        OutTimeOrderFragment fragment = new OutTimeOrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_single_recyclerview;
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
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new TimeOutOrderAdapter();
        recyclerview.setAdapter(mAdapter);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }

        tvEnd.setText(getString(R.string.selector));
        getHttpData();
        selectedTime();
    }


    private List<String> statusList = new ArrayList<>();
    private List<String> teamList = new ArrayList<>();
    private List<String> makeTeamList = new ArrayList<>();

    private List<MenuBean> statusBeanList = new ArrayList<>();
    private List<MenuBean> teamBeanList = new ArrayList<>();
    private List<MenuBean> makeTeamBeanList = new ArrayList<>();

    private void initPopSelector() {
        if (statusList.size() > 0) {
            statusList.clear();
        }
        if (mPopList.size() > 0) {
            mPopList.clear();
        }
        if (teamList.size() > 0) {
            teamList.clear();
        }

        if (makeTeamList.size() > 0) {
            makeTeamList.clear();
        }

        if (statusBeanList.size() > 0) {
            statusBeanList.clear();
        }

        if (teamBeanList.size() > 0) {
            teamBeanList.clear();
        }

        if (makeTeamBeanList.size() > 0) {
            makeTeamBeanList.clear();
        }


        Integer[] widthAndHeight = Util.getWidthAndHeight(_mActivity.getWindow());
        popEvaluate = new CommonPopupWindow.Builder(_mActivity)
                .setView(R.layout.css_multi_selector)
                .setWidthAndHeight(widthAndHeight[0] * 5 / 6, ViewGroup.LayoutParams.MATCH_PARENT)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.RightAnimation)
                .create();

        popEvaluate.showRightView(toolbar, 0.5f);
        View view = popEvaluate.getContentView();
        TextView tvPopTitle = view.findViewById(R.id.tv_title);
        tvPopTitle.setText(getString(R.string.selector));
        tvPopTitle.setTextColor(Color.BLACK);
        RecyclerView popRecyclerView = view.findViewById(R.id.recyclerview);
        popRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        TextView tvSure = view.findViewById(R.id.tv_sure);
        TextView tvReset = view.findViewById(R.id.tv_reset);

        mSelectorAdapter = new SelectorAdapter();
        popRecyclerView.setAdapter(mSelectorAdapter);

        LatteLogger.d("saveList", GsonBuildUtil.GsonBuilder(mSaveList));
        SelectorBean popItem1 = new SelectorBean();
        popItem1.setItemType(SelectorBean.TYPE_1);
        popItem1.setTitle("单号");
        popItem1.setHint("查找单号可模糊查询");
        popItem1.setContent(strOrderId);
        mPopList.add(popItem1);

        SelectorBean popItem2 = new SelectorBean();
        popItem2.setItemType(SelectorBean.TYPE_2);
        popItem2.setTitle("时区");
        popItem2.setHint("开始时间");
        popItem2.setHint2("结束时间");
        popItem1.setContent(strStartTime);
        popItem1.setContent(strEndTime);
        mPopList.add(popItem2);

        for (int i = 0; i < mSaveList.size(); i++) {
            statusList.add(mSaveList.get(i).订单状态);
            teamList.add(mSaveList.get(i).订单组别);
            makeTeamList.add(mSaveList.get(i).制作组别名称);
        }
        StringUtil.DeleteArrayRepeatStr(statusList);
        StringUtil.DeleteArrayRepeatStr(teamList);
        StringUtil.DeleteArrayRepeatStr(makeTeamList);

        SelectorBean popItem3 = new SelectorBean();
        popItem3.setItemType(SelectorBean.TYPE_3);
        popItem3.setTitle("审批状态");
        for (int i = 0; i < statusList.size(); i++) {
            MenuBean menuBean = new MenuBean();
            menuBean.setTitle(statusList.get(i));
            if (collection1.size() > 0) {
                if (collection1.contains(statusList.get(i))) {
                    menuBean.setCheck(true);
                } else {
                    menuBean.setCheck(false);
                }
            } else {
                menuBean.setCheck(false);
            }
            statusBeanList.add(menuBean);
        }
        popItem3.setMenuBeanList(statusBeanList);
        mPopList.add(popItem3);

        SelectorBean popItem4 = new SelectorBean();
        popItem4.setItemType(SelectorBean.TYPE_3);
        popItem4.setTitle("订单组别");
        for (int i = 0; i < teamList.size(); i++) {
            MenuBean menuBean = new MenuBean();
            menuBean.setTitle(teamList.get(i));
            if (collection2.size() > 0) {
                if (collection2.contains(teamList.get(i))) {
                    menuBean.setCheck(true);
                } else {
                    menuBean.setCheck(false);
                }
            } else {
                menuBean.setCheck(false);
            }
            teamBeanList.add(menuBean);
        }

        popItem4.setMenuBeanList(teamBeanList);
        mPopList.add(popItem4);

        SelectorBean popItem5 = new SelectorBean();
        popItem5.setItemType(SelectorBean.TYPE_3);
        popItem5.setTitle("制作组别");
        for (int i = 0; i < makeTeamList.size(); i++) {
            MenuBean menuBean = new MenuBean();
            menuBean.setTitle(makeTeamList.get(i));
            if (collection3.size() > 0) {
                if (collection3.contains(makeTeamList.get(i))) {
                    menuBean.setCheck(true);
                } else {
                    menuBean.setCheck(false);
                }
            } else {
                menuBean.setCheck(false);
            }
            makeTeamBeanList.add(menuBean);
        }
        popItem5.setMenuBeanList(makeTeamBeanList);
        mPopList.add(popItem5);

        mSelectorAdapter.setList(mPopList);
        mSelectorAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.et_start_time:
                        oldPos = position;
                        pvTime1.show();
                        break;
                    case R.id.et_end_time:
                        oldPos = position;
                        pvTime2.show();
                        break;
                }
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatteLogger.d("testmSelectorAdapter", GsonBuildUtil.GsonBuilder(mSelectorAdapter.getData()));
                timeList.clear();
                mSearchList.clear();
                collection1.clear();
                collection2.clear();
                collection3.clear();
                strOrderId = "";
                strStartTime = "";
                strEndTime = "";

                for (int i = 0; i < mSelectorAdapter.getData().size(); i++) {
                    SelectorBean itemBean = mSelectorAdapter.getData().get(i);
                    if ("单号".equals(itemBean.getTitle())) {
                        strOrderId = itemBean.getContent();
                    } else if ("时区".equals(itemBean.getTitle()) && (!TextUtils.isEmpty(strStartTime) || !TextUtils.isEmpty(strEndTime))) {
                        strStartTime = itemBean.getStartTime();
                        strEndTime = itemBean.getEndTime();

                        if (!TextUtils.isEmpty(strStartTime) && TextUtils.isEmpty(strEndTime)) {
                            strEndTime = strStartTime;
                        } else if (TextUtils.isEmpty(strEndTime) && !TextUtils.isEmpty(strStartTime)) {
                            strStartTime = strEndTime;
                        }
                        timeList = DateUtil.getDays(strStartTime, strEndTime);
                    } else if ("审批状态".equals(itemBean.getTitle())) {
                        for (int j = 0; j < mSelectorAdapter.getData().get(i).getMenuBeanList().size(); j++) {
                            MenuBean statusBean = mSelectorAdapter.getData().get(i).getMenuBeanList().get(j);
                            if (statusBean.isCheck()) {
                                collection1.add(statusBean.getTitle());
                            }
                        }
                    } else if ("订单组别".equals(itemBean.getTitle())) {
                        for (int j = 0; j < mSelectorAdapter.getData().get(i).getMenuBeanList().size(); j++) {
                            MenuBean statusBean = mSelectorAdapter.getData().get(i).getMenuBeanList().get(j);
                            if (statusBean.isCheck()) {
                                collection2.add(statusBean.getTitle());
                            }
                        }
                    } else if ("制作组别".equals(itemBean.getTitle())) {
                        for (int j = 0; j < mSelectorAdapter.getData().get(i).getMenuBeanList().size(); j++) {
                            MenuBean statusBean = mSelectorAdapter.getData().get(i).getMenuBeanList().get(j);
                            if (statusBean.isCheck()) {
                                collection3.add(statusBean.getTitle());
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

                        ProduceProgressBean.DataBean dataBean = mSaveList.get(i);
                        if (!"".equals(strOrderId) && strOrderId != null) {
                            if (dataBean.生产单号.contains(strOrderId)) {
                                isEdit1 = true;
                            }
                        } else {
                            isEdit1 = true;
                        }

                        if (timeList.size() > 0) {
                            for (String date : timeList) {
                                if (date.contains(dataBean.下单日期)) {
                                    isEdit2 = true;
                                }
                            }
                        } else {
                            isEdit2 = true;
                        }

                        if (collection1.size() > 0) {
                            if (collection1.contains(dataBean.订单状态)) {
                                isEdit3 = true;
                            }
                        } else {
                            isEdit3 = true;
                        }

                        if (collection2.size() > 0) {
                            if (collection2.contains(dataBean.订单组别)) {
                                isEdit4 = true;
                            }
                        } else {
                            isEdit4 = true;
                        }


                        if (collection3.size() > 0) {
                            if (collection3.contains(dataBean.订单组别)) {
                                isEdit3 = true;
                            }
                        } else {
                            isEdit5 = true;
                        }

                        if (isEdit1 && isEdit2 && isEdit3 && isEdit4 && isEdit5) {
                            mSearchList.add(dataBean);
                        }
                    }
                }

                if ("".equals(strOrderId) && timeList.size() == 0
                        && collection1.size() == 0 && collection2.size() == 0 && collection3.size() == 0) {
                    mSearchList.addAll(mSaveList);
                }
                mAdapter.setList(mSearchList);
                mAdapter.notifyDataSetChanged();
                popEvaluate.dismiss();
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mSelectorAdapter.getData().size(); i++) {
                    if (mSelectorAdapter.getData().get(i).getItemType() == 1) {
                        mSelectorAdapter.getData().get(i).setContent("");
                    } else if (mSelectorAdapter.getData().get(i).getItemType() == 2) {
                        mSelectorAdapter.getData().get(i).setStartTime("");
                        mSelectorAdapter.getData().get(i).setEndTime("");
                    } else if (mSelectorAdapter.getData().get(i).getItemType() == 3) {
                        for (int j = 0; j < mSelectorAdapter.getData().get(i).getMenuBeanList().size(); j++) {
                            mSelectorAdapter.getData().get(i).getMenuBeanList().get(j).setCheck(false);
                        }
                    }
                }

                mSelectorAdapter.notifyDataSetChanged();
            }
        });


    }

    private void getHttpData() {
        EasyHttp.post(Constants.OutTimeOrder(firstDay, lastDay)).execute(new SimpleCallBack<String>() {
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
                        if (mSaveList.size() > 0) {
                            mSaveList.clear();
                        }
                        if (mList != null) {
                            if (mList.size() > 0) {
                                mList.clear();
                            }
                        }

                        ProduceProgressBean bean = GsonBuildUtil.create().fromJson(s, ProduceProgressBean.class);
                        LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(bean));
                        HashSet<ProduceProgressBean.DataBean> uniqueSet = new HashSet<>(bean.data);
                        mList = new ArrayList<>(uniqueSet);
                        mAdapter.setList(mList);
                        mSaveList.addAll(mAdapter.getData());
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
                initPopSelector();
                break;
        }
    }

    private void selectedTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR) - 5, startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE)
                , startDate.get(Calendar.HOUR_OF_DAY), startDate.get(Calendar.MINUTE));

        pvTime1 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String strYMDHM = DateUtil.ymdhm.format(date);
                mSelectorAdapter.getData().get(oldPos).setStartTime(strYMDHM);
                mSelectorAdapter.notifyItemChanged(oldPos);
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("开始时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();

        pvTime2 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String strYMDHM = DateUtil.ymdhm.format(date);
                mSelectorAdapter.getData().get(oldPos).setEndTime(strYMDHM);
                mSelectorAdapter.notifyItemChanged(oldPos);
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("结束时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.popwindow_anim_style);
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom，底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
        Dialog mDialog2 = pvTime2.getDialog();
        if (mDialog2 != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime2.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog2.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.popwindow_anim_style);
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom，底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }



}





























