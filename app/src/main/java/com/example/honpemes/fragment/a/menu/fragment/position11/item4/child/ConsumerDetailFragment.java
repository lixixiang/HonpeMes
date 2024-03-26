package com.example.honpemes.fragment.a.menu.fragment.position11.item4.child;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.bean.ConsumerBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.child.adapter.ConsumerDetailAdapter;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus on 2024/1/3 09:57
 * 注释：客诉列表
 */
public class ConsumerDetailFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private ConsumerDetailAdapter mAdapter;
    private ConsumerBean.DataBean dataBean;
    private List<ConsumerBean.DataBean> mList = new ArrayList<>();
    private String startTime, endTime;


    public static ConsumerDetailFragment newInstance(Bundle bundle) {
        ConsumerDetailFragment fragment = new ConsumerDetailFragment();
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


    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        dataBean = (ConsumerBean.DataBean) getArguments().getSerializable(FinalClass.bean);
        LatteLogger.d("dataBeandataBean", GsonBuildUtil.GsonBuilder(dataBean));
        startTime = dataBean.投诉日期 +  " 00:00:00";
        endTime = DateUtil.ymdhms.format(new Date());

        initRecyclerview();

        getHttpData();

    }

    private void getHttpData() {

        EasyHttp.post(Constants.ListConsumerComplaints(apiToken, startTime, endTime))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                LatteLogger.d("getItemHttpData", s);

                                ConsumerBean bean = GsonBuildUtil.create().fromJson(s, ConsumerBean.class);

                                LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(bean));
                                if (mList.size() > 0) {
                                    mList.clear();
                                }

                                mList.add(0,dataBean);

                                for (ConsumerBean.DataBean dataBean : bean.data) {
                                    mList.add(dataBean);
                                }

//                                mList.addAll(bean.data);
//                                // 使用 HashSet 去除重复对象
//                                HashSet<ConsumerBean.DataBean> uniqueSet = new HashSet<>(mList);
//                                List<ConsumerBean.DataBean> uniqueList = new ArrayList<>(uniqueSet);

                                mAdapter.setList(mList);
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

    private void initRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ConsumerDetailAdapter();
        recyclerview.setAdapter(mAdapter);


    }

}






























