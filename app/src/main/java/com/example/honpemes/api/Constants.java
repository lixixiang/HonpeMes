package com.example.honpemes.api;

import com.example.honpemes.utils.LatteLogger;

/**
 * FileName: Constants
 * Author: asus
 * Date: 2021/12/10 9:38
 * Description:
 */
public class Constants {
    public static final String URL = "http://api.honpe.com:6689";
    public static final String SQ = "&strSQL=SELECT * FROM SysUser WHERE ApiToken='";
    //登录
    public static final String Login = URL + "/User/Login?";
    //退出
    public static final String Login_Off = URL + "/User/LoginOff?";

    /*********App更新apk***********/
    public static String APPDownload = "http://api.honpe.com:8089/honpeapp/mes.apk";
    public static String UPDATE_SIZE = "http://api.honpe.com:8096/Login/PostRequestforMesAppVer";

    //检测App是否为最新版本 红品APK
    public static final String DownloadAPK = "http://api.honpe.com:81/index.html";

    /*********生产订单接口**********/
    public static final String PostOrderNoRecord = URL + "/MESApi/PostOrderNoRecord?Apitoken=";
    /***修改用户个人信息***/
    public static final String PostUpdateUserInfo = URL + "/Supplier/PostUpdateUserInfo?Apitoken=";
    /**********个人信息**********/
    public static final String PERSON_INFO = URL + "/MESApi/PosDataTableRecord?Apitoken=";
    /**********修改密码**********/
    public static final String ChangePassword = URL + "/User/ChangePassword";

    /**********员工信息**********/
    public static final String URL_P = URL + "/MESApi/PosDataTableRecord?Apitoken=";
    /*********生产订单列表***********/
    public static final String OrderList = "&strSQL=SELECT * FROM MakeProductionOrder where 下单日期>='";
    public static final String orderDate = "' and 下单日期<='";
    public static final String orderDate2 = "' order by 下单日期 asc";
    /*********报表数据集***********/
    public static final String PostReportRecord = URL + "/MESApi/PostReportRecord?Apitoken=";

    public static final String DocumentManagement = URL + "/MESApi/DocumentManagement?Apitoken=";

    public static final String DocumentManagementVerifyJson = URL + "/MESApi/DocumentManagementVerifyJson?Apitoken=";

    /*********获取机台状态接口***********/
    public static final String PosERPDataTableRecord = URL + "/MESApi/PosERPDataTableRecord?Apitoken=";


    /*********获取机台状态接口表查找***********/
    public static String PosERPDataTableRecord(String startTime, String endTime) {
        return " SELECT *\n" +
                "  FROM [HPERP].[dbo].[加工机台实时状态],[加工机台管理]\n" +
                "  WHERE  [加工机台实时状态].机台编码=[加工机台管理].机台编号 AND 时间>='" +
                startTime +
                "' and 时间<='" +
                endTime +
                "'";
    }

    public static String PosDataTableCurrentRecord() {
        return " select * from (select *,row_number() over(PARTITION by 机台编码 order by 时间 desc) " +
                "as rowid from 加工机台实时状态) a,加工机台管理 where a.rowid<=1 and a.机台编码=[加工机台管理].机台编号";
    }


    public static String DeviceDetail(String startTime, String endTime, String deviceId) {
        return " SELECT *\n" +
                "  FROM [HPERP].[dbo].[加工机台实时状态],[加工机台管理]\n" +
                "  WHERE  [加工机台实时状态].机台编码=[加工机台管理].机台编号 AND 时间>='" +
                startTime +
                "' and 时间<='" +
                endTime +
                "' and 机台编码='" +
                deviceId +
                "'";
    }

