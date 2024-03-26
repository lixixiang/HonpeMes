package com.example.honpemes.fragment.a.menu.fragment.position1.item1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.adapter.OrderItemAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.TeamBean;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.net.ApiTokenDelayedUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * @author: asus
 * @date: 2022/11/10
 * @Description: 待审核订单
 */
public class OrderItemFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;

    public OrderItemAdapter mAdapter;
    public TeamBean mTeamBean;

    public static OrderItemFragment newInstance(Bundle bundle) {
        OrderItemFragment fragment = new OrderItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new OrderItemAdapter(mTeamBean);
        mRecyclerview.setAdapter(mAdapter);
        getListData();
    }

    @Override
    protected void initView() {
        String strData = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_TEAM, "");
        LatteLogger.d("strData", strData);
        mTeamBean = GsonBuildUtil.create().fromJson(strData, TeamBean.class);

        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));

    }

    private void getListData() {
        EasyHttp.post(Constants.waitPassOrder(apiToken))
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
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt(FinalClass.Tag) == 0) {
                                if (object.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    ApiTokenDelayedUtil.HttpLogin();
                                } else {
                                    ToastUtil.getInstance().showToast(object.getString(FinalClass.Message));
                                }
                            } else {
                                OrderEntity mItemBean = GsonBuildUtil.create().fromJson(s, OrderEntity.class);
                                LatteLogger.d("testData", GsonBuildUtil.GsonBuilder(mItemBean));
                                mAdapter.setList(mItemBean.getData());
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

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EVENT:
                bundle = (Bundle) event.getData();
                sureHttp(bundle.getString(FinalClass.ORDER_ID),bundle.getInt(FinalClass.POSITION));
                break;

        }
    }

    private void sureHttp(String orderId,int pos) {
        EasyHttp.post(Constants.PassAudit(apiToken,orderId))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testefdf0",s);

                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                               mAdapter.notifyItemRemoved(pos);
                            }else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}











