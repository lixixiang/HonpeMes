package com.example.honpemes.fragment.login;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/1 11:00
 */
public class PolicyFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_about)
    TextView tvAbout;


    public static PolicyFragment newInstance(Bundle bundle) {
        PolicyFragment fragment = new PolicyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_text;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        if (tvTitle.getText().toString().contains("指南")) {
            tvAbout.setText(getArguments().getString(FinalClass.content));
        } else if (tvTitle.getText().toString().contains("保障服务")
                || tvTitle.getText().toString().contains("隐私政策")) {
            tvAbout.setText(DataClass.service);
        } else {
            tvAbout.setText(DataClass.users);
        }
    }
}
