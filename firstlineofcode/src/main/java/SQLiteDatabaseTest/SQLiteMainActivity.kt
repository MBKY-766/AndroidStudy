package SQLiteDatabaseTest

import SQLiteDatabaseTest.databaseHelper.MyDatabaseHelper
import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineofcode.R

class SQLiteMainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_main)
        val createDatabase = findViewById<Button>(R.id.createDatabase)
        val addData = findViewById<Button>(R.id.addData)
        val updateData = findViewById<Button>(R.id.updateData)
        val deleteData = findViewById<Button>(R.id.deleteData)
        val queryData = findViewById<Button>(R.id.queryData)
        val replaceData = findViewById<Button>(R.id.replaceData)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                //组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)

            }
            db.insert("Book", null, values1)//插入第一条数据
            val values2 = ContentValues().apply {
                //组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("price", 10.99)
            }
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            //查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    //遍历cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("query", "book name is $name")
                    Log.d("query", "book author is $author")
                    Log.d("query", "book pages is $pages")
                    Log.d("query", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() //开启事务
            try {
                db.delete("Book",null,null)
                if (true) {
                    //手动抛出一个异常，让事务失败
                    throw NullPointerException()
                    }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() //事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() //结束事务
            }
        }

    }
}