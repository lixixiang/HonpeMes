package com.example.honpemes.fragment.a.menu.fragment.position5.item1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item1.adapter.InventoryBoardAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * 库存看板
 */
public class InventoryBoardFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    InventoryBoardAdapter mAdapter;

    public String[] item11 = {"325","125","225","425","400","415","225","115","400","415","225","115"};
    public String[] item22 = {"1325","2125","3225","2425","3400","1415","2225","3115","3400","1415","2225","3115"};
    public String[] item33 = {"1325","2125","3225","2425","3400","1415","2225","3115","3400","1415","2225","3115"};
    public String[] item44 = {"1325","2125","3225","2425","3400","1415","2225","3115","3400","1415","2225","3115"};


    public String[] item_11 = {"2325","2125","2225","2425","400","415","225","115","400","415","225","115"};
    public String[] item_22 = {"1325","2125","3225","2425","23400","1415","2225","3115","3400","1415","2225","3115"};
    public String[] item_33 = {"1325","2925","3225","2825","3400","1415","2625","3115","3490","1415","2295","3915"};
    public String[] item_44 = {"1395","2125","3225","2495","3400","1415","2925","3115","3400","1415","2225","3115"};
    public String[] months = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    public String[] titles = {"数量统计","成本统计"};



    public static InventoryBoardFragment newInstance(Bundle bundle) {
        InventoryBoardFragment fragment = new InventoryBoardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_single_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        initRecyclerview();
    }


    private void initRecyclerview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new InventoryBoardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setList(initData());

    }

    private Collection<? extends BoardBean> initData() {
        List<BoardBean> mList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            BoardBean entity = new BoardBean();
            entity.produceType = titles[i];
            int total1 = 0,total2 = 0,total3 = 0,total4 = 0;
            if (i == 0) {
                entity.nums1 = item11;
                entity.nums2 = item22;
                entity.nums3 = item33;
                entity.nums4 = item44;
                for (String s : item11) {
                    total1 =total1 + Integer.parseInt(s);
                }
                for (String s : item22) {
                    total2 =total2 + Integer.parseInt(s);
                }
                for (String s : item33) {
                    total3 =total3 + Integer.parseInt(s);
                }
                for (String s : item44) {
                    total4 =total4 + Integer.parseInt(s);
                }
                entity.item1 = total1;
                entity.item2 = total2;
                entity.item3 = total3;
                entity.item4 = total4;
                entity.months = null;
            } else {
                entity.nums1 = item_11;
                entity.nums2 = item_22;
                entity.nums3 = item_33;
                entity.nums4 = item_44;

                for (String s : item_11) {
                    total1 =total1 + Integer.parseInt(s);
                }
                for (String s : item_22) {
                    total2 =total2 + Integer.parseInt(s);
                }
                for (String s : item_33) {
                    total3 =total3 + Integer.parseInt(s);
                }
                for (String s : item_44) {
                    total4 =total4 + Integer.parseInt(s);
                }
                entity.item1 = total1;
                entity.item2 = total2;
                entity.item3 = total3;
                entity.item4 = total4;
                entity.months = months;
            }

            mList.add(entity);
        }
        return mList;
    }

}



























