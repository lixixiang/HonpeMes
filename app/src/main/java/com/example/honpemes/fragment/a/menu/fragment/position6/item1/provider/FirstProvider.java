package com.example.honpemes.fragment.a.menu.fragment.position6.item1.provider;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.adapter.FileManagerAdapter;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.tree.FirstNode;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2024/1/23 15:18
 * 注释：
 */
public class FirstProvider extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_first;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        FirstNode firstNode = (FirstNode) baseNode;
        helper.setText(R.id.tv_item1,"项目名称："+ firstNode.getTitle());
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        FileManagerAdapter adapter = (FileManagerAdapter) getAdapter();
        RecyclerView recyclerView = adapter.getRecyclerView();


        // 获取点击后的布局位置
        int clickedPosition = adapter.getHeaderLayoutCount() + position;
        // 获取点击的一级菜单的底部坐标
        int clickedItemBottom = view.getBottom();


        // 获取当前可见的最后一个 item 的位置
        int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        LatteLogger.d("clickedItemBottom",clickedItemBottom+"   "+(recyclerView.getHeight() - 300) +"  "+clickedPosition +"    "+lastVisibleItemPosition);
        // 获取当前一级菜单节点
        FirstNode clickedNode = (FirstNode) adapter.getData().get(position);

        // 判断当前一级菜单是否已展开
        boolean isExpanded = clickedNode.isExpanded();
        // 判断是否是当前可见的最后一个 item 且点击位置在界面底部 300 区域内

        // 获取最后一个 item 的 View
        View lastItemView = recyclerView.getLayoutManager().findViewByPosition(position);


        // 获取最后一个 item 的底部坐标
        int lastItemBottom = lastItemView.getBottom();

        // 计算 RecyclerView 底部到屏幕底部的距离
        int recyclerViewBottomToScreenBottom = recyclerView.getHeight() - recyclerView.getPaddingBottom();


        // 计算需要添加的空间
        int additionalSpace = Math.max(0, recyclerViewBottomToScreenBottom - lastItemBottom + 300);
        // 判断是否是最后一个 item
        boolean isLastItem = clickedPosition == lastVisibleItemPosition;


        if (!isExpanded) {
            if ( clickedItemBottom > recyclerView.getHeight() - 300) {
                // 计算需要上移的距离
                int offsetY = 300;

                // 上移 RecyclerView
                recyclerView.scrollBy(0, offsetY);
            } else if (lastVisibleItemPosition == clickedPosition) {
                if (isLastItem) {
                    // 动态设置 RecyclerView 的高度
                    ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                    layoutParams.height += additionalSpace;
                    recyclerView.setLayoutParams(layoutParams);
                }
                    // 计算需要上移的距离
                    int offsetY = 300;
                    // 上移 RecyclerView
                    recyclerView.scrollBy(0, offsetY);
            }
        }


        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        adapter.expandOrCollapse(position, true, true, FileManagerAdapter.EXPAND_COLLAPSE_PAYLOAD);

    }
}


















