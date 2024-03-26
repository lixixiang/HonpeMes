package com.example.honpemes.fragment.a.menu.fragment.position2.item4;

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
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.adapter.ProcessingRecordAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.ProcessRecordDetailFragment;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
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
 * 作者：asus on 2023/12/21 09:09
 * 注释：加工记录
 */
public class ProcessingRecordFragment extends BaseBackFragment {

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
    private ProcessingRecordAdapter mAdapter;

    public static ProcessingRecordFragment newInstance(Bundle bundle) {
        ProcessingRecordFragment fragment = new ProcessingRecordFragment();
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
        item1.setText("日期");
        item2.setText("班别");
        item3.setText("编号");
        item4.setText("加工数\n待机数");
        item5.setText("使用部门\n制作组别");

        llProcessRecordBg.setVisibility(View.VISIBLE);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ProcessingRecordAdapter();
        contentRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                bundle.putString(FinalClass.title,tvTitle.getText().toString() + "明细");
                bundle.putInt("id",mAdapter.getData().get(position).id);
                bundle.putSerializable(FinalClass.bean,mAdapter.getData().get(position));
                start(ProcessRecordDetailFragment.newInstance(bundle));
            }
        });
    }

    private void getHttpData() {
        EasyHttp.post(Constants.Mach_WorkTask(firstDay,lastDay,apiToken))
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
                                ProcessingRecordBean processingRecordBean =  GsonBuildUtil.create().fromJson(s, ProcessingRecordBean.class);
                                mAdapter.setList(processingRecordBean.data);
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




































