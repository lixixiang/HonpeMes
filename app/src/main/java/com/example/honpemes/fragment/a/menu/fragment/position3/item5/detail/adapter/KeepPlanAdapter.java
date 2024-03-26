package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepPlanBean;
import com.example.honpemes.utils.Util;

import org.jetbrains.annotations.NotNull;


/**
 * Created by Lixixiang on 2023/2/14 15:27
 */
public class KeepPlanAdapter extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_keep_plan;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {

        KeepPlanBean bean = (KeepPlanBean) baseNode;
        holder.setText(R.id.tv_0, bean.getDeviceId()).setTextColorRes(R.id.tv_0, R.color.grey_l)
                .setText(R.id.tv_2, bean.getDepart()).setTextColorRes(R.id.tv_2, R.color.grey_l)
                .setText(R.id.tv_3, bean.getPeople()).setTextColorRes(R.id.tv_3, R.color.grey_l)
                .setText(R.id.tv_4, bean.getNearKeep()).setTextColorRes(R.id.tv_4, R.color.grey_l)
                .setText(R.id.tv_5, bean.getProgress() + "/" + 16);
        TextView tv5 = holder.getView(R.id.tv_5);
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_grey_arrow_down_24);
        tv5.setCompoundDrawables(null, null, drawable, null);
        tv5.setCompoundDrawablePadding(4);

        if (bean.getProgress() == 16) {
            tv5.setTextColor(getContext().getResources().getColor(R.color.green_l));
        } else if (bean.getProgress() == 0) {
            tv5.setTextColor(getContext().getResources().getColor(R.color.grey_l));
        } else {
            tv5.setTextColor(getContext().getResources().getColor(R.color.red_l));
        }

        LinearLayout rl = holder.getView(R.id.ll_bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 0, 5);
        rl.setLayoutParams(params);
        rl.setPadding(0, Util.dp2px(10),0,Util.dp2px(10));
    }


    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position, true, true);
    }
}
