package com.example.honpemes.fragment.a.menu.fragment.position9.item4.EmployeeDataAdapter;

import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.b.setting.mob.bean.UseInfoBean;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.StringUtil;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Lixixiang on 2023/3/7 14:59
 */
public class EmployeeDataAdapter extends BaseQuickAdapter<UseInfoBean.DataBean, BaseViewHolder> {
    private LinearLayoutManager mLayoutManager;
    private boolean stateChanged;

    public EmployeeDataAdapter() {
        super(R.layout.css_headicon_info);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, UseInfoBean.DataBean bean) {
        holder.setText(R.id.tv_job_num, bean.getEmpno() + "")
                .setText(R.id.tv_username, bean.getRealname())
                .setImageResource(R.id.iv_sex, bean.getGender() == 1 ? R.mipmap.ic_male : R.mipmap.ic_female)
                .setText(R.id.tv_work_age, bean.getWorkyear() + "年工龄")
                .setText(R.id.tv_work_date, DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, StringUtil.replaceT(bean.getEmploydate()))))
                .setText(R.id.tv_xueli, bean.getEducation())
                .setText(R.id.tv_post, bean.getStationname())
                .setText(R.id.tv_team, bean.getRemark())
                .setText(R.id.tv_phone, bean.getMobile());
    }

    public void setLayoutManager(LinearLayoutManager manager) {
        this.mLayoutManager = manager;
    }

    public void refreshLocationItem() {
        //如果定位城市的item可见则进行刷新
        if (stateChanged && mLayoutManager.findFirstVisibleItemPosition() == 0) {
            stateChanged = false;
            notifyItemChanged(0);
        }
    }

    /**
     * 滚动RecyclerView到索引位置
     *
     * @param index
     */
    public void scrollToSection(String index) {
        if (getData() == null || getData().isEmpty()) return;
        if (TextUtils.isEmpty(index)) return;
        int size = getData().size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), getData().get(i).getSection().substring(0, 1))) {
                if (mLayoutManager != null) {
                    mLayoutManager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        }
    }
}



