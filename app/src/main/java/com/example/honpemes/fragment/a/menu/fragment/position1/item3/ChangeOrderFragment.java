package com.example.honpemes.fragment.a.menu.fragment.position1.item3;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item3.adapter.ChangeOrderAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item3.bean.ChangeOrderBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
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
 * Created by Lixixiang on 2023/3/7 10:56
 * 订单变更
 */
public class ChangeOrderFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private ChangeOrderAdapter mAdapter;
    private int index = 0;
    private String enterTime, loadMoreTime;

    public static ChangeOrderFragment newInstance(Bundle bundle) {
        ChangeOrderFragment fragment = new ChangeOrderFragment();
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
        tvTitle.setText(getArguments().getString(FinalClass.title));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ChangeOrderAdapter();
        recyclerView.setAdapter(mAdapter);
        enterTime = DateUtil.ymdhms.format(new Date());
        refreshLayout.autoRefresh();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {//下拉刷新
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
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void initLoadMore() {
        LatteLogger.d("testinitLoadMore===" + enterTime + " ====loadMoreTime===  " + loadMoreTime);

        EasyHttp.post(Constants.RefreshChangeOrder(apiToken, enterTime, loadMoreTime))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                ChangeOrderBean bean = GsonBuildUtil.create().fromJson(s, ChangeOrderBean.class);
                                LatteLogger.d("testddddffe", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getData().size() > 0) {
                                    mAdapter.addData(bean.getData());
                                } else {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initHttp() {
        EasyHttp.post(Constants.ChangeOrder(apiToken, index))
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                ChangeOrderBean bean = GsonBuildUtil.create().fromJson(s, ChangeOrderBean.class);
                                LatteLogger.d("testddddffe", GsonBuildUtil.GsonBuilder(bean));
                                mAdapter.setList(bean.getData());
                            } else {
                                if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
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



}






































