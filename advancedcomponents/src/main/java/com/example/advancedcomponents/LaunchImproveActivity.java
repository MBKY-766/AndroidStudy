package com.example.advancedcomponents;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.advancedcomponents.adapter.LaunchImproveAdapter;

public class LaunchImproveActivity extends AppCompatActivity {

    //声明引导页面的图片数组
    private int[] lanuchImageArray = {
            R.drawable.guide_bg1,R.drawable.guide_bg2,
            R.drawable.guide_bg3
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_improve);
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        LaunchImproveAdapter adapter = new LaunchImproveAdapter(getSupportFragmentManager(), lanuchImageArray);
        vp_launch.setAdapter(adapter);
    }
}