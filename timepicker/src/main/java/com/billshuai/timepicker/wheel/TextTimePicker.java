package com.billshuai.timepicker.wheel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.billshuai.timepicker.R;

/**
 * Created by Billshuai on 2016/8/11.
 */
public class TextTimePicker extends TextView {

    TimePickerDialog timePickerDialog;
    OnTimePickerClickListener onTimePickClickListener;
    public TextTimePicker(Context context) {
        super(context);
        registReceiver();
    }

    public TextTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        registReceiver();
    }

    public TextTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                if (onTimePickClickListener != null) {
                    timePickerDialog = new TimePickerDialog(getContext(), R.style.TimePickerDialog, this, R.layout.time_picker);
                    timePickerDialog .showDatePickerDialog();
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setOnTimePickClickListener(OnTimePickerClickListener onTimePickClickListener) {
        this.onTimePickClickListener = onTimePickClickListener;
    }

    public  interface OnTimePickerClickListener{
        void onTimePickerClick(String timeValue);
    }
    /**
     * 注册广播
     */
    private void registReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("GetTimeValue");
        getContext().registerReceiver(mReceiver, intentFilter);
    }
    /**
     * 接收广播
     */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("GetTimeValue")) {
                String s = intent.getStringExtra("TimeValue");
                if (onTimePickClickListener != null) {
                    onTimePickClickListener.onTimePickerClick(s);
                }
            }
        }
    };
}
