package com.example.honpemes.fragment.b;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseMainFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.MainFragment;
import com.example.honpemes.fragment.b.setting.SettingFragment;
import com.example.honpemes.fragment.b.setting.app_download.DownloadQRCodeFragment;
import com.example.honpemes.fragment.b.setting.mob.MobPersonInfoFragment;
import com.example.honpemes.fragment.login.PolicyFragment;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/2/27 15:11
 */
public class MeFragment extends BaseMainFragment {

    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerView;

    MeAdapter mAdapter;
    private List<HomeBean> mList = new ArrayList<>();
    private String[] listTitles = {"基本服务"};
    private String[] content1 = {"设置", "APP下载及当前版本", "隐私政策"};
    private Integer[] icons = {R.mipmap.ic_me_1, R.mipmap.ic_me_2, R.mipmap.ic_me_3};

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_me;
    }


    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new MeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(getMeData());
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_mod:
                        ((MainFragment) getParentFragment()).start(MobPersonInfoFragment.newInstance());
                        break;
                }
            }
        });
    }
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.Event_title:
                String strTitle = (String) event.getData();
                LatteLogger.d("strTitle", strTitle);
                bundle.putString(FinalClass.title, strTitle);
                switch (strTitle) {
                    case "设置":
                        ((MainFragment) getParentFragment()).start(SettingFragment.newInstance(bundle));
                        break;
                    case "APP下载及当前版本":
                       ((MainFragment) getParentFragment()).start(DownloadQRCodeFragment.newInstance(bundle));
                        break;
                    case "隐私政策":
                        ((MainFragment) getParentFragment()).start(PolicyFragment.newInstance(bundle));
                        break;
                }
                break;
        }
    }
    private Collection<? extends HomeBean> getMeData() {
        HomeBean homeBean = new HomeBean();
        homeBean.setItemType(HomeBean.TYPE_1);
        mList.add(homeBean);

        for (int i = 0; i < listTitles.length; i++) {
            HomeBean homeBean1 = new HomeBean();
            homeBean1.setTitle(listTitles[i]);
            homeBean1.setItemType(HomeBean.TYPE_2);
            List<MenuBean> listMenus = new ArrayList<>();
            for (int j = 0; j < content1.length; j++) {
                MenuBean menuBean = new MenuBean();
                menuBean.setTitle(content1[j]);
                menuBean.setIcons(icons[j]);
                listMenus.add(menuBean);
            }
            homeBean1.setMenuBeanList(listMenus);
            mList.add(homeBean1);
        }

        return mList;
    }

    public class MeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

        public MeAdapter() {
            addItemType(HomeBean.TYPE_1, R.layout.item_me_head);
            addItemType(HomeBean.TYPE_2, R.layout.item_tag_reyclerview);
            addChildClickViewIds(R.id.tv_mod);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {
            switch (holder.getItemViewType()) {
                case HomeBean.TYPE_1:
                    if (mLoginBean != null) {
                        holder.setText(R.id.tv_login_name, mLoginBean.getData().getRealName())
                                .setText(R.id.tv_job_num, "工号：" + mLoginBean.getData().getEmpNo() + "")
                                .setText(R.id.tv_post, "岗位：" + mLoginBean.getData().getDepartmentName() + " (" + mLoginBean.getData().getStationName() + ")");
                    }
                    break;
                case HomeBean.TYPE_2:
                    holder.setText(R.id.tv_item_title, bean.getTitle());
                    RecyclerView mRecyclerView = holder.getView(R.id.item_recyclerView);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    ChildAdapter mChildAdapter = new ChildAdapter();
                    mRecyclerView.setAdapter(mChildAdapter);
                    mChildAdapter.setList(bean.getMenuBeanList());
                    mChildAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                            EventBusUtil.sendEvent(new Event(FinalClass.Event_title, mChildAdapter.getData().get(position).getTitle()));
                        }
                    });

                    break;
            }
        }
    }

    public static class ChildAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

        public ChildAdapter() {
            super(R.layout.item_icon_title_director);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, MenuBean bean) {
            holder.setText(R.id.tv_title, bean.getTitle())
                    .setImageResource(R.id.iv_icon, bean.getIcons());

        }
    }
}

