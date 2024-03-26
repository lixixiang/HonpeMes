package com.example.honpemes.fragment.a.menu.fragment.position9.item1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.dialog.CustomDialog;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus  on 2024/3/8 11:09
 * 注释：客户资料
 */
public class CustomInfoFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    private CustomInfoAdapter mAdapter;
    private List<CustomInfoBean> mList = new ArrayList<>();


    public static CustomInfoFragment newInstance(Bundle bundle) {
        CustomInfoFragment fragment = new CustomInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_titleba_refresh_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        tvEnd.setText("新增");

        mAdapter = new CustomInfoAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);

        CustomInfoBean bean1 = new CustomInfoBean();
        bean1.name = "RMKJ";
        bean1.status = "使用";
        bean1.cord = "0006";
        bean1.lister = "LXF";
        bean1.saveMan = "李小凤";
        bean1.time = "2024.03.06 15:12";
        bean1.team = "国内业务部";

        CustomInfoBean bean2 = new CustomInfoBean();
        bean2.name = "VAST";
        bean2.status = "停用";
        bean2.cord = "0006";
        bean2.lister = "LXF";
        bean2.saveMan = "李小凤";
        bean2.time = "2024.03.06 15:12";
        bean2.team = "国内业务部";

        CustomInfoBean bean3 = new CustomInfoBean();
        bean3.name = "VAST";
        bean3.status = "未使用";
        bean3.cord = "0006";
        bean3.lister = "LXF";
        bean3.saveMan = "李小凤";
        bean3.time = "2024.03.06 15:12";
        bean3.team = "国内业务部";

        mList.add(bean1); mList.add(bean2); mList.add(bean3);

        mAdapter.setList(mList);

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                CustomDialog dialog = new CustomDialog(_mActivity, mAdapter.getData().get(position).name, "修改", "删除", new CustomDialog.OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {
                        ToastUtil.getInstance().showToast("onPositiveClick");
                    }

                    @Override
                    public void onNegativeClick() {
                        ToastUtil.getInstance().showToast("onNegativeClick");
                    }
                });

                dialog.show();
            }
        });
    }
}





























