package com.example.honpemes.fragment.a.menu.fragment.position1.item4.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/18 11:56
 * 注释：
 */
public class VerticalShowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public VerticalShowAdapter() {
        super(R.layout.item_single_text2);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String s) {
        TextView tvItemTitle = holder.getView(R.id.item_single_text);
        tvItemTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);


        if (s.contains("_")) {
            String[] parts = s.split("_");
            // 创建一个 SpannableString，用于显示两列
            SpannableString spannableString = new SpannableString(parts[0] + "\n" + parts[1]);

            // 设置第一列的样式
            spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, parts[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 设置第二列的样式
            spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), parts[0].length() + 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            LatteLogger.d("resultresult", spannableString.toString());

            tvItemTitle.setText(spannableString);
        } else {
            char[] characters = s.toCharArray();
            StringBuilder verticallyAlignedText = new StringBuilder();
            for (char character : characters) {
                verticallyAlignedText.append(character).append("\n");
            }
            tvItemTitle.setText(verticallyAlignedText.toString());

        }


    }
}
