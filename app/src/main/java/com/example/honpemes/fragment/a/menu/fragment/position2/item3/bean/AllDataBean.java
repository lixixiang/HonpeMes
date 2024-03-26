package com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2023/12/7 14:51
 * 注释：所有生产数据合集
 */
public class AllDataBean implements Serializable{


    private List<ProductQualityBean.DataBean> mListProductQuality;
    private List<ProduceProgressBean.DataBean> mListProduceProgress;
    private List<ProduceMachineHoursBean.DataBean> mListProduceMachineHours;
    private List<OutsourcingPurchaseBean.DataBean> mListOutsourcingPurchase;
    private List<OutsourcingBarDataBean.DataBean> mListOutsourcingBarData;


    public List<ProductQualityBean.DataBean> getmListProductQuality() {
        return mListProductQuality;
    }

    public void setmListProductQuality(List<ProductQualityBean.DataBean> mListProductQuality) {
        this.mListProductQuality = mListProductQuality;
    }

    public List<ProduceProgressBean.DataBean> getmListProduceProgress() {
        return mListProduceProgress;
    }

    public void setmListProduceProgress(List<ProduceProgressBean.DataBean> mListProduceProgress) {
        this.mListProduceProgress = mListProduceProgress;
    }

    public List<ProduceMachineHoursBean.DataBean> getmListProduceMachineHours() {
        return mListProduceMachineHours;
    }

    public void setmListProduceMachineHours(List<ProduceMachineHoursBean.DataBean> mListProduceMachineHours) {
        this.mListProduceMachineHours = mListProduceMachineHours;
    }

    public List<OutsourcingPurchaseBean.DataBean> getmListOutsourcingPurchase() {
        return mListOutsourcingPurchase;
    }

    public void setmListOutsourcingPurchase(List<OutsourcingPurchaseBean.DataBean> mListOutsourcingPurchase) {
        this.mListOutsourcingPurchase = mListOutsourcingPurchase;
    }

    public List<OutsourcingBarDataBean.DataBean> getmListOutsourcingBarData() {
        return mListOutsourcingBarData;
    }

    public void setmListOutsourcingBarData(List<OutsourcingBarDataBean.DataBean> mListOutsourcingBarData) {
        this.mListOutsourcingBarData = mListOutsourcingBarData;
    }


}
