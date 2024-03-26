package com.example.honpemes.widget.pop.adapter;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.widget.pop.bean.SelectorBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/26 15:34
 * 注释：
 */
public class SelectorAdapter extends BaseMultiItemQuickAdapter<SelectorBean, BaseViewHolder> {

    public SelectorAdapter() {
        addItemType(SelectorBean.TYPE_1, R.layout.item_vertical_tag_edit);
        addItemType(SelectorBean.TYPE_2, R.layout.item_vertical_tag_two_text);
        addItemType(SelectorBean.TYPE_3, R.layout.item_tag_right_recyclerview);
        addItemType(SelectorBean.TYPE_5, R.layout.item_tag_bg);
        addChildClickViewIds(R.id.et_start_time,R.id.et_end_time);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SelectorBean bean) {
        switch (holder.getItemViewType()) {
            case SelectorBean.TYPE_1:
                holder.setText(R.id.tv_tag, bean.getTitle());
                EditText etContent = holder.getView(R.id.et_content);
                etContent.setSelection(0);
                etContent.setHint(bean.getHint());
                etContent.setText(bean.getContent());
                etContent.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() > 0) {
                            bean.setContent(s.toString());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            case SelectorBean.TYPE_2:
                holder.setText(R.id.tv_tag, bean.getTitle());

                TextView etStartTime = holder.getView(R.id.et_start_time);
                TextView etEndTime = holder.getView(R.id.et_end_time);
                etStartTime.setHint(bean.getHint());
                etEndTime.setHint(bean.getHint2());
                etStartTime.setText(bean.getStartTime());
                etEndTime.setText(bean.getEndTime());
                break;
            case SelectorBean.TYPE_3:
                holder.setText(R.id.tv_tag, bean.getTitle());

                RecyclerView mItemRecyclerView = holder.getView(R.id.m_recyclerView);
                mItemRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                ItemCheckAdapter mAdapter = new ItemCheckAdapter();
                mItemRecyclerView.setAdapter(mAdapter);
                mAdapter.setList(bean.getMenuBeanList());
                break;
        }
    }

    public class ItemCheckAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

        public ItemCheckAdapter() {
            super(R.layout.item_single_checkbox);
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
































