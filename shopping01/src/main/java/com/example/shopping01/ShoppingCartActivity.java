package com.example.shopping01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopping01.Util.ToastUtil;
import com.example.shopping01.database.ShoppingDBHelper;
import com.example.shopping01.enity.CartInfo;
import com.example.shopping01.enity.GoodsInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_count;
    private LinearLayout ll_cart;
    private ShoppingDBHelper dbHelper;
    //购物车中的商品信息列表
    private List<CartInfo> mCartlist;
    //声明一个根据商品编号查找商品信息的映射，把商品信息缓存起来，这样不用每一次都去查询数据库
    private Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        TextView tv_title = findViewById(R.id.title_tv);
        ll_cart = findViewById(R.id.ll_cart);
        tv_title.setText("购物车");
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_count = findViewById(R.id.count_tv);

        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        dbHelper = ShoppingDBHelper.getInstance(this);
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        ll_empty = findViewById(R.id.ll_empty);
        ll_content = findViewById(R.id.ll_content);
    }

    @Override
    protected void onResume() {

        super.onResume();
        showCart();
        showCount();
    }

    //展示购物车中的商品列表
    private void showCart() {
        //移除下面的所有子视图
        ll_cart.removeAllViews();

        //查询购物车数据库中所有的商品记录
        mCartlist = dbHelper.queryAllCartInfo();

        if (mCartlist.isEmpty()) {
            return;
        }
        for (CartInfo info : mCartlist) {
            //根据商品编号查询商品数据库中的商品记录
            GoodsInfo goods = dbHelper.queryGoodsInfoById(info.goodsId);

            mGoodsMap.put(info.goodsId, goods);

            //获取布局文件item_cart.xml的根视图
            View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            TextView tv_count = view.findViewById(R.id.tv_count);
            TextView tv_price = view.findViewById(R.id.tv_price);
            TextView tv_sum = view.findViewById(R.id.tv_sum);

            iv_thumb.setImageURI(Uri.parse(goods.picturePath));
            tv_name.setText(goods.name);
            tv_desc.setText(goods.description);
            tv_count.setText(String.valueOf(info.count));
            tv_price.setText(String.valueOf((int) goods.price));
            //设置商品总价
            tv_sum.setText(String.valueOf((int) (info.count * goods.price)));

            //给商品行添加长按事件，长按商品行就删除该商品
            view.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage("是否从购物车删除" + goods.name + "?");
                builder.setPositiveButton("是", (dialog, which) -> {
                    //移除当前视图
                    ll_cart.removeView(v);
                    //从数据库中删除该商品
                    deleteGoods(info);
                });
                builder.setNegativeButton("否", null);
                builder.create().show();
                return true;
            });

            //给商品行添加点击事件，点击商品行跳转到商品详情页面
            view.setOnClickListener(v -> {
                Intent intent = new Intent(ShoppingCartActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id",goods.id);
                startActivity(intent);
            });
            //往购物车列表添加商品行
            ll_cart.addView(view);
        }

        //重新计算购物车的商品总金额
        refreshTotalPrice();
    }

    private void deleteGoods(CartInfo info) {
        MyApplication.getInstance().goodsCount -= info.count;
        //从购物车的数据库中删除商品
        dbHelper.deleteCartInfoByGoodsId(info.goodsId);
        //从购物车的列表中删除商品
        CartInfo removed = null;
        for (CartInfo cartInfo : mCartlist) {
            if (cartInfo.goodsId == info.goodsId) {
                removed = cartInfo;
                break;
            }
        }
        mCartlist.remove(removed);
        //显示最新的商品数量
        showCount();
        ToastUtil.show(this, "已从购物车中删除" + mGoodsMap.get(info.goodsId).name);
        mGoodsMap.remove(info.goodsId);
        //刷新购物车中所有商品的总金额
        refreshTotalPrice();
    }
    //显示购物车图标中的商品数量

    private void showCount() {
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodsCount));
        if (MyApplication.getInstance().goodsCount == 0) {
            //购物车中没有商品，显示"空空如也"
            ll_empty.setVisibility(View.VISIBLE);
            //隐藏标题
            ll_content.setVisibility(View.GONE);
            ll_cart.removeAllViews();
        } else {
            ll_content.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
        }
    }

    //重新计算购物车中的商品总金额
    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo info : mCartlist) {
            GoodsInfo goods = mGoodsMap.get(info.goodsId);
            totalPrice += goods.price * info.count;
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_iv) {
            //点击返回图标
            //关闭当前页面
            finish();
        } else if (v.getId() == R.id.btn_shopping_channel) {
            //逛逛手机商城
            //跳转到手机商城
            //跟返回页面一样，可以结束当前页面，也可以跳转到手机商场页面
            //采用第二种

            Intent intent = new Intent(this, ShoppingChannelActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//避免出现重复页面
            startActivity(intent);
//            finish();
        } else if (v.getId() == R.id.btn_clear) {
            //清空购物车-删除所有商品信息
            //从购物车的数据库中删除商品
            dbHelper.deleteAllCartInfo();
            //商品数量归零
            MyApplication.getInstance().goodsCount = 0;
//            //从购物车的列表中删除商品
//            CartInfo removed = null;
//            for (CartInfo cartInfo : mCartlist) {
//                removed = cartInfo;
//                mCartlist.remove(removed);
//            }
            //显示最新的商品数量
            showCount();
            ToastUtil.show(this, "购物车已清空");
//            //刷新购物车中所有商品的总金额
//            refreshTotalPrice();
        } else if (v.getId()==R.id.btn_settle) {
            //结算-提示暂未开通
            ToastUtil.show(this,"支付功能暂未开通，敬请期待～");
        }
    }
}