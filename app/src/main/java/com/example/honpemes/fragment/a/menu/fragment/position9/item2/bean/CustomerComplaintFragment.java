package com.example.honpemes.fragment.a.menu.fragment.position9.item2.bean;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;

/**
 * @author: asus
 * @date: 2022/11/22
 * @Description: 客诉 / 客供资料
 */
public class CustomerComplaintFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    String enterTime, loadMoreTime;
    BaseCustomerAdapter mAdapter;
    int index = 0;

    public static CustomerComplaintFragment newInstance(Bundle bundle) {
        CustomerComplaintFragment fragment = new CustomerComplaintFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_titlebar_head_foot_refresh_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        enterTime = DateUtil.ymdhms.format(new Date());
        tvTitle.setText(getArguments().getString(FinalClass.title));
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new BaseCustomerAdapter();
        mRecyclerview.setAdapter(mAdapter);
        refreshLayout.autoRefresh();
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                index = 0;
                initHttp();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreTime = DateUtil.ymdhms.format(new Date());
                initLoadMore();
            }
        });
    }

    private void initLoadMore() {
        EasyHttp.post("http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM MakeProductionOrderChangeNotice where " +
                tabDate +
                ">'" +
                "2022-11-21 09:39:03" +
                "' and " +
                tabDate +
                "<='" +
                "2022-11-21 09:39:03" +
                "' order by " +
                tabDate +
                " desc")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                CustomerBean bean = GsonBuildUtil.create().fromJson(s, CustomerBean.class);
                                LatteLogger.d("testddddffe", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getData().size() > 0) {
                                    mAdapter.addData(bean.getData());
                                } else {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                                //  mAdapter.setList(bean.getData());
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private String tabName, tabDate;

    private void initHttp() {
        if (tvTitle.getText().toString().contains("客诉")) {
            tabName = "生产订单客诉管理";
            tabDate = "投诉日期";
        } else if (tvTitle.getText().toString().contains("客供资料")) {
            tabName = "生产订单客供资料";
            tabDate = "上传日期";
        }

        EasyHttp.post("http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM " +
                tabName +
                " order by " +
                tabDate +
                " desc offset " +
                index +
                "*20 rows fetch next 20 rows only")
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testdfdd", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                CustomerBean bean = GsonBuildUtil.create().fromJson(s, CustomerBean.class);
                                if (tvTitle.getText().toString().contains("客诉")) {
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        bean.getData().get(i).setItemType(CustomerBean.DataBean.TYPE_1);
                                    }
                                } else if (tvTitle.getText().toString().contains("客供资料")) {
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        bean.getData().get(i).setItemType(CustomerBean.DataBean.TYPE_2);
                                    }
                                }

                                mAdapter.setList(bean.getData());
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public class BaseCustomerAdapter extends BaseMultiItemQuickAdapter<CustomerBean.DataBean, BaseViewHolder> {


        public BaseCustomerAdapter() {
            addItemType(CustomerBean.DataBean.TYPE_1, R.layout.item_custom_complain);
            addItemType(CustomerBean.DataBean.TYPE_2, R.layout.item_custom_info);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, CustomerBean.DataBean bean) {
            switch (holder.getItemViewType()) {
                case CustomerBean.DataBean.TYPE_1:
                    String title1 = "生产单号：" + bean.get生产单号();
                    String title2 = "产品名称：" + bean.get产品名称();
                    String title3 = "投诉问题：" + bean.get投诉问题();
                    String title4 = "客诉单号：" + bean.get客诉单号();
                    String title5 = "客户名称：" + bean.get客户名称();
                    String title6 = "订单组别：" + bean.get订单组别();
                    String title7 = "下单日期：" + bean.get下单日期();
                    String title8 = "交货日期：" + bean.get交货日期();
                    String title9 = "实际交货日期：" + bean.get实际交货日期();
                    String title10 = "客诉类型：" + bean.get客诉类型();

                    holder.setText(R.id.tv_title1, StringUtil.changeStrColor(title1, R.color.black, "生产单号：".length(), title1.length()))
                            .setText(R.id.tv_title2, StringUtil.changeStrColor(title2, R.color.black, "产品名称：".length(), title2.length()))
                            .setText(R.id.tv_title3, StringUtil.changeStrColor(title3, R.color.black, "投诉问题：".length(), title3.length()))
                            .setText(R.id.tv_title4, StringUtil.changeStrColor(title4, R.color.black, "客诉单号：".length(), title4.length()))
                            .setText(R.id.tv_title5, StringUtil.changeStrColor(title5, R.color.black, "客户名称：".length(), title5.length()))
                            .setText(R.id.tv_title6, StringUtil.changeStrColor(title6, R.color.black, "订单组别：".length(), title6.length()))
                            .setText(R.id.tv_title6, StringUtil.changeStrColor(title7, R.color.black, "下单日期：".length(), title7.length()))
                            .setText(R.id.tv_title7, StringUtil.changeStrColor(title8, R.color.black, "交货日期：".length(), title8.length()))
                            .setText(R.id.tv_title8, StringUtil.changeStrColor(title9, R.color.black, "实际交货日期：".length(), title9.length()))
                            .setText(R.id.tv_content4, StringUtil.changeStrColor(title10, R.color.black, "客诉类型：".length(), title10.length()))
                            .setText(R.id.tv_content5, "投诉日期：" + bean.get投诉日期());

                    ImageView imageView = holder.getView(R.id.iv_director);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bean.isExpanded()) {
                                bean.setExpanded(false);
                                ViewCompat.animate(imageView).setDuration(200)
                                        .setInterpolator(new DecelerateInterpolator())
                                        .rotation(0)
                                        .start();
                            } else {
                                bean.setExpanded(true);
                                ViewCompat.animate(imageView).setDuration(200)
                                        .setInterpolator(new DecelerateInterpolator())
                                        .rotation(-180f)
                                        .start();
                            }
                            holder.setGone(R.id.ll_show, bean.isExpanded() ? false : true);
                        }
                    });
                    break;
                case CustomerBean.DataBean.TYPE_2:
                    String str = getString(R.string.author) + bean.get上传人员() + "\n"+
                             getString(R.string.sort)+bean.get文件分类()+ "\n"+
                            "发布时间："+bean.get上传日期()+ "\n"+
                            "链接地址："+ Constants.URL+bean.get存放位置();

                    holder.setText(R.id.tv_item1, str)
                            .setGone(R.id.tv_item2,true)
                            .setGone(R.id.tv_item3,true)
                            .setGone(R.id.tv_item4,true);

