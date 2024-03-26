package com.example.honpemes.fragment.b.setting.mob.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.utils.StringUtil;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: asus
 * @date: 2022/11/5
 * @Description:
 */
public class UseInfoBean {

    @SerializedName("Total")
    private int total;
    @SerializedName("Data")
    private List<DataBean> data;
    @SerializedName("Tag")
    private int tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private Object description;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
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

    public static class DataBean implements MultiItemEntity {
        private Long id;
        private int baseisdelete;
        private String basecreatetime;
        private String basemodifytime;
        private Long basemodifierid;
        private int baseversion;
        private String username;
        private String password;
        private String salt;
        private String realname;
        private Long departmentid;
        private int gender;
        private String birthday;
        private String portrait;
        private String email;
        private String mobile;
        private String qq;
        private String wechat;
        private int logincount;
        private int userstatus;
        private int issystem;
        private int isonline;
        private String firstvisit;
        private String previousvisit;
        private String lastvisit;
        private String remark;
        private String webtoken;
        private String apitoken;
        private String companyid;
        private String stationname;
        private String idcardnum;
        private String employdate;
        private String school;
        private String idcardaddr;
        private String presentaddr;
        private String nation;
        private int empno;
        private String education;
        private int emp_age;
        private int workyear;
        private int itemType;
        private String title;
        private String content;

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

        public DataBean() {
        }

        public DataBean(int itemType) {
            this.itemType = itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getBaseisdelete() {
            return baseisdelete;
        }

        public void setBaseisdelete(int baseisdelete) {
            this.baseisdelete = baseisdelete;
        }

        public String getBasecreatetime() {
            return basecreatetime;
        }

        public void setBasecreatetime(String basecreatetime) {
            this.basecreatetime = basecreatetime;
        }

        public String getBasemodifytime() {
            return basemodifytime;
        }

        public void setBasemodifytime(String basemodifytime) {
            this.basemodifytime = basemodifytime;
        }

        public Long getBasemodifierid() {
            return basemodifierid;
        }

        public void setBasemodifierid(Long basemodifierid) {
            this.basemodifierid = basemodifierid;
        }

        public int getBaseversion() {
            return baseversion;
        }

        public void setBaseversion(int baseversion) {
            this.baseversion = baseversion;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Long getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(Long departmentid) {
            this.departmentid = departmentid;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public int getLogincount() {
            return logincount;
        }

        public void setLogincount(int logincount) {
            this.logincount = logincount;
        }

        public int getUserstatus() {
            return userstatus;
        }

        public void setUserstatus(int userstatus) {
            this.userstatus = userstatus;
        }

        public int getIssystem() {
            return issystem;
        }

        public void setIssystem(int issystem) {
            this.issystem = issystem;
        }

        public int getIsonline() {
            return isonline;
        }

        public void setIsonline(int isonline) {
            this.isonline = isonline;
        }

        public String getFirstvisit() {
            return firstvisit;
        }

        public void setFirstvisit(String firstvisit) {
            this.firstvisit = firstvisit;
        }

        public String getPreviousvisit() {
            return previousvisit;
        }

        public void setPreviousvisit(String previousvisit) {
            this.previousvisit = previousvisit;
        }

        public String getLastvisit() {
            return lastvisit;
        }

        public void setLastvisit(String lastvisit) {
            this.lastvisit = lastvisit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWebtoken() {
            return webtoken;
        }

        public void setWebtoken(String webtoken) {
            this.webtoken = webtoken;
        }

        public String getApitoken() {
            return apitoken;
        }

        public void setApitoken(String apitoken) {
            this.apitoken = apitoken;
        }

        public String getCompanyid() {
            return companyid;
        }

        public void setCompanyid(String companyid) {
            this.companyid = companyid;
        }

        public String getStationname() {
            return stationname;
        }

        public void setStationname(String stationname) {
            this.stationname = stationname;
        }

        public String getIdcardnum() {
            return idcardnum;
        }

        public void setIdcardnum(String idcardnum) {
            this.idcardnum = idcardnum;
        }

        public String getEmploydate() {
            return employdate;
        }

        public void setEmploydate(String employdate) {
            this.employdate = employdate;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getIdcardaddr() {
            return idcardaddr;
        }

        public void setIdcardaddr(String idcardaddr) {
            this.idcardaddr = idcardaddr;
        }

        public String getPresentaddr() {
            return presentaddr;
        }

        public void setPresentaddr(String presentaddr) {
            this.presentaddr = presentaddr;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public int getEmpno() {
            return empno;
        }

        public void setEmpno(int empno) {
            this.empno = empno;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public int getEmp_age() {
            return emp_age;
        }

        public void setEmp_age(int emp_age) {
            this.emp_age = emp_age;
        }

        public int getWorkyear() {
            return workyear;
        }

        public void setWorkyear(int workyear) {
            this.workyear = workyear;
        }


        public String getSection() {
            if (!"".equals(realname) && !TextUtils.isEmpty(realname)) {
                String pinyin = StringUtil.getPingYin(realname);
                String c = pinyin.substring(0, 1);
                Pattern p = Pattern.compile("[a-zA-Z]");
                Matcher m = p.matcher(c);
                if (m.matches()) {
                    return c.toUpperCase();
                }
                return pinyin;
            }
            return "#";
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
