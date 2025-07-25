package com.example.shopping01.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shopping01.enity.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shopping.db";
    //商品信息表
    private static final String TABLE_GOODS_INFO = "goods_info";
    //购物车信息表
    private static final String TABLE_CART_INFO = "cart_info";

    private static final int DB_VERSION = 1;
    private static ShoppingDBHelper helper = null;
    private SQLiteDatabase RDB = null;
    private SQLiteDatabase WDB = null;


    private ShoppingDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //利用单例模式获取数据库帮助器的唯一实例
    public static ShoppingDBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new ShoppingDBHelper(context);
        }
        return helper;
    }

    //打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (RDB == null || RDB.isOpen()) {
            RDB = helper.getReadableDatabase();
        }
        return RDB;
    }

    //打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (WDB == null || WDB.isOpen()) {
            WDB = helper.getWritableDatabase();

        }
        return WDB;
    }

    //关闭数据库连接
    public void closeLink() {
        if (RDB != null && RDB.isOpen()) {
            RDB.close();
            RDB = null;
        }
        if (WDB != null && WDB.isOpen()) {
            WDB.close();
            WDB = null;
        }

    }

    //创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建商品信息表
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_GOODS_INFO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name VARCHAR NOT NULL," +
                " description VARCHAR NOT NULL," +
                " picture_path VARCHAR NOT NULL," +
                " price FLOAT NOT NULL);";
        db.execSQL(sql);
        //创建购物车信息表
        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CART_INFO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " goods_id INTEGER NOT NULL," +
                " count INTEGER NOT NULL);";

        db.execSQL(sql);
    }


    //    数据库的版本更新时触发
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //添加多条商品信息
    public void insertGoodsInfos(List<GoodsInfo> list) {
        //开启事物—插入多条记录
        try {
            WDB.beginTransaction();
            for (GoodsInfo info : list) {
                ContentValues values = new ContentValues();
                values.put("name", info.name);
                values.put("description", info.description);
                values.put("price", info.price);
                values.put("picture_path", info.picturePath);
                WDB.insert(TABLE_GOODS_INFO, null, values);
            }
            WDB.setTransactionSuccessful();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            WDB.endTransaction();
        }
    }

    //查询所有的商品信息
    public List<GoodsInfo> queryAllGoodsInfo() {
        String sql = "select * from " + TABLE_GOODS_INFO;
        List<GoodsInfo> list = new ArrayList<>();
        Cursor cursor = RDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            GoodsInfo info = new GoodsInfo();
            info.id = cursor.getInt(0);
            info.name = cursor.getString(1);
            info.description = cursor.getString(2);
            info.picturePath = cursor.getString(3);
            info.price = cursor.getFloat(4);
            list.add(info);

        }
        cursor.close();
        return list;
    }
}