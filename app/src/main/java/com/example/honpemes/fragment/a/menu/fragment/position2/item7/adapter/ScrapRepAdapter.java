package com.example.honpemes.fragment.a.menu.fragment.position2.item7.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item7.bean.SRBean;
import com.example.honpemes.utils.ChartUtil;
import com.example.honpemes.utils.DateUtil;
import com.github.mikephil.charting.charts.BarChart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：asus on 2023/12/28 12:05
 * 注释：
 */
public class ScrapRepAdapter extends BaseMultiItemQuickAdapter<SRBean.DataBean, BaseViewHolder> {
    private List<String> mList = new ArrayList<>();
    private List<Integer> mDatas = new ArrayList<>();

    public ScrapRepAdapter() {
        addItemType(SRBean.DataBean.type_1, R.layout.item_scrap_replenish);
        addItemType(SRBean.DataBean.type_2, R.layout.item_scrap_barchat);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SRBean.DataBean bean) {
        switch (holder.getItemViewType()) {
            case SRBean.DataBean.type_1:
                holder.setText(R.id.item_1, bean.责任部门)
                        .setText(R.id.item_2, bean.补料次数)
                        .setText(R.id.item_3, bean.占比)
                        .setText(R.id.item_4, bean.报废主要原因)
                        .setGone(R.id.ll_scrap_replenish, false)
                        .setText(R.id.item_6, DateUtil.strMD.format(DateUtil.setDate(DateUtil.ymdhm, bean.预计完成时间)));
                break;
            case SRBean.DataBean.type_2:
                BarChart mBarChart = holder.getView(R.id.m_bar_chart);

                mList.clear();
                mDatas.clear();
                for (int i = 0; i < bean.chatList.size(); i++) {
                    mList.add(bean.chatList.get(i).title);
                    mDatas.add(bean.chatList.get(i).count);
                }

                ChartUtil.setBarChart2(mBarChart, bean.chatList);
                break;
        }
    }
}
