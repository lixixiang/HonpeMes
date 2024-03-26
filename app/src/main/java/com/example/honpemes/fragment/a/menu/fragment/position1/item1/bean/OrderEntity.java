package com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/11/17
 * @Description:
 */
public class OrderEntity implements Serializable {

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

    public static class DataBean  implements Serializable, MultiItemEntity{
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
        private boolean ShengPi;

        private int id;
        private String 生产单号;
        private String 订单组别;
        private String 制作组别;
        private String 制作级别;
        private String 客户代码;
        private String 客户名称;
        private String 客户部门;
        private String 客户订单号;
        private String 交货日期;
        private String 客户等级;
        private String 业务人员;
        private String 付款方式;
        private String 交货方式;
        private String 结算币别;
        private String 产品名称;
        private double 订金金额;
        private double 订单金额;
        private String 订单备注;
        private String 下单人员;
        private String 下单日期;
        private String 提交人员;
        private String 提交日期;
        private String 核准人员;
        private String 核准日期;
        private String 订单状态;
        private String 零件编程;
        private String 品质检查人员;
        private String 品质检查时间;
        private String 品质检查意见;
        private String 项目审核人员;
        private String 项目审核时间;
        private String 项目审核意见;
        private String 营业qc人员;
        private String 营业qc时间;
        private String 营业qc意见;
        private String 零件编码;
        private String 制单;
        private String 是否报关;
        private String 是否快递;
        private String 是否收费;
        private String 订单策划;
        private String 订单类型;
        private String 加工方式;
        private String 父订单号;
        private int baseisdelete;
        private String basecreatetime;
        private Object basemodifytime;
        private long basecreatorid;
        private Object basemodifierid;
        private int baseversion;
        private List<String> content;
        private boolean Expanded;


        private String 主件编号;
        private String 零件编号;
        private String 零件名称;
        private String 零件材质;
        private String 零件尺寸;
        private String 制作方式;
        private String 登记人;
        private String 编程人员;
        private String 编程时间;
        private boolean 程式单;
        private boolean nc程式单;
        private int 订单数量;
        private int 制作数量;
        private int 完成数量;
        private int 待加工数量;
        private String 备注;
        private String 主件编码;
        private String 项目预审说明;
        private String 工艺要求;
        private String 存放位置;
        private String 文件类型;
        private int 手工完成数量;
        private String 编程人员代码;
        private boolean 五金加工;
        private boolean 零件取消;
        private String 委外工艺;
        private boolean 制程设定;

        private String 清单编码;
        private int 零件序号;

        private String 零件单位;
        private int 零件数量;
        private double 零件单价;
        private double 零件金额;

        private int 零件重量;
        private Object filename;

        private String 文件编码;
        private String 文件名称;
        private String 上传日期;
        private String 上传人员;
        private String 最后变更人;
        private String 最后变更日期;
        private int 变更次数;
        private String 文件分类;

        private String 预审确认;
        private String 预审核准;
        private String 取消核准;

        private String 项目核准人;
        private String 项目核准日期;
        private String 项目预审日期;
        private String 项目预审人员;
        private String 项目资料评审;
        private String 工艺图文件位置;
        private String 工艺图文件类型;
        private boolean 工期调整;
        private String 按钮状态;

        public boolean isShengPi() {
            return ShengPi;
        }

        public void setShengPi(boolean shengPi) {
            ShengPi = shengPi;
        }

        public String get按钮状态() {
            return 按钮状态;
        }

        public void set按钮状态(String 按钮状态) {
            this.按钮状态 = 按钮状态;
        }

        public boolean is工期调整() {
            return 工期调整;
        }

        public void set工期调整(boolean 工期调整) {
            this.工期调整 = 工期调整;
        }

        public String get项目核准人() {
            return 项目核准人;
        }

        public void set项目核准人(String 项目核准人) {
            this.项目核准人 = 项目核准人;
        }

        public String get项目核准日期() {
            return 项目核准日期;
        }

