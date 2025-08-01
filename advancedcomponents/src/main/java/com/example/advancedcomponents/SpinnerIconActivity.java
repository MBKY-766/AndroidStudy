package com.example.advancedcomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.advancedcomponents.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //定义下拉列表需要显示的行星图标数组
    private static final int[] iconArr= {
            R.drawable.shuixing,R.drawable.huoxing
    };
    //定义下拉列表所要展示的行星名称数组
    private static final String[] starArr={"水星","火星"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);
        
        //声明一个映射对象的列表，用于保存行星的图标雨名称配对信息
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < iconArr.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("icon",iconArr[i]);
            item.put("name",starArr[i]);
            list.add(item);
        }
        SimpleAdapter startAdapter = new SimpleAdapter(this,list,R.layout.item_simple,
                new String[]{"icon","name"},
                new int[]{R.id.iv_icon,R.id.tv_name});
        Spinner sp_icon = findViewById(R.id.sp_icon);
        sp_icon.setAdapter(startAdapter);
        sp_icon.setSelection(0);
        sp_icon.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"你选择的是"+starArr[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}