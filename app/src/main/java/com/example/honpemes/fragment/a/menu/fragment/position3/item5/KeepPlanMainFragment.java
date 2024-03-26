package com.example.honpemes.fragment.a.menu.fragment.position3.item5;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.KeepPlanDetailFragment;
import com.example.honpemes.utils.DateUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/7 11:34
 * 保养
 */
public class KeepPlanMainFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;


    String[] tabsTitle = {"第一季度", "第二季度", "第三季度", "第四季度"};
    List<Fragment> fragments = new ArrayList<>();
    public int oneDay = 0;
    private String curYear, changeYear;

    public static KeepPlanMainFragment newInstance(Bundle bundle) {
        KeepPlanMainFragment fragment = new KeepPlanMainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_tab_viewpager;
    }

    @Override
    protected void initView() {
        init();
        initToolbarNav(llBack);
        rgDate.setVisibility(View.VISIBLE);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        tvEnd.setVisibility(View.VISIBLE);

        for (int i = 0; i < tabsTitle.length; i++) {
            fragments.add(new KeepPlanDetailFragment());
        }

        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabsTitle[position];
            }
        });

        tab.setupWithViewPager(viewpager);
        tab.setBackgroundResource(R.color.grey_l_l_l_l);

    }

    private void init() {
        curYear = DateUtil.y.format(new Date());
        tvDate.setText(curYear);
        btnUpPager.setText("上一年");
        btnNextPager.setText("下一年");
        checkCurrent();


    }

    private void checkCurrent() {
        if (Integer.parseInt(tvDate.getText().toString()) >= Integer.parseInt(curYear)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneYear();
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneYear();
                break;
            case R.id.tv_end:
                //   popEvaluate.showDownView(mToolbar, 0, 0);
                break;
        }
    }

    private void setOneYear() {
        changeYear = (Integer.parseInt(curYear) + oneDay) + "";
        tvDate.setText(changeYear);
        checkCurrent();
    }
}
