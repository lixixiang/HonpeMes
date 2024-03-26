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
import com.example.honpemes.utils.LatteLogger;

import java.util.List;

/**
 * 作者：asus on 2023/12/8 14:33
 * 注释：
 */
public class LeftAdapter extends ArrayAdapter<OrderStatBean.DataBean> {
    private List<OrderStatBean.DataBean> mList;

    public LeftAdapter(@NonNull Context context, int resource, List<OrderStatBean.DataBean> objects) {
        super(context, resource, objects);
        this.mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_order_stat, null);
            viewHolder.tv_ite0 = convertView.findViewById(R.id.tv_item0);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String part = mList.get(position).订单制作组别;
        if (!part.contains("合计")) {
            String[] parts = part.split("\\.");
            viewHolder.tv_ite0.setText(parts[0]);
        } else {
            viewHolder.tv_ite0.setText("");
        }


        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public OrderStatBean.DataBean getItem(int position) {
        if (mList != null && mList.size() > 0) {
            return mList.get(position);
        } else {
            return null;
        }
    }

    class ViewHolder {
        TextView tv_ite0;

    }


}






























