package com.example.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.broadcast.BroadOrderActivity;

public class OrderReceiverB extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(BroadOrderActivity.ORDER_ACTION)) {
            Log.d("n","接收器B收到一个有序广播");
            abortBroadcast();//中断广播，此时后面的接收器无法收到广播
        }
    }
}