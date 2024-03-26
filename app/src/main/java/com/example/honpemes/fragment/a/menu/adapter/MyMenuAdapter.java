package com.example.honpemes.fragment.a.menu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.menu.MenuManagerFragment;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.widget.gridview.DragAdapterInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: MyMenuAdapter
 * Author: asus
 * Date: 2020/8/27 13:39
 * Description: 顶部选中的菜单模块
 */
public class MyMenuAdapter extends BaseAdapter implements DragAdapterInterface {
    private boolean IsEdit = false;
    private List<MenuBean> datas;
    private Context context;
    private MenuManagerFragment fragment;

    public MyMenuAdapter(Context context, MenuManagerFragment fragment, List<MenuBean> datas) {
        this.context = context;
        this.datas = datas;
        this.fragment = fragment;
    }

    public void setDatas(List<MenuBean> listData) {
        this.datas = listData;
        notifyDataSetChanged();
    }

    public List<MenuBean> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuBean bean = datas.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.items_cate_child, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.DelMenu(datas.get(position), position);
                EventBusUtil.sendEvent(new Event(FinalClass.MENU_ADD_DEL, datas));
            }
        });

        /**
         * enable 为true 显示原有颜色图标，为false 则为灰色图标，场景为尚未开发应用
         */
        if (!datas.get(position).isEnable()) {
            holder.iconImg.setColorFilter(context.getResources().getColor(R.color.grey_l_l_l));
            holder.nameTv.setTextColor(context.getResources().getColor(R.color.grey_l_l_l));
        } else {
            holder.iconImg.setColorFilter(Color.TRANSPARENT);
            holder.nameTv.setTextColor(context.getResources().getColor(R.color.grey_l));
        }

        if (IsEdit) {
            holder.deleteImg.setVisibility(View.VISIBLE);
        } else {
            holder.deleteImg.setVisibility(View.GONE);
        }

        //获取资源图片
        int drawableId = context.getResources().getIdentifier(bean.getIco(), "mipmap", context.getPackageName());
        holder.iconImg.setImageResource(drawableId);
        holder.nameTv.setText(bean.getTitle());
        holder.itemContainer.setBackgroundColor(Color.WHITE);
        return convertView;
    }

    @Override
    public void reOrder(int startPosition, int endPosition) {
        if (endPosition < datas.size()) {
            MenuBean object = datas.remove(startPosition);
            datas.add(endPosition, object);
            notifyDataSetChanged();
        }
    }

    public void setEdit() {
        IsEdit = true;
        notifyDataSetChanged();
    }


    public void endEdit() {
        IsEdit = false;
        notifyDataSetChanged();
    }

    public boolean getEditStatue() {
        return IsEdit;
    }

    static class ViewHolder {
        @BindView(R.id.icon_img)
        ImageView iconImg;
        @BindView(R.id.tv_title)
        TextView nameTv;
        @BindView(R.id.delete_img)
        ImageView deleteImg;
        @BindView(R.id.item_container)
        FrameLayout itemContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
