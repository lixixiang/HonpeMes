package com.example.honpemes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.honpemes.R;
import com.example.honpemes.fragment.a.HomeFragment;
import com.example.honpemes.fragment.b.MeFragment;
import com.example.honpemes.utils.EventBus.EventBusActivityScope;
import com.example.honpemes.utils.EventBus.TabSelectedEvent;
import com.example.honpemes.widget.view.BottomBar;
import com.example.honpemes.widget.view.BottomBarTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Lixixiang on 2023/2/27 12:06
 */
public class MainFragment extends SupportFragment {

    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private Unbinder unbinder;

    private SupportFragment[] mFragments = new SupportFragment[2];


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(MeFragment.class);
        }
    }


    protected void initView() {
        mBottomBar.addItem(new BottomBarTab(_mActivity, R.mipmap.bottom1, "工作台"))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.bottom5, "我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {
                    tab.setUnreadCount(0);
                } else {
                    tab.setUnreadCount(tab.getUnreadCount());
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}























