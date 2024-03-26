package com.example.honpemes.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;

import com.example.honpemes.R;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Lixixiang on 2023/2/27 9:39
 * 所有类的基类
 */
public abstract class BaseActivity extends SupportActivity {
    protected Context mContext;
    protected Activity _mActivity;
    private Unbinder unbinder;
    private InputMethodManager imm;
    public String apiToken ="";
    protected Bundle bundle = new Bundle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        View titleBar = findViewById(setTitleBar());
        ImmersionBar.setTitleBar(this, titleBar);
        mContext = this;
        _mActivity = this;
        this.initView();

    }
    protected void initToolbarNav(final View homeBack) {
        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard();
                finish();
            }
        });
    }

    protected int setTitleBar() {
        return R.id.toolbar;
    }

    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView();

    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }
    /**
     * 接收到分发的粘性事件
     *
     * @param event
     */
    protected void receiveStickyEvent(Event event) {

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        this.imm = null;
        unbinder.unbind();
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
