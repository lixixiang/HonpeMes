package com.example.honpemes.fragment.a.menu.fragment.position9.item1;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus  on 2024/3/8 11:14
 * 注释：
 */
public class CustomInfoAdapter extends BaseQuickAdapter<CustomInfoBean, BaseViewHolder> {


    public CustomInfoAdapter() {
        super(R.layout.item_custom_info_2);
        addChildClickViewIds(R.id.tv_item4_4);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, CustomInfoBean bean) {
        String infoName = "名称："+bean.name;
        String infoName_1 = "状态："+bean.status;

        holder.setText(R.id.tv_item1, StringUtil.changeStrColor(infoName,R.color.blue_l_l,3,infoName.length()));
        holder.setText(R.id.tv_item1_1, StringUtil.changeStrColor(infoName_1,R.color.blue_l_l,3,infoName_1.length()));
        holder.setText(R.id.tv_item2, "代码："+bean.cord);
        holder.setText(R.id.tv_item2_2, "营业跟单："+bean.lister);
        holder.setText(R.id.tv_item3, "建档人："+bean.saveMan);
        holder.setText(R.id.tv_item3_3, "建档人："+bean.time);
        holder.setText(R.id.tv_item4, "客户组别："+bean.team);
    }
}




















