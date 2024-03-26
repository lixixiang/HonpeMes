package com.example.honpemes.fragment.b.setting.mob;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.datepickview.wheelpicker.DatePicker;
import com.example.datepickview.wheelpicker.EthnicPicker;
import com.example.datepickview.wheelpicker.NumberPicker;
import com.example.datepickview.wheelpicker.OptionPicker;
import com.example.datepickview.wheelpicker.annotation.EthnicSpec;
import com.example.datepickview.wheelpicker.contract.OnDatePickedListener;
import com.example.datepickview.wheelpicker.contract.OnNumberPickedListener;
import com.example.datepickview.wheelpicker.contract.OnOptionPickedListener;
import com.example.datepickview.wheelpicker.contract.OnOptionSelectedListener;
import com.example.datepickview.wheelpicker.entity.DateEntity;
import com.example.datepickview.wheelpicker.entity.EthnicEntity;
import com.example.datepickview.wheelview.contract.WheelFormatter;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.base.BaseBackFragment;
import com.example.honpemes.bean.HomeBean;
import com.example.honpemes.fragment.b.setting.mob.bean.UseInfoBean;
import com.example.honpemes.fragment.login.LoginFragment;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.PictureUtils;
import com.example.honpemes.utils.ProgressUtils;
import com.example.honpemes.utils.StringUtil;
import com.example.honpemes.utils.ToastUtil;
import com.example.honpemes.utils.dialog.Base.DialogUtils;
import com.example.honpemes.utils.dialog.Base.EditDialog;
import com.example.honpemes.utils.glide.Base64Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shenzhen.honpe.picure_library.entity.LocalMedia;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lixixiang on 2023/3/1 15:52
 * 修改个人资料
 */
