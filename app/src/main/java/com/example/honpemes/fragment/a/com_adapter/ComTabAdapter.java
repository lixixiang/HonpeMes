package com.example.honpemes.fragment.a.com_adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.utils.Util;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/3/7 13:47
 */
public class ComTabAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {

    private int[] colors;
    public ComTabAdapter(int[] colors) {
        super(R.layout.device_status_title);
        this.colors = colors;
    }

    public ComTabAdapter(int[] colors, int textSize) {
        super(R.layout.device_status_title);
        this.colors = colors;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {
        holder.setText(R.id.tv_0, bean.getTitle())
                .setTextColor(R.id.tv_0, colors[holder.getAdapterPosition()]);
        TextView tvTab = holder.getView(R.id.tv_0);

        LinearLayout linearLayout = holder.getView(R.id.ll_bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Util.dp2px(45));
        params.setMargins(0, 0, 0, Util.dp2px(2));
        linearLayout.setLayoutParams(params);
    }
}
