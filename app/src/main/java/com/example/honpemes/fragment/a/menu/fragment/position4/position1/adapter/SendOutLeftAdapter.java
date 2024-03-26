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

import java.util.List;

/**
 * 作者：asus on 2023/12/13 16:12
 * 注释：
 */
public class SendOutLeftAdapter extends ArrayAdapter<SendOutBean.DataBean> {
    private List<SendOutBean.DataBean> mList;

    public SendOutLeftAdapter(@NonNull Context context, int resource, List<SendOutBean.DataBean> objects) {
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
        String part = mList.get(position).业务组别;
        viewHolder.tv_ite0.setText(part);


        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public SendOutBean.DataBean getItem(int position) {
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

