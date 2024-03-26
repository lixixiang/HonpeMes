package com.example.honpemes.fragment.a.menu.fragment.position9.item4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.fragment.a.menu.fragment.position9.item2.adapter.SideIndexAdapter;
import com.example.honpemes.fragment.a.menu.fragment.position9.item4.EmployeeDataAdapter.EmployeeDataAdapter;
import com.example.honpemes.fragment.b.setting.mob.bean.UseInfoBean;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.widget.DJEditText;
import com.example.honpemes.widget.decoration.SectionItemDecoration;
import com.example.honpemes.widget.pinyin.PinyinComparator;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/7 14:52
 * 员工信息
 */
public class EmployeeDataFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.cp_search_box)
    DJEditText mSearchBox;
    @BindView(R.id.cp_overlay)
    TextView cpOverlay;
    @BindView(R.id.cp_side_index_bar)
    RecyclerView mIndexBarRecyclerView;
    @BindView(R.id.cp_no_result_icon)
    ImageView cpNoResultIcon;
    @BindView(R.id.cp_no_result_text)
    TextView cpNoResultText;
    @BindView(R.id.cp_empty_view)
    LinearLayout mEmptyView;

    List<String> mIndexItems = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private EmployeeDataAdapter mAdapter;
    private SideIndexAdapter mSideIndexAdapter;

    List<UseInfoBean.DataBean> mList = new ArrayList<>();
    List<UseInfoBean.DataBean> mSaveList = new ArrayList<>();
    private PinyinComparator mComparator;

    public static EmployeeDataFragment newInstance(Bundle bundle) {
        EmployeeDataFragment fragment = new EmployeeDataFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mComparator = new PinyinComparator();
        EmployeeDataData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview_sideindexbar;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getArguments().getString(FinalClass.title));
        mIndexBarRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mSearchBox.addTextChangedListener(this);
    }

    private void EmployeeDataData() {
        disposable = EasyHttp.post(Constants.URL_P + apiToken + "&strSQL=SELECT * FROM SysUser order by empno asc")
                .retryCount(0)
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
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                bundle.putString(FinalClass.title, tvTitle.getText().toString());
                                startWithPop(LoginFragment.newInstance(bundle));
                                return;
                            }

                            if (o.getInt(FinalClass.Tag) == 1) { //返回成功
                                UseInfoBean useInfoBean = GsonBuildUtil.create().fromJson(s, UseInfoBean.class);
                                LatteLogger.d("useInfoBean",GsonBuildUtil.GsonBuilder(useInfoBean));

                                mList.clear();
                                mSaveList.clear();
                                for (int i = 0; i < useInfoBean.getData().size(); i++) {
                                    if (!"".equals(useInfoBean.getData().get(i).getRealname()) && !TextUtils.isEmpty(useInfoBean.getData().get(i).getRealname())) {
                                        mList.add(useInfoBean.getData().get(i));
                                    }
                                }

                                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setHasFixedSize(true);

                                // 根据a-z进行排序源数据
                                Collections.sort(mList, mComparator);
                                mSaveList.addAll(mList);
                                recyclerView.addItemDecoration(new SectionItemDecoration(_mActivity, mList), 0);
                                recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, RecyclerView.VERTICAL));
                                recyclerView.setLayoutManager(mLayoutManager);
                                mAdapter = new EmployeeDataAdapter();
                                mAdapter.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(mAdapter);
                                mAdapter.setList(mList);

                                for (int i = 0; i < mList.size(); i++) {
                                    mIndexItems.add(mList.get(i).getSection());
                                }
                                StringUtil.removeDuplicate(mIndexItems);
                                mIndexBarRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
                                mSideIndexAdapter = new SideIndexAdapter(cpOverlay);
                                mSideIndexAdapter.setList(mIndexItems);
                                mIndexBarRecyclerView.setAdapter(mSideIndexAdapter);


                                mSideIndexAdapter.setOnIndexChangedListener(new SideIndexAdapter.OnIndexTouchedChangedListener() {
                                    @Override
                                    public void onIndexChanged(String index, int position) {
                                        //滚动RecyclerView到索引位置
                                        mAdapter.scrollToSection(index);
                                    }
                                });
                                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                        super.onScrollStateChanged(recyclerView, newState);
                                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                            mAdapter.refreshLocationItem();
                                        }
                                        hideSoftInput();
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
        filterData(s.toString());
    }

    int length;

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<UseInfoBean.DataBean> filterDateList = new ArrayList<>();
        if (filterStr.length() == 0) {
            mList.clear();
            filterDateList.clear();
            mList.addAll(mSaveList);
            filterDateList.addAll(mList);
        } else {
            if (length > filterStr.length()) {
                mList.clear();
                mList.addAll(mSaveList);
            }
            length = filterStr.length();
            for (UseInfoBean.DataBean sortModel : mList) {
                String name = sortModel.getRealname();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        StringUtil.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || StringUtil.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || StringUtil.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                        || String.valueOf(sortModel.getEmpno()).contains(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, mComparator);
        mList.clear();
        mList.addAll(filterDateList);
        mAdapter.setList(mList);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick(R.id.cp_cancel)
    public void onClick() {
        mSearchBox.setText("");
    }
}




