//                    holder.setText(R.id.tv_item1, StringUtil.changeStrColor(str1, R.color.black, "文件分类：".length(), str1.length()))
//                            .setText(R.id.tv_item2, StringUtil.changeStrColor(str2, R.color.black, "文件名称：".length(), str2.length()))
//                            .setText(R.id.tv_item3, StringUtil.changeStrColor(str3, R.color.black, "上传人员：".length(), str3.length()))
//                            .setText(R.id.tv_item4, "发布于："+DateUtil.ymd_hm.format(DateUtil.setDate(DateUtil.ymdhm, bean.get上传日期())));

                    ImageView ivItem = holder.getView(R.id.iv_icon);
                    if (!"".equals(bean.get存放位置()) || !TextUtils.isEmpty(bean.get存放位置())) {
                        String fileType = bean.get存放位置().substring(bean.get存放位置().lastIndexOf("."));
                        if (fileType.contains("txt")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_txt);
                        }else if (fileType.contains("file")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_file);
                        } else if (fileType.contains("pdf")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_pdf);
                        }  else if (fileType.contains("ppt")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_ppt);
                        } else if (fileType.contains("excel")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_excel);
                        } else if (fileType.contains("word")) {
                            ivItem.setImageResource(R.mipmap.vw_ic_word);
                        } else if (fileType.contains("png")){
                            Glide.with(getContext()).load(Constants.URL + bean.get存放位置())
                                    .placeholder(R.drawable.ic_default_icon_24).error(R.drawable.ic_default_icon_24).into(ivItem);
                        }else{
                            ivItem.setImageResource(R.mipmap.vw_ic_zip);
                        }
                        ivItem.setVisibility(View.VISIBLE);
                    }else {
                        ivItem.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    }
}






























