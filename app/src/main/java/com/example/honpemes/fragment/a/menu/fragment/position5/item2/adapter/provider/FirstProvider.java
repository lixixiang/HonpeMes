package com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.provider;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;
import com.example.honpemes.utils.DateUtil;

import org.jetbrains.annotations.NotNull;

public class FirstProvider extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_enter_board;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        BoardBean bean = (BoardBean) baseNode;
        String week = DateUtil.getWeek(bean.date, DateUtil.ymd);
        String date =  DateUtil.dd.format(DateUtil.setDate(DateUtil.ymd,bean.date));
        holder.setText(R.id.tv_item1, date+"/"+week)
                .setText(R.id.tv_item2,bean.produceID)
                .setText(R.id.tv_item3,bean.produceType)
                .setText(R.id.tv_item4,bean.produceNum)
                .setText(R.id.tv_item5,bean.unit);
        ImageView ivSelector = holder.getView(R.id.iv_selector);
        ivSelector.setVisibility(View.VISIBLE);

        if (bean.isExpanded()) {
            ivSelector.setImageResource(R.drawable.ic_grey_arrow_down_24);
        } else {
            ivSelector.setImageResource(R.drawable.picture_icon_arrow_up);
            ivSelector.setColorFilter(getContext().getResources().getColor(R.color.grey_l));
        }
    }

    @Override
    public void onClick(@NonNull BaseViewHolder helper, @NonNull View view, BaseNode data, int position) {
        BoardBean entity = (BoardBean) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
    }
}
