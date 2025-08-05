package com.example.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

public class ShockReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals("com.example.broadcast.shock")) {
            //震动
            Log.d("n","收到一个震动广播");
            //从系统当中获取震动管理器
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            //震动1000毫秒
            vibrator.vibrate(1000);

        }
    }
}