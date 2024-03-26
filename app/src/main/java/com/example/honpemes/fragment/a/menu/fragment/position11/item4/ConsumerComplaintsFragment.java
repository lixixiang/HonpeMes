package com.example.honpemes.fragment.a.menu.fragment.position11.item4;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.bean.MeterManagerBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.adapter.ConsumerAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.bean.ConsumerBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.child.ConsumerDetailFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：asus on 2024/1/2 09:59
 * 注释：客诉
 */
public class ConsumerComplaintsFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private ConsumerAdapter mAdapter;
    private List<ConsumerBean.DataBean> mList = new ArrayList<>();

    public static ConsumerComplaintsFragment newInstance(Bundle bundle) {
        ConsumerComplaintsFragment fragment = new ConsumerComplaintsFragment();
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

        initRecyclerView();
        getHttpData();
        refreshLayout.autoRefresh();
        refresh();
    }

    private void refresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getHttpData();
            }
        });
    }

    private void getHttpData() {
        EasyHttp.post(Constants.ConsumerComplaints(apiToken, page))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }


                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();

                    }

                    @Override
                    public void onError(ApiException e) {
                    }


                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                ConsumerBean bean = GsonBuildUtil.create().fromJson(s, ConsumerBean.class);
                                LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(bean));
                                if (mList.size() > 0) {
                                    mList.clear();
                                }
                                mList.addAll(bean.data);
                                // 使用 HashSet 去除重复对象
                                HashSet<ConsumerBean.DataBean> uniqueSet = new HashSet<>(mList);
                                List<ConsumerBean.DataBean> uniqueList = new ArrayList<>(uniqueSet);
                                mAdapter.setList(uniqueList);
                            } else {
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

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ConsumerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ConsumerBean.DataBean dataBean = mAdapter.getData().get(position);
                dataBean.Expanded = !dataBean.Expanded;
                mAdapter.getData().set(position, dataBean);
                mAdapter.notifyItemChanged(position);
//                if (dataBean.Expanded) {
//                    getItemHttpData();
//                }
                bundle.putString(FinalClass.title,tvTitle.getText().toString());
                bundle.putSerializable(FinalClass.bean,dataBean);

                start(ConsumerDetailFragment.newInstance(bundle));
            }
        });

    }

    private void getItemHttpData() {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }




}
















