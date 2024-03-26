package com.example.honpemes.fragment.a.menu.fragment.position6.item1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.adapter.FileManagerAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.bean.FileManagerBean;
import com.example.honpemes.utils.tree.FirstNode;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.tree.ThirdNode;
import com.example.honpemes.widget.pop.CommonPopupWindow;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/2 17:24
 * 文档
 */
public class FileManagerFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_times)
    TextView tvTimes;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.head_horizon_recyclerview)
    RecyclerView headHorizonRecyclerview;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;


    private int itemPos;
    private FileManagerAdapter mAdapter;
    private CommonPopupWindow popEvaluate;
    private List<BaseNode> list = new ArrayList<>();


    public static FileManagerFragment newInstance(Bundle bundle) {
        FileManagerFragment fragment = new FileManagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initData();

    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new FileManagerAdapter();
        mRecyclerview.setAdapter(mAdapter);
        initPopSelector();

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                bundle.putString("ids", mAdapter.getData().get(position).getId());
//                itemPos = position;
//                LatteLogger.d("onItemChildClick", mAdapter.getData().get(position).getId());
//                String fileName = mAdapter.getData().get(position).getDocumentName();
//                DialogUtils.tipsDialog(_mActivity, "温馨提示", "是否要审核" + "\"" +
//                        StringUtil.changeFontColor(_mActivity, fileName, R.color.blue_l, 0, fileName.length())
//                        + "\"" + "文档吗?");


            }
        });
    }


    private void initPopSelector() {
        popEvaluate = new CommonPopupWindow.Builder(_mActivity)
                .setView(R.layout.single_recyclerview)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .create();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView mPopRecyclerView = popEvaluate.getContentView().findViewById(R.id.m_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mPopRecyclerView.setLayoutManager(manager);
        mPopRecyclerView.setBackgroundResource(R.color.white);
        mPopRecyclerView.setLayoutParams(params);


    }

    /**
     * 显示列表
     */
    private void initData() {
        EasyHttp.post(Constants.DocumentManagement + apiToken)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("DocumentManagement", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                FileManagerBean bean = GsonBuildUtil.create().fromJson(s, FileManagerBean.class);
                                LatteLogger.d("testDocumentManagement", GsonBuildUtil.GsonBuilder(bean));

                                for (int i = 0; i < bean.data.size(); i++) {
                                    FirstNode entity = new FirstNode();
                                    entity.setTitle(bean.data.get(i).productName);
                                    entity.setCreate(bean.data.get(i).baseCreater);
                                    entity.setRemark(bean.data.get(i).remarks);
                                    entity.setDate(bean.data.get(i).baseCreateTime.replace("T"," "));
                                    List<BaseNode> childList = new ArrayList<>();
                                    for (int j = 0; j < bean.data.get(i).documentManagement.size(); j++) {
                                        FileManagerBean.DataBean.DocumentManagementBean childBean = bean.data.get(i).documentManagement.get(j);
                                        ThirdNode thirdNode = new ThirdNode();
                                        thirdNode.setDocumentManagement(childBean);
                                        thirdNode.set_id(j+1);
                                        childList.add(thirdNode);
                                    }
                                    entity.setChildNode(childList);
                                    list.add(entity);
                                }

                                 mAdapter.setList(list);
                            } else {
                                if (o.getString(FinalClass.Message).contains("Apitoken")) {
                                    bundle.putString(FinalClass.title, tvTitle.getText().toString());
                                    start(LoginFragment.newInstance(bundle));
                                }
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick(R.id.tv_end)
    public void onClick() {
        popEvaluate.showDownView(toolbar, 0.5f);

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EVENT:
                Http(bundle.getString("ids"));
                break;

        }
    }

    /**
     * 审核
     *
     * @param ids
     */
    private void Http(String ids) {
        EasyHttp.post(Constants.DocumentManagementVerifyJson + apiToken + "&ids=" + ids)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("DocumentManagementVerifyJson", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getString(FinalClass.Message).contains("已审核")) {
//                                mAdapter.getData().get(itemPos).setState("已审核");
                                mAdapter.notifyItemChanged(itemPos);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public class FileStatusSearchAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

        public FileStatusSearchAdapter() {
            addItemType(HomeBean.TYPE_1, R.layout.item_tv_et);
            addItemType(HomeBean.TYPE_2, R.layout.item_tag_text);
            addItemType(HomeBean.TYPE_3, R.layout.item_tag_right_recyclerview);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {

        }
    }

}



















