package com.example.honpemes.activity;


import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseActivity;
import com.example.honpemes.fragment.MainFragment;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ScreenAdapterUtils;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by lixixiang on 2023/2/27 9:30
 * 欢迎页
 */
public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.single_fragment;
    }

    @Override
    public void initView() {
        ScreenAdapterUtils.getPhoneScreen(_mActivity);
        intentFragment();

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    private void intentFragment() {
        // 已经获取到权限，执行你的操作
        String userType = (String) DBUtils.get(TableClass.TABLE_USER_INFO, TableClass.KEY_INFO, "");

        LatteLogger.d("userType", userType);
        bundle.putString(FinalClass.title, "MainFragment");
        if ("".equals(userType)) {
            loadRootFragment(R.id.fl_constance, LoginFragment.newInstance(bundle));
        } else {
            loadRootFragment(R.id.fl_constance, MainFragment.newInstance());
        }
    }

}































