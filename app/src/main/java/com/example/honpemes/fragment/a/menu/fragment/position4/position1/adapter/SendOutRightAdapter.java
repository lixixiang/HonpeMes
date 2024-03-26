package com.example.honpemes.fragment.a.menu.fragment.position4.position1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position4.position1.bean.SendOutBean;
import com.example.honpemes.utils.StringUtil;

import java.util.List;

/**
 * 作者：asus on 2023/12/13 16:12
 * 注释：
 */
public class SendOutRightAdapter extends ArrayAdapter<SendOutBean.DataBean> {
    private List<SendOutBean.DataBean> mList;

    public SendOutRightAdapter(@NonNull Context context, int resource, List<SendOutBean.DataBean> objects) {
        super(context, resource,objects);
        this.mList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_order_stat_right, null);
            viewHolder.tv_item0 = (TextView) convertView.findViewById(R.id.tv_right_item0);
            viewHolder.tv_item1 = (TextView) convertView.findViewById(R.id.tv_right_item1);
            viewHolder.tv_item2 = (TextView) convertView.findViewById(R.id.tv_right_item2);
            viewHolder.tv_item3 = (TextView) convertView.findViewById(R.id.tv_right_item3);
            viewHolder.tv_item4 = (TextView) convertView.findViewById(R.id.tv_right_item4);
            viewHolder.tv_item5 = (TextView) convertView.findViewById(R.id.tv_right_item5);
            viewHolder.tv_item6 = (TextView) convertView.findViewById(R.id.tv_right_item6);
            viewHolder.tv_item7 = (TextView) convertView.findViewById(R.id.tv_right_item7);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SendOutBean.DataBean bean = mList.get(position);
        viewHolder.tv_item0.setText(bean.制作组别);
        viewHolder.tv_item1.setText(bean.外发订单数);
        viewHolder.tv_item2.setText(bean.外发零件数);
        viewHolder.tv_item3.setText(bean.确认收货订单数);
        viewHolder.tv_item4.setText(bean.确认收货零件数);
        viewHolder.tv_item5.setText(bean.报价订单数);
        viewHolder.tv_item6.setText(StringUtil.formatDouble(bean.报价金额));

        viewHolder.tv_item7.setVisibility(View.GONE);

        viewHolder.tv_item1.setTextColor(getContext().getResources().getColor(R.color.black));
        viewHolder.tv_item2.setTextColor(getContext().getResources().getColor(R.color.green_l));
        viewHolder.tv_item3.setTextColor(getContext().getResources().getColor(R.color.green_l));
        viewHolder.tv_item4.setTextColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        viewHolder.tv_item5.setTextColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        viewHolder.tv_item6.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_light));
//        viewHolder.tv_item7.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_light));


        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public SendOutBean.DataBean getItem(int position) {
        if(mList != null && mList.size()>0){
            return mList.get(position);
        }else{
            return null;
        }
    }

    class ViewHolder {
        TextView tv_item1, tv_item2, tv_item3, tv_item4, tv_item5, tv_item6, tv_item7, tv_item0;
    }





}


