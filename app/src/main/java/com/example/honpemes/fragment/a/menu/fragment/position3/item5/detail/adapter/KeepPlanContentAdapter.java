package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.adapter;

import android.widget.RelativeLayout;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepDetailBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.flyco.roundview.RoundTextView;


import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/2/14 15:27
 */
public class KeepPlanContentAdapter extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_keep_plan_content_step;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        KeepDetailBean bean = (KeepDetailBean) baseNode;
        LatteLogger.d("estefwf", GsonBuildUtil.GsonBuilder(bean));
            holder.setText(R.id.tv_1,bean.getContent());
            holder.setText(R.id.tv_1_down,bean.getName());
            holder.setText(R.id.tv_2, bean.getStatus());
            holder.setText(R.id.tv_2_down, bean.getDate());
        RoundTextView rv = holder.getView(R.id.tv_status);
        RelativeLayout re = holder.getView(R.id.re_right_content);
        rv.setText((bean.getId()+1)+"");
        if (bean.getId() == 0 || bean.getId() == 1) {
            rv.getDelegate().setBackgroundColor(getContext().getResources().getColor(R.color.green_l));
            re.setBackgroundResource(R.color.green_l);
        } else if (bean.getId() == 2) {
            rv.getDelegate().setBackgroundColor(getContext().getResources().getColor(R.color.orange_l));
            re.setBackgroundResource(R.color.orange_l);
        }
    }

}



















