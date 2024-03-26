package com.example.honpemes.fragment.a.menu.fragment.position3.item4.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position3.item4.bean.CheckRepairBean;
import com.example.honpemes.utils.DateUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/25 16:37
 * 注释：检修记录
 */
public class CheckRepairAdapter extends BaseQuickAdapter<CheckRepairBean.DataBean, BaseViewHolder> {
    private String startTime,endTime,strTime;

    public CheckRepairAdapter() {
        super(R.layout.item_check_repair);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, CheckRepairBean.DataBean bean) {
        if (bean.开始时间 != null && bean.开始时间.length() > 0) {
            startTime = bean.开始时间.replace("T", " ");
        } else {
            startTime = "";
        }
        if (bean.结束时间 != null && bean.结束时间.length() > 0) {
            endTime = bean.结束时间.replace("T", " ");
        } else {
            endTime = "";
        }
        if (bean.检修工时 != null && bean.检修工时 > 0) {
            strTime = DateUtil.CountTMS( (long)(bean.检修工时  * 60 * 60 * 1000));
        } else {
            strTime = "";
        }

        holder.setText(R.id.item_1, bean.机台编号)
                .setText(R.id.item_2, startTime)
                .setText(R.id.item_3, endTime)
                .setText(R.id.item_4, strTime)
                .setText(R.id.item_5, bean.检修次数+"")
                .setText(R.id.item_6, bean.创建人);

    }
}
