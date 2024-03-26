package com.example.honpemes.fragment.a.com_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.bean.MenuBean;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.Util;
import com.example.honpemes.widget.gridview.BaseGridView;
import com.flyco.roundview.RoundLinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/27 15:54
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<MenuBean, BaseViewHolder> {


    HomeModeAdapter homeModeAdapter = new HomeModeAdapter();

    public HomeAdapter() {
        addItemType(MenuBean.TYPE_1, R.layout.item_home);
        addItemType(MenuBean.TYPE_2, R.layout.item_cate_parent);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MenuBean bean) {
        switch (holder.getItemViewType()) {
            case MenuBean.TYPE_1:
                holder.setText(R.id.tv_item_title, bean.getTitle());
                RecyclerView mRecyclerView = holder.getView(R.id.m_recyclerView);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(homeModeAdapter);
                for (int i = 0; i < bean.getDetail().size(); i++) {
                    bean.getDetail().get(i).setSpanSize(1);
                }

                homeModeAdapter.setList(bean.getDetail());
                homeModeAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
                    @Override
                    public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                        return bean.getDetail().get(position).getSpanSize();
                    }
                });
                homeModeAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        EventBusUtil.sendEvent(new Event(FinalClass.ENTER_CHILD_CLASS, homeModeAdapter.getData().get(position).getTitle()));
                    }
                });
                break;
            case MenuBean.TYPE_2:
                RoundLinearLayout llMenuBg = holder.getView(R.id.ll_menu1_bg);
                llMenuBg.getDelegate().setBackgroundColor(getContext().getResources().getColor(R.color.white));
                llMenuBg.getDelegate().setCornerRadius(4);
                holder.setText(R.id.tv_item_title, bean.getTitle());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(Util.dp2px(16), 10, Util.dp2px(16), 10);
                llMenuBg.setLayoutParams(params);

                BaseGridView gridView =holder.getView(R.id.gv_toolbar);
                ChildAdapter mChildAdapter = new ChildAdapter(getContext(), bean.getDetail());
                gridView.setAdapter(mChildAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        EventBusUtil.sendEvent(new Event(FinalClass.ENTER_CHILD_CLASS, bean.getDetail().get(position).getTitle()));
                    }
                });
                break;
        }
    }


    public class ChildAdapter extends BaseAdapter {
        private List<MenuBean> menuList;
        private Context context;

        public ChildAdapter(Context context, List<MenuBean> list) {
            this.context = context;
            this.menuList = list;

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

                viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.icon_img);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final MenuBean menuEntity = menuList.get(position);
            //获取资源图片
            int drawableId = context.getResources().getIdentifier(menuEntity.getIco(), "mipmap", context.getPackageName());
            viewHolder.iconImg.setImageResource(drawableId);
            viewHolder.tv_item_cate_child_name.setText(menuEntity.getTitle());

            if (menuEntity.isEnable()) {
                viewHolder.iconImg.setColorFilter(Color.TRANSPARENT);
                viewHolder.tv_item_cate_child_name.setTextColor(context.getResources().getColor(R.color.grey_l));
            }  else {
                viewHolder.iconImg.setColorFilter(context.getResources().getColor(R.color.grey_l_l_l));
                viewHolder.tv_item_cate_child_name.setTextColor(context.getResources().getColor(R.color.grey_l_l_l));
            }

            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tv_item_cate_child_name;
        private ImageView  iconImg;
    }
}


























