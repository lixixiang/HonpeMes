package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.adapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.com_adapter.ComTabAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean.DeviceStatusBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.honpemes.api.DataClass.strHeads;

/**
 * Created by Lixixiang on 2023/3/12 13:18
 */
public class DetailDeviceStatusAdapter extends BaseQuickAdapter<DeviceStatusBean.DataBean, BaseViewHolder> {

    List<HomeBean> HeadList = new ArrayList<>();

    public DetailDeviceStatusAdapter() {
        super(R.layout.single_recyclerview);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DeviceStatusBean.DataBean bean) {
        RecyclerView mHeadRecyclerView = holder.getView(R.id.m_recyclerView);
        mHeadRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        ComTabAdapter headAdapter = new ComTabAdapter(DataClass.COLORS2);
        mHeadRecyclerView.setAdapter(headAdapter);
        String strDate = DateUtil.dd.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(bean.get入库时间())))+"/"+
                DateUtil.getWeek(StringUtil.replaceT(bean.get入库时间()),DateUtil.ymdhms)+ "\n" +
                DateUtil.hms.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(bean.get入库时间())));

        HeadList.clear();

        for (int i = 0; i < strHeads.length; i++) {
            HomeBean homeBean = new HomeBean();
            if (i == 0) {
                homeBean.setTitle(strDate);
            } else if (i == 1) {
                if (strHeads[i].equals(bean.get状态())) {
                    homeBean.setTitle("亮灯");
                } else {
                    homeBean.setTitle("");
                }
            } else if (i == 2) {
                if (strHeads[i].equals(bean.get状态())) {
                    homeBean.setTitle("亮灯");
                } else {
                    homeBean.setTitle("");
                }
            } else if (i == 3) {
                if (bean.get状态().contains(strHeads[i])) {
                    homeBean.setTitle("亮灯");
                } else {
                    homeBean.setTitle("");
                }
            } else if (i == 4) {
                if (strHeads[i].equals(bean.get状态())) {
                    homeBean.setTitle("亮灯");
                } else {
                    homeBean.setTitle("");
                }
            }
            homeBean.setSpanSize(1);
            HeadList.add(homeBean);
        }

        headAdapter.setList(HeadList);
    }
}
