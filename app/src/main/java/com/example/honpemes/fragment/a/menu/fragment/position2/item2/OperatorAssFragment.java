package com.example.honpemes.fragment.a.menu.fragment.position2.item2;

import android.os.Bundle;
import android.view.View;
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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.adapter.OperatorAssAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.bean.OperatorAssBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.OperatorAssDetailFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.adapter.ProcessingRecordAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.ProcessRecordDetailFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/21 17:43
 * 注释：操机指派
 */
public class OperatorAssFragment extends BaseBackFragment {
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
    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_3)
    TextView item3;
    @BindView(R.id.item_4)
    TextView item4;
    @BindView(R.id.item_5)
    TextView item5;
    @BindView(R.id.item_6)
    TextView item6;
    @BindView(R.id.ll_process_record_bg)
    LinearLayout llProcessRecordBg;
    @BindView(R.id.content_recyclerView)
    RecyclerView contentRecyclerView;

    private String curMonth, firstDay, lastDay,curDate;
    public int oneDay = 0; //这个初始日子
    private OperatorAssAdapter mAdapter;
    private List<OperatorAssBean> mList = new ArrayList<>();

    public static OperatorAssFragment newInstance(Bundle bundle) {
        OperatorAssFragment fragment = new OperatorAssFragment();
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
        item1.setText("生产单号");
        item2.setText("机台\n类型");
        item3.setText("制作\n数量");
        item4.setText("执行\n次数");
        item5.setText("开料尺寸\n长x宽x高");

        llProcessRecordBg.setVisibility(View.VISIBLE);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new OperatorAssAdapter();
        contentRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                bundle.putString(FinalClass.title,tvTitle.getText().toString() + "明细");
                bundle.putSerializable(FinalClass.bean,mAdapter.getData().get(position));
                start(OperatorAssDetailFragment.newInstance(bundle));
            }
        });
    }

    private void getHttpData() {
        EasyHttp.post(Constants.OperatorAss(firstDay,lastDay,apiToken))
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
                            JSONObject object = new JSONObject(s);
                            if (object.getInt(FinalClass.Tag) == 0) {
                                if (object.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(object.getString(FinalClass.Message));
                                }
                            } else {
                                JSONArray dataArr = object.getJSONArray("Data");
                                for (int i = 0; i < dataArr.length(); i++) {
                                    JSONObject dataBean = dataArr.getJSONObject(i);
                                    OperatorAssBean bean = new OperatorAssBean();
                                    bean.rowid = dataBean.getInt("rowid");
                                    bean.仓库开料出单时间 = dataBean.getString("仓库开料出单时间");
                                    bean.制作数量 = dataBean.getInt("制作数量");
                                    bean.开料备注 = dataBean.getString("开料备注");
                                    bean.开料尺寸 = dataBean.getString("开料尺寸");
                                    bean.执行次数 = dataBean.getInt("执行次数");
                                    bean.指派人员 = dataBean.getString("指派人员");
                                    bean.机台类型 = dataBean.getString("机台类型");
                                    bean.材料宽度 = dataBean.getDouble("材料宽度");
                                    bean.生产单号 = dataBean.getString("生产单号");
                                    bean.零件编码 = dataBean.getString("零件编码");
                                    bean.补数申请次数 = dataBean.getInt("补数申请次数");
                                    bean.指派时间 = dataBean.getString("指派时间");
                                    bean.材料长度 = dataBean.getDouble("材料长度");
                                    bean.材料高度 = dataBean.getDouble("材料高度");
                                    mList.add(bean);
                                }

                                mAdapter.setList(mList);
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(oneDay) + " 00:00:00";
        lastDay = DateUtil.getLastDayOfMonth(oneDay)  + " 23:59:59";
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




































