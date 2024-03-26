package com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.adapter.DeviceInfoAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceInBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.adapter.DeviceInfoDetailAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.bean.DeviceInDetailBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/25 08:34
 * 注释：设备信息明细
 */
public class DeviceInfoDetailFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private DeviceInBean deviceInBean;
    private List<DeviceInDetailBean.DataBean> mList = new ArrayList<>();
    private DeviceInfoDetailAdapter mAdapter;

    public static DeviceInfoDetailFragment newInstance(Bundle bundle) {
        DeviceInfoDetailFragment fragment = new DeviceInfoDetailFragment();
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

        deviceInBean = (DeviceInBean) getArguments().getSerializable(FinalClass.bean);
        LatteLogger.d("deviceInBean", GsonBuildUtil.GsonBuilder(deviceInBean));
        tvTitle.setText(deviceInBean.机台编号 + "_" + deviceInBean.机台类型 + "设备明细");
        initRecyclerview();
        getHttpData();
    }

    private void getHttpData() {
        EasyHttp.post(Constants.deviceInfoDetail(apiToken, deviceInBean.生产单号, deviceInBean.零件编码))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getHttpData", s);
                        DeviceInDetailBean bean = GsonBuildUtil.create().fromJson(s, DeviceInDetailBean.class);
                        if (mList.size() > 0) {
                            mList.clear();
                        }

                        LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(bean));
                        DeviceInDetailBean.DataBean dataBean = new DeviceInDetailBean.DataBean();
                        dataBean.itemType = DeviceInDetailBean.DataBean.TYPE_1;
                        dataBean.deviceInBean = deviceInBean;
                        mList.add(dataBean);

                        DeviceInDetailBean.DataBean dataBean2 = new DeviceInDetailBean.DataBean();
                        dataBean2.itemType = DeviceInDetailBean.DataBean.TYPE_2;
                        mList.add(dataBean2);

                        for (int i = 0; i < bean.data.size(); i++) {
                            bean.data.get(i).itemType =DeviceInDetailBean.DataBean.TYPE_3;
                            DeviceInDetailBean.DataBean dataBean3 = bean.data.get(i);
                            mList.add(dataBean3);
                        }

                        mAdapter.setList(mList);

                    }
                });
    }

    private void initRecyclerview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
       mAdapter = new DeviceInfoDetailAdapter();
       mRecyclerView.setAdapter(mAdapter);
    }
}
































