package com.billshuai.timepicker.wheel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.billshuai.timepicker.R;

import java.util.Calendar;

/**
 * Created by Billshuai on  2016/8/11.
 */
public class TimePickerDialog extends Dialog {

    private int minYear = 1950; // minimum year
    private int yearSize = 100; // sum years
    private WheelView yearWheel, monthWheel, dayWheel, hourWheel, minuteWheel,
            secondWheel;
    public String[] yearContent = null;
    public String[] monthContent = null;
    public String[] dayContent = null;
    public String[] hourContent = null;
    public String[] minuteContent = null;
    public String[] secondContent = null;
    public StringBuffer sb;
    Button btn_confirm;

    boolean isClick = false;

    int layoutRes;//layout file
    Context context;

    public TimePickerDialog(Context context) {
        super(context);
        this.context = context;
    }

    public TimePickerDialog(Context context, int themeResId, TextView textView, int layoutRes) {
        super(context, themeResId);
        this.context = context;
        this.layoutRes = layoutRes;
        showDatePicker(textView);
    }


    private void showDatePicker(final TextView textView) {
        initContent();
        this.setContentView(layoutRes);
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMinute = calendar.get(Calendar.MINUTE);
        int curSecond = calendar.get(Calendar.SECOND);

        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        yearWheel = (WheelView) findViewById(R.id.yearwheel);
        monthWheel = (WheelView) findViewById(R.id.monthwheel);
        dayWheel = (WheelView) findViewById(R.id.daywheel);
        hourWheel = (WheelView) findViewById(R.id.hourwheel);
        minuteWheel = (WheelView) findViewById(R.id.minutewheel);
        secondWheel = (WheelView) findViewById(R.id.secondwheel);

        yearWheel.setAdapter(new StrericWheelAdapter(yearContent));
        yearWheel.setCurrentItem(curYear - minYear);
        yearWheel.setCyclic(true);
        yearWheel.setInterpolator(new AnticipateOvershootInterpolator());

        monthWheel.setAdapter(new StrericWheelAdapter(monthContent));

        monthWheel.setCurrentItem(curMonth - 1);

        monthWheel.setCyclic(true);
        monthWheel.setInterpolator(new AnticipateOvershootInterpolator());

        dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
        dayWheel.setCurrentItem(curDay - 1);
        dayWheel.setCyclic(true);
        dayWheel.setInterpolator(new AnticipateOvershootInterpolator());

        hourWheel.setAdapter(new StrericWheelAdapter(hourContent));
        hourWheel.setCurrentItem(curHour);
        hourWheel.setCyclic(true);
        hourWheel.setInterpolator(new AnticipateOvershootInterpolator());

        minuteWheel.setAdapter(new StrericWheelAdapter(minuteContent));
        minuteWheel.setCurrentItem(curMinute);
        minuteWheel.setCyclic(true);
        minuteWheel.setInterpolator(new AnticipateOvershootInterpolator());

        secondWheel.setAdapter(new StrericWheelAdapter(secondContent));
        secondWheel.setCurrentItem(curSecond);
        secondWheel.setCyclic(true);
        secondWheel.setInterpolator(new AnticipateOvershootInterpolator());

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb = new StringBuffer();
                sb.append(yearWheel.getCurrentItemValue()).append("-")
                        .append(monthWheel.getCurrentItemValue())
                        .append("-")
                        .append(dayWheel.getCurrentItemValue())
                        .append(" ")
                        .append(hourWheel.getCurrentItemValue()).append(":")
                        .append(minuteWheel.getCurrentItemValue())
                        .append(":")
                        .append(secondWheel.getCurrentItemValue());
                textView.setText(sb);
                cancelDatePickerDialog();
                //send broadcast
                Intent sendIntent = new Intent();
                sendIntent.setAction("GetTimeValue");
                sendIntent.putExtra("TimeValue", sb.toString());
                getContext().sendBroadcast(sendIntent);
            }
        });
    }

    public void initContent() {
        yearContent = new String[yearSize];
        for (int i = 0; i < yearContent.length; i++)
            yearContent[i] = String.valueOf(i + minYear);

        monthContent = new String[12];
        for (int i = 0; i < 12; i++) {
            monthContent[i] = String.valueOf(i + 1);
            if (monthContent[i].length() < 2) {
                monthContent[i] = "0" + monthContent[i];
            }
        }

        dayContent = new String[31];
        for (int i = 0; i < 31; i++) {
            dayContent[i] = String.valueOf(i + 1);
            if (dayContent[i].length() < 2) {
                dayContent[i] = "0" + dayContent[i];
            }
        }
        hourContent = new String[24];
        for (int i = 0; i < 24; i++) {
            hourContent[i] = String.valueOf(i);
            if (hourContent[i].length() < 2) {
                hourContent[i] = "0" + hourContent[i];
            }
        }

        minuteContent = new String[60];
        for (int i = 0; i < 60; i++) {
            minuteContent[i] = String.valueOf(i);
            if (minuteContent[i].length() < 2) {
                minuteContent[i] = "0" + minuteContent[i];
            }
        }
        secondContent = new String[60];
        for (int i = 0; i < 60; i++) {
            secondContent[i] = String.valueOf(i);
            if (secondContent[i].length() < 2) {
                secondContent[i] = "0" + secondContent[i];
            }
        }
    }

    public String getTimeValue() {
        return sb.toString();
    }

    public void showDatePickerDialog() {
        this.show();
    }

    public void cancelDatePickerDialog() {
        this.dismiss();
    }

}