        public void set项目核准日期(String 项目核准日期) {
            this.项目核准日期 = 项目核准日期;
        }

        public String get项目预审日期() {
            return 项目预审日期;
        }

        public void set项目预审日期(String 项目预审日期) {
            this.项目预审日期 = 项目预审日期;
        }

        public String get项目预审人员() {
            return 项目预审人员;
        }

        public void set项目预审人员(String 项目预审人员) {
            this.项目预审人员 = 项目预审人员;
        }

        public String get项目资料评审() {
            return 项目资料评审;
        }

        public void set项目资料评审(String 项目资料评审) {
            this.项目资料评审 = 项目资料评审;
        }

        public String get工艺图文件位置() {
            return 工艺图文件位置;
        }

        public void set工艺图文件位置(String 工艺图文件位置) {
            this.工艺图文件位置 = 工艺图文件位置;
        }

        public String get工艺图文件类型() {
            return 工艺图文件类型;
        }

        public void set工艺图文件类型(String 工艺图文件类型) {
            this.工艺图文件类型 = 工艺图文件类型;
        }



        public String get预审确认() {
            return 预审确认;
        }

        public void set预审确认(String 预审确认) {
            this.预审确认 = 预审确认;
        }

        public String get预审核准() {
            return 预审核准;
        }

        public void set预审核准(String 预审核准) {
            this.预审核准 = 预审核准;
        }

        public String get取消核准() {
            return 取消核准;
        }

