package com.example.honpemes.fragment.a.menu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honpemes.R;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.fragment.a.menu.MenuManagerFragment;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/28 9:54
 */
public class MenuChildAdapter extends BaseAdapter {
    private List<MenuBean> menuList;
    private MenuManagerFragment fragment;
    private Context context;
    private boolean IsEdit;

    public MenuChildAdapter(Context context, MenuManagerFragment fragment, List<MenuBean> list, boolean isEdit2) {
        this.fragment = fragment;
        this.context = context;
        this.menuList = list;
        this.IsEdit = isEdit2;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.items_cate_child, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_cate_child_name = (TextView) convertView.findViewById(R.id.tv_title);

            viewHolder.deleteImg = (ImageView) convertView.findViewById(R.id.delete_img);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.icon_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MenuBean menuEntity = menuList.get(position);
        if (IsEdit) {
            if (menuEntity.isEnable()) {
                viewHolder.deleteImg.setVisibility(View.VISIBLE);
            } else {
                viewHolder.deleteImg.setVisibility(View.GONE);
            }
            if (menuEntity.isSelect()) {
                viewHolder.deleteImg.setBackgroundResource(R.mipmap.menu_select);
            } else {
                viewHolder.deleteImg.setBackgroundResource(R.mipmap.menu_add);
            }
        } else {
            viewHolder.deleteImg.setVisibility(View.GONE);
        }


        /**
         * enable 为true 显示原有颜色图标，为false 则为灰色图标，场景为尚未开发应用
         */
        if (menuEntity.isEnable()) {
            viewHolder.iconImg.setColorFilter(Color.TRANSPARENT);
            viewHolder.tv_item_cate_child_name.setTextColor(context.getResources().getColor(R.color.grey_l));
        }  else {
            viewHolder.iconImg.setColorFilter(context.getResources().getColor(R.color.grey_l_l_l));
            viewHolder.tv_item_cate_child_name.setTextColor(context.getResources().getColor(R.color.grey_l_l_l));
        }


        viewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!menuEntity.isSelect()) {
                    fragment.AddMenu(menuEntity);
                }
            }
        });

        //获取资源图片
        int drawableId = context.getResources().getIdentifier(menuEntity.getIco(), "mipmap", context.getPackageName());
        viewHolder.iconImg.setImageResource(drawableId);
        viewHolder.tv_item_cate_child_name.setText(menuEntity.getTitle());

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_item_cate_child_name;
        private ImageView deleteImg, iconImg;
    }


}