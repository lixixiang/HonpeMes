package com.example.honpemes.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honpemes.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * 作者：asus on 2023/12/18 16:12
 * 注释：柱状图提示框
 */
public class XYMarkerView extends MarkerView {

    private final TextView tvContent;
    private final ImageView ivBg;


    public XYMarkerView(Context context,int colorsFont, int colorsBg) {
        super(context, R.layout.custom_marker_view);

        tvContent = findViewById(R.id.tvContent);
        tvContent.setTextColor(getResources().getColor(colorsFont));
        ivBg = findViewById(R.id.iv_marker);
        ivBg.setColorFilter(getResources().getColor(colorsBg));
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int dataIndex = (int) highlight.getX();
        float value = highlight.getY();
        String markData = "Index: " + dataIndex + "\n Value: " + value;


        tvContent.setText(markData);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
