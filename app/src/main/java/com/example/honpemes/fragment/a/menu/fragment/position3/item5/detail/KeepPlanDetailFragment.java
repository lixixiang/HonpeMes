package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.R;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.com_adapter.ComTabAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.adapter.KeepPlanMainAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepDetailBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepPlanBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/7 11:59
 */
public class KeepPlanDetailFragment extends BaseBackFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.head_horizon_recyclerview)
    RecyclerView headHorizonRecyclerview;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;

    private KeepPlanMainAdapter mAdapter;

    private String[] titles = {"机台编号", "部门", "保养人", "最近保养", "保养进度"};
    private int[] headColors = {R.color.black, R.color.black, R.color.black, R.color.black, R.color.black};
    private ComTabAdapter mHeadAdapter;
    private List<HomeBean> headList = new ArrayList<>();

    public static KeepPlanDetailFragment newInstance() {
        KeepPlanDetailFragment fragment = new KeepPlanDetailFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
    }

    @Override
    protected void initView() {
        mToolbar.setVisibility(View.GONE);
        rgDate.setVisibility(View.GONE);
        initHeadRecyclerView();
        initContentRecyclerView();
    }

    private void initHeadRecyclerView() {
        headHorizonRecyclerview.setLayoutManager(new GridLayoutManager(_mActivity, titles.length));
        mHeadAdapter = new ComTabAdapter(headColors, 14);
        headHorizonRecyclerview.setAdapter(mHeadAdapter);
        mHeadAdapter.setList(getHead());
    }


    private Collection<? extends HomeBean> getHead() {
        for (int i = 0; i < titles.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(titles[i]);
            homeBean.setSpanSize(1);
            headList.add(homeBean);
        }
        return headList;
    }

    private void initContentRecyclerView() {
        mAdapter = new KeepPlanMainAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(getContent());

    }

    private List<KeepPlanBean> mList = new ArrayList<>();
    private int strProgress;

    private Collection<? extends BaseNode> getContent() {
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                strProgress = 0;
            } else if (i == 1) {
                strProgress = 3;
            } else {
                strProgress = 16;
            }

            List<BaseNode> secondList = new ArrayList<>();

            for (int j = 0; j < 16; j++) {
                KeepDetailBean detailBean = new KeepDetailBean();
                detailBean.setId(j);
                if (j == 0) {
                    detailBean.setContent("防风罩/板/门有无变形");
                    detailBean.setName("张三");
                    detailBean.setStatus("完好");
                    detailBean.setDate("2023.01.02 15:00");
                } else if (j == 1) {
                    detailBean.setContent("各轴内部清洁");
                    detailBean.setName("欧阳凯");
                    detailBean.setStatus("清洁");
                    detailBean.setDate("2023.02.02 09:29");
                } else if (j == 2) {
                    detailBean.setContent("线/管/拖链是否完好");
                    detailBean.setName("");
                    detailBean.setStatus("");
                    detailBean.setDate("");
                } else {
                    detailBean.setContent("行程开关/挡块是否可靠");
                    detailBean.setName("");
                    detailBean.setStatus("");
                    detailBean.setDate("");
                }
                secondList.add(detailBean);
            }

            KeepPlanBean mBean = new KeepPlanBean(secondList, "A001\n北京精雕",
                    "数码", "欧阳凯",
                    "02.08 09:29", strProgress);

            mList.add(mBean);
        }


        return mList;
    }


}





























