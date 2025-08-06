package com.example.broadcast.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    //定义一个ACTION
    public static final String ACTION_ALARM = "com.example.broadcast.alarm";
    public static int count=0;
    private final Context mContext;

    public AlarmReceiver(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(ACTION_ALARM)) {
            Log.d("broadcast", "收到一个闹钟广播"+count);
            count++;
            sendAlarmBroadcast();
        }
    }

    //发送闹钟广播
    public void sendAlarmBroadcast() {
        Intent intent = new Intent(ACTION_ALARM);
        //用于广播的延迟意图
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        //计算发送时间
        long triggerAtTime = System.currentTimeMillis() + 1000;
        //从系统服务中获取闹钟管理器
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //允许在空闲时发送广播，Android6.0之后新增的方法
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        } else {
            //设置一次性闹钟，延迟若干秒后，携带延迟意图发送闹钟广播
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        }
//        mContext.sendBroadcast(intent);
    }
}
