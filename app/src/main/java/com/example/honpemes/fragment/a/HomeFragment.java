package com.example.honpemes.fragment.a;

import static com.example.honpemes.api.FinalClass.ENTER_CHILD_CLASS;
import static com.example.honpemes.api.FinalClass.UPDATA_CLIENT;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseMainFragment;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.MainFragment;
import com.example.honpemes.fragment.a.com_adapter.HomeAdapter;
import com.example.honpemes.fragment.a.menu.MenuManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.OrderItemFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item3.ChangeOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.OrderStatFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item6.OutTimeOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position10.item5.BusinessMangerOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position10.item6.SupplierClassFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.OrderFormFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.MeterManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item4.ConsumerComplaintsFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item1.OutSendOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.OperatorAssFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.ProduceProgressFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.ProcessingRecordFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.WorkTimeFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item6.QualityFragment;
import com.example.honpemes.fragment.a.menu.fragment.position2.item7.ScrapReplenishmentFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.DeviceInfoFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.DeviceStatusHomeFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item3.CurrentTaskFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item4.CheckRepairFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.KeepPlanMainFragment;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.SendOutFragment;
import com.example.honpemes.fragment.a.menu.fragment.position4.position2.SendOutTimeOutFragment;
import com.example.honpemes.fragment.a.menu.fragment.position4.position3.AboutRunOutFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item1.InventoryBoardFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.EnterBoardFragment;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.OutBoardFragment;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.FileManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item1.CustomInfoFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item2.CustomerComplaintFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item4.EmployeeDataFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.QrUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.widget.DJEditText;
import com.example.honpemes.widget.dialog.UpdateDialog;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.shenzhen.honpe.picure_library.tools.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/2/27 15:11
 * 首页工作台
 */
