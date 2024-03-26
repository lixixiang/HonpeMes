package com.example.honpemes.fragment.a.menu.fragment.position3.item1;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.adapter.DeviceInfoAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceInBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceWorkBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.DeviceInfoDetailFragment;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/23 11:45
 * 注释：设备信息
 */
public class DeviceInfoFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.pieChat)
    PieChart pieChat;
    @BindView(R.id.ll_out_send_order)
    LinearLayout llOutSendOrder;

    private DeviceInfoAdapter mAdapter;
    private int runSum,stopSum,restSum,infoSum;
    private List<Integer> mListSum = new ArrayList<>();

    public static DeviceInfoFragment newInstance(Bundle bundle) {
        DeviceInfoFragment fragment = new DeviceInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_device_info;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvEnd.setText("筛选");
        tvTitle.setText(getArguments().getString(FinalClass.title));

        initRecyclerview();
        getHttpData();
    }

    private void getHttpData() {
        EasyHttp.post(Constants.deviceInfo(apiToken))
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

                        DeviceWorkBean bean = GsonBuildUtil.create().fromJson(s, DeviceWorkBean.class);

                        LatteLogger.d("JSONException", GsonBuildUtil.GsonBuilder(bean));

                        mAdapter.setList(bean.data);

                        for (int i = 0; i < bean.data.size(); i++) {
                            if (bean.data.get(i).机台状态.contains("▲")) {
                                restSum++;
                            } else if (bean.data.get(i).机台状态.contains("●")) {
                                runSum++;
                            } else if (bean.data.get(i).机台状态.contains("■")) {
                                stopSum++;
                            }
                        }
                        infoSum = restSum + runSum + stopSum;
                        mListSum.add(runSum);
                        mListSum.add(restSum);
                        mListSum.add(stopSum);
                        ChartUtil.setPieChart2(pieChat, mListSum,infoSum, DataClass.COLORS4);
                    }
                });
    }

    private void initRecyclerview() {
        llOutSendOrder.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new DeviceInfoAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                bundle.putSerializable(FinalClass.bean, mAdapter.getData().get(position));
                start(DeviceInfoDetailFragment.newInstance(bundle));
            }
        });
    }
}















