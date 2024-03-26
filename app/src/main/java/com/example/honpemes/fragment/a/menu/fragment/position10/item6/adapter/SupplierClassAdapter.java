package com.example.honpemes.fragment.a.menu.fragment.position10.item6.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.bean.SupplierClassBean;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus  on 2024/3/11 9:59
 * 注释：
 */
public class SupplierClassAdapter extends BaseQuickAdapter<SupplierClassBean, BaseViewHolder> {


    public SupplierClassAdapter() {
        super(R.layout.item_tab_status_date);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SupplierClassBean bean) {

        String strStatus = "状态：" + bean.status;

        holder.setText(R.id.tv_address, bean.address)
                .setText(R.id.tv_content, StringUtil.changeStrColor(strStatus,R.color.black_l, "状态：".length(),strStatus.length()))
                .setText(R.id.tv_date, "日期：" + bean.date);
    }
}
