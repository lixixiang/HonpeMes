package com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.bean.DeviceInDetailBean;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.QrUtil;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

import cn.bertsir.zbar.utils.QRUtils;

/**
 * 作者：asus on 2023/12/25 09:55
 * 注释：
 */
public class DeviceInfoDetailAdapter extends BaseMultiItemQuickAdapter<DeviceInDetailBean.DataBean, BaseViewHolder> {

   private String sDetails;

    public DeviceInfoDetailAdapter() {
        addItemType(DeviceInDetailBean.DataBean.TYPE_1, R.layout.item_device_info_detail);
        addItemType(DeviceInDetailBean.DataBean.TYPE_2, R.layout.item_device_info_detail2);
        addItemType(DeviceInDetailBean.DataBean.TYPE_3, R.layout.item_device_info_detail3);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, DeviceInDetailBean.DataBean bean) {
        switch (holder.getItemViewType()) {
            case DeviceInDetailBean.DataBean.TYPE_1:
                    if (bean.deviceInBean.机台状态.contains("▲")) {
                        sDetails = "闲置";
                        holder.setTextColor(R.id.item_1_1,getContext().getResources().getColor(R.color.orange_l));
                    } else if (bean.deviceInBean.机台状态.contains("●")) {
                        sDetails = "运行";
                        holder.setTextColor(R.id.item_1_1,getContext().getResources().getColor(R.color.green_l));
                    } else if (bean.deviceInBean.机台状态.contains("■")) {
                        sDetails = "检修";
                        holder.setTextColor(R.id.item_1_1,getContext().getResources().getColor(R.color.red_l));
                    }

                String s1 = "机台型号："+bean.deviceInBean.机台型号;
                String s6 =  "生产单号："+bean.deviceInBean.生产单号;
                String s7 = "产品名称："+bean.deviceInBean.产品名称;

                holder.setText(R.id.item_1, StringUtil.changeFontColor(s1, R.color.black, "机台型号：".length(), s1.length()))
                        .setText(R.id.item_1_1,"机台状态："+sDetails)
                        .setText(R.id.item_2,"机器行程："+bean.deviceInBean.机器行程).setText(R.id.item_2_1,"待机数量："+bean.deviceInBean.待机数量)
                        .setText(R.id.item_3,"主轴转速："+bean.deviceInBean.主轴转速).setText(R.id.item_3_1,"切削速度："+bean.deviceInBean.切削速度)
                        .setText(R.id.item_4,"使用部门："+bean.deviceInBean.使用部门).setText(R.id.item_4_1,"制作组别："+bean.deviceInBean.制作组别)
                        .setText(R.id.item_5,"加工人员："+bean.deviceInBean.加工人员).setText(R.id.item_5_1,"上机时间："+bean.deviceInBean.上机时间)
                        .setText(R.id.item_6,StringUtil.changeFontColor(s6, R.color.black, "生产单号：".length(), s6.length()))
                        .setText(R.id.item_6_1,"零件编号："+bean.deviceInBean.零件编码2).setTextColorRes(R.id.item_6_1,R.color.black)
                        .setText(R.id.item_7,StringUtil.changeFontColor(s7, R.color.black, "产品名称：".length(), s7.length()));

                break;
            case DeviceInDetailBean.DataBean.TYPE_2:

                break;
            case DeviceInDetailBean.DataBean.TYPE_3:
                LatteLogger.d("beanbeanbean", GsonBuildUtil.GsonBuilder(bean));

                holder.setText(R.id.item_1, bean.主件编号)
                        .setText(R.id.item_2, bean.零件名称)
                        .setText(R.id.item_3, bean.零件材质)
                        .setText(R.id.item_4, bean.零件尺寸)
                        .setText(R.id.item_5, bean.订单数量 +"")
                        .setText(R.id.item_des_content,"项目预审说明："+bean.项目预审说明)
                        .setText(R.id.item_note,"备注："+bean.备注);
                Bitmap qrCode = QRUtils.getInstance().createQRCode(bean.零件编码);
                holder.setImageBitmap(R.id.iv_zxing, qrCode);
                Glide.with(getContext()).load(Constants.URL + bean.存放位置)
                        .placeholder(R.drawable.ic_default_no_show).error(R.drawable.ic_default_no_show).into(((ImageView)holder.getView(R.id.iv_icon)));
                break;
        }
    }
}




































