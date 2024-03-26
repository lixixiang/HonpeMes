package com.example.honpemes.fragment.b.setting.password;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.widget.DJEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * @author: asus
 * @date: 2022/10/26
 * @Description:
 */
public class PassWordFragment extends BaseBackFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;


    private String[] strTitles = {"原始密码", "新密码", "确认密码"};
    private String[] strHint = {"填写旧密码", "填写新密码", "再次填写新密码确认"};
    private String passwordValue, configPassWord;
    private PassWordAdapter mAdapter;

    public static PassWordFragment newInstance(Bundle bundle) {
        PassWordFragment fragment = new PassWordFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_month_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        passwordValue = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, "");

        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new PassWordAdapter();
        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setList(getData());
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                LatteLogger.d("testMAdapter",GsonBuildUtil.GsonBuilder(mAdapter.getData()));
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if ("新密码".equals(mAdapter.getData().get(i).getTitle())) {
                        passwordValue = mAdapter.getData().get(i).getContent();
                    } else if ("确认密码".equals(mAdapter.getData().get(i).getTitle())) {
                        configPassWord = mAdapter.getData().get(i).getContent();
                    }
                }
               HttpRequest();
            }
        });
    }

    List<HomeBean> mList = new ArrayList<>();

    private Collection<? extends HomeBean> getData() {
        for (int i = 0; i < strTitles.length; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle(strTitles[i]);
            homeBean.setHint(strHint[i]);
            homeBean.setItemType(HomeBean.TYPE_1);
            mList.add(homeBean);
        }

        HomeBean homeBean = new HomeBean();
        homeBean.setTitle("保存");
        homeBean.setItemType(HomeBean.TYPE_2);
        mList.add(homeBean);

        return mList;
    }

    private void HttpRequest() {

        JsonObject o = new JsonObject();
        JsonArray arr = new JsonArray();
        o.addProperty("Apitoken", apiToken);
        JsonObject o1 = new JsonObject();
        o1.addProperty("Apitoken", apiToken);
        o1.addProperty("Password", passwordValue);
        o1.addProperty("NewPassword", configPassWord);
        arr.add(o1);
        o.addProperty("Info", GsonBuildUtil.GsonToString(arr));
        LatteLogger.d("HttpRequest", GsonBuildUtil.GsonToString(o));

        EasyHttp.post(Constants.ChangePassword)
                .upJson(GsonBuildUtil.GsonToString(o))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testonSuccess", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                ToastUtil.getInstance().showToast("密码修改成功！");
                                DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, configPassWord);
                                hideSoftInput();
                            } else {
                                ToastUtil.getInstance().showToast("密码修改失败！原因：" + o.getString(FinalClass.Message));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public class PassWordAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

        public PassWordAdapter() {
            addItemType(HomeBean.TYPE_1, R.layout.item_tv_et);
            addItemType(HomeBean.TYPE_2, R.layout.item_btn);
            addChildClickViewIds(R.id.tv_btn);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {
            switch (holder.getItemViewType()) {
                case HomeBean.TYPE_1:
                    holder.setText(R.id.tv_title, bean.getTitle());
                    DJEditText mEdit = holder.getView(R.id.et_content);
                    Util.setEditTextHint(mEdit, 14, bean.getHint());
                    mEdit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            bean.setContent(mEdit.getText().toString());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    break;
                case HomeBean.TYPE_2:
                    break;
            }
        }
    }


}



























