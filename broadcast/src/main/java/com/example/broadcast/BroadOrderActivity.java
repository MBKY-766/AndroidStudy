package com.example.broadcast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.broadcast.receiver.OrderReceiverA;
import com.example.broadcast.receiver.OrderReceiverB;

public class BroadOrderActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ORDER_ACTION = "com.example.broadcast.order";
    private OrderReceiverA orderReceiverA;
    private OrderReceiverB orderReceiverB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_order);
        findViewById(R.id.btn_send_order).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //创建一个指定动作的意图
        Intent intent = new Intent(ORDER_ACTION);
        sendOrderedBroadcast(intent, null);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        //多个接收器处理有序广播的顺序规则为：
        //1.优先级越大的接收器，越早收到有序广播；
        //2.优先级相同的时候，越早注册的接收器越早收到有序广播
        super.onStart();
        orderReceiverA = new OrderReceiverA();
        IntentFilter filterA = new IntentFilter(ORDER_ACTION);
        filterA.setPriority(8);
        registerReceiver(orderReceiverA, filterA);

        orderReceiverB = new OrderReceiverB();
        IntentFilter filterB = new IntentFilter(ORDER_ACTION);
        filterB.setPriority(10);
        registerReceiver(orderReceiverB, filterB);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(orderReceiverA);
        unregisterReceiver(orderReceiverB);

    }
}