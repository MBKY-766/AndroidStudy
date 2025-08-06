package com.example.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.broadcast.MyAlarmActivity;

public class MyReceiverB extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null&&intent.getAction().equals(MyAlarmActivity.MY_ACTION)){
            //接收到广播，打印信息
            Log.d("n","接收器B收到一个广播");
        }
    }
}
