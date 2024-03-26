package com.example.honpemes.fragment.a.menu.fragment.position10.item6.adapter;

import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.bean.SupplierSumBean;
import com.example.honpemes.widget.AnchorView;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus  on 2024/3/11 9:59
 * 注释：
 */
public class SupplierSumAdapter extends BaseQuickAdapter<SupplierSumBean, BaseViewHolder> {
    private String[] tabTxt;
    private int lastH;

    public SupplierSumAdapter(String[] tabTxt, int lastH) {
        super(R.layout.item_supplier);
        this.tabTxt = tabTxt;
        this.lastH = lastH;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SupplierSumBean bean) {
        AnchorView anchorView = holder.getView(R.id.anchorview);
        anchorView.setAnchorTxt(bean.name);
        anchorView.setContentTxt(bean.mData);
        if (holder.getAdapterPosition() == tabTxt.length - 1) {
            if (anchorView.getHeight() < lastH) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height = lastH;
                anchorView.setLayoutParams(params);
            }
        }
    }
}
