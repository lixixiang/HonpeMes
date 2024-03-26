package com.example.honpemes.fragment.a.menu.fragment.position4.position3;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position4.position3.adapter.AboutRunOutAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position4.position3.bean.AboutRunOutBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;

/**
 * 即将超时
 */
public class AboutRunOutFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private AboutRunOutAdapter mAdapter;

    public static AboutRunOutFragment newInstance(Bundle bundle) {
        AboutRunOutFragment fragment = new AboutRunOutFragment();
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

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initAdapter();
        getRequestJsonData();
    }

    private void initAdapter() {
        mAdapter = new AboutRunOutAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerview.setAdapter(mAdapter);

    }

    private void getRequestJsonData() {
        EasyHttp.post(Constants.getAboutRunOut())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                AboutRunOutBean bean = GsonBuildUtil.create().fromJson(s, AboutRunOutBean.class);
                                LatteLogger.d("getRequestJsonData", GsonBuildUtil.GsonBuilder(bean));

                                for (int i = 0; i < bean.getData().size(); i++) {
                                    AboutRunOutBean.DataBean dataBean = bean.getData().get(i);
                                    bean.getData().get(i).setTips(
                                            DateUtil.getDatePoors
                                                    (DateUtil.setDate(dataBean.get要求交货日期()), new Date()));
                                }

                                mAdapter.setList(bean.getData());

                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                });
    }
}




















