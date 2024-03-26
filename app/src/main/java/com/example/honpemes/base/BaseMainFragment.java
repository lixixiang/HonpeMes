package com.example.honpemes.base;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.RxPermissionsTool;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Lixixiang on 2023/2/27 12:06
 * 主页基类
 */
public abstract class BaseMainFragment extends SupportFragment {
    public static final String TAG = "BaseMainFragment";
    private Unbinder unbinder;
    private View rootView;
    // 再点一次退出程序时间设置
    public static final long WAIT_TIME = 2000L;
    public static long TOUCH_TIME = 0;
    protected Toolbar toolbar;
    protected Activity _mActivity;
    //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框
    protected String Description;
    public LoginBean mLoginBean;
    protected Bundle bundle = new Bundle();
    protected String userType, apiToken;
    protected int statusColor =0;

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
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxPermissionsTool.
                with(_mActivity).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.INTERNET).
                initPermission();
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

    protected int setTitleBar() {
        return R.id.toolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
            userType = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_INFO, "");
            View titleBar = rootView.findViewById(setTitleBar());
            ImmersionBar.setTitleBar(_mActivity, titleBar);
            View statusBarView = rootView.findViewById(setStatusBarView());
            ImmersionBar.setStatusBarView(_mActivity, statusBarView);
            try {
                JSONObject o = new JSONObject(userType);
                if (o.getInt(FinalClass.Tag) == 1 && o.getJSONObject("Data") != null) {
                    if (userType != null && !"".equals(userType)) {
                        mLoginBean = GsonBuildUtil.create().fromJson(userType, LoginBean.class);
                        LatteLogger.d("testLoginBean", GsonBuildUtil.GsonBuilder(mLoginBean));
                        apiToken = mLoginBean.getData().getApiToken();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            initView();

        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }

    protected int setStatusBarView() {
        return statusColor;
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();

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
    public void onStickyEventBusCome(Event event) {
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
        ImmersionBar.with(this).statusBarColor(R.color.blue_l).navigationBarColor(R.color.black).init();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
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
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        _mActivity = null;
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }
}



















































