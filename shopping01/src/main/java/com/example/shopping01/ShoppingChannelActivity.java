package com.example.shopping01;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping01.database.ShoppingDBHelper;
import com.example.shopping01.enity.GoodsInfo;

import java.util.List;

public class  ShoppingChannelActivity extends AppCompatActivity {

    //声明一个商品数据库的帮助器对象
    private ShoppingDBHelper dbHelper;
    private GridLayout channel_gl;
    private TextView count_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        TextView title_tv = findViewById(R.id.title_tv);
        title_tv.setText("手机商城");
        count_tv = findViewById(R.id.count_tv);
        channel_gl = findViewById(R.id.gl_channel);
        dbHelper = ShoppingDBHelper.getInstance(this);
        dbHelper.openWriteLink();
        dbHelper.openReadLink();

        //从数据库查询出商品信息，并展示
//        showGoods();
    }

//    private void showGoods() {
//        //商品条目是一个线性布局，设置布局的宽度为屏幕的一半
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
//        //查询商品数据库中的所有商品记录
//        List<GoodsInfo> list = dbHelper.queryAllGoodsInfo();
//        for (GoodsInfo info : list) {
//            //获取布局文件item_goods.xml的根视图
//            View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
//            ImageView thumb_iv = view.findViewById(R.id.thumb_iv);
//            TextView price_tv = findViewById(R.id.price_tv);
//            TextView name_tv = findViewById(R.id.name_tv);
//            //给控件设置值
//            thumb_iv.setImageURI(Uri.parse(info.picturePath));
//            name_tv.setText(info.name);
//            price_tv.setText(String.valueOf(info.price));
//            //把商品视图添加到网格布局
//            channel_gl.addView(view,params);
//
//        }
//    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbHelper.closeLink();
    }
}