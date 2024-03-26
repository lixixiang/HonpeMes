package com.example.honpemes.fragment.a.menu.fragment.position6.item1.provider;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.Constants;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.bean.FileManagerBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.tree.ThirdNode;
import com.flyco.roundview.RoundTextView;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2024/1/23 15:18
 * 注释：
 */
public class ThirdProvider extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_second;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        ThirdNode thirdNode = (ThirdNode) baseNode;
        FileManagerBean.DataBean.DocumentManagementBean bean = thirdNode.getDocumentManagement();
        helper.setText(R.id.tv_id,thirdNode.get_id()+"");
        helper.setText(R.id.tv_item1,bean.documentClassification+"："+bean.documentName+bean.fileType)
        .setGone(R.id.tv_item3,true);

        helper.setText(R.id.tv_item2,"上传人员："+bean.designer +" "+
                DateUtil.strYMDhm.format(DateUtil.setDate(DateUtil.ymdhms,bean.baseCreateTime.replace("T"," "))));

//        helper.setText(R.id.tv_item3, Constants.URL+bean.storageLocation);

        if (bean.documentClassification.contains("内部资料")) {
            helper.setTextColorRes(R.id.tv_item1, R.color.blue_l);
        } else {
            helper.setTextColorRes(R.id.tv_item1, R.color.orange_l);
        }

       RoundTextView tvLook = helper.getView(R.id.tv_look);
        tvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Constants.URL + bean.storageLocation;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(intent);
            }
        });
    }
}


















