package com.example.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.broadcast.MyAlarmActivity;

public class MyReceiverA extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(MyAlarmActivity.MY_ACTION)) {
            //一旦接收到标准广播，马上触发接收器的onReceive方法
            Log.d("n","接收器A收到一个广播");

        }
    }
}
