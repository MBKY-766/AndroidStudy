package com.example.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.broadcast.BroadStandardActivity;

public class StandardReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //一旦接收到标准广播，马上触发接收器的onReceive方法
        if (intent != null && intent.getAction().equals(BroadStandardActivity.STANDARD_ACTION)) {
            Log.d("n","收到一个标准广播");
        }
    }
}
