package com.example.advancedcomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.advancedcomponents.util.ToastUtil;

public class SpinnerDropDownActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //定义下拉列表需要显示的文本数组
    private final static String[] starArray = {"水星","金星","火星","冥王星","天王星","海王星","地球","木星","土星"};

    private Spinner sp_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_drop_down);
        sp_dialog = findViewById(R.id.sp_dialog);
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(this,R.layout.item_select,starArray);
       //设置下拉框标题，对话框模式菜显示标题，下拉模式不显示标题
        sp_dialog.setPrompt("请选择行星");
        sp_dialog.setAdapter(startAdapter);
        //设置下拉列表默认显示第一项
        sp_dialog.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp_dialog.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"你选择的是"+starArray[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}