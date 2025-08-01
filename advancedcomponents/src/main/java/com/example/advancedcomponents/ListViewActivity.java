package com.example.advancedcomponents;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.advancedcomponents.adapter.PlanetBaseAdapter;
import com.example.advancedcomponents.bean.Planet;
import com.example.advancedcomponents.util.ToastUtil;

import java.util.List;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private List<Planet> planetList;
    private CheckBox ck_divider;
    private CheckBox ck_selector;
    private ListView lv_planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv_planet = findViewById(R.id.lv_planet);
        planetList = Planet.getDefaultList();
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this, planetList);
        lv_planet.setAdapter(adapter);

        lv_planet.setOnItemClickListener(this);
        ck_divider = findViewById(R.id.ck_divider);
        ck_selector = findViewById(R.id.ck_selector);
        ck_divider.setOnCheckedChangeListener(this);
        ck_selector.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"你选择的是："+planetList.get(position).name);
    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.ck_divider){
            if(ck_divider.isChecked()){
                //从资源文件获得图形对象
                Drawable drawable = getResources().getDrawable(R.color.black,getTheme());
                lv_planet.setDivider(drawable);
                //设置列表视图的分割线高度
                lv_planet.setDividerHeight(10);
            }else{
                lv_planet.setDivider(null);
                lv_planet.setDividerHeight(0);
            }
        } else if (buttonView.getId() == R.id.ck_selector) {
            //显示按压背景
            if(ck_selector.isChecked()){
                //设置列表项的按压状态图形
                lv_planet.setSelector(R.drawable.list_selector);
            }else{
                Drawable drawable = getResources().getDrawable(R.color.white,getTheme());
                lv_planet.setSelector(drawable);
            }
        }
    }
}