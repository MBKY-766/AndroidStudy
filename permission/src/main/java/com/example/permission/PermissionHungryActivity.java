package com.example.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.permission.util.PermissionUtil;
import com.example.permission.util.ToastUtil;

public class PermissionHungryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };
    private static final int REQUEST_CODE_ALL = 1;

    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);
        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        PermissionUtil.checkPermission(this, PERMISSIONS, REQUEST_CODE_ALL);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_contact) {
            PermissionUtil.checkPermission(this,new String[] {PERMISSIONS[0],PERMISSIONS[1]},REQUEST_CODE_CONTACTS);
        } else if (v.getId() == R.id.btn_sms) {
            PermissionUtil.checkPermission(this,new String[] {PERMISSIONS[2],PERMISSIONS[3]},REQUEST_CODE_SMS);
        }
    }

    //请求权限时
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ALL:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("permission", "所有权限获取成功");
                } else {
                    //部分权限获取失败
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            //判断是什么权限没有获取成功
                            switch(permissions[i]){
                                case Manifest.permission.READ_CONTACTS:
                                case Manifest.permission.WRITE_CONTACTS:
                                    ToastUtil.show(this, "通讯录权限获取失败");
                                    jumpToSettings();
                                    break;
                                case Manifest.permission.SEND_SMS:
                                case Manifest.permission.READ_SMS:
                                    ToastUtil.show(this, "收发短信权限获取失败");
                                    jumpToSettings();
                            }
                        }
                    }
                }
                break;
            case REQUEST_CODE_CONTACTS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("permission", "通讯录权限获取成功");
                } else {
                    ToastUtil.show(this, "通讯录权限获取失败");
                    jumpToSettings();
                }
                break;
            case REQUEST_CODE_SMS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("permission", "收发短信权限获取成功");
                } else {
                    ToastUtil.show(this, "收发短信权限获取失败");
                    jumpToSettings();

                }
                break;
        }
    }

    //跳转到应用设置页面
    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}