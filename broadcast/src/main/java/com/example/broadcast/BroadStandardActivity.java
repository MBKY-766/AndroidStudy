package com.example.broadcast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.broadcast.receiver.StandardReceiver;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String STANDARD_ACTION = "com.example.broadcast";
    private StandardReceiver standardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);
        findViewById(R.id.btn_send_standard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //发送标准广播
        Intent intent = new Intent(STANDARD_ACTION);
        sendBroadcast(intent);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        //创建一个意图过滤器，只处理STANDARD_ACTION的广播
        IntentFilter intentFilter = new IntentFilter(STANDARD_ACTION);
        //注册接收器，注册之后才能正常接受广播
        registerReceiver(standardReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //注销接收器，不再接受广播
        unregisterReceiver(standardReceiver);
    }
}