package com.example.shopping01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping01.Util.ToastUtil;
import com.example.shopping01.database.ShoppingDBHelper;
import com.example.shopping01.enity.GoodsInfo;

import java.util.List;

public class  ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {

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
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.cart_iv).setOnClickListener(this);
        dbHelper = ShoppingDBHelper.getInstance(this);
        dbHelper.openWriteLink();
        dbHelper.openReadLink();

        //从数据库查询出商品信息，并展示
        showGoods();
    }

    private void showGoods() {
        //商品条目是一个线性布局，设置布局的宽度为屏幕的一半
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        //查询商品数据库中的所有商品记录
        List<GoodsInfo> list = dbHelper.queryAllGoodsInfo();

        //移除下面的所有子视图
        channel_gl.removeAllViews();

        for (GoodsInfo info : list) {
            //获取布局文件item_goods.xml的根视图
            View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_price = view.findViewById(R.id.tv_price);
            TextView tv_name = view.findViewById(R.id.tv_name);
            Button btn_add = view.findViewById(R.id.btn_add);
            //给控件设置值
            iv_thumb.setImageURI(Uri.parse(info.picturePath));
            tv_price.setText(String.valueOf((int)(info.price)));
            tv_name.setText(info.name);
            //添加购物车
            btn_add.setOnClickListener(v -> {
                addToCart(info.id,info.name);
            });

            //点击商品图片,跳转到商品详情页面
            iv_thumb.setOnClickListener(v -> {
                Intent intent = new Intent(ShoppingChannelActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id",info.id);
                startActivity(intent);
            });
            //把商品视图添加到网格布局
            channel_gl.addView(view,params);

        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        //当页面获得焦点时会调用
        showCartInfoTotal();
    }

    private void showCartInfoTotal() {
        int count = dbHelper.countCartInfo();
        MyApplication.getInstance().goodsCount=count;
        count_tv.setText(String.valueOf(count));
    }

    //将指定商品加入购物车
    private void addToCart(int id, String name) {
        //购物车数量+1
        int count = ++MyApplication.getInstance().goodsCount;
        //加入购物车
        count_tv.setText(String.valueOf(count));
        dbHelper.insertCartInfo(id);
        ToastUtil.show(this,"已添加一部"+name+"到购物车");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_iv){
            //返回
            finish();
        } else if (v.getId()==R.id.cart_iv) {
            //点击购物车
            //跳转到购物车页面
            Intent intent = new Intent(this,ShoppingCartActivity.class);
            //设置启动标志，避免多次返回同一页面
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }
}