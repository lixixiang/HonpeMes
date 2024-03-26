package com.example.honpemes.fragment.a.menu.fragment.position9.item2;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item2.adapter.BaseCustomerAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position9.item2.bean.CustomerBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/7 14:41
 * 客供资料
 */
public class CustomerComplaintFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    String enterTime, loadMoreTime;
    BaseCustomerAdapter mAdapter;
    int index = 0;

    public static CustomerComplaintFragment newInstance(Bundle bundle) {
        CustomerComplaintFragment fragment = new CustomerComplaintFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_titlebar_head_foot_refresh_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        enterTime = DateUtil.ymdhms.format(new Date());
        tvTitle.setText(getArguments().getString(FinalClass.title));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new BaseCustomerAdapter();
        mRecyclerview.setAdapter(mAdapter);
        refreshLayout.autoRefresh();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                index = 0;
                initHttp();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreTime = DateUtil.ymdhms.format(new Date());
                initLoadMore();
            }
        });
    }

    private void initLoadMore() {
        EasyHttp.post("http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM MakeProductionOrderChangeNotice where " +
                tabDate +
                ">'" +
                "2022-11-21 09:39:03" +
                "' and " +
                tabDate +
                "<='" +
                "2022-11-21 09:39:03" +
                "' order by " +
                tabDate +
                " desc")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                CustomerBean bean = GsonBuildUtil.create().fromJson(s, CustomerBean.class);
                                LatteLogger.d("testddddffe", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getData().size() > 0) {
                                    mAdapter.addData(bean.getData());
                                } else {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                                //  mAdapter.setList(bean.getData());
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private String tabName, tabDate;

    private void initHttp() {
        if (tvTitle.getText().toString().contains("客诉")) {
            tabName = "生产订单客诉管理";
            tabDate = "投诉日期";
        } else if (tvTitle.getText().toString().contains("客供资料")) {
            tabName = "生产订单客供资料";
            tabDate = "上传日期";
        }

        EasyHttp.post("http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM " +
                tabName +
                " order by " +
                tabDate +
                " desc offset " +
                index +
                "*20 rows fetch next 20 rows only")
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testdfdd", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                CustomerBean bean = GsonBuildUtil.create().fromJson(s, CustomerBean.class);
                                if (tvTitle.getText().toString().contains("客诉")) {
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        bean.getData().get(i).setItemType(CustomerBean.DataBean.TYPE_1);
                                    }
                                } else if (tvTitle.getText().toString().contains("客供资料")) {
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        bean.getData().get(i).setItemType(CustomerBean.DataBean.TYPE_2);
                                    }
                                }

                                mAdapter.setList(bean.getData());
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

