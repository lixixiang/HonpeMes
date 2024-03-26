package com.example.honpemes.fragment.a.menu.fragment.position11.item1;

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
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter.OrderFormAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.OrderFormBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.comm.FormDetailFragment;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/7 15:55
 * 订单看板
 */
public class OrderFormFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;

    private OrderFormAdapter mOrderFormAdapter;
    private List<OrderFormBean> mList;
    private OrderFormBean orderFormBean;

    public static OrderFormFragment newInstance(Bundle bundle) {
        OrderFormFragment fragment = new OrderFormFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        initRecyclerView();
        httpNet();
    }

    private void initRecyclerView() {
        mList = new ArrayList<>();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mOrderFormAdapter = new OrderFormAdapter();
        mRecyclerview.setAdapter(mOrderFormAdapter);

        mOrderFormAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_card_chart_bg:
                        statusList.clear();
                        statusList.addAll(orderFormBean.getData().get加工机台状态());
                        bundle.putString("name","全厂机台");
                        bundle.putSerializable(FinalClass.bean,statusList);
                        start(FormDetailFragment.newInstance(bundle));
                        break;
                }
            }
        });
    }

    private void httpNet() {
        EasyHttp.post(Constants.PostReportRecord + apiToken)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("PostReportRecord", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                orderFormBean = GsonBuildUtil.create().fromJson(s, OrderFormBean.class);
                                LatteLogger.d("orderFormBean", GsonBuildUtil.GsonBuilder(orderFormBean));
                                mList.add(orderFormBean);
                                mOrderFormAdapter.setList(mList);
//Double.parseDouble(bean.getNum()) / Double.parseDouble(bean.getSum()
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                                if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    bundle.putString(FinalClass.title, tvTitle.getText().toString());
                                    startWithPop(LoginFragment.newInstance(bundle));
                                    return;
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


    ArrayList<OrderFormBean.DataBean.加工机台状态Bean> statusList = new ArrayList<>();

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EVENT:
                bundle = (Bundle) event.getData();
                String name = bundle.getString("name");
                statusList.clear();

                for (int i = 0; i < orderFormBean.getData().get加工机台状态().size(); i++) {
                    if ("制作数量".equals(name)) {
                        if ("●".equals(orderFormBean.getData().get加工机台状态().get(i).get机台状态())) {
                            statusList.add(orderFormBean.getData().get加工机台状态().get(i));
                        }
                    } else  if ("空闲数量".equals(name)) {
                        if ("▲".equals(orderFormBean.getData().get加工机台状态().get(i).get机台状态())) { //空闲数量
                            statusList.add(orderFormBean.getData().get加工机台状态().get(i));
                        }
                    }else  if ("检修数量".equals(name)) {
                        if ("■".equals(orderFormBean.getData().get加工机台状态().get(i).get机台状态())) { //空闲数量
                            statusList.add(orderFormBean.getData().get加工机台状态().get(i));
                        }
                    }
                }

                bundle.putSerializable(FinalClass.bean,statusList);
                start(FormDetailFragment.newInstance(bundle));
                break;
        }
    }
}

