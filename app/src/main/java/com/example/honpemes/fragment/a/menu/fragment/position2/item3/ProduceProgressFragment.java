package com.example.honpemes.fragment.a.menu.fragment.position2.item3;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.com_adapter.MultiSelectPopAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.adapter.ProduceProgressAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.example.honpemes.widget.pop.CommonPopupWindow;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.honpemes.api.DataClass.editTitles2;

/**
 * 作者：asus on 2023/12/6 16:39
 * 注释：生产进度
 */
public class ProduceProgressFragment extends BaseBackFragment {

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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    public int oneDay = 0;

    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;
    private ProduceProgressAdapter mAdapter;
    private List<ProduceProgressBean.DataBean> mList;
    private CommonPopupWindow popEvaluate;

    private List<String> dateList = new ArrayList<>();
    private List<ProduceProgressBean.DataBean> mSaveList = new ArrayList<>();
    private List<ProduceProgressBean.DataBean> mSearchList = new ArrayList<>();

    public static ProduceProgressFragment newInstance(Bundle bundle) {
        ProduceProgressFragment fragment = new ProduceProgressFragment();
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
        ivSearch.setVisibility(View.VISIBLE);

        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd) + " 00:00:00";
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd) + " 23:59:59";
        curDate = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        rgDate.setVisibility(View.VISIBLE);
        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);

        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ProduceProgressAdapter();
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


    private void getHttpData() {
        EasyHttp.post(Constants.ProduceProgress(firstDay, lastDay,
                "", "", "", "", ""))
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
                            if (object.getInt(FinalClass.Tag) == 0) {
                                if (object.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(object.getString(FinalClass.Message));
                                }
                            } else {
                                if (mSaveList.size() > 0) {
                                    mSaveList.clear();
                                }
                                if (mList != null) {
                                    if (mList.size() > 0) {
                                        mList.clear();
                                    }
                                }

                                ProduceProgressBean bean = GsonBuildUtil.create().fromJson(s, ProduceProgressBean.class);
                                HashSet<ProduceProgressBean.DataBean> uniqueSet = new HashSet<>(bean.data);
                                mList = new ArrayList<>(uniqueSet);

                                mAdapter.setList(mList);
                                mSaveList.addAll(mAdapter.getData());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                ProduceProgressBean.DataBean dataBean = mAdapter.getData().get(position);
                dataBean.Expanded = !dataBean.Expanded;
                mAdapter.getData().set(position, dataBean);
                mAdapter.notifyItemChanged(position);
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

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.iv_search})
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
            case R.id.iv_search:
                popEvaluate.showDownView(toolbar, 0.5f);
                break;
        }
    }

    private void initPopSelector() {
        popEvaluate = new CommonPopupWindow.Builder(_mActivity)
                .setView(R.layout.single_recyclerview)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)//在外不可用手指取消
                .create();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RecyclerView mPopRecyclerView = popEvaluate.getContentView().findViewById(R.id.m_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mPopRecyclerView.setLayoutManager(manager);

        mPopRecyclerView.setBackgroundResource(R.color.white);
        mPopRecyclerView.setLayoutParams(params);
        MultiSelectPopAdapter mPopAdapter = new MultiSelectPopAdapter();
        mPopRecyclerView.setAdapter(mPopAdapter);
        mPopAdapter.setList(getPopData());

        mPopAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_reset:
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
                    case R.id.tv_sure:
                        mSearchList.clear();
                        //{"生产单号",  "下单人员","下单时间","交货日期","制作组别", "制作组别", "订单状态"
                        String strOrderId = "";
                        String strName = "";
                        String strStartTime = "";
                        String strEndTime = "";
                        String strTeam = "";
                        String strOrderStatus = "";

                        for (HomeBean homeBean : mPopAdapter.getData()) {
                            if (editTitles2[0].equals(homeBean.getTitle())) {
                                strOrderId = homeBean.getContent();
                            } else if (editTitles2[1].equals(homeBean.getTitle())) {
                                strName = homeBean.getContent();
                            } else if (editTitles2[2].equals(homeBean.getTitle())) {
                                strStartTime = homeBean.getContent();
                            } else if (editTitles2[3].equals(homeBean.getTitle())) {
                                strEndTime = homeBean.getContent();
                            } else if (editTitles2[4].equals(homeBean.getTitle())) {
                                strTeam = homeBean.getContent();
                            } else if (editTitles2[5].equals(homeBean.getTitle())) {
                                strOrderStatus = homeBean.getContent();
                            }
                        }
                        LatteLogger.d("tv_suretv_sure", "$strOrderId:" + strOrderId + "$strName:" + strName +
                                "$strStartTime:" + strStartTime + "$strEndTime:" + strEndTime + "$strTeam:" + strTeam + "$strOrderStatus:" + strOrderStatus);
                        if (mSaveList.size() > 0) {
                            for (int i = 0; i < mSaveList.size(); i++) {
                                boolean isEdit1 = false;
                                boolean isEdit2 = false;
                                boolean isEdit3 = false;
                                boolean isEdit4 = false;
                                boolean isEdit5 = false;
                                boolean isEdit6 = false;
                                ProduceProgressBean.DataBean dataBean = mSaveList.get(i);
                                if (!"".equals(strOrderId)) {
                                    if (dataBean.生产单号.contains(strOrderId)) {
                                        isEdit1 = true;
                                    }
                                } else {
                                    isEdit1 = true;
                                }
                                if (!"".equals(strName)) {
                                    if (dataBean.下单人员.contains(strName)) {
                                        isEdit2 = true;
                                    }
                                } else {
                                    isEdit2 = true;
                                }
                                if (!"".equals(strStartTime)) {
                                    if (dataBean.下单日期.contains(strStartTime)) {
                                        isEdit3 = true;
                                    }
                                } else {
                                    isEdit3 = true;
                                }
                                if (!"".equals(strStartTime)) {
                                    if (dataBean.交货日期.contains(strEndTime)) {
                                        isEdit4 = true;
                                    }
                                } else {
                                    isEdit4 = true;
                                }
                                if (!"".equals(strTeam)) {
                                    if (dataBean.制作组别.contains(strTeam)) {
                                        isEdit5 = true;
                                    }
                                } else {
                                    isEdit5 = true;
                                }
                                if (!"".equals(strOrderStatus)) {
                                    if (dataBean.订单状态.contains(strOrderStatus)) {
                                        isEdit6 = true;
                                    }
                                } else {
                                    isEdit6 = true;
                                }

                                if (isEdit1 && isEdit2 && isEdit3 && isEdit4 && isEdit5 && isEdit6) {
                                    mSearchList.add(dataBean);
                                }
                            }

                            if ("".equals(strOrderId) && "".equals(strName) && "".equals(strStartTime)
                                    && "".equals(strEndTime) && "".equals(strTeam) && "".equals(strOrderStatus)) {
                                mSearchList.addAll(mSaveList);
                            }

                            mAdapter.setList(mSearchList);
                            mAdapter.notifyDataSetChanged();
                            popEvaluate.dismiss();

                        }
                        break;
                }
            }
        });

    }

    private List<HomeBean> mHomeList = new ArrayList<>();

    private Collection<? extends HomeBean> getPopData() {
        for (int i = 0; i < editTitles2.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(editTitles2[i]);
            homeBean.setContent("");
            homeBean.setHint("输入查询" + editTitles2[i]);
            homeBean.setItemType(HomeBean.TYPE_4);
            mHomeList.add(homeBean);
        }


        HomeBean homeBean = new HomeBean();
        homeBean.setItemType(HomeBean.TYPE_6);
        mHomeList.add(homeBean);

        return mHomeList;
    }


}















