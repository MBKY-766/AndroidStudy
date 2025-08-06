package com.example.broadcast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.broadcast.receiver.MyReceiverA;
import com.example.broadcast.receiver.MyReceiverB;

public class MyAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_ACTION="com.example.broadcast.my";
    private MyReceiverA myReceiverA;
    private MyReceiverB myReceiverB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_alarm);
        Button btn_alarm = findViewById(R.id.btn_alarm);
        //1.给按钮添加事件-发送广播
        btn_alarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //发送有序广播
        //创建事件
        Intent intent = new Intent(MY_ACTION);
        sendOrderedBroadcast(intent,null);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        super.onStart();
        //注册两个接收器
        //接收器A
        myReceiverA = new MyReceiverA();
        IntentFilter filterA  = new IntentFilter(MY_ACTION);
        filterA.setPriority(3);
        registerReceiver(myReceiverA,filterA);

        //接收器B
        myReceiverB = new MyReceiverB();
        IntentFilter filterB = new IntentFilter(MY_ACTION);
        filterB.setPriority(5);
        registerReceiver(myReceiverB,filterB);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销接收器
        unregisterReceiver(myReceiverA);
        unregisterReceiver(myReceiverB);
    }
}