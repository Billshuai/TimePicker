package com.billshuai.test;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.billshuai.timepicker.wheel.TextTimePicker;
import com.billshuai.timepicker.wheel.TextTimePicker.OnTimePickerClickListener;

public class MainActivity extends Activity {

    TextTimePicker pick_bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pick_bt = (TextTimePicker) findViewById(R.id.pick_bt);
        initContent();
    }

    public void initContent() {
        pick_bt.setOnTimePickClickListener(new OnTimePickerClickListener() {
            @Override
            public void onTimePickerClick(String timeValue) {
                Log.v("time", timeValue);
            }
        });
    }

}