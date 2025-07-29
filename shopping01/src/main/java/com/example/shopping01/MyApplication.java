package com.example.shopping01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping01.Util.FileUtil;
import com.example.shopping01.Util.SharedUtil;
import com.example.shopping01.database.ShoppingDBHelper;
import com.example.shopping01.enity.GoodsInfo;

import java.io.File;
import java.util.List;

public class MyApplication extends AppCompatActivity {

    private static MyApplication instance;
    //购物车中的商品总数量
    public int goodsCount = 0;

    public static MyApplication getInstance() {
        if(instance == null){
            instance =new MyApplication();
        }
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_application);
        //初始化商品信息
        initGoodsInfo();
    }

    private void initGoodsInfo() {
        //获取共享参数保存的是否首次打开参数
        boolean isFirst = SharedUtil.getInstance(this).readBoolean("first", true);
        //获取当前App的私有下载路径
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;

        if (isFirst) {
            //模拟网络图片下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.picture);
                String path = directory + info.id + ".jpeg";
                //往存储卡保存商品图片
                FileUtil.saveImage(path, bitmap);
                //回收位图对象
                bitmap.recycle();
                info.picturePath = path;
            }
            //打开数据库，把商品信息插入到表中
            ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();
            //把是否首次打开写入共享参数
            SharedUtil.getInstance(this).writeBoolean("first", false);
        }
    }
}