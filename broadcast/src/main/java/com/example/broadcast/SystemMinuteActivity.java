package com.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.broadcast.receiver.TimeReceiver;

public class SystemMinuteActivity extends AppCompatActivity {

    private TimeReceiver timeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_minute);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //创建一个分钟变更的广播接收器
        timeReceiver = new TimeReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(timeReceiver, filter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(timeReceiver);
    }

}