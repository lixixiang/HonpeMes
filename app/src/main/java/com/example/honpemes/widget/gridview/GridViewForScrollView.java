package com.example.honpemes.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * FileName: GridViewForScrollView
 * Author: asus
 * Date: 2020/8/27 14:09
 * Description:
 */
public class GridViewForScrollView extends GridView {
    public GridViewForScrollView(Context context) {
        super(context);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写该方法，达到使GridView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}


