package com.example.advancedcomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.advancedcomponents.adapter.ImagePagerAdapter;
import com.example.advancedcomponents.entity.GoodsInfo;
import com.example.advancedcomponents.util.ToastUtil;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<GoodsInfo> mGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mGoodsList = GoodsInfo.getDefaultList();
        ViewPager vp_content = findViewById(R.id.vp_content);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, mGoodsList);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);

    }

    //翻页过程中触发，该方法的三个参数取值说明：第一个参数表示当前页面的序号
    //第二个参数表示页面偏移的百分比，取值为0到1:第三个参数表示页面的偏移距离
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //翻页结束后触发，position表示当前滑倒了哪一个页面
    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this,"您翻到的手机品牌是："+mGoodsList.get(position).name);

    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }
}