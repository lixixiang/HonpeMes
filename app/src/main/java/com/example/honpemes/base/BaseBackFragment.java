package com.example.honpemes.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.TeamBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.gyf.immersionbar.ImmersionBar;
import com.zhouyou.http.EasyHttp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Lixixiang on 2023/2/27 18:04
 */
public abstract class BaseBackFragment extends SupportFragment {
    public static final String TAG = "BaseBackFragment";
    protected View rootView;
    public Disposable disposable;
    protected Bundle mSavedInstanceState;
    private boolean isFragmentVisible;
    public boolean isFirst;
    protected  Bundle bundle = new Bundle();
    public String apiToken,userType;
    protected LoginBean loginBean;
    protected Activity _mActivity;
    protected TeamBean mTeamBean;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _mActivity = (Activity) activity;
    }
    protected void initToolbarNav(final View homeBack) {
        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                EasyHttp.cancelSubscription(disposable);
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(TAG);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TAG, isHidden());
    }


    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发的粘性事件
     *
     * @param event
     */
    protected void receiveStickyEvent(Event event) {

    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
            ButterKnife.bind(this, rootView);
            this.mSavedInstanceState = savedInstanceState;

            try {
                userType = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_INFO, "");
                LatteLogger.d("userType",userType);
                JSONObject o = new JSONObject(userType);

                String strTeamData = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_TEAM, "");
                if (!"".equals(strTeamData) && !TextUtils.isEmpty(strTeamData)) {
                    mTeamBean = GsonBuildUtil.create().fromJson(strTeamData, TeamBean.class);
                }
//                LatteLogger.d("mTeamBean",GsonBuildUtil.GsonBuilder(mTeamBean));

                if (o.getInt(FinalClass.Tag) == 1 && o.getJSONObject("Data") != null) {
                    if (userType != null && !"".equals(userType)) {
                        loginBean = GsonBuildUtil.create().fromJson(userType, LoginBean.class);
                        apiToken = loginBean.getData().getApiToken();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            initFontScale();
            initView();

            //可见，但是并没有加载过
            if (isFragmentVisible && !isFirst) {
                onFragmentVisibleChange(true);
            }
        } else {
            ButterKnife.bind( rootView);
        }
        return rootView;
    }


    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //请在onSupportVisible实现沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).
                keyboardEnable(true).statusBarColor(R.color.blue_l)
                .statusBarDarkFont(true).navigationBarColor(R.color.black).init();
    }

    public void  initFontScale(){
        Configuration configuration = getResources().getConfiguration();
        LatteLogger.d("fontScale", configuration.fontScale);
        DisplayMetrics metrics = new DisplayMetrics();
        _mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getResources().updateConfiguration(configuration, metrics);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFragmentVisible = true;
        }
        if (rootView == null) {
            return;
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisible) {
            onFragmentVisibleChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        EasyHttp.cancelSubscription(disposable);
        return super.onBackPressedSupport();
    }
}



























