package com.example.honpemes.fragment.b.setting;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.MainFragment;
import com.example.honpemes.fragment.b.setting.password.PassWordFragment;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.dialog.TipsDialog;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.Util;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by Lixixiang on 2023/3/1 11:46
 */
public class SettingFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    private String[] titles = {"检测新版本", "修改密码", "退出登录"};

    private SettingAdapter mAdapter;

    public static SettingFragment newInstance(Bundle bundle) {
        SettingFragment fragment = new SettingFragment();
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
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new SettingAdapter();
        mAdapter.setList(StringUtil.ArrToList(titles));
        mRecyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String strTitle = mAdapter.getData().get(position);
                bundle.putString(FinalClass.title, strTitle);
                switch (strTitle) {
                    case "检测新版本":
                        ToastUtil.getInstance().showToast("暂无接口");
                        break;
                    case "修改密码":
                        start(PassWordFragment.newInstance(bundle));
                        break;
                    case "退出登录":
                        TipsDialog.create(_mActivity, "退出登录", "是否确定退出程序?", getString(R.string.sure), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getLoginOff();
                            }
                        }, getString(R.string.cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }, true, true, true).show();
                        break;
                }
            }
        });
    }

    public static class SettingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SettingAdapter() {
            super(R.layout.item_single_text);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, String s) {
            holder.setText(R.id.tv_item_title, s);
            LinearLayout llBg = holder.getView(R.id.item_ll_bg);
            TextView tvTitle = holder.getView(R.id.tv_item_title);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dp2px(55));
            llBg.setLayoutParams(params);
            llBg.setBackgroundResource(R.color.white);

        }
    }



    private void getLoginOff() {
        EasyHttp.post(Constants.Login_Off + "token=" + apiToken)
                .retryCount(0)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testonSuccess",s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 0) {
                                DBUtils.clear(_mActivity, TableClass.TABLE_USER_INFO);
                                startWithPop(LoginFragment.newInstance(bundle));
                            }else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}

