package com.example.honpemes.fragment.a.menu.fragment.position2.item7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.fragment.a.menu.fragment.position2.item7.adapter.ScrapRepAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position2.item7.bean.SRBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：asus on 2023/12/28 11:09
 * 注释：报废补料
 */
public class ScrapReplenishmentFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.content_recyclerView)
    RecyclerView contentRecyclerView;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.ll_scrap_replenish)
    LinearLayout llBg;

    private String curMonth, firstDay, lastDay, curDate, NextMonthDay;
    public int oneDay = 0; //这个初始日子
    private ScrapRepAdapter mAdapter;
    private List<SRBean.DataBean> mList = new ArrayList<>();
    private List<String> departList = new ArrayList<>();
    private List<SRBean.DataBean> mBarChatList = new ArrayList<>();


    public static ScrapReplenishmentFragment newInstance(Bundle bundle) {
        ScrapReplenishmentFragment fragment = new ScrapReplenishmentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_content_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title) + "分析");
        firstDay = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        lastDay = DateUtil.getCurrentLastDayOfMonth(DateUtil.ymd);
        curDate = DateUtil.getFirstDayOfMonth(DateUtil.ymd);
        rgDate.setVisibility(View.VISIBLE);
        curMonth = DateUtil.ym.format(new Date());
        tvDate.setText(curMonth);
        llBg.setVisibility(View.VISIBLE);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        initRecyclerview();
        getHttpData();

    }

    private void initRecyclerview() {
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new ScrapRepAdapter();
        contentRecyclerView.setAdapter(mAdapter);
    }


    private void getHttpData() {
        EasyHttp.post(Constants.ScrapReplenish(firstDay, lastDay))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        SRBean srBean = GsonBuildUtil.create().fromJson(s, SRBean.class);
                        mList.clear();


                        for (int i = 0; i < srBean.data.size(); i++) {
                            if ("".equals(srBean.data.get(i).责任部门)) {
                                srBean.data.get(i).责任部门 = "备件";
                            }
                            srBean.data.get(i).itemType = SRBean.DataBean.type_1;
                            SRBean.DataBean dataBean = srBean.data.get(i);
                            departList.add(srBean.data.get(i).报废主要原因);
                            mList.add(dataBean);
                        }

                        // 使用 Collections.sort 排序 mList
                        Collections.sort(mList, new Comparator<SRBean.DataBean>() {
                            @Override
                            public int compare(SRBean.DataBean dataBean1, SRBean.DataBean dataBean2) {
                                // 按照补料次数降序排序
                                return Integer.parseInt(dataBean2.补料次数) - Integer.parseInt(dataBean1.补料次数);
                            }
                        });

                        StringUtil.DeleteArrayRepeatStr(departList);
                        for (int i = 0; i < departList.size(); i++) {
                            SRBean.DataBean bean = new SRBean.DataBean();
                            bean.title = departList.get(i);
                            int count = 0;
                            for (int j = 0; j < mList.size(); j++) {
                                if (departList.get(i).equals(mList.get(j).报废主要原因)) {
                                    count = count + Integer.parseInt(mList.get(j).补料次数);
                                }
                            }
                            bean.count = count;
                            mBarChatList.add(bean);
                        }

                        Collections.sort(mBarChatList, new Comparator<SRBean.DataBean>() {
                            @Override
                            public int compare(SRBean.DataBean o1, SRBean.DataBean o2) {
                                return Integer.compare(o2.count, o1.count);
                            }
                        });
                        LatteLogger.d("getHttpData", GsonBuildUtil.GsonBuilder(mBarChatList));


                        SRBean.DataBean css2Bean = new SRBean.DataBean();
                        css2Bean.itemType = SRBean.DataBean.type_2;
                        css2Bean.chatList = mBarChatList;
                        mList.add(css2Bean);
                        mAdapter.setList(mList);
                    }
                });
    }


    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(oneDay);
        lastDay = DateUtil.getLastDayOfMonth(oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));


        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getHttpData();

    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
            case R.id.tv_end:

                break;
        }
    }


}


























