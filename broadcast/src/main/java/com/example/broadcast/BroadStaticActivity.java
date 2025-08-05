package com.example.broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BroadStaticActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_SHOCK = "com.example.broadcast.shock";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_static);
        findViewById(R.id.btn_send_shock).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Android8.0之后删除了大部分静态注册，防止推出App后仍在接受广播
        //为了让应用能够继续接受静态广播，需要给静态注册的广播指定包名
        String fullName = "com.example.broadcast.receiver.ShockReceiver";
        Intent intent = new Intent(ACTION_SHOCK);
        //发送静态广播之时，需要通过setComponent方法指定接收器的全名
        intent.setComponent(new ComponentName(this,fullName ));
        sendBroadcast(intent);
    }
}