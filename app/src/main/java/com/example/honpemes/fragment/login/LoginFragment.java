package com.example.honpemes.fragment.login;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.honpemes.MyApplication;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.LoginBean;
import com.example.honpemes.fragment.MainFragment;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.OrderItemFragment;
import com.example.honpemes.fragment.a.menu.fragment.position10.item5.BusinessMangerOrderFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.OrderFormFragment;
import com.example.honpemes.fragment.a.menu.fragment.position11.item2.MeterManagerFragment;
import com.example.honpemes.fragment.a.menu.fragment.position3.item2.DeviceStatusHomeFragment;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.FileManagerFragment;
import com.example.honpemes.fragment.b.setting.mob.MobPersonInfoFragment;
import com.example.honpemes.utils.DBUtils;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.widget.DJEditText;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lixixiang on 2023/3/1 9:25
 */
public class LoginFragment extends BaseBackFragment {

    @BindView(R.id.tv_policy_content)
    TextView tvPolicyContent;
    @BindView(R.id.ck_agree_policy)
    CheckBox ckAgreePolicy;
    @BindView(R.id.et_mobile)
    DJEditText mEtMobile;
    @BindView(R.id.et_password)
    DJEditText mEtPassword;

    private String strPolicy, userNameValue, passwordValue;
    // 再点一次退出程序时间设置
    public static final long WAIT_TIME = 2000L;
    public static long TOUCH_TIME = 0;

    public static LoginFragment newInstance(Bundle bundle) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_login;
    }

    @Override
    protected void initView() {
        strPolicy = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.KEY_ISPOLICY, "不同意隐私政策");
        userNameValue = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.USER_NAME, "");
        passwordValue = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, "");

        if (!"".equals(userNameValue)) {
            mEtMobile.setText(userNameValue);
        }
        if (!"".equals(passwordValue)) {
            mEtPassword.setText(passwordValue);
        }
        initSpannable();
        if ("同意隐私政策".equals(strPolicy)) {
            ckAgreePolicy.setChecked(true);
        } else {
            ckAgreePolicy.setChecked(false);
        }

        ckAgreePolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ckAgreePolicy.setChecked(isChecked);
                if (isChecked) {
                    DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.KEY_ISPOLICY, "同意隐私政策");
                } else {
                    DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.KEY_ISPOLICY, "不同意隐私政策");
                }
            }
        });

    }

    private void initSpannable() {
        //我已阅读并同意《用户服务协议》和《隐私政策》
        SpannableString spannableString = new SpannableString(tvPolicyContent.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan
                (MyApplication.getContext().getResources().getColor(R.color.blue_l)), 7, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan
                (MyApplication.getContext().getResources().getColor(R.color.blue_l)), 16, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAgreementClick(), 7, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextPrivacyClick(), 16, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置点击事件，加上这句话才有效果
        tvPolicyContent.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色为透明（有默认背景）
        tvPolicyContent.setHighlightColor(getResources().getColor(R.color.blue_alpha_l_l));
        tvPolicyContent.setText(spannableString);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        userNameValue = mEtMobile.getText().toString().trim();
        passwordValue = mEtPassword.getText().toString().trim();
        hideSoftInput();

        ckAgreePolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ckAgreePolicy.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_l)));
                } else {
                    ckAgreePolicy.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_l)));
                }
            }
        });

        if (ckAgreePolicy.isChecked()) {
            if (!TextUtils.isEmpty(userNameValue) && !TextUtils.isEmpty(passwordValue)) {
                DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.USER_NAME, userNameValue);
                DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.PASSWORD, passwordValue);
                DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.WebToken, "WebToken");

                HttpLogin(userNameValue, passwordValue);
                ckAgreePolicy.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_l)));
            }
        } else {
            ckAgreePolicy.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_l)));
            ToastUtil.getInstance().showToast("需要同意相关协议才能登录！");
        }
    }

    private void HttpLogin(String userName, String password) {
        LatteLogger.d("testddddd", userName + "   " + password);
        disposable = EasyHttp.post(Constants.Login + "userName=" + userName + "&password=" + password)
                .retryCount(0)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(ApiException e) {
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("onSuccessonSuccess", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 1) {
                                LoginBean mLoginBean = GsonBuildUtil.create().fromJson(s, LoginBean.class);
                                DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.KEY_INFO, s);

                                DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.KEY_ISPOLICY, "同意隐私政策");
                                DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.USER_NAME, userNameValue);
                                DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, passwordValue);
                                //   DepartCodeName(mLoginBean.getData().getApiToken());
                                TeamCodeTeamName(mLoginBean.getData().getApiToken());
                            } else {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void DepartCodeName(String apiToken) {
        EasyHttp.post(Constants.SelectDepart(apiToken))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testSrtring", s);
                    }
                });
    }

    private void TeamCodeTeamName(String token) {
        EasyHttp.post(Constants.SelectTeam(token))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testTeamCodeTeamName", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getInt(FinalClass.Tag) == 0) {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            } else if (o.getInt(FinalClass.Tag) == 1) {
                                DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.KEY_TEAM, s);
                            }
                            LatteLogger.d("tesetef", getArguments().getSerializable(FinalClass.title));
                            String strTitle = getArguments().getString(FinalClass.title);
                            bundle.putString(FinalClass.title, strTitle);
                            if (strTitle.contains("退出登录")) {
                                pop();
                            } else if (strTitle.contains("生产管理")) {
                                bundle.putString(FinalClass.title, "生产管理");
                                //  startWithPop(ProduceManagerFragment.newInstance(bundle));
                            } else if (strTitle.contains("文档")) {
                                startWithPop(FileManagerFragment.newInstance(bundle));
                            } else if (strTitle.contains("设备状态")) {
                                startWithPop(DeviceStatusHomeFragment.newInstance(bundle));
                            } else if (strTitle.contains("待审核订单")) {
                                startWithPop(OrderItemFragment.newInstance(bundle));
                            } else if (strTitle.contains("订单看板")) {
                                startWithPop(OrderFormFragment.newInstance(bundle));
                            } else if (strTitle.contains("电表管理")) {
                                startWithPop(MeterManagerFragment.newInstance(bundle));
                            } else if (strTitle.contains("订单")) {
                                startWithPop(BusinessMangerOrderFragment.newInstance(bundle));
                            } else if (strTitle.contains("修改个人资料")) {
                                startWithPop(MobPersonInfoFragment.newInstance());
                            } else if (strTitle.contains("MainFragment")) {
                                startWithPop(MainFragment.newInstance());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public class TextAgreementClick extends ClickableSpan {
        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            //设置文本的颜色
            //   ds.setColor(getResources().getColor(R.color.colorPrimary));
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(@NonNull View widget) {
            bundle.putString(FinalClass.title, "用户服务协议");
            start(PolicyFragment.newInstance(bundle));
        }
    }

    public class TextPrivacyClick extends ClickableSpan {

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            //设置文本的颜色
            //     ds.setColor(getResources().getColor(R.color.colorPrimary));
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(@NonNull View widget) {
            bundle.putString(FinalClass.title, "隐私政策");
            start(PolicyFragment.newInstance(bundle));
        }
    }

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
}








