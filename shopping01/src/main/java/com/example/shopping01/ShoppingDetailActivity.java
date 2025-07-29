package com.example.shopping01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopping01.Util.ToastUtil;
import com.example.shopping01.database.ShoppingDBHelper;
import com.example.shopping01.enity.GoodsInfo;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_tv;
    private TextView tv_count;
    private TextView tv_goods_price;
    private TextView tv_goods_desc;
    private ImageView tv_goods_picture;
    private ShoppingDBHelper dbHelper;
    private int mGoodsId;
    private int count;
    private TextView count_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);
        title_tv = findViewById(R.id.title_tv);
        tv_count = findViewById(R.id.count_tv);
        tv_goods_price = findViewById(R.id.tv_goods_price);
        tv_goods_desc = findViewById(R.id.tv_goods_desc);
        tv_goods_picture = findViewById(R.id.iv_goods_picture);
        dbHelper = ShoppingDBHelper.getInstance(this);
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.cart_iv).setOnClickListener(this);
        findViewById(R.id.btn_add_to_cart).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showDetail();
    }
//    private void showCartInfoTotal() {
//        int count = dbHelper.countCartInfo();
//        MyApplication.getInstance().goodsCount=count;
//        count_tv.setText(String.valueOf(count));
//    }

    private void showDetail() {
        //获取上一个页面传来的商品编号
        mGoodsId = getIntent().getIntExtra("goods_id", 0);
        if (mGoodsId > 0) {
            //根据商品编号查询商品数据库中的商品记录
            GoodsInfo info = dbHelper.queryGoodsInfoById(mGoodsId);
            title_tv.setText(info.name);
            tv_goods_price.setText(String.valueOf((int) info.price));
            tv_goods_desc.setText(info.description);
            tv_goods_picture.setImageURI(Uri.parse(info.picturePath));
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_iv) {
            //结束当前页面
            finish();
        } else if (v.getId() == R.id.cart_iv) {
            //跳转到购物车页面
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btn_add_to_cart) {
            addToCart(mGoodsId);
        }
    }

    //将指定商品加入购物车
    private void addToCart(int goodsId) {
        //购物车数量+1
        int count = ++MyApplication.getInstance().goodsCount;
        //加入购物车
        tv_count.setText(String.valueOf(count));
        dbHelper.insertCartInfo(goodsId);
        ToastUtil.show(this, "成功添加到购物车！");

    }
}