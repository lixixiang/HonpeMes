package com.example.honpemes.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.datepickview.wheelview.contract.TextProvider;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/9/2
 * @Description:
 */
public class HomeBean implements Serializable, MultiItemEntity, TextProvider {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;
    public static final int TYPE_5 = 5;
    public static final int TYPE_6 = 6;
    public static final int TYPE_7 = 7;
    public static final int TYPE_8 = 8;
    public static final int TYPE_9 = 9;
    private int spanSize;
    private int itemType;
    private String deviceName;
    private String type;
    private int status;
    private int runNum;
    private int nullNum;
    private int warningNum;
    private int stopWorkNum;
    private int stopLineNum;
    private String code;
    private String startTime;
    private String endTime;
    private List<DeviceListBean> deviceListBeanList;
    private int today_production;//今日产量
    private int yesterday_production;//昨日产量

    private double fpy;//合格率
    private double fpy2;
    private float run_time_rate;//运行时长率

    private String title;
    private String content;
    private String task;
    private String time;

    private String reason;
    private String hint;
    private boolean isClick;
    private String maintainTime;

    public HomeBean() {
    }

    public HomeBean(int status, String type) {
        this.type = type;
        this.status = status;
    }

    private List<MenuBean> menuBeanList;
    private LoginBean mLogin;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginBean getLogin() {
        return mLogin;
    }

    public void setLogin(LoginBean login) {
        this.mLogin = login;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<MenuBean> getMenuBeanList() {
        return menuBeanList;
    }

    public void setMenuBeanList(List<MenuBean> menuBeanList) {
        this.menuBeanList = menuBeanList;
    }


    public double getFpy2() {
        return fpy2;
    }

    public void setFpy2(double fpy2) {
        this.fpy2 = fpy2;
    }


    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<DeviceListBean> getDeviceListBeanList() {
        return deviceListBeanList;
    }


    public void setDeviceListBeanList(List<DeviceListBean> deviceListBeanList) {
        this.deviceListBeanList = deviceListBeanList;
    }

    public int getToday_production() {
        return today_production;
    }

    public void setToday_production(int today_production) {
        this.today_production = today_production;
    }

    public int getYesterday_production() {
        return yesterday_production;
    }

    public void setYesterday_production(int yesterday_production) {
        this.yesterday_production = yesterday_production;
    }

    public double getFpy() {
        return fpy;
    }

    public void setFpy(double fpy) {
        this.fpy = fpy;
    }

    public float getRun_time_rate() {
        return run_time_rate;
    }

    public void setRun_time_rate(float run_time_rate) {
        this.run_time_rate = run_time_rate;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getNullNum() {
        return nullNum;
    }

    public void setNullNum(int nullNum) {
        this.nullNum = nullNum;
    }

    public int getWarningNum() {
        return warningNum;
    }

    public void setWarningNum(int warningNum) {
        this.warningNum = warningNum;
    }

    public int getStopWorkNum() {
        return stopWorkNum;
    }

    public void setStopWorkNum(int stopWorkNum) {
        this.stopWorkNum = stopWorkNum;
    }

    public int getStopLineNum() {
        return stopLineNum;
    }

    public void setStopLineNum(int stopLineNum) {
        this.stopLineNum = stopLineNum;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String provideText() {
        return type;
    }


    public static class DeviceListBean extends BaseNode implements Serializable {
        private String deviceName; //挤出机
        private String reason;//温控故障
        private String applyRename;//申请人
        private int status;//处理状态
        private String title;
        private String content;//辅机机头温控系系统故障
        private String today;//今天 15:32:34

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getApplyRename() {
            return applyRename;
        }

        public void setApplyRename(String applyRename) {
            this.applyRename = applyRename;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }


    @Override
    public int getItemType() {
        return itemType;
    }


}
