package com.example.broadcast;

import android.annotation.SuppressLint;
import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReturnDesktopActivity extends AppCompatActivity {

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_desktop);
        DesktopReceiver desktopReceiver = new DesktopReceiver();
        registerReceiver(desktopReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

    }

    //在进入画中画模式或退出画中画模式时触发
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if(isInPictureInPictureMode){
            //记录日志—进入画中画模式
            Log.d("broadcast","进入画中画模式");
        }else{
            //记录日志-退出画中画模式
            Log.d("broadcast","退出画中画模式");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播接收器
        unregisterReceiver(new DesktopReceiver());
    }

    //定义一个返回到桌面的广播接收器
    private class DesktopReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra("reason");
                if (reason.equals("homekey")||reason.equals("recentapps")) {
                    //判断当前Android版本
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                    &&!isInPictureInPictureMode()) {
                        //开启画中画模式
                        //创建画中画模式的参数构建器
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        //设置宽高比例值,第一个参数表示分子，第二个参数表示分母
                        //下面的10/5=2，表示画中画窗口的宽度是高度的2倍
                        //设置画中画窗口的宽高比例
                        Rational ration = new Rational(10, 5);
                        builder.setAspectRatio(ration);
                        //开启画中画模式
                        enterPictureInPictureMode(builder.build());

                    }

                }

            }
        }
    }
}