package com.example.broadcast;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.broadcast.receiver.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarm);
        findViewById(R.id.btn_alarm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //发送广播
        alarmReceiver.sendAlarmBroadcast();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        super.onStart();
        //注册广播
        alarmReceiver = new AlarmReceiver(getApplicationContext());
        //过滤器
        IntentFilter filter = new IntentFilter(AlarmReceiver.ACTION_ALARM);
        registerReceiver(alarmReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(alarmReceiver);

    }
}