        public void set取消核准(String 取消核准) {
            this.取消核准 = 取消核准;
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

        public String get文件编码() {
            return 文件编码;
        }

        public void set文件编码(String 文件编码) {
            this.文件编码 = 文件编码;
        }

        public String get文件名称() {
            return 文件名称;
        }

        public void set文件名称(String 文件名称) {
            this.文件名称 = 文件名称;
        }

        public String get上传日期() {
            return 上传日期;
        }

        public void set上传日期(String 上传日期) {
            this.上传日期 = 上传日期;
        }

        public String get上传人员() {
            return 上传人员;
        }

        public void set上传人员(String 上传人员) {
            this.上传人员 = 上传人员;
        }

        public String get最后变更人() {
            return 最后变更人;
        }

        public void set最后变更人(String 最后变更人) {
            this.最后变更人 = 最后变更人;
        }

        public String get最后变更日期() {
            return 最后变更日期;
        }

        public void set最后变更日期(String 最后变更日期) {
            this.最后变更日期 = 最后变更日期;
        }

        public int get变更次数() {
            return 变更次数;
        }

        public void set变更次数(int 变更次数) {
            this.变更次数 = 变更次数;
        }

        public String get文件分类() {
            return 文件分类;
        }

        public void set文件分类(String 文件分类) {
            this.文件分类 = 文件分类;
        }

        public String get清单编码() {
            return 清单编码;
        }

        public void set清单编码(String 清单编码) {
            this.清单编码 = 清单编码;
        }

        public int get零件序号() {
            return 零件序号;
        }

        public void set零件序号(int 零件序号) {
            this.零件序号 = 零件序号;
        }

        public String get零件单位() {
            return 零件单位;
        }

        public void set零件单位(String 零件单位) {
            this.零件单位 = 零件单位;
        }

        public int get零件数量() {
            return 零件数量;
        }

        public void set零件数量(int 零件数量) {
            this.零件数量 = 零件数量;
        }

        public double get零件单价() {
            return 零件单价;
        }

        public void set零件单价(double 零件单价) {
            this.零件单价 = 零件单价;
        }

        public double get零件金额() {
            return 零件金额;
        }

        public void set零件金额(double 零件金额) {
            this.零件金额 = 零件金额;
        }

        public int get零件重量() {
            return 零件重量;
        }

        public void set零件重量(int 零件重量) {
            this.零件重量 = 零件重量;
        }

        public Object getFilename() {
            return filename;
        }

        public void setFilename(Object filename) {
            this.filename = filename;
        }

        public String get主件编号() {
            return 主件编号;
        }

        public void set主件编号(String 主件编号) {
            this.主件编号 = 主件编号;
        }

        public String get零件编号() {
            return 零件编号;
        }

        public void set零件编号(String 零件编号) {
            this.零件编号 = 零件编号;
        }

        public String get零件名称() {
            return 零件名称;
        }

        public void set零件名称(String 零件名称) {
            this.零件名称 = 零件名称;
        }

        public String get零件材质() {
            return 零件材质;
        }

        public void set零件材质(String 零件材质) {
            this.零件材质 = 零件材质;
        }

        public String get零件尺寸() {
            return 零件尺寸;
        }

        public void set零件尺寸(String 零件尺寸) {
            this.零件尺寸 = 零件尺寸;
        }

        public String get制作方式() {
            return 制作方式;
        }

        public void set制作方式(String 制作方式) {
            this.制作方式 = 制作方式;
        }

        public String get登记人() {
            return 登记人;
        }

        public void set登记人(String 登记人) {
            this.登记人 = 登记人;
        }

        public String get编程人员() {
            return 编程人员;
        }

        public void set编程人员(String 编程人员) {
            this.编程人员 = 编程人员;
        }

        public String get编程时间() {
            return 编程时间;
        }

        public void set编程时间(String 编程时间) {
            this.编程时间 = 编程时间;
        }

        public boolean is程式单() {
            return 程式单;
        }

        public void set程式单(boolean 程式单) {
            this.程式单 = 程式单;
        }

        public boolean isNc程式单() {
            return nc程式单;
        }

        public void setNc程式单(boolean nc程式单) {
            this.nc程式单 = nc程式单;
        }

        public int get订单数量() {
            return 订单数量;
        }

        public void set订单数量(int 订单数量) {
            this.订单数量 = 订单数量;
        }

        public int get制作数量() {
            return 制作数量;
        }

        public void set制作数量(int 制作数量) {
            this.制作数量 = 制作数量;
        }

        public int get完成数量() {
            return 完成数量;
        }

        public void set完成数量(int 完成数量) {
            this.完成数量 = 完成数量;
        }

        public int get待加工数量() {
            return 待加工数量;
        }

        public void set待加工数量(int 待加工数量) {
            this.待加工数量 = 待加工数量;
        }

        public String get备注() {
            return 备注;
        }

        public void set备注(String 备注) {
            this.备注 = 备注;
        }

        public String get主件编码() {
            return 主件编码;
        }

        public void set主件编码(String 主件编码) {
            this.主件编码 = 主件编码;
        }

        public String get项目预审说明() {
            return 项目预审说明;
        }

        public void set项目预审说明(String 项目预审说明) {
            this.项目预审说明 = 项目预审说明;
        }

        public String get工艺要求() {
            return 工艺要求;
        }

        public void set工艺要求(String 工艺要求) {
            this.工艺要求 = 工艺要求;
        }

        public String get存放位置() {
            return 存放位置;
        }

        public void set存放位置(String 存放位置) {
            this.存放位置 = 存放位置;
        }

        public String get文件类型() {
            return 文件类型;
        }

        public void set文件类型(String 文件类型) {
            this.文件类型 = 文件类型;
        }

        public int get手工完成数量() {
            return 手工完成数量;
        }

        public void set手工完成数量(int 手工完成数量) {
            this.手工完成数量 = 手工完成数量;
        }

        public String get编程人员代码() {
            return 编程人员代码;
        }

        public void set编程人员代码(String 编程人员代码) {
            this.编程人员代码 = 编程人员代码;
        }

        public boolean is五金加工() {
            return 五金加工;
        }

        public void set五金加工(boolean 五金加工) {
            this.五金加工 = 五金加工;
        }

        public boolean is零件取消() {
            return 零件取消;
        }

        public void set零件取消(boolean 零件取消) {
            this.零件取消 = 零件取消;
        }

        public String get委外工艺() {
            return 委外工艺;
        }

        public void set委外工艺(String 委外工艺) {
            this.委外工艺 = 委外工艺;
        }

        public boolean is制程设定() {
            return 制程设定;
        }

        public void set制程设定(boolean 制程设定) {
            this.制程设定 = 制程设定;
        }

        public boolean isExpanded() {
            return Expanded;
        }

        public void setExpanded(boolean expanded) {
            Expanded = expanded;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String get生产单号() {
            return 生产单号;
        }

        public void set生产单号(String 生产单号) {
            this.生产单号 = 生产单号;
        }

        public String get订单组别() {
            return 订单组别;
        }

        public void set订单组别(String 订单组别) {
            this.订单组别 = 订单组别;
        }

        public String get制作组别() {
            return 制作组别;
        }

        public void set制作组别(String 制作组别) {
            this.制作组别 = 制作组别;
        }

        public String get制作级别() {
            return 制作级别;
        }

        public void set制作级别(String 制作级别) {
            this.制作级别 = 制作级别;
        }

        public String get客户代码() {
            return 客户代码;
        }

        public void set客户代码(String 客户代码) {
            this.客户代码 = 客户代码;
        }

        public String get客户名称() {
            return 客户名称;
        }

        public void set客户名称(String 客户名称) {
            this.客户名称 = 客户名称;
        }

        public String get客户部门() {
            return 客户部门;
        }

        public void set客户部门(String 客户部门) {
            this.客户部门 = 客户部门;
        }

        public String get客户订单号() {
            return 客户订单号;
        }

        public void set客户订单号(String 客户订单号) {
            this.客户订单号 = 客户订单号;
        }

        public String get交货日期() {
            return 交货日期;
        }

        public void set交货日期(String 交货日期) {
            this.交货日期 = 交货日期;
        }

        public String get客户等级() {
            return 客户等级;
        }

        public void set客户等级(String 客户等级) {
            this.客户等级 = 客户等级;
        }

        public String get业务人员() {
            return 业务人员;
        }

        public void set业务人员(String 业务人员) {
            this.业务人员 = 业务人员;
        }

        public String get付款方式() {
            return 付款方式;
        }

        public void set付款方式(String 付款方式) {
            this.付款方式 = 付款方式;
        }

        public String get交货方式() {
            return 交货方式;
        }

        public void set交货方式(String 交货方式) {
            this.交货方式 = 交货方式;
        }

        public String get结算币别() {
            return 结算币别;
        }

        public void set结算币别(String 结算币别) {
            this.结算币别 = 结算币别;
        }

        public String get产品名称() {
            return 产品名称;
        }

        public void set产品名称(String 产品名称) {
            this.产品名称 = 产品名称;
        }

        public double get订金金额() {
            return 订金金额;
        }

        public void set订金金额(double 订金金额) {
            this.订金金额 = 订金金额;
        }

        public double get订单金额() {
            return 订单金额;
        }

        public void set订单金额(double 订单金额) {
            this.订单金额 = 订单金额;
        }

        public String get订单备注() {
            return 订单备注;
        }

        public void set订单备注(String 订单备注) {
            this.订单备注 = 订单备注;
        }

        public String get下单人员() {
            return 下单人员;
        }

        public void set下单人员(String 下单人员) {
            this.下单人员 = 下单人员;
        }

        public String get下单日期() {
            return 下单日期;
        }

        public void set下单日期(String 下单日期) {
            this.下单日期 = 下单日期;
        }

        public String get提交人员() {
            return 提交人员;
        }

        public void set提交人员(String 提交人员) {
            this.提交人员 = 提交人员;
        }

        public String get提交日期() {
            return 提交日期;
        }

        public void set提交日期(String 提交日期) {
            this.提交日期 = 提交日期;
        }

        public String get核准人员() {
            return 核准人员;
        }

        public void set核准人员(String 核准人员) {
            this.核准人员 = 核准人员;
        }

        public String get核准日期() {
            return 核准日期;
        }

        public void set核准日期(String 核准日期) {
            this.核准日期 = 核准日期;
        }

        public String get订单状态() {
            return 订单状态;
        }

        public void set订单状态(String 订单状态) {
            this.订单状态 = 订单状态;
        }

        public String get零件编程() {
            return 零件编程;
        }

        public void set零件编程(String 零件编程) {
            this.零件编程 = 零件编程;
        }

        public String get品质检查人员() {
            return 品质检查人员;
        }

        public void set品质检查人员(String 品质检查人员) {
            this.品质检查人员 = 品质检查人员;
        }

        public String get品质检查时间() {
            return 品质检查时间;
        }

        public void set品质检查时间(String 品质检查时间) {
            this.品质检查时间 = 品质检查时间;
        }

        public String get品质检查意见() {
            return 品质检查意见;
        }

        public void set品质检查意见(String 品质检查意见) {
            this.品质检查意见 = 品质检查意见;
        }

        public String get项目审核人员() {
            return 项目审核人员;
        }

        public void set项目审核人员(String 项目审核人员) {
            this.项目审核人员 = 项目审核人员;
        }

        public String get项目审核时间() {
            return 项目审核时间;
        }

        public void set项目审核时间(String 项目审核时间) {
            this.项目审核时间 = 项目审核时间;
        }

        public String get项目审核意见() {
            return 项目审核意见;
        }

        public void set项目审核意见(String 项目审核意见) {
            this.项目审核意见 = 项目审核意见;
        }

        public String get营业qc人员() {
            return 营业qc人员;
        }

        public void set营业qc人员(String 营业qc人员) {
            this.营业qc人员 = 营业qc人员;
        }

        public String get营业qc时间() {
            return 营业qc时间;
        }

        public void set营业qc时间(String 营业qc时间) {
            this.营业qc时间 = 营业qc时间;
        }

        public String get营业qc意见() {
            return 营业qc意见;
        }

        public void set营业qc意见(String 营业qc意见) {
            this.营业qc意见 = 营业qc意见;
        }

        public String get零件编码() {
            return 零件编码;
        }

        public void set零件编码(String 零件编码) {
            this.零件编码 = 零件编码;
        }

        public String get制单() {
            return 制单;
        }

        public void set制单(String 制单) {
            this.制单 = 制单;
        }

        public String get是否报关() {
            return 是否报关;
        }

        public void set是否报关(String 是否报关) {
            this.是否报关 = 是否报关;
        }

        public String get是否快递() {
            return 是否快递;
        }

        public void set是否快递(String 是否快递) {
            this.是否快递 = 是否快递;
        }

        public String get是否收费() {
            return 是否收费;
        }

        public void set是否收费(String 是否收费) {
            this.是否收费 = 是否收费;
        }

        public String get订单策划() {
            return 订单策划;
        }

        public void set订单策划(String 订单策划) {
            this.订单策划 = 订单策划;
        }

        public String get订单类型() {
            return 订单类型;
        }

        public void set订单类型(String 订单类型) {
            this.订单类型 = 订单类型;
        }

        public String get加工方式() {
            return 加工方式;
        }

        public void set加工方式(String 加工方式) {
            this.加工方式 = 加工方式;
        }

        public String get父订单号() {
            return 父订单号;
        }

        public void set父订单号(String 父订单号) {
            this.父订单号 = 父订单号;
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

        public Object getBasemodifytime() {
            return basemodifytime;
        }

        public void setBasemodifytime(Object basemodifytime) {
            this.basemodifytime = basemodifytime;
        }

        public long getBasecreatorid() {
            return basecreatorid;
        }

        public void setBasecreatorid(long basecreatorid) {
            this.basecreatorid = basecreatorid;
        }

        public Object getBasemodifierid() {
            return basemodifierid;
        }

        public void setBasemodifierid(Object basemodifierid) {
            this.basemodifierid = basemodifierid;
        }

        public int getBaseversion() {
            return baseversion;
        }

        public void setBaseversion(int baseversion) {
            this.baseversion = baseversion;
        }


        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
