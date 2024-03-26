package com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 入库适配
 */
public class BoardBean extends BaseExpandNode {
    public String date;  //产品日期
    public String produceID;  //产品
    public String produceType;//型号
    public String produceNum;//入库数
    public String[] nums1;//成本\入库数
    public String[] nums2;//成本\入库数
    public String[] nums3;//成本\入库数
    public String[] nums4;//成本\入库数

    public int item1;//采购入库
    public int item2;//调拔入库
    public int item3;//盘盈入库
    public int item4;//供应商受赠入库
    public String[] months;//月份

    public String unit;//单位

    private List<BaseNode> childNode;

    public BoardBean() {
    }

    public BoardBean(String date, String produceID, String produceType, String produceNum, String unit, List<BaseNode> childNode) {
        this.date = date;
        this.produceID = produceID;
        this.produceType = produceType;
        this.produceNum = produceNum;
        this.unit = unit;
        this.childNode = childNode;

        setExpanded(false);
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }


    public static class SecondBoarBean extends BaseNode {
        public String produceName;  //产品日期
        public String produceType;//型号
        public String produceNum;//入库数
        public String unit;//单位

        public SecondBoarBean(String produceName, String produceType, String produceNum, String unit) {
            this.produceName = produceName;
            this.produceType = produceType;
            this.produceNum = produceNum;
            this.unit = unit;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }
}

















