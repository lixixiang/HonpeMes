package com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.adapter.DetailAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.bean.DetailBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus on 2023/12/21 10:22
 * 注释：
 */
public class ProcessRecordDetailFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private DetailAdapter mAdapter;
    private ProcessingRecordBean.DataBean dataBean;
    private List<DetailBean.DataBean> mList = new ArrayList<>();
    private long TimeSum;

    public static ProcessRecordDetailFragment newInstance(Bundle bundle) {
        ProcessRecordDetailFragment fragment = new ProcessRecordDetailFragment();
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
        dataBean = (ProcessingRecordBean.DataBean) getArguments().getSerializable(FinalClass.bean);
        LatteLogger.d("dataBeandataBean",GsonBuildUtil.GsonBuilder(dataBean));

        initRecyclerview();
        getHttpData();

    }

    private void initRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new DetailAdapter();
        recyclerview.setAdapter(mAdapter);

    }

    private void getHttpData() {
        EasyHttp.post(Constants.Mach_WorkTaskSubtable(getArguments().getInt("id"), apiToken))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        DetailBean detailBean = GsonBuildUtil.create().fromJson(s, DetailBean.class);
                        LatteLogger.d("dataBeandataBean",GsonBuildUtil.GsonBuilder(detailBean));

                        DetailBean.DataBean entity = new DetailBean.DataBean();
                        entity.dataBean = dataBean;
                        entity.itemType = DetailBean.DataBean.TYPE_1;
                        mList.add(entity);

                        DetailBean.DataBean entity2 = new DetailBean.DataBean();
                        entity2.itemType = DetailBean.DataBean.TYPE_2;
                        for (int i = 0; i < detailBean.data.size(); i++) {
                            String strStartTime = detailBean.data.get(i).startTime.replace("T", " ");
                            String strEndTime = detailBean.data.get(i).endTime.replace("T", " ");

                            Date startDate = DateUtil.setDate(DateUtil.ymdhms, strStartTime);
                            Date endDate =  DateUtil.setDate(DateUtil.ymdhms, strEndTime);

                            long diffInMillis = endDate.getTime() - startDate.getTime();
                            LatteLogger.d("testdiffInMillis",diffInMillis);
                            TimeSum = TimeSum +diffInMillis;
                        }

                        dataBean.diffTime = TimeSum;
                        entity2.dataBean = dataBean;
                        mList.add(entity2);

                        for (int i = 0; i < detailBean.data.size(); i++) {
                            DetailBean.DataBean entity3 = detailBean.data.get(i);
                            entity3.itemType = DetailBean.DataBean.TYPE_3;
                            mList.add(entity3);
                        }

                        mAdapter.setList(mList);
                    }
                });
    }
}






















