# TimePicker 

## result map
![result map](https://github.com/Billshuai/TimePicker/blob/master/img/1.png) --- ![result map](https://github.com/Billshuai/TimePicker/blob/master/img/2.png) --- ![result map](https://github.com/Billshuai/TimePicker/blob/master/img/3.png)

## Integration
Add the dependencies to your gradle file:

Download the project,and Copy the timepicker file to your project,finally,

dependencies {
     compile project(':timepicker')
}

OR

dependencies {
     compile 'com.billshuai.timepicker:timepicker:1.0.1'
}
## XML Usage
```
<com.billshuai.timepicker.wheel.TextTimePicker
        android:id="@+id/pick_bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="点击选择时间"
        android:textColor="#FF0000"
        android:textSize="20sp"/>
```
## Java Usage
```
TextTimePicker pick_bt;
pick_bt = (TextTimePicker) findViewById(R.id.pick_bt);
pick_bt.setOnTimePickClickListener(new OnTimePickerClickListener() {
            @Override
            public void onTimePickerClick(String timeValue) {
                Log.v("time", timeValue);
            }
        });
```

## Thanks
Yuri Kanivets
