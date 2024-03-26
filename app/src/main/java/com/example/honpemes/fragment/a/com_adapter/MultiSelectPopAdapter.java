package com.example.honpemes.fragment.a.com_adapter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.widget.DJEditText;
import com.flyco.roundview.RoundTextView;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/3/6 10:18
 * 多样式筛选结果
 */
public class MultiSelectPopAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    public MultiSelectPopAdapter() {
        addItemType(HomeBean.TYPE_1, R.layout.item_tag_start_time_end_time);
        addItemType(HomeBean.TYPE_2, R.layout.item_tag_right_recyclerview);
        addItemType(HomeBean.TYPE_3, R.layout.item_tag_text);
        addItemType(HomeBean.TYPE_4, R.layout.item_tag_edit);
        addItemType(HomeBean.TYPE_6, R.layout.item_sure_cancel);
        addChildClickViewIds(R.id.tv_start, R.id.tv_end,R.id.tv_content, R.id.tv_sure,R.id.tv_reset);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {
        switch (holder.getItemViewType()) {
            case HomeBean.TYPE_1:
                holder.setText(R.id.tv_start, bean.getStartTime())
                        .setText(R.id.tv_end, bean.getEndTime())
                        .setText(R.id.tv_title,bean.getTitle());
                break;
            case HomeBean.TYPE_2:
                holder.setText(R.id.tv_tag, bean.getTitle());
                RecyclerView mItemRecyclerView = holder.getView(R.id.m_recyclerView);
                mItemRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                ItemChildAdapter mAdapter = new ItemChildAdapter();
                mItemRecyclerView.setAdapter(mAdapter);
                mAdapter.setList(bean.getMenuBeanList());
//                mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//                    @Override
//                    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                        for (MenuBean menuBean : mAdapter.getData()) {
//                            menuBean.setCheck(false);
//                        }
//                        if (sameItem.equals(mAdapter.getData().get(position).getTitle())) {
//                            mAdapter.getData().get(position).setCheck(false);
//                            sameItem = "";
//                        } else {
//                            mAdapter.getData().get(position).setCheck(true);
//                            sameItem = mAdapter.getData().get(position).getTitle();
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    }
//                });
                break;
            case HomeBean.TYPE_3:
                holder.setText(R.id.tv_tag, bean.getTitle())
                .setText(R.id.tv_content,bean.getContent());
               RoundTextView tv = holder.getView(R.id.tv_content);
                StringUtil.HintUtil(tv,bean.getHint(),Util.sp2px(getContext(),5));
                break;
            case HomeBean.TYPE_4:
                holder.setText(R.id.tv_tag, bean.getTitle())
                        .setText(R.id.et_content,bean.getContent());
                EditText et = holder.getView(R.id.et_content);
                StringUtil.HintUtil(et, bean.getHint(),Util.sp2px(getContext(),3));
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        bean.setContent(et.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                break;
        }
    }


    public class ItemChildAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

        public ItemChildAdapter() {
            super(R.layout.item_single_checkbox);
//            addChildClickViewIds(R.id.ck);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, MenuBean bean) {
            CheckBox ck = holder.getView(R.id.ck);
            ck.setText(bean.getTitle());
            ck.setChecked(bean.isCheck());
            ck.setId(holder.getAdapterPosition());

            ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MenuBean dat = getData().get(buttonView.getId());
                    dat.setCheck(isChecked);
                    getData().set(buttonView.getId(), dat);
                    if (bean.isCheck()) {
                        ck.setTextColor(getContext().getResources().getColor(R.color.white));
                    } else {
                        ck.setTextColor(Color.BLACK);
                    }
                }
            });
        }
    }
}




























