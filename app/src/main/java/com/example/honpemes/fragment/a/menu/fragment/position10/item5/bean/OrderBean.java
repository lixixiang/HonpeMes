package com.example.honpemes.fragment.a.menu.fragment.position10.item5.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean.OrderEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/9/30
 * @Description: 生产实体
 */
public class OrderBean implements Serializable, MultiItemEntity {
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

    private String title;
    private String content;//内容
    private String orderId;//外部物料单号
    private String order;
    private String orderDate;
    private String unit; //单位
    private String customerName;
    private String customerId;
    private String orderTeam="";
    private String madeTeam="";
    private String level;
    private boolean isExpress;//是否快递
    private boolean isClearance;//是否报关
    private boolean isFee;//是否收费
    private String deliveryDate;//交货日期
    private String ProductName;//产品名称
    private String status="";//订单状态

    private String enterNum;//入库单号
    private String enterStatus;//入库状态
    private String enterAddress;//入库地址
    private String AllNum;//总件数
    private String num;
    private String AllWeight;//总净重
    private String complain;//公司

    private boolean isCheck; //多选
    private boolean isMoreMenu;//右侧订单
    private boolean isOrderCheck;//左边的checkBox

    private String orderDetail;//物料描述
    private String orderPackage;//包装代码
    private String orderUnit;//包装单位
    private String netWeight;//毛重
    private String _id;//行号
    private String describe;

    private OrderIdDetailBean idDetailBeans;
    private List<OrderBean> orderBeanList;
    private List<OrderEntity.DataBean> orderEntityList;

    public List<OrderEntity.DataBean> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity.DataBean> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public OrderIdDetailBean getIdDetailBeans() {
        return idDetailBeans;
    }

    public void setIdDetailBeans(OrderIdDetailBean idDetailBeans) {
        this.idDetailBeans = idDetailBeans;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
    }

    public String getOrderPackage() {
        return orderPackage;
    }

    public void setOrderPackage(String orderPackage) {
        this.orderPackage = orderPackage;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getEnterNum() {
        return enterNum;
    }

    public void setEnterNum(String enterNum) {
        this.enterNum = enterNum;
    }

    public String getEnterStatus() {
        return enterStatus;
    }

    public void setEnterStatus(String enterStatus) {
        this.enterStatus = enterStatus;
    }

    public String getEnterAddress() {
        return enterAddress;
    }

    public void setEnterAddress(String enterAddress) {
        this.enterAddress = enterAddress;
    }

    public String getAllNum() {
        return AllNum;
    }

    public void setAllNum(String allNum) {
        AllNum = allNum;
    }

    public String getAllWeight() {
        return AllWeight;
    }

    public void setAllWeight(String allWeight) {
        AllWeight = allWeight;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public boolean isOrderCheck() {
        return isOrderCheck;
    }

    public void setOrderCheck(boolean orderCheck) {
        isOrderCheck = orderCheck;
    }

    public boolean isMoreMenu() {
        return isMoreMenu;
    }

    public void setMoreMenu(boolean moreMenu) {
        isMoreMenu = moreMenu;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderTeam() {
        return orderTeam;
    }

    public void setOrderTeam(String orderTeam) {
        this.orderTeam = orderTeam;
    }

    public String getMadeTeam() {
        return madeTeam;
    }

    public void setMadeTeam(String madeTeam) {
        this.madeTeam = madeTeam;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isExpress() {
        return isExpress;
    }

    public void setExpress(boolean express) {
        isExpress = express;
    }

    public boolean isClearance() {
        return isClearance;
    }

    public void setClearance(boolean clearance) {
        isClearance = clearance;
    }

    public boolean isFee() {
        return isFee;
    }

    public void setFee(boolean fee) {
        isFee = fee;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "title='" + title + '\'' +
                ", orderId='" + orderId + '\'' +
                ", order='" + order + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderTeam='" + orderTeam + '\'' +
                ", madeTeam='" + madeTeam + '\'' +
                ", level='" + level + '\'' +
                ", isExpress=" + isExpress +
                ", isClearance=" + isClearance +
                ", isFee=" + isFee +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", status='" + status + '\'' +
                ", isCheck=" + isCheck +
                ", orderBeanList=" + orderBeanList +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }


    public static class OrderIdDetailBean implements Serializable {
        private String packageCode="--";
        private String materialCode="--";
        private String packageUnit="--";
        private String weight="--";
        private String roughWeight="--";
        private String describe="--";

        public String getPackageCode() {
            return packageCode;
        }

        public void setPackageCode(String packageCode) {
            this.packageCode = packageCode;
        }

        public String getMaterialCode() {
            return materialCode;
        }

        public void setMaterialCode(String materialCode) {
            this.materialCode = materialCode;
        }

        public String getPackageUnit() {
            return packageUnit;
        }

        public void setPackageUnit(String packageUnit) {
            this.packageUnit = packageUnit;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getRoughWeight() {
            return roughWeight;
        }

        public void setRoughWeight(String roughWeight) {
            this.roughWeight = roughWeight;
        }
        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }
}
