    /**
     * 选择组别
     *
     * @param token
     * @return
     */
    public static String SelectTeam(String token) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" + token + "&strSQL=SELECT 组别代码,组别名称,类别 FROM 组别资料 order by 组别名称 asc";
    }

    public static String SelectDepart(String token) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" + token + "&strSQL=SELECT 部门代码,部门名称,类别 FROM 部门资料 order by 部门名称 asc";
    }

    public static String waitPassOrder(String apiToken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken="
                + apiToken + "&strSQL=SELECT * FROM MakeProductionOrder where 订单状态='已提交' order by 下单日期 desc";
    }

    /**
     * 审批
     *
     * @param apiToken
     * @param orderId
     * @return
     */
    public static String PassAudit(String apiToken, String orderId) {
        return "http://api.honpe.com:6689/MESApi/VerifyJson?Apitoken=" + apiToken + "&OrderNo=" + orderId;
    }

    /**
     * 订单变更
     *
     * @param apiToken
     * @param index
     * @return
     */
    public static String ChangeOrder(String apiToken, int index) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM MakeProductionOrderChangeNotice order by 变更日期 desc offset " +
                index +
                "*20 rows fetch next 20 rows only";
    }

    public static String RefreshChangeOrder(String apiToken, String enterTime, String loadMoreTime) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM MakeProductionOrderChangeNotice where 变更日期>'" +
                enterTime +
                "' and 变更日期<='" +
                loadMoreTime +
                "' order by 变更日期 desc";
    }

    public static String PowerManger(String apiToken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM eneElectricMeter order by BaseCreateTime asc";
    }

    public static String PowerManagerDetail(String apiToken, String startDay, String lastDay, String title) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM eneCurrentEnergy where usingTime>='" +
                startDay + " 00:00:00" +
                "' and usingTime<='" +
                lastDay + " 23:59:59" +
                "' and eleName='" +
                title +
                "' order by usingTime asc";
    }

    public static String OrderManager(String apiToken, String firstDay, String lastDay) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                apiToken +
                "&strSQL=SELECT * FROM MakeProductionOrder where 下单日期>='" +
                firstDay+" 00:00:00" +
                "' and 下单日期<='" +
                lastDay+" 23:59:59" +
                "' order by 下单日期 asc";
    }


    /**
     * 获取生产进度接口
     *
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param OrderStatus 订单状态
     * @param OrderGroup  订单组别
     * @param MakeGroup   制作组别
     * @param keyword     生产单号
     * @param productName 项目跟单人
     * @return
     */
    public static String ProduceProgress(String startTime, String endTime, String OrderStatus, String OrderGroup,
                                         String MakeGroup, String keyword, String productName) {
        return "http://183.11.227.203:6689/MESApi/GetReportActualityBoard?" +
                "strbegdate="
                + startTime +
                "&strendDate="
                + endTime +
                "&OrderStatus="
                + OrderStatus +
                "&OrderGroup="
                + OrderGroup +
                "&MakeGroup="
                + MakeGroup +
                "&keyword="
                + keyword +
                "&productName="
                + productName;
    }

    public static String OutTimeOrder(String startTime, String endTime) {
        return "http://183.11.227.203:6689/MESApi/GetActualityTimeOutBoard?" +
                "strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * @param startTime            开始时间
     * @param endTime              结束时间
     * @param strOperator          操作员
     * @param strProcessingMachine 加工机台
     * @param strProcessingGroup   加工班次
     * @param strProcessingShift   加工组别
     * @return 生产操机工时看板
     */
    public static String ProduceMachineHours(String startTime, String endTime, String strOperator,
                                             String strProcessingMachine, String strProcessingGroup
            , String strProcessingShift) {
        return "http://183.11.227.203:6689/MESApi/GetDigitalWorkingBoard?strbegdate="
                + startTime +
                "&strendDate="
                + endTime +
                "&strOperator="
                + strOperator +
                "&strProcessingMachine="
                + strProcessingMachine +
                "&strProcessingShift="
                + strProcessingShift +
                "&strProcessingGroup="
                + strProcessingGroup;
    }


    /**
     * 获取产品品质看板接口
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String GetOutsourcingPurchase(String startTime, String endTime) {
        return "http://183.11.227.203:6689/MESApi/GetOutsourcingPurchase?" +
                "strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * 近一年的外发订单数以及外发报价总金额柱状图
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String GetOutsourcingBarData(String startTime, String endTime) {
        return "http://183.11.227.203:6689/MESApi/GetOutsourcingBarData?" +
                "strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * 生产进度
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String GetPorductData(String startTime, String endTime) {
        return "http://api.honpe.com:6689/MESApi/GetPorductData?strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * 生产工时
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String GetDigitalWorkingBoard(String startTime, String endTime) {
        return "http://api.honpe.com:6689/MESApi/GetDigitalWorkingBoard?strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * 产品品质
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String GetProductQuality(String startTime, String endTime) {
        return "http://api.honpe.com:6689/MESApi/GetProductQuality?strbegdate="
                + startTime +
                "&strendDate="
                + endTime;
    }

    /**
     * 加工记录
     *
     * @param startTime
     * @param endTime
     * @param Apitoken
     * @return
     */
    public static String Mach_WorkTask(String startTime, String endTime, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" + Apitoken
                + "&strSQL=select * from Mach_WorkTask left join 加工机台管理 on Mach_WorkTask.MachineNO=加工机台管理.机台编号 where CreateTime>='" +
                startTime +
                "' and CreateTime<='" +
                endTime +
                "' order by CreateTime asc";

    }


    /**
     * 加工记录明细
     *
     * @param Apitoken
     * @return
     */
    public static String Mach_WorkTaskSubtable(int WorkTaskId, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM Mach_WorkTaskSubtable where WorkTaskId='" +
                WorkTaskId +
                "' order by StartTime asc";

    }


    /**
     * 项目外发
     *
     * @param Apitoken
     * @return
     */
    public static String PosDataTableRecord(String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单项目外发 order by 外发时间 desc offset 0*30 rows fetch next 30 rows only";
    }


    /**
     * 项目外发刷新
     *
     * @param Apitoken
     * @return
     */
    public static String PosDataTableRecordRefresh(String startTime, String endTime, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单项目外发 where 外发时间>'" +
                startTime +
                "' and 外发时间<='" +
                endTime +
                "' order by 外发时间 desc";
    }

    /**
     * 项目外发加载
     *
     * @param Apitoken
     * @return
     */
    public static String PosDataTableRecordOnLoadMore(String num, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单项目外发 order by 外发时间 desc offset " +
                num +
                "*30 rows fetch next 30 rows only";
    }

    /**
     * 操机指派
     *
     * @param Apitoken
     * @return
     */
    public static String OperatorAss(String startTime, String endTime, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单操机工作指派 where 指派时间>='" +
                startTime +
                "' and 指派时间<='" +
                endTime +
                "' order by 指派时间 asc";
    }

    /**
     * 操机指派明细
     *
     * @param Apitoken
     * @return
     */
    public static String OperatorAssDetail(String orderId, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单操机工作指派明细 where 生产单号='" +
                orderId +
                "'";
    }

    /**
     * 设备信息
     *
     * @param Apitoken
     * @return
     */
    public static String deviceInfo(String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosERPDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM [加工机台管理] LEFT JOIN [生产订单操机工作指派明细] ON [生产订单操机工作指派明细].指派单号=[加工机台管理].指派单号 LEFT JOIN [生产订单] ON [生产订单操机工作指派明细].生产单号=[生产订单].生产单号 ORDER BY 机台编号 asc";
    }


    /**
     * 设备信息明细
     *
     * @param Apitoken
     * @return
     */
    public static String deviceInfoDetail(String Apitoken, String 生产单号, String 零件编码) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单工程零件编程 where 生产单号='" +
                生产单号 +
                "' and 零件编码='" +
                零件编码 +
                "'";
    }


    /**
     * 当前任务
     *
     * @param Apitoken
     * @return
     */
    public static String currentTask(String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=select * from Mach_TaskInfo a where exists(select * from (select machineIP,max(BaseCreateTime) as FTime from Mach_TaskInfo group by machineIP) x where x.machineIP=a.machineIP and a.BaseCreateTime=x.FTime)";
    }


    /**
     * 当前任务内容
     *
     * @param Apitoken
     * @return
     */
    public static String checkRepair(String startTime, String endTime, String Apitoken) {
        return "http://api.honpe.com:6689/MESApi/PosERPDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 加工机台检修记录 where 开始时间>='" +
                startTime +
                " 00:00:00" +
                "' and 开始时间<='" +
                endTime +
                " 23:59:59" +
                "' order by 机台编号 asc";
    }

    /**
     * 报废补料
     *
     * @return
     */
    public static String ScrapReplenish(String startTime, String endTime) {
        return "http://api.honpe.com:6689/MESApi/PostReplenishmentBoard?strbegdate=" +
                startTime +
                "&strendDate=" +
                endTime +
                "";
    }

    /**
     * 客诉
     *
     * @return
     */
    public static String ConsumerComplaints(String Apitoken,int page) {
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单客诉管理 order by 投诉日期 desc offset " +
                page  +
                "*20" +
                " rows fetch next 20 rows only";
    }

    /**
     * 客诉列表
     *
     * @return
     */
    public static String ListConsumerComplaints(String Apitoken,String startTime,String endTime) {
        LatteLogger.d("Apitoken",startTime +"   "+endTime);
        return "http://api.honpe.com:6689/MESApi/PosDataTableRecord?Apitoken=" +
                Apitoken +
                "&strSQL=SELECT * FROM 生产订单客诉管理 where 投诉日期>'" +
                startTime+
                "' and 投诉日期<='" +
                endTime +
                "' order by 投诉日期 desc";
    }

    /**
     * 即将超时
     *
     * @return
     */
    public static String getListTimeOut(String startTime, String endTime) {
        LatteLogger.d("Apitoken",startTime +"   "+endTime);
        return "http://api.honpe.com:6689/MESApi/GetOutboundTimeOutBoard?strBeginDate=" +
                startTime +" 00:00:00"+
                "&strendDate=" +
                endTime +" 08:59:59";
    }

    public static String getAboutRunOut() {
        return "http://api.honpe.com:6689/MESApi/GetOutboundPurchaseTimeingBoard";
    }

}



















