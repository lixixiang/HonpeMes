package com.example.honpemes.fragment.a.menu.fragment.position10.item6;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.adapter.SupplierSumAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.bean.SupplierClassBean;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.bean.SupplierSumBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：asus  on 2024/3/11 9:45
 * 注释：
 */
public class SupplierClassFragment extends BaseBackFragment {


    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    private String[] tabTxt = {"注塑", "氧化", "线割", "碳纤维", "丝印"};
    private LinearLayoutManager manager;
    private SupplierSumAdapter mAdapter;
    private boolean isRecyclerScroll;
    private int scrollToPosition;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos;
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    List<SupplierSumBean> mList = new ArrayList<>();

    public static SupplierClassFragment newInstance(Bundle bundle) {
        SupplierClassFragment fragment = new SupplierClassFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_tab_recyclerview;
    }

    @Override
    protected void initView() {
       initToolbarNav(llBack);
       tvTitle.setText(bundle.getString(FinalClass.title));

        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
            SupplierSumBean bean = new SupplierSumBean();
            bean.name = tabTxt[i];
            List<SupplierClassBean> cList = new ArrayList<>();
            for (int j = 0; j < tabTxt.length; j++) {
                SupplierClassBean item1 = new SupplierClassBean();
                item1.address="深圳市柏生龙五金制品有限公司";
                item1.date = "2024.03.06";
                item1.status = "已审核";
                cList.add(item1);
            }
            bean.mData = cList;
            mList.add(bean);
        }

        //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度(这里固定高度50dp)，用于recyclerView的最后一个item view填充高度
        int screenH = getScreenHeight();
        int statusBarH = getStatusBarHeight(_mActivity);
        int tabH = 50 * 3;
        int lastH = screenH - statusBarH - tabH;
        manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        mAdapter = new SupplierSumAdapter(tabTxt,lastH);
        recyclerView.setAdapter(mAdapter);


        mAdapter.setList(mList);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                isRecyclerScroll = false;
                moveToPosition(manager, recyclerView, pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isRecyclerScroll = true;
                }
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canScroll) {
                    canScroll = false;
                    moveToPosition(manager,recyclerView,scrollToPosition);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    int position = manager.findFirstVisibleItemPosition();
                    if (lastPos != position) {
                        tabLayout.setScrollPosition(position, 0, true);
                    }
                    lastPos = position;
                }
            }
        });

    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }
}




























