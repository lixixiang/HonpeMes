package com.example.honpemes.widget.net;

import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.api.TableClass;
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
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

/**
 * 作者：asus on 2023/12/27 16:31
 * 注释：ApiToken过期
 */
public class ApiTokenDelayedUtil {

    public static void HttpLogin() {
       String userNameValue = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.USER_NAME, "");
        String  passwordValue = (String) DBUtils.get(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, "");
        if (!"".equals(userNameValue)) {
            EasyHttp.post(Constants.Login + "userName=" + userNameValue + "&password=" + passwordValue)
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
                                    DBUtils.put(TableClass.TABLE_USER_INFO, TableClass.KEY_INFO, s);

                                    DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.KEY_ISPOLICY, "同意隐私政策");
                                    DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.USER_NAME, userNameValue);
                                    DBUtils.put(TableClass.TABLE_LOGIN_INFO, TableClass.PASSWORD, passwordValue);

                                    EventBusUtil.sendEvent(new Event(FinalClass.Login_fair,s));
                                } else {
                                    ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
}
