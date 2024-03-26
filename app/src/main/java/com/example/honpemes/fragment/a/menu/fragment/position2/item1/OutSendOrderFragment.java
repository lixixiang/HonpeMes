package com.example.honpemes.fragment.a.menu.fragment.position2.item1;

import android.os.Bundle;
import android.view.View;
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
import com.example.honpemes.fragment.a.menu.fragment.position2.item1.adapter.OutSendOrderAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item1.bean.OutSendOrderBean;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/21 15:18
 * 注释：项目外发
 */
public class OutSendOrderFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_out_send_order)
    LinearLayout llOutSendOrder;
    @BindView(R.id.m_recyclerView)
    RecyclerView contentRecyclerView;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private OutSendOrderAdapter mAdapter;
    private String startTime,endTime,url;
    private List<OutSendOrderBean.DataBean> mSaveList = new ArrayList<>();
    private int num = 0;

    public static OutSendOrderFragment newInstance(Bundle bundle) {
        OutSendOrderFragment fragment = new OutSendOrderFragment();
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
        tvEnd.setText("筛选");
        tvTitle.setText(getArguments().getString(FinalClass.title));
        startTime = DateUtil.ymdhms.format(new Date());
        initRecyclerview();
        getHttpData(1);
        refresh();
    }

    private void refresh() {
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            endTime = DateUtil.ymdhms.format(new Date());
            // 执行刷新数据的操作
            getHttpData(2);

            startTime = DateUtil.ymdhms.format(new Date());

            refreshLayout.finishRefresh();

        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                LatteLogger.d("setOnLoadMoreListener",num);
                getHttpData(3);
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void initRecyclerview() {
        llOutSendOrder.setVisibility(View.VISIBLE);
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new OutSendOrderAdapter();
        contentRecyclerView.setAdapter(mAdapter);
    }

    private void getHttpData(int type) {
        if (type == 1) {
            url = Constants.PosDataTableRecord(apiToken);
        } else if (type == 2) {
            url = Constants.PosDataTableRecordRefresh(startTime, endTime, apiToken);
        } else {
            url = Constants.PosDataTableRecordOnLoadMore(String.valueOf(num),apiToken);
        }

        EasyHttp.post(url)
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
                            if (object.getInt("Tag") == 1) {
                                OutSendOrderBean bean = GsonBuildUtil.create().fromJson(s, OutSendOrderBean.class);
                                LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(bean));

                                mSaveList.addAll(bean.data);
                                if (mSaveList.size() > mAdapter.getData().size()) {
                                    num++;
                                }

                                Set<OutSendOrderBean.DataBean> uniqueData = new HashSet<>(mSaveList);
                                mAdapter.setList(uniqueData);
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


}
























