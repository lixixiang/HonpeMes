package com.example.honpemes.fragment.a.menu.fragment.position3.item3;

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
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceWorkBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item3.adapter.CurrentTaskAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item3.bean.CurrentTaskBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/25 12:06
 * 注释：当前任务
 */
public class CurrentTaskFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private CurrentTaskAdapter mAdapter;

    public static CurrentTaskFragment newInstance(Bundle bundle) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
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

        initRecyclerview();
        getHttpData();

    }

    private void getHttpData() {
        EasyHttp.post(Constants.currentTask(apiToken))
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
                        CurrentTaskBean bean = GsonBuildUtil.create().fromJson(s, CurrentTaskBean.class);
                        LatteLogger.d("JSONException", GsonBuildUtil.GsonBuilder(bean));

                        mAdapter.setList(bean.data);
                    }
                });
    }

    private void initRecyclerview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new CurrentTaskAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                CurrentTaskBean.DataBean dataBean = mAdapter.getData().get(position);
                dataBean.isOpen = !dataBean.isOpen;
                mAdapter.getData().set(position, dataBean);
                mAdapter.notifyItemChanged(position);

            }
        });
    }


}


















