public class MobPersonInfoFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.m_recyclerView)
    RecyclerView mRecyclerview;
    private String[] strSettings = {"姓名", "性别", "民族", "生日", "年龄", "学校", "学历"};//基本情况
    private String[] strSettings2 = {"部/组", "工号", "职位"};//现居何职
    private String[] strSettings3 = {"现居住处", "QQ", "微信", "电话"};//联系方式

    private String[] sex = {"男", "女"};
    private int[] colors = {R.color.blue_l, R.color.red_l};

    private ChildAdapter mAdapter = new ChildAdapter();
    private List<UseInfoBean.DataBean> menuBeanList = new ArrayList<>();
    private Calendar selectedDate, startDate, endDate;


    public static MobPersonInfoFragment newInstance() {
        MobPersonInfoFragment fragment = new MobPersonInfoFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_titleba_refresh_recyclerview;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initData();
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("修改个人资料");
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerview.setAdapter(mAdapter);
        PictureUtils.getDefaultStyle(getContext());
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                PictureUtils.createPictureSelector(_mActivity);
            }
        });
        //  selectTime();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                UseInfoBean.DataBean itemData = mAdapter.getData().get(position);
                LatteLogger.d("tefwwfewff", itemData.getTitle());
                String title = "修改" + itemData.getTitle();
                switch (itemData.getTitle()) {
                    case "电话":
                    case "QQ":
                    case "微信":
                    case "现居住处":
                    case "工号":
                    case "职位":
                    case "学校":
                    case "姓名":
                        EditDialog editDialog = new EditDialog(_mActivity, title, getString(R.string.cancel), getString(R.string.sure));
                        editDialog.setOnEditInterfaceLister(new EditDialog.EdiInterfaceListener() {
                            @Override
                            public void InputContent(String content) {
                                String tag = "";
                                if (title.contains("电话")) {
                                    tag = "mobile";
                                } else if (title.contains("QQ")) {
                                    tag = "qq";
                                } else if (title.contains("微信")) {
                                    tag = "wechat";
                                } else if (title.contains("现居住处")) {
                                    tag = "presentaddr";
                                } else if (title.contains("工号")) {
                                    tag = "empno";
                                } else if (title.contains("职位")) {
                                    tag = "stationname";
                                } else if (title.contains("学校")) {
                                    tag = "school";
                                } else if (title.contains("姓名")) {
                                    tag = "realname";
                                }
                                itemData.setContent(content);
                                MobInfo(tag, itemData.getContent());
                                mAdapter.getData().set(position, itemData);
                                mAdapter.notifyItemChanged(position);
                            }
                        });
                        editDialog.show();
                        break;
                    case "生日":
                        Calendar calendar = Calendar.getInstance();
                        int currentYear = calendar.get(Calendar.YEAR);
                        int currentMonth = calendar.get(Calendar.MONTH) + 1;
                        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                        DateEntity startValue = DateEntity.target(currentYear - 60, currentMonth, currentDay);
                        DateEntity endValue = DateEntity.target(currentYear - 15, currentMonth, currentDay);
                        DateEntity defaultEntity = DateEntity.target(endValue.getYear() - 10, currentMonth, currentDay);
                        DatePicker picker = DialogUtils.onYearMonthDay(_mActivity, title, startValue, endValue, defaultEntity);
                        picker.setOnDatePickedListener(new OnDatePickedListener() {
                            @Override
                            public void onDatePicked(int year, int month, int day) {
                                String text = year + "-" + month + "-" + day;
                                itemData.setContent(DateUtil.ymd.format(DateUtil.setDate(text)));

                                MobInfo("birthday", itemData.getContent());
                                mAdapter.getData().set(position, itemData);
                                mAdapter.notifyItemChanged(position);
                            }
                        });
                        picker.show();
                        break;
                    case "民族":
                        EthnicPicker ethnicPicker = new EthnicPicker(_mActivity);
                        ethnicPicker.setBodyWidth(200);
                        ethnicPicker.setEthnicSpec(EthnicSpec.SEVENTH_NATIONAL_CENSUS);

                        ethnicPicker.setDefaultValueByName(itemData.getContent());
                        ethnicPicker.getTitleView().setText(itemData.getContent());
                        ethnicPicker.setOnOptionPickedListener(new OnOptionPickedListener() {
                            @Override
                            public void onOptionPicked(int pos, Object item) {
                                EthnicEntity entity = (EthnicEntity) item;
                                itemData.setContent(entity.getName());
                                MobInfo("nation", itemData.getContent());
                                mAdapter.getData().set(position, itemData);
                                mAdapter.notifyItemChanged(position);
                            }
                        });
                        ethnicPicker.getWheelLayout().setOnOptionSelectedListener(new OnOptionSelectedListener() {
                            @Override
                            public void onOptionSelected(int position, Object item) {
                                ethnicPicker.getTitleView().setText(ethnicPicker.getWheelView().formatItem(position));
                            }
                        });
                        ethnicPicker.show();
                        break;
                    case "年龄":
                        NumberPicker numberPicker = new NumberPicker(_mActivity);
                        numberPicker.setOnNumberPickedListener(new OnNumberPickedListener() {
                            @Override
                            public void onNumberPicked(int pos, Number item) {
                                itemData.setContent(item.toString());
                                MobInfo("emp_age", itemData.getContent());
                                mAdapter.getData().set(position, itemData);
                                mAdapter.notifyItemChanged(position);
                            }
                        });
                        numberPicker.setFormatter(new WheelFormatter() {
                            @Override
                            public String formatItem(@NonNull Object item) {
                                return item.toString() + "岁";
                            }
                        });
                        numberPicker.setRange(18, 65, 1);
                        numberPicker.setDefaultValue(18);
                        numberPicker.setTitle(title);
                        numberPicker.show();
                        break;
                    case "学历":
                        List<HomeBean> data = new ArrayList<>();
                        data.add(new HomeBean(1, "小学"));
                        data.add(new HomeBean(2, "初中"));
                        data.add(new HomeBean(3, "高中"));
                        data.add(new HomeBean(4, "中专"));
                        data.add(new HomeBean(5, "大专"));
                        data.add(new HomeBean(6, "本科"));
                        data.add(new HomeBean(7, "硕士"));
                        data.add(new HomeBean(6, "博士"));
                        OptionPicker optionPicker = new OptionPicker(_mActivity);
                        optionPicker.setTitle(title);
                        optionPicker.setBodyWidth(200);
                        optionPicker.setData(data);
                        optionPicker.setDefaultPosition(2);
                        optionPicker.setOnOptionPickedListener(new OnOptionPickedListener() {
                            @Override
                            public void onOptionPicked(int pos, Object item) {
                                HomeBean homeBean = (HomeBean) item;
                                itemData.setContent(homeBean.getType());
                                MobInfo("education", itemData.getContent());
                                mAdapter.getData().set(position, itemData);
                                mAdapter.notifyItemChanged(position);
                            }
                        });
                        optionPicker.show();
                        break;
                }
            }
        });
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.PICTURE:
                bundle = (Bundle) event.getData();
                ArrayList<LocalMedia> pictures = (ArrayList<LocalMedia>) bundle.getSerializable(FinalClass.bean);

                JsonArray list = new JsonArray();
                JsonObject main = new JsonObject();
                JsonObject main2 = new JsonObject();
                main2.addProperty("Portrait", Base64Util.BitMapIconToString(pictures.get(0).getRealPath()));
                list.add(main2);
                main.add("个人信息", list);


                LatteLogger.d("tefwfewfgetData", GsonBuildUtil.GsonBuilder(mAdapter.getData()));
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getData().get(i).getItemType() == 1) {//头像
                        mAdapter.getData().get(i).setPortrait(pictures.get(0).getRealPath());
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void MobInfo(String tag, String content) {
        EasyHttp.post(Constants.PERSON_INFO + apiToken + "&strSQL=UPDATE SysUser SET " + tag + "='" + content + "' WHERE ApiToken='" + apiToken + "'")
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
                        ToastUtil.getInstance().showToast(e.getMessage().toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testMobInfoMobInfo", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                ToastUtil.getInstance().showToast(o.getString(FinalClass.Message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initData() {
        EasyHttp.post(Constants.PERSON_INFO + apiToken + Constants.SQ + apiToken + "'")
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
                        LatteLogger.d("initDatainitData", s);
                        try {
                            JSONObject o = new JSONObject(s);
                            if (o.getString(FinalClass.Message).contains(FinalClass.ApiFail)) {
                                bundle.putString(FinalClass.title, tvTitle.getText().toString());
                                startWithPop(LoginFragment.newInstance(bundle));
                            }

                            if (o.getInt(FinalClass.Tag) == 1) { //返回成功
                                UseInfoBean infoBean = GsonBuildUtil.create().fromJson(s, UseInfoBean.class);
                                LatteLogger.d("infoBean", GsonBuildUtil.GsonBuilder(infoBean));
                                mAdapter.setList(getData(infoBean.getData()));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private Collection<? extends UseInfoBean.DataBean> getData(List<UseInfoBean.DataBean> list) {
        UseInfoBean.DataBean infoBean = list.get(0);

        UseInfoBean.DataBean dataBean = new UseInfoBean.DataBean();
        dataBean.setItemType(HomeBean.TYPE_1);
        dataBean.setPortrait(infoBean.getPortrait());
        menuBeanList.add(dataBean);

        menuBeanList.add(new UseInfoBean.DataBean(HomeBean.TYPE_3));

        for (int i = 0; i < strSettings.length; i++) {
            UseInfoBean.DataBean dataBean1 = new UseInfoBean.DataBean(HomeBean.TYPE_2);
            if (strSettings[i].equals("性别")) {
                dataBean1 = new UseInfoBean.DataBean(HomeBean.TYPE_4);
                dataBean1.setContent(String.valueOf(infoBean.getGender())); //1是男2是女
            } else if (strSettings[i].equals("姓名")) {
                dataBean1.setContent(infoBean.getRealname());
            } else if (strSettings[i].equals("民族")) {
                dataBean1.setContent(infoBean.getNation());
            } else if (strSettings[i].equals("生日")) {
                dataBean1.setContent(DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(infoBean.getBirthday()))));
            } else if (strSettings[i].equals("年龄")) {
                dataBean1.setContent(String.valueOf(infoBean.getEmp_age()));
            } else if (strSettings[i].equals("学校")) {
                dataBean1.setContent(String.valueOf(infoBean.getSchool()));
            } else if (strSettings[i].equals("学历")) {
                dataBean1.setContent(String.valueOf(infoBean.getEducation()));
            }
            dataBean1.setTitle(strSettings[i]);

            menuBeanList.add(dataBean1);
        }

        menuBeanList.add(new UseInfoBean.DataBean(HomeBean.TYPE_3));

        for (int i = 0; i < strSettings2.length; i++) {
            UseInfoBean.DataBean dataBean1 = new UseInfoBean.DataBean(HomeBean.TYPE_2);
            if (strSettings2[i].equals("部/组")) {
                dataBean1.setContent(infoBean.getRemark());
            } else if (strSettings2[i].equals("工号")) {
                dataBean1.setContent(String.valueOf(infoBean.getEmpno()));
            } else if (strSettings2[i].equals("职位")) {
                dataBean1.setContent(infoBean.getStationname());
            }
            dataBean1.setTitle(strSettings2[i]);
            menuBeanList.add(dataBean1);
        }

        menuBeanList.add(new UseInfoBean.DataBean(HomeBean.TYPE_3));

        for (int i = 0; i < strSettings3.length; i++) {
            UseInfoBean.DataBean dataBean1 = new UseInfoBean.DataBean(HomeBean.TYPE_2);
            dataBean1.setTitle(strSettings3[i]);
            if (strSettings3[i].equals("现居住处")) {
                dataBean1.setContent(infoBean.getPresentaddr());
            } else if (strSettings3[i].equals("QQ")) {
                dataBean1.setContent(infoBean.getQq());
            } else if (strSettings3[i].equals("微信")) {
                dataBean1.setContent(infoBean.getWechat());
            } else if (strSettings3[i].equals("电话")) {
                dataBean1.setContent(infoBean.getMobile());
            }
            menuBeanList.add(dataBean1);
        }

        return menuBeanList;
    }


    public static class ChildAdapter extends BaseMultiItemQuickAdapter<UseInfoBean.DataBean, BaseViewHolder> {

        public ChildAdapter() {
            addItemType(HomeBean.TYPE_1, R.layout.item_head);
            addItemType(HomeBean.TYPE_2, R.layout.item_icon_title_director);
            addItemType(HomeBean.TYPE_3, R.layout.item_space);
            addItemType(HomeBean.TYPE_4, R.layout.item_icon_title_sex_radio);
            addChildClickViewIds(R.id.ll_Portrait);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, UseInfoBean.DataBean bean) {
            switch (holder.getItemViewType()) {
                case HomeBean.TYPE_1:
                    Glide.with(getContext()).load(bean.getPortrait()).placeholder(R.mipmap.image_avatar_1)
                            .into((CircleImageView) holder.getView(R.id.circleImage));
                    break;
                case HomeBean.TYPE_2:

                    holder.setText(R.id.tv_title, bean.getTitle()).setText(R.id.tv_content, bean.getContent()).setGone(R.id.iv_icon, true);
                    ImageView ivType = holder.getView(R.id.iv_type);
                    if ("姓名".equals(bean.getTitle())
                            || "学校".equals(bean.getTitle())
                            || "工号".equals(bean.getTitle())
                            || "职位".equals(bean.getTitle())
                            || "现居住处".equals(bean.getTitle())
                            || "QQ".equals(bean.getTitle())
                            || "微信".equals(bean.getTitle())
                            || "电话".equals(bean.getTitle())) {
                        ivType.setImageResource(R.drawable.ic_baseline_edit_24);
                    } else {
                        ivType.setImageResource(R.drawable.ic_right_grey_24);
                    }
                    break;
                case HomeBean.TYPE_4:
                    holder.setText(R.id.tv_title, bean.getTitle()).setGone(R.id.iv_icon, true);
                    RadioGroup rg = holder.getView(R.id.rg);
                    if ("1".equals(bean.getContent())) {
                        rg.check(R.id.rb_man);
                    } else {
                        rg.check(R.id.rb_woman);
                    }
                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rb_man:
                                    bean.setGender(1);
                                    break;
                                case R.id.rb_woman:
                                    bean.setGender(2);
                                    break;
                            }
                        }
                    });
                    break;
            }
        }

    }
}












































