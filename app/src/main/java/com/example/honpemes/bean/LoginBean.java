package com.example.honpemes.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * FileName: LoginBean
 * Author: asus
 * Date: 2021/12/9 14:23
 * Description:
 */
public class LoginBean implements Serializable {

    @SerializedName("Total")
    private Integer total;
    @SerializedName("Data")
    private DataBean data;
    @SerializedName("Tag")
    private Integer tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private Object description;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public static class DataBean {
        @SerializedName("UserId")
        private Long userId;
        @SerializedName("UserStatus")
        private Integer userStatus;
        @SerializedName("IsOnline")
        private Integer isOnline;
        @SerializedName("UserName")
        private String userName;
        @SerializedName("RealName")
        private String realName;
        @SerializedName("WebToken")
        private String webToken;
        @SerializedName("ApiToken")
        private String apiToken;
        @SerializedName("IsSystem")
        private Integer isSystem;
        @SerializedName("Portrait")
        private String portrait;
        @SerializedName("DepartmentId")
        private Long departmentId;
        private Integer empNo;
        @SerializedName("DepartmentName")
        private String departmentName;
        private String stationName;
        private Integer workyear;
        @SerializedName("PositionIds")
        private Object positionIds;
        @SerializedName("RoleIds")
        private String roleIds;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Integer getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(Integer userStatus) {
            this.userStatus = userStatus;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getWebToken() {
            return webToken;
        }

        public void setWebToken(String webToken) {
            this.webToken = webToken;
        }

        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }

        public Integer getIsSystem() {
            return isSystem;
        }

        public void setIsSystem(Integer isSystem) {
            this.isSystem = isSystem;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
        }

        public Integer getEmpNo() {
            return empNo;
        }

        public void setEmpNo(Integer empNo) {
            this.empNo = empNo;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public Integer getWorkyear() {
            return workyear;
        }

        public void setWorkyear(Integer workyear) {
            this.workyear = workyear;
        }

        public Object getPositionIds() {
            return positionIds;
        }

        public void setPositionIds(Object positionIds) {
            this.positionIds = positionIds;
        }

        public String getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(String roleIds) {
            this.roleIds = roleIds;
        }
    }
}