public class HomeFragment extends BaseMainFragment {

    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.et_search_text)
    DJEditText etSearchText;

    private ArrayList<MenuBean> indexDataAll = new ArrayList<>();
    private ArrayList<MenuBean> indexDetailList = new ArrayList<>();
    private ArrayList<MenuBean> searchDetailList = new ArrayList<>();
    private ArrayList<MenuBean> mList = new ArrayList<>();
    private HomeAdapter mAdapter;
    private String strMenu;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {
        initData();
        initRecyclerView();
    }

    private void initData() {
        Util.NewVersionRequest(_mActivity);//检测版本是否一致
        strMenu = (String) DBUtils.get(TableClass.TABLE_MENU, TableClass.KEY_USER_TEMP, "");
        JsonParser parser = new JsonParser();
        String strAll = GsonBuildUtil.getAssetsJson(_mActivity, "menulist.json");
        JsonArray jsonArray = parser.parse(strAll).getAsJsonArray();
        for (JsonElement indexArr : jsonArray) {
            MenuBean myHomeBean = GsonBuildUtil.create().fromJson(indexArr, MenuBean.class);
            indexDataAll.add(myHomeBean);
        }

        if (!"".equals(strMenu)) {
            indexDetailList.clear();
            ArrayList<MenuBean> indexDataList = GsonBuildUtil.create().fromJson(strMenu, new TypeToken<ArrayList<MenuBean>>() {
            }.getType());
            indexDetailList.addAll(indexDataList);
        } else {
           LatteLogger.d("testGson",GsonBuildUtil.GsonBuilder(indexDataAll));
            for (int i = 0; i < indexDataAll.size(); i++) {
                for (int j = 0; j < indexDataAll.get(i).getDetail().size(); j++) {
                    MenuBean myHomeBean = indexDataAll.get(i).getDetail().get(j);

                    if (myHomeBean.isEnable())
                        indexDetailList.add(indexDataAll.get(i).getDetail().get(j));
                }
            }
        }
    }

    private void initRecyclerView() {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new HomeAdapter();
        mRecyclerview.setAdapter(mAdapter);

        MenuBean allMyHomeBean = new MenuBean();
        allMyHomeBean.setIco("menu_all");
        allMyHomeBean.setId("all");
        allMyHomeBean.setEnable(true);
        allMyHomeBean.setTitle(getString(R.string.more));
        indexDetailList.add(allMyHomeBean);

        mAdapter.setList(getData());
        mAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                return mList.get(position).getSpanSize();
            }
        });
    }

    private Collection<? extends MenuBean> getData() {
        mList.clear();

        MenuBean bean = new MenuBean();
        bean.setTitle("已对接模块");
        bean.setItemType(MenuBean.TYPE_1);
        bean.setDetail(indexDetailList);
        mList.add(bean);

        for (int i = 0; i < indexDataAll.size(); i++) {
            MenuBean menuBean = indexDataAll.get(i);
            menuBean.setItemType(MenuBean.TYPE_2);
            menuBean.setTitle(menuBean.getTitle());
            menuBean.setDetail(indexDataAll.get(i).getDetail());
            mList.add(menuBean);
        }

        return mList;
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(Event event) {
        switch (event.getCode()) {
            case ENTER_CHILD_CLASS:
                String title = (String) event.getData();
                MenuStart(title);
                break;
            case FinalClass.BACK:
                indexDetailList = (ArrayList<MenuBean>) event.getData();
                MenuBean allMyHomeBean = new MenuBean();
                allMyHomeBean.setIco("menu_all");
                allMyHomeBean.setId("all");
                allMyHomeBean.setEnable(true);
                allMyHomeBean.setTitle(getString(R.string.more));
                indexDetailList.add(allMyHomeBean);
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getData().get(i).getItemType() == (MenuBean.TYPE_1)) {
                        mAdapter.getData().get(i).setDetail(indexDetailList);
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;
            case FinalClass.DJ_EDIT_CLEAR:
                mAdapter.setList(getData());
                break;
            case UPDATA_CLIENT:
                LatteLogger.d("testDatabea", "UPDATA_CLIENT");
                String des = _mActivity.getString(R.string.new_version) + StringUtils.getVersionDes(_mActivity);
                bundle.putString("des", des);
                UpdateDialog updateDialog = new UpdateDialog(_mActivity, bundle);
                updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                updateDialog.setCanceledOnTouchOutside(false);
                updateDialog.show();
                break;
        }
    }

    private void MenuStart(String title) {
        bundle.putString(FinalClass.title, title);
        switch (title) {
            case "全部":
                indexDetailList.remove(indexDetailList.size() - 1);
                bundle.putSerializable(FinalClass.headMenu, indexDetailList);
                bundle.putSerializable(FinalClass.contentMenu, indexDataAll);
                ((MainFragment) getParentFragment()).start(MenuManagerFragment.newInstance(bundle));
                break;
            case "待审核订单":
                ((MainFragment) getParentFragment()).start(OrderItemFragment.newInstance(bundle));
                break;
            case "订单变更":
                ((MainFragment) getParentFragment()).start(ChangeOrderFragment.newInstance(bundle));
                break;
            case "保养":
                ((MainFragment) getParentFragment()).start(KeepPlanMainFragment.newInstance(bundle));
                break;
            case "设备状态":
                ((MainFragment) getParentFragment()).start(DeviceStatusHomeFragment.newInstance(bundle));
                break;
            case "文档":
                ((MainFragment) getParentFragment()).start(FileManagerFragment.newInstance(bundle));
                break;
            case "客供资料":
                ((MainFragment) getParentFragment()).start(CustomerComplaintFragment.newInstance(bundle));
                break;
            case "员工资料":
                ((MainFragment) getParentFragment()).start(EmployeeDataFragment.newInstance(bundle));
                break;
            case "订单看板":
                ((MainFragment) getParentFragment()).start(OrderFormFragment.newInstance(bundle));
                break;
            case "电表管理":
                ((MainFragment) getParentFragment()).start(MeterManagerFragment.newInstance(bundle));
                break;
            case "生产订单":
                ((MainFragment) getParentFragment()).start(BusinessMangerOrderFragment.newInstance(bundle));
                break;
            case "订单":
                ((MainFragment) getParentFragment()).start(BusinessMangerOrderFragment.newInstance(bundle));
                break;
            case "生产进度":
                ((MainFragment) getParentFragment()).start(ProduceProgressFragment.newInstance(bundle));
                break;
            case "订单统计":
                ((MainFragment) getParentFragment()).start(OrderStatFragment.newInstance(bundle));
                break;
            case "操机工时":
                ((MainFragment) getParentFragment()).start(WorkTimeFragment.newInstance(bundle));
                break;
            case "产品品质":
                ((MainFragment) getParentFragment()).start(QualityFragment.newInstance(bundle));
                break;
            case "外发采购单":
                ((MainFragment) getParentFragment()).start(SendOutFragment.newInstance(bundle));
                break;
            case "订单超时":
                ((MainFragment) getParentFragment()).start(SendOutTimeOutFragment.newInstance(bundle));
                break;
            case "超时订单":
                ((MainFragment) getParentFragment()).start(OutTimeOrderFragment.newInstance(bundle));
                break;
            case "加工记录":
                ((MainFragment) getParentFragment()).start(ProcessingRecordFragment.newInstance(bundle));
                break;
            case "项目外发":
                ((MainFragment) getParentFragment()).start(OutSendOrderFragment.newInstance(bundle));
                break;
            case "操机指派":
                ((MainFragment) getParentFragment()).start(OperatorAssFragment.newInstance(bundle));
                break;
            case "设备信息":
                ((MainFragment) getParentFragment()).start(DeviceInfoFragment.newInstance(bundle));
                break;
            case "当前任务":
                ((MainFragment) getParentFragment()).start(CurrentTaskFragment.newInstance(bundle));
                break;
            case "检修记录":
                ((MainFragment) getParentFragment()).start(CheckRepairFragment.newInstance(bundle));
                break;
            case "报废补料":
                ((MainFragment) getParentFragment()).start(ScrapReplenishmentFragment.newInstance(bundle));
                break;
            case "客诉":
                ((MainFragment) getParentFragment()).start(ConsumerComplaintsFragment.newInstance(bundle));
                break;
            case "客户资料":
                ((MainFragment) getParentFragment()).start(CustomInfoFragment.newInstance(bundle));
                break;
            case "供应商分类":
                ((MainFragment) getParentFragment()).start(SupplierClassFragment.newInstance(bundle));
                break;
            case "库存看板":
                ((MainFragment) getParentFragment()).start(InventoryBoardFragment.newInstance(bundle));
                break;
            case "产品入库":
                ((MainFragment) getParentFragment()).start(EnterBoardFragment.newInstance(bundle));
                break;
            case "产品出库":
                ((MainFragment) getParentFragment()).start(OutBoardFragment.newInstance(bundle));
                break;
            case "订单即将超时":
                ((MainFragment) getParentFragment()).start(AboutRunOutFragment.newInstance(bundle));
                break;
        }
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initImmersionBar() {
//        ImmersionBar.with(this).fitsSystemWindows(true).
//                keyboardEnable(false).statusBarColor(R.color.blue_l)
//                .statusBarDarkFont(false).navigationBarColor(R.color.black).init();
    }

    @OnClick({R.id.iv_scan, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                QrUtil.ScanZxing(_mActivity);
                break;
            case R.id.iv_search:
                searchDetailList.clear();
                mList.clear();

                for (int i = 0; i < indexDataAll.size(); i++) {
                    for (int j = 0; j < indexDataAll.get(i).getDetail().size(); j++) {
                        MenuBean menuBean = indexDataAll.get(i).getDetail().get(j);
                        if (menuBean.getTitle().contains(etSearchText.getText().toString())) {
                            searchDetailList.add(menuBean);
                        }
                    }
                }

                MenuBean bean = new MenuBean();
                bean.setTitle("已搜索模块");
                bean.setItemType(MenuBean.TYPE_1);
                bean.setDetail(searchDetailList);

                mList.add(bean);
                mAdapter.setList(mList);
                LatteLogger.d("indexDataAll", GsonBuildUtil.GsonBuilder(searchDetailList));
                break;

        }
    }
}













