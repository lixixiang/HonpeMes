package com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.bean.OperatorAssBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.adapter.OperatorAssDetailAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.bean.OperatorAssDetailBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/22 09:57
 * 注释：操机指派详情
 */
public class OperatorAssDetailFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    private OperatorAssDetailAdapter mAdapter;
    private OperatorAssBean dataBean;
    private List<OperatorAssDetailBean.DataBean> mList = new ArrayList<>();
    private long TimeSum;

    public static OperatorAssDetailFragment newInstance(Bundle bundle) {
        OperatorAssDetailFragment fragment = new OperatorAssDetailFragment();
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
        dataBean = (OperatorAssBean) getArguments().getSerializable(FinalClass.bean);
        LatteLogger.d("dataBeandataBean", GsonBuildUtil.GsonBuilder(dataBean));

        initRecyclerview();
        getHttpData();
    }

    private void initRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new OperatorAssDetailAdapter();
        recyclerview.setAdapter(mAdapter);


    }

    private void getHttpData() {
        EasyHttp.post(Constants.OperatorAssDetail(dataBean.生产单号, apiToken))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        OperatorAssDetailBean detailBean = GsonBuildUtil.create().fromJson(s, OperatorAssDetailBean.class);


                        OperatorAssDetailBean.DataBean bean = new OperatorAssDetailBean.DataBean();
                        bean.itemType = OperatorAssDetailBean.DataBean.TYPE_1;
                        bean.operatorAssBean = dataBean;
                        mList.add(bean);

                        detailBean.data.get(0).itemType = OperatorAssDetailBean.DataBean.TYPE_2;
                        mList.add(detailBean.data.get(0));


                        detailBean.data.remove(0);


                        for (int i = 0; i < detailBean.data.size(); i++) {
                            detailBean.data.get(i).itemType = OperatorAssDetailBean.DataBean.TYPE_3;
                            mList.add(detailBean.data.get(i));
                        }

                        mAdapter.setList(mList);
                        LatteLogger.d("dataBeandataBean", GsonBuildUtil.GsonBuilder(mAdapter.getData()));
                    }
                });
    }
}
























