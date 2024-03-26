package com.example.honpemes.fragment.a.menu.fragment.position3.item3.adapter;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position3.item3.bean.CurrentTaskBean;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * 作者：asus on 2023/12/25 14:22
 * 注释：
 */
public class CurrentTaskAdapter extends BaseQuickAdapter<CurrentTaskBean.DataBean, BaseViewHolder> {

    public CurrentTaskAdapter() {
        super(R.layout.item_current_task);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, CurrentTaskBean.DataBean bean) {
        holder.setText(R.id.item_1,bean.mainProgs)
                .setText(R.id.item_2,bean.machineIP);
        String[] dataArray = bean.subProgs.split(",");
        List<String> dataList = Arrays.asList(dataArray);

        RecyclerView mRecyclerView = holder.getView(R.id.item_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CurrentTaskDetailAdapter mItemAdapter = new CurrentTaskDetailAdapter();
        mRecyclerView.setAdapter(mItemAdapter);

        mItemAdapter.setList(dataList);
        ImageView imageView = holder.getView(R.id.iv_director);

        if (bean.isOpen) {
            ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .rotation(-180f)
                    .start();
        } else {
            ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .rotation(0)
                    .start();
        }
        mRecyclerView.setVisibility(bean.isOpen ? View.VISIBLE : View.GONE);
    }
}



































