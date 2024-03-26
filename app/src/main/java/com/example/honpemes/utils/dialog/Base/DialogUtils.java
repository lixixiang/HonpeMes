package com.example.honpemes.utils.dialog.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datepickview.calendarpicker.CalendarPicker;
import com.example.datepickview.calendarpicker.OnRangeDatePickListener;
import com.example.datepickview.calendarpicker.core.ColorScheme;
import com.example.datepickview.wheelpicker.DatePicker;
import com.example.datepickview.wheelpicker.DatimePicker;
import com.example.datepickview.wheelpicker.SexPicker;
import com.example.datepickview.wheelpicker.annotation.DateMode;
import com.example.datepickview.wheelpicker.annotation.TimeMode;
import com.example.datepickview.wheelpicker.contract.OnDatePickedListener;
import com.example.datepickview.wheelpicker.contract.OnOptionPickedListener;
import com.example.datepickview.wheelpicker.contract.OnOptionSelectedListener;
import com.example.datepickview.wheelpicker.entity.DateEntity;
import com.example.datepickview.wheelpicker.entity.DatimeEntity;
import com.example.datepickview.wheelpicker.widget.DateWheelLayout;
import com.example.datepickview.wheelpicker.widget.DatimeWheelLayout;
import com.example.honpemes.R;
import com.example.honpemes.api.FinalClass;
import com.example.honpemes.utils.DateUtil;
import com.example.honpemes.utils.EventBus.Event;
import com.example.honpemes.utils.EventBus.EventBusUtil;
import com.example.honpemes.utils.GsonBuildUtil;
import com.example.honpemes.utils.LatteLogger;
import com.example.honpemes.utils.dialog.TipsDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author: asus
 * @date: 2022/10/26
 * @Description:
 */
public class DialogUtils {

    private static Bundle bundle = new Bundle();
    private static long startTimeInMillis, endTimeInMillis, singleTimeInMillis;


    public static DatimePicker onYearMonthDayTime(Activity activity, String title,DatimeEntity startValue,DatimeEntity endValue,DatimeEntity defaultEntity) {
        DatimePicker picker = new DatimePicker(activity);
        DatimeWheelLayout wheelLayout = picker.getWheelLayout();
//        picker.setOnDatimePickedListener(new OnDatimePickedListener() {
//            @Override
//            public void onDatimePicked(int year, int month, int day, int hour, int minute, int second) {
//                String text = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
//                bundle.putString(FinalClass.title, title);
//                bundle.putString(FinalClass.content, text);
//                EventBusUtil.sendEvent(new Event(FinalClass.EVENT, bundle));
//            }
//        });
        picker.setTitle(title);
        picker.getTitleView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getOkView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getCancelView().setTextColor(activity.getResources().getColor(R.color.grey_l_l));
        wheelLayout.setDateMode(DateMode.YEAR_MONTH_DAY);
        wheelLayout.setTimeMode(TimeMode.HOUR_24_HAS_SECOND);
        wheelLayout.setSelectedTextBold(true);
        wheelLayout.setRange(startValue, endValue,defaultEntity);
        wheelLayout.setDateLabel("年", "月", "日");
        wheelLayout.setTimeLabel("时", "分", "秒");
        return picker;
    }


    public static DatePicker onYearMonthDay(Activity activity, String title,DateEntity startValue,DateEntity endValue,DateEntity defaultEntity) {
        DatePicker picker = new DatePicker(activity);
        DateWheelLayout wheelLayout = picker.getWheelLayout();
        picker.setTitle(title);
        picker.getTitleView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getOkView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getCancelView().setTextColor(activity.getResources().getColor(R.color.grey_l_l));
        wheelLayout.setSelectedTextBold(true);
        wheelLayout.setDateMode(DateMode.YEAR_MONTH_DAY);
        wheelLayout.setRange(startValue, endValue,defaultEntity);
        wheelLayout.setDateLabel("年", "月", "日");
        return picker;
    }


    public static void onSex(Activity activity, String title) {
        SexPicker picker = new SexPicker(activity);
        picker.setBodyWidth(140);
        picker.setIncludeSecrecy(false);
        picker.setDefaultValue("男");
        picker.setOnOptionPickedListener(new OnOptionPickedListener() {
            @Override
            public void onOptionPicked(int position, Object item) {

            }
        });
        picker.getWheelLayout().setOnOptionSelectedListener(new OnOptionSelectedListener() {
            @Override
            public void onOptionSelected(int position, Object item) {
                bundle.putString(FinalClass.title, title);
                bundle.putString(FinalClass.content, picker.getWheelView().formatItem(position));
                EventBusUtil.sendEvent(new Event(FinalClass.EVENT, bundle));

            }
        });
        picker.setTitle(title);
        picker.getTitleView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getOkView().setTextColor(activity.getResources().getColor(R.color.black));
        picker.getCancelView().setTextColor(activity.getResources().getColor(R.color.grey_l_l));
        picker.show();
    }

    public static void onCalendarView(Activity activity) {
        Date minDate = new Date(System.currentTimeMillis() - 5 * android.text.format.DateUtils.DAY_IN_MILLIS);
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        CalendarPicker picker = new CalendarPicker(activity);
        if (startTimeInMillis == 0 && endTimeInMillis == 0) {
            startTimeInMillis = currentDate.getTime() - 3 * android.text.format.DateUtils.DAY_IN_MILLIS;
            endTimeInMillis = currentDate.getTime() + 3 * android.text.format.DateUtils.DAY_IN_MILLIS;
        }
        picker.setSelectedDate(startTimeInMillis, endTimeInMillis); //时间区间
        picker.setColorScheme(new ColorScheme()
                .daySelectBackgroundColor(0xFF00CC00)
                .dayStressTextColor(0xFF00AA00));
        picker.setOnRangeDatePickListener(new OnRangeDatePickListener() {
            @Override
            public void onRangeDatePicked(@NonNull Date startDate, @NonNull Date endDate) {
                startTimeInMillis = startDate.getTime();
                endTimeInMillis = endDate.getTime();
                String startTime = DateUtil.ymd.format(startDate) + DateUtil.hms.format(new Date());
                String endTime = DateUtil.ymd.format(endDate) + DateUtil.hms.format(new Date());
                bundle.putString("startTime", startTime);
                bundle.putString("endTime", endTime);
                Toast.makeText(activity, DateFormat.getDateTimeInstance().format(startDate)
                        + "\n" + DateFormat.getDateTimeInstance().format(endDate), Toast.LENGTH_SHORT).show();
                EventBusUtil.sendEvent(new Event(FinalClass.START_TIME_END_TIME, bundle));
            }
        });
        picker.show();
    }


    public static void tipsDialog(Context context, String title, String content) {
        TipsDialog.create(context, title, content, context.getString(R.string.sure), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtil.sendEvent(new Event(FinalClass.EVENT));
            }
        }, context.getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, true, true, true).show();
    }

    public static void tipsDialog(Context context, String title, String content,Bundle bundle) {
        TipsDialog.create(context, title, content, context.getString(R.string.sure), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtil.sendEvent(new Event(FinalClass.EVENT,bundle));
            }
        }, context.getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, true, true, true).show();
    }
}























