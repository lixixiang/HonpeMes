package com.example.honpemes.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：asus on 2024/1/23 11:26
 * 注释：用于设置每个 Item 的宽度：
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private float[] itemWidthPercentage;

    public CustomItemDecoration(float[] itemWidthPercentage) {
        this.itemWidthPercentage = itemWidthPercentage;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int parentWidth = parent.getWidth();
        float totalPercentage = 0;

        for (float percentage : itemWidthPercentage) {
            totalPercentage += percentage;
        }

        int itemWidth = (int) (parentWidth * (itemWidthPercentage[position % itemWidthPercentage.length] / totalPercentage));
        outRect.right = itemWidth;
    }
}
