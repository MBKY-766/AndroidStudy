package com.example.accountbook.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.accountbook.entity.BillInfo;


import java.util.ArrayList;
import java.util.List;

public class BillDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "BillDBHelper";
    private static final String DB_NAME = "bill.db";
    //信息表
    private static final String TABLE_BILLS_INFO = "bill_info";

    private static final int DB_VERSION = 1;
    private static BillDBHelper helper = null;
    private SQLiteDatabase RDB = null;
    private SQLiteDatabase WDB = null;

    private boolean flag = false;


    private BillDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //利用单例模式获取数据库帮助器的唯一实例
    public static BillDBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new BillDBHelper(context);
        }
        return helper;
    }

    //打开数据库的读连接
    public SQLiteDatabase openReadLink() {
//        Log.d(TAG, "openReadLink");
        if (RDB == null || !RDB.isOpen()) {
            RDB = helper.getReadableDatabase();
        }
        return RDB;
    }

    //打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
//        Log.d(TAG, "openWriteLink");
        if (WDB == null || !WDB.isOpen()) {
            WDB = helper.getWritableDatabase();

        }
        return WDB;
    }

    //关闭数据库连接
    public void closeLink() {
//        Log.d(TAG, "closeLink");
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
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_BILLS_INFO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " date VARCHAR NOT NULL," +
                " type INTEGER NOT NULL," +
                " amount DOUBLE NOT NULL," +
                " remark VARCHAR NOT NULL);";
        db.execSQL(sql);

    }


    //    数据库的版本更新时触发
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //保存一条订单记录
    public long save(BillInfo bill) {
        ContentValues values = new ContentValues();
        values.put("date", bill.date);
        values.put("type", bill.type);
        values.put("amount", bill.amount);
        values.put("remark", bill.remark);
        return WDB.insert(TABLE_BILLS_INFO, null, values);
    }

    public List<BillInfo> queryByMonth(String yearMonth) {
        List<BillInfo> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_BILLS_INFO + " WHERE date LIKE '" + yearMonth + "%'";
        Cursor cursor = RDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            BillInfo bill = new BillInfo();
            bill.id = cursor.getInt(0);
            bill.date = cursor.getString(1);
            bill.type = cursor.getInt(2);
            bill.amount = cursor.getDouble(3);
            bill.remark = cursor.getString(4);
            list.add(bill);
        }
        return list;
    }


}