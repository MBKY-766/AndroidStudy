package com.example.advancedcomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.advancedcomponents.adapter.PlanetListWithButtonAdapter;
import com.example.advancedcomponents.bean.Planet;
import com.example.advancedcomponents.util.ToastUtil;

import java.util.List;

public class ListFocusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_focus);
        ListView lv_planet = findViewById(R.id.lv_planet);
        planetList = Planet.getDefaultList();
        PlanetListWithButtonAdapter adapter = new PlanetListWithButtonAdapter(this, planetList);
        lv_planet.setAdapter(adapter);
        lv_planet.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"条目被点击了"+planetList.get(position).name);
    }
}