package com.example.honpemes.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.adapter.SupplierClassAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.bean.SupplierClassBean;

import java.util.List;

/**
 * 作者：asus  on 2024/3/11 10:48
 * 注释：
 */
public class AnchorView extends LinearLayout {
    private TextView tvTitle;
    private RecyclerView mRecyclerview;
    private SupplierClassAdapter mAdapter;

    public AnchorView(Context context) {
        this(context, null);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_anchor, this, true);
        tvTitle = view.findViewById(R.id.title);
        mRecyclerview = view.findViewById(R.id.m_recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new SupplierClassAdapter();
        mRecyclerview.setAdapter(mAdapter);
    }


    public void setAnchorTxt(String txt) {
        tvTitle.setText(txt);
    }

    public void setContentTxt(List<SupplierClassBean> mList) {
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }
}





























