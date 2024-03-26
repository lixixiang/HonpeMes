package com.example.honpemes.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

/**
 * FileName: DragForScrollView
 * Author: asus
 * Date: 2020/8/27 11:53
 * Description:
 */
public class DragForScrollView extends NestedScrollView {
    private boolean isDrag;
    public DragForScrollView(Context context) {
        super(context);
    }
    public DragForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DragForScrollView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("isDrag",ev.getAction()+"");
        if (ev.getAction() == MotionEvent.ACTION_DOWN||ev.getAction() ==MotionEvent.ACTION_UP||ev.getAction() ==MotionEvent.ACTION_MOVE) {
            if (isDrag) {
                return false;
            } else {
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
    public void startDrag(int position) {
        isDrag = true;
    }
    public void endDrag(int position) {
        isDrag = false;
    }
}
