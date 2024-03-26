package com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position1.item4.bean.OrderStatBean;

import java.util.List;

/**
 * 作者：asus on 2023/12/8 14:33
 * 注释：
 */
public class RightAdapter extends ArrayAdapter<OrderStatBean.DataBean> {
   private List<OrderStatBean.DataBean> mList;

    public RightAdapter(@NonNull Context context, int resource, List<OrderStatBean.DataBean> objects) {
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

        OrderStatBean.DataBean bean = mList.get(position);
        String part = mList.get(position).订单制作组别;
        if (part.contains("合计")) {
            viewHolder.tv_item0.setText(part);
        } else {
            String[] parts = part.split("\\.");
            viewHolder.tv_item0.setText(parts[1]);
        }


        viewHolder.tv_item1.setText(bean.订单数量);
        viewHolder.tv_item2.setText(bean.完成数量);
        viewHolder.tv_item3.setText(bean.完成率);
        viewHolder.tv_item4.setText(bean.延迟数量);
        viewHolder.tv_item5.setText(bean.延迟率);
        viewHolder.tv_item6.setText(bean.报废数量);
        viewHolder.tv_item7.setText(bean.报废率);

        viewHolder.tv_item1.setTextColor(getContext().getResources().getColor(R.color.black));
        viewHolder.tv_item2.setTextColor(getContext().getResources().getColor(R.color.green_l));
        viewHolder.tv_item3.setTextColor(getContext().getResources().getColor(R.color.green_l));
        viewHolder.tv_item4.setTextColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        viewHolder.tv_item5.setTextColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        viewHolder.tv_item6.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_light));
        viewHolder.tv_item7.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_light));


        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public OrderStatBean.DataBean getItem(int position) {
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






























