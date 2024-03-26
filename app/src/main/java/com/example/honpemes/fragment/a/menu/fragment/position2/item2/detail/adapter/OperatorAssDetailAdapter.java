package com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.adapter;

import android.graphics.Bitmap;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.bean.OperatorAssDetailBean;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import cn.bertsir.zbar.utils.QRUtils;

/**
 * 作者：asus on 2023/12/22 10:15
 * 注释：
 */
public class OperatorAssDetailAdapter extends BaseMultiItemQuickAdapter<OperatorAssDetailBean.DataBean, BaseViewHolder> {

    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_1_1)
    TextView item11;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_2_2)
    TextView item22;
    @BindView(R.id.item_3)
    TextView item3;
    @BindView(R.id.item_3_2)
    TextView item32;
    @BindView(R.id.item_4)
    TextView item4;
    @BindView(R.id.item_4_2)
    TextView item42;
    private Bitmap qrCode;

    public OperatorAssDetailAdapter() {
        addItemType(OperatorAssDetailBean.DataBean.TYPE_1, R.layout.item_operator_ass_css1);
        addItemType(OperatorAssDetailBean.DataBean.TYPE_2, R.layout.item_operator_ass_css2);
        addItemType(OperatorAssDetailBean.DataBean.TYPE_3, R.layout.item_operator_ass_css2);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OperatorAssDetailBean.DataBean bean) {
        switch (holder.getItemViewType()) {
            case OperatorAssDetailBean.DataBean.TYPE_1:
                holder.setText(R.id.item_1, "生产单号")
                        .setText(R.id.item_2, "机台\n类型")
                        .setText(R.id.item_3, "制作\n数量")
                        .setText(R.id.item_4, "执行\n次数")
                        .setText(R.id.item_5, "开料尺寸\n长x宽x高")
                        .setText(R.id.item_1_2, bean.operatorAssBean.生产单号)
                        .setText(R.id.item_2_2, bean.operatorAssBean.机台类型)
                        .setText(R.id.item_3_2, bean.operatorAssBean.制作数量+"")
                        .setText(R.id.item_4_2, bean.operatorAssBean.执行次数+"")
                        .setText(R.id.item_5_2, bean.operatorAssBean.开料尺寸);

                qrCode = QRUtils.getInstance().createQRCode(bean.operatorAssBean.生产单号);
                holder.setImageBitmap(R.id.iv_zxing, qrCode);
                holder.setText(R.id.tv_item_1, "指派人员：" + bean.operatorAssBean.指派人员)
                        .setText(R.id.tv_item_2, "指派时间：" + bean.operatorAssBean.指派时间)
                        .setText(R.id.tv_item_3, "开料备注：" + bean.operatorAssBean.开料备注.replaceAll("\\s", ""));

                break;

            case OperatorAssDetailBean.DataBean.TYPE_2:
                String machineCord = "机台编码：" + bean.机台编码;
                String isKL = bean.仓库开料 ? "是" : "否";

                holder.setText(R.id.item_1, StringUtil.changeStrColor(machineCord, R.color.black, "机台编码：".length(), machineCord.length()))
                        .setText(R.id.item_1_1, "仓库开料：" + isKL)
                        .setText(R.id.item_2, "加工数量：" + bean.加工数量)
                        .setText(R.id.item_2_1, "加工执行次数：" + bean.加工执行次数)
                        .setText(R.id.item_3, "预计加工工时：" + bean.预计加工工时)
                        .setText(R.id.item_3_1, "要求完成时间：" + bean.要求完成时间)
                        .setText(R.id.item_4, "作废人员：" + bean.作废人)
                        .setText(R.id.item_4_1, "作废时间：" + bean.作废时间);
                break;
            case OperatorAssDetailBean.DataBean.TYPE_3:
                String machineCord2 = "机台编码：" + bean.机台编码;
                String isKL2 = bean.仓库开料 ? "是" : "否";
               String JGBZ = "加工备注：" + bean.加工备注;

                holder.setText(R.id.item_1, StringUtil.changeStrColor(machineCord2, R.color.black, "机台编码：".length(), machineCord2.length()))
                        .setText(R.id.item_1_1, "仓库开料：" + isKL2)
                        .setText(R.id.item_2, StringUtil.changeStrColor(JGBZ, R.color.orange_l, "加工备注：".length(), JGBZ.length()))
                        .setGone(R.id.item_2_1, true)
                        .setText(R.id.item_3, "加工数量：" + bean.加工数量)
                        .setText(R.id.item_3_1, "加工执行次数：" + bean.加工执行次数)
                        .setText(R.id.item_4, "预计加工工时：" + bean.预计加工工时)
                        .setText(R.id.item_4_1, "要求完成时间：" + bean.要求完成时间);
                break;
        }
    }
}
