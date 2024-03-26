package com.example.honpemes.fragment.a.menu.fragment.position11.item1.comm;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter.FormDetailAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.DetailFormBean;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.OrderFormBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

import static com.example.honpemes.utils.RecyclerViewAnimation.runLayoutAnimation;


/**
 * Created by Lixixiang on 2023/2/7 16:22
 * 机台详情
 */
public class FormDetailFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerview;

    FormDetailAdapter mAdapter;
    ArrayList<OrderFormBean.DataBean.加工机台状态Bean> mStatusList;
    HashMap<String, ArrayList<OrderFormBean.DataBean.加工机台状态Bean>> maps = new LinkedHashMap<>();
    List<DetailFormBean> DataList = new ArrayList<>();

    public static int Sum, makeNum, repairNum, freeNum;

    public static FormDetailFragment newInstance(Bundle bundle) {
        FormDetailFragment fragment = new FormDetailFragment();
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
        tvTitle.setText(getArguments().getString("name"));
        mStatusList = (ArrayList<OrderFormBean.DataBean.加工机台状态Bean>) getArguments().getSerializable(FinalClass.bean);

        if (mStatusList.size() > 0) {

            for (int i = 0; i < mStatusList.size(); i++) {
                if ("MJ".equals(mStatusList.get(i).get制作组别())) {
                    mStatusList.get(i).set制作组别("WJ");
                }

                String nameDepart = mStatusList.get(i).get制作组别();
                ArrayList<OrderFormBean.DataBean.加工机台状态Bean> rowsEntities = maps.get(nameDepart);
                if (rowsEntities == null) {
                    rowsEntities = new ArrayList<>();
                }

                rowsEntities.add(mStatusList.get(i));
                maps.put(nameDepart, rowsEntities);

            }

            //   for (int i = 0; i < list.size(); i++) {
            //            Sum = Sum + list.get(i).getCn();
            //            if ("●".equals(list.get(i).get机台状态())) { //制作数量
            //                makeNum = makeNum + list.get(i).getCn();
            //            } else if ("▲".equals(list.get(i).get机台状态())) { //空闲数量
            //                repairNum = repairNum + list.get(i).getCn();
            //            } else if ("■".equals(list.get(i).get机台状态())) { //检修数量
            //                freeNum = freeNum + list.get(i).getCn();
            //            }
            //        }
            for (String key : maps.keySet()) {
                DetailFormBean entity = new DetailFormBean();
                ArrayList<OrderFormBean.DataBean.加工机台状态Bean> list = maps.get(key);
                Sum = 0;
                makeNum = 0;
                repairNum = 0;
                freeNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    Sum = Sum + list.get(i).getCn();
                    if (list.get(i).get机台状态().contains("●")) { //制作数量 126
                        makeNum = makeNum + list.get(i).getCn();
                    }  if (list.get(i).get机台状态().contains("▲")) { //空闲数量 85
                        repairNum = repairNum + list.get(i).getCn();
                    }  if (list.get(i).get机台状态().contains("■")) { //检修数量  6
                        freeNum = freeNum + list.get(i).getCn();
                    }
                }
                entity.setName(key);
                entity.setSum(Sum);
                entity.setMakeNum(makeNum);
                entity.setRepairNum(repairNum);
                entity.setFreeNum(freeNum);

                DataList.add(entity);
            }


            mAdapter = new FormDetailAdapter();
            mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
            mRecyclerview.setAdapter(mAdapter);
            mAdapter.setList(DataList);

            runLayoutAnimation(mRecyclerview);
        }
    }

}





























