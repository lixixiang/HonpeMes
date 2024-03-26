package com.example.honpemes.fragment.a.menu.fragment.position2.item3.adapter;


import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 作者：asus on 2023/12/7 14:49
 * 注释：生产进度
 */
public class ProduceProgressAdapter extends BaseQuickAdapter<ProduceProgressBean.DataBean, BaseViewHolder> {

    private String firstDay, lastDay, keyword;
    private ProduceDetailAdapter mChildAdapter;
    private List<ProduceProgressBean.DataBean> mList = new ArrayList<>();
    private int oldPos = -1;
    private RecyclerView itemRecyclerView;

    public ProduceProgressAdapter() {
        super(R.layout.item_produce_progress);
        addChildClickViewIds(R.id.ll_item_bg);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, ProduceProgressBean.DataBean bean) {
        holder.setText(R.id.tv_order_id, bean.生产单号)
                .setText(R.id.tv_order_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymdh, bean.下单日期)))
                .setText(R.id.tv_delivery_date, DateUtil.y_m_d.format(DateUtil.setDate(DateUtil.ymdh, bean.交货日期)))
                .setText(R.id.tv_buyer, "下单人:" + bean.下单人员)
                .setText(R.id.tv_team, "组别:" + bean.订单组别 + "_" + bean.制作组别);
        LinearLayout llItemBg = holder.getView(R.id.ll_item_bg);
        ImageView imageView = holder.getView(R.id.iv_director);
        itemRecyclerView = holder.getView(R.id.item_recyclerview);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mChildAdapter = new ProduceDetailAdapter();
        itemRecyclerView.setAdapter(mChildAdapter);
        oldPos = holder.getAdapterPosition();
        firstDay = bean.下单日期;
        lastDay = bean.交货日期;
        keyword = bean.生产单号;


        if (bean.Expanded) {
            ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .rotation(-180f)
                    .start();
            getHttpData();
        } else {
            ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .rotation(0)
                    .start();
        }

        itemRecyclerView.setVisibility(bean.Expanded ? View.VISIBLE : View.GONE);


    }

    private void getHttpData() {

        EasyHttp.post(Constants.ProduceProgress(firstDay, lastDay,
                "", "", "", keyword, ""))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        ProduceProgressBean bean = GsonBuildUtil.create().fromJson(s, ProduceProgressBean.class);

                        if (mList.size() > 0) {
                            mList.clear();
                        }

                        mList.addAll(bean.data);

                        Collections.sort(mList, new Comparator<ProduceProgressBean.DataBean>() {
                            @Override
                            public int compare(ProduceProgressBean.DataBean o1, ProduceProgressBean.DataBean o2) {
                                int order1 = o1.工序序号;
                                int order2 = o2.工序序号;
                                return Integer.compare(order1, order2);
                            }
                        });


                        mChildAdapter.setList(mList);

                    }
                });
    }
}






























