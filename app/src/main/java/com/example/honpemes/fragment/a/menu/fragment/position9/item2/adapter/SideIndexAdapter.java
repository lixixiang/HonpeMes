package com.example.honpemes.fragment.a.menu.fragment.position9.item2.adapter;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author: asus
 * @date: 2022/11/8
 * @Description:
 */
@SuppressLint("ClickableViewAccessibility")
public class SideIndexAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
     private TextView mOverlayTextView;

    public SideIndexAdapter(TextView cpOverlay) {
        super(R.layout.item_single_text);
        this.mOverlayTextView = cpOverlay;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String s) {
        TextView text = holder.getView(R.id.tv_item_title);
        text.setText(s);

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        mOverlayTextView.setVisibility(View.VISIBLE);
                        mOverlayTextView.setText(s);
                        if (mOnIndexChangedListener != null) {
                            mOnIndexChangedListener.onIndexChanged(mOverlayTextView.getText().toString(), holder.getAdapterPosition());
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mOverlayTextView.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });

    }

    private OnIndexTouchedChangedListener mOnIndexChangedListener;


    public void setOnIndexChangedListener(OnIndexTouchedChangedListener listener) {
        this.mOnIndexChangedListener = listener;
    }

    public interface OnIndexTouchedChangedListener {
        void onIndexChanged(String index, int position);
    }
}
