package com.example.honpemes.fragment.a.com_adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.MenuBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/2/27 17:39
 */
public class HomeModeAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {


    public HomeModeAdapter() {
        super(R.layout.items_cate_child);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MenuBean bean) {
        ImageView ImageIcon = holder.getView(R.id.icon_img);
        holder.setGone(R.id.delete_img, true);
        /**
         * enable 为true 显示原有颜色图标，为false 则为灰色图标，场景为尚未开发应用
         */
        if (!bean.isEnable() && !bean.getTitle().contains("更多")) {
            ImageIcon.setColorFilter(getContext().getResources().getColor(R.color.grey_l));
            holder.setTextColorRes(R.id.tv_title, R.color.grey_l);
        }
        int drawableId = getContext().getResources().getIdentifier(bean.getIco(), "mipmap", getContext().getPackageName());
        holder.setImageResource(R.id.icon_img, drawableId)
                .setText(R.id.tv_title, bean.getTitle());
    }
}
