package com.example.honpemes.fragment.a.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.DataClass;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.menu.adapter.MenuChildAdapter;
import com.example.honpemes.fragment.a.menu.adapter.MyMenuAdapter;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.widget.gridview.BaseGridView;
import com.example.honpemes.widget.gridview.DragCallback;
import com.example.honpemes.widget.gridview.DragForScrollView;
import com.example.honpemes.widget.gridview.DragGridView;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/2/27 18:04
 * 菜单管理
 */
public class MenuManagerFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_item_cate_name)
    TextView tvItemCateName;
    @BindView(R.id.tv_drag_tip)
    TextView tvDragTip;
    @BindView(R.id.gridview)
    DragGridView dragGridView;
    @BindView(R.id.expandableListView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sv_index)
    DragForScrollView svIndex;
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    private ArrayList<MenuBean> indexSelect;
    private ArrayList<MenuBean> indexDataAll;

    private MyMenuAdapter adapterSelect;
    private MenuParentAdapter menuParentAdapter;

    public static MenuManagerFragment newInstance(Bundle bundle) {
        MenuManagerFragment fragment = new MenuManagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_fragment_menu_all;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        indexSelect = (ArrayList<MenuBean>) getArguments().getSerializable(FinalClass.headMenu);
        indexDataAll = (ArrayList<MenuBean>) getArguments().getSerializable(FinalClass.contentMenu);

        //获取设置保存到本地的菜单
        String strMenu = (String) DBUtils.get(TableClass.TABLE_MENU, TableClass.KEY_USER_TEMP, "");
        if (strMenu != null && !"".equals(strMenu)) {
            indexSelect.clear();
            ArrayList<MenuBean> indexDataList =  GsonBuildUtil.create().fromJson(strMenu,new TypeToken<ArrayList<MenuBean>>(){}.getType());
            indexSelect.addAll(indexDataList);
        }

        for (int i = 0; i < indexSelect.size(); i++) {
            indexSelect.get(i).setSelect(true);
            for (int j = 0; j < indexDataAll.size(); j++) {
                for (int k = 0; k < indexDataAll.get(j).getDetail().size(); k++) {
                    if (indexDataAll.get(j).getDetail().get(k).getTitle().equals(indexSelect.get(i).getTitle())) {
                        indexDataAll.get(j).getDetail().get(k).setSelect(true);
                    }
                }
            }
        }

        //已选应用
        adapterSelect = new MyMenuAdapter(_mActivity, MenuManagerFragment.this, indexSelect);
        dragGridView.setAdapter(adapterSelect);

        //所有应用
        menuParentAdapter = new MenuParentAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(menuParentAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        menuParentAdapter.setList(indexDataAll);

    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("全部应用");
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("管理");


        dragGridView.setDragCallback(new DragCallback() {
            @Override
            public void startDrag(int position) {
                svIndex.startDrag(position);
            }

            @Override
            public void endDrag(int position) {
                svIndex.endDrag(position);
            }
        });

        dragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!adapterSelect.getEditStatue()) {
                    MenuBean cateModel = indexSelect.get(position);
                    initUrl(cateModel.getTitle());
                }
            }
        });

        dragGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (tvEnd.getText().toString().equals("管理")) {
                    tvEnd.setText("完成");
                    adapterSelect.setEdit();
                    if(menuParentAdapter !=null){
                        menuParentAdapter.setEdit();
                    }
                    tvDragTip.setVisibility(View.VISIBLE);

                }
                dragGridView.startDrag(position);
                return false;
            }
        });

    }

    private void initUrl(String title) {
        if (tvEnd.getText().toString().equals("管理")) {
            Bundle bundle = new Bundle();
            bundle.putString(FinalClass.title, title);
            DataClass.startFragment(title,this,bundle);
        }
    }


    public void DelMenu(MenuBean indexData, int position) {

        indexSelect.remove(indexData);
        adapterSelect.setDatas(indexSelect);

        for (int i = 0; i < indexDataAll.size(); i++) {
            for (int j = 0; j < indexDataAll.get(i).getDetail().size(); j++) {
                if (indexDataAll.get(i).getDetail().get(j).getTitle().equals(indexData.getTitle())) {
                    indexDataAll.get(i).getDetail().get(j).setSelect(false);
                }
            }
        }

        if (menuParentAdapter != null)
            menuParentAdapter.notifyDataSetChanged();
        adapterSelect.notifyDataSetChanged();
    }

    public void AddMenu(MenuBean menuBean) {
        indexSelect.add(menuBean);
        EventBusUtil.sendEvent(new Event(FinalClass.MENU_ADD_DEL,indexSelect));
        for (int i = 0; i < indexDataAll.size(); i++) {
            for (int k = 0; k < indexDataAll.get(i).getDetail().size(); k++) {
                if (indexDataAll.get(i).getDetail().get(k).getTitle().equals(menuBean.getTitle())) {
                    indexDataAll.get(i).getDetail().get(k).setSelect(true);
                }
            }
        }

        menuParentAdapter.notifyDataSetChanged();
        adapterSelect.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_end)
    public void onClick() {
        if (tvEnd.getText().toString().equals("管理")) {
            tvEnd.setText("完成");
            adapterSelect.setEdit();
            if (menuParentAdapter != null) {
                menuParentAdapter.setEdit();
            }
            tvDragTip.setVisibility(View.VISIBLE);
        } else {
            tvEnd.setText("管理");
            tvDragTip.setVisibility(View.GONE);
            adapterSelect.endEdit();
            if (menuParentAdapter != null) {
                menuParentAdapter.endEdit();
            }

            DBUtils.put(TableClass.TABLE_MENU, TableClass.KEY_USER_TEMP, GsonBuildUtil.GsonToString(adapterSelect.getDatas()));

        }
    }


    public class MenuParentAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {
        private boolean IsEdit;

        public MenuParentAdapter() {
            super(R.layout.item_cate_parent);
        }


        @Override
        protected void convert(@NotNull BaseViewHolder holder, MenuBean menuBean) {
            holder.setText(R.id.tv_item_title, menuBean.getTitle()).setBackgroundResource(R.id.item_ll_bg,R.color.white);
            BaseGridView baseGridView = holder.getView(R.id.gv_toolbar);
            MenuChildAdapter adapter = new MenuChildAdapter(getContext(), MenuManagerFragment.this, menuBean.getDetail(), IsEdit);
            baseGridView.setAdapter(adapter);
            baseGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventBusUtil.sendEvent(new Event(FinalClass.MENU_DATA, menuBean.getDetail().get(position).getTitle()));
                }
            });
        }

        public void setEdit() {
            IsEdit = true;
            notifyDataSetChanged();
        }

        public void endEdit() {
            IsEdit = false;
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.MENU_ADD_DEL:
                indexSelect = (ArrayList<MenuBean>) event.getData();
                DBUtils.put(TableClass.TABLE_MENU, TableClass.KEY_USER_TEMP, GsonBuildUtil.GsonToString(indexSelect));
                break;
            case FinalClass.MENU_DATA:
                String title = (String) event.getData();
                initUrl(title);
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        EventBusUtil.sendEvent(new Event(FinalClass.BACK,adapterSelect.getDatas()));
        return super.onBackPressedSupport();
    }
}



















