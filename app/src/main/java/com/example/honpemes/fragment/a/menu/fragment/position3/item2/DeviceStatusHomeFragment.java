package com.example.honpemes.fragment.a.menu.fragment.position3.item2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.com_adapter.ComTabAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter.DeviceStatusHomeAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceStatusBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.detail.DeviceDetailFragment;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/2 12:05
 * 设备状态
 */
public class DeviceStatusHomeFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.head_horizon_recyclerview)
    RecyclerView headHorizonRecyclerview;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;

    private DeviceStatusHomeAdapter mAdapter;
    private ComTabAdapter mHeadStatusAdapter;


    private String[] strHeads = {"编号/型号", "待机数量", "状态"};
    private Timer timer = new Timer();
    private MyHomeTimer myTimer = new MyHomeTimer();
    private int[] headColors = {R.color.black_l, R.color.black_l, R.color.black_l};

    private List<DeviceStatusBean.DataBean> mList = new ArrayList<>();

    public static DeviceStatusHomeFragment newInstance(Bundle bundle) {
        DeviceStatusHomeFragment fragment = new DeviceStatusHomeFragment();
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
        initHead();
        initData();
        tvTimes.setVisibility(View.VISIBLE);
        timer.schedule(myTimer, FinalClass.Delay, FinalClass.Period);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new DeviceStatusHomeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                bundle.putSerializable(FinalClass.bean, mAdapter.getData().get(position));
                start(DeviceDetailFragment.newInstance(bundle));
                tvEnd.setText("");
                stopMsg();
            }
        });

    }

    private void initHead() {
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        headHorizonRecyclerview.setLayoutManager(manager);
        mHeadStatusAdapter = new ComTabAdapter(headColors);
        headHorizonRecyclerview.setAdapter(mHeadStatusAdapter);
        mHeadStatusAdapter.setList(getHeadData());

    }

    List<HomeBean> HeadList = new ArrayList<>();

    private Collection<? extends HomeBean> getHeadData() {
        for (int i = 0; i < strHeads.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(strHeads[i]);
            homeBean.setSpanSize(1);
            HeadList.add(homeBean);
        }
        return HeadList;
    }


    private void initData() {
        EasyHttp.post(Constants.PosERPDataTableRecord + apiToken + "&strSQL=" + Constants.PosDataTableCurrentRecord())
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
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                DeviceStatusBean bean = GsonBuildUtil.create().fromJson(s, DeviceStatusBean.class);
                                //   LatteLogger.d("DeviceStatusBean", GsonBuildUtil.GsonBuilder(bean));
                                mList.clear();

                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A001"))
                                        mList.add(dataBean);
                                }
                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A002"))
                                        mList.add(dataBean);
                                }
                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A003"))
                                        mList.add(dataBean);
                                }
                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A004"))
                                        mList.add(dataBean);
                                }
                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A005"))
                                        mList.add(dataBean);
                                }
                                for (DeviceStatusBean.DataBean dataBean : bean.getData()) {
                                    if (dataBean.get机台编号().contains("A006"))
                                        mList.add(dataBean);
                                }

                                String strStatus = DateUtil.ymdhms.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(bean.getData().get(0).get入库时间())));
                                tvTimes.setText(strStatus);


                                LatteLogger.d("DeviceStatusBean", GsonBuildUtil.GsonBuilder(mList));
                                mAdapter.setList(mList);
                            } else {
                                if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                    bundle.putString(FinalClass.title, "设备状态");
                                    startWithPop(LoginFragment.newInstance(bundle));
                                }
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    protected void initView() {
        mToolbar.setVisibility(View.VISIBLE);
        initToolbarNav(llBack);

        tvTitle.setText(getArguments().getString(FinalClass.title));

    }


    long num = 10;

    public class MyHomeTimer extends TimerTask {

        @Override
        public void run() {
            num--;
            tvEnd.setText(num+" s");
            if (num == 0) {
                stopMsg();
                try {
                    Thread.sleep(1000);
                    tvEnd.setText("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }

        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //间格10s请求一次
            initData();
            startMsg();
        }
    };

    private void startMsg() {
        num = 10;
        timer = new Timer();
        myTimer = new MyHomeTimer();
        timer.schedule(myTimer, FinalClass.Delay, FinalClass.Period);
    }

    private void stopMsg() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (myTimer != null) {
            myTimer.cancel();
            myTimer = null;
        }
    }



    @Override
    public void onDestroyView() {
        stopMsg();
        handler.removeCallbacksAndMessages(null);
        super.onDestroyView();

    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.DEVICE_TIME_STATUS://定时器
                startMsg();
                break;
        }
    }
}
