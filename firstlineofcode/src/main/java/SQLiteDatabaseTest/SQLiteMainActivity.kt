package SQLiteDatabaseTest

import SQLiteDatabaseTest.databaseHelper.MyDatabaseHelper
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.example.firstlineofcode.R

class SQLiteMainActivity : AppCompatActivity() {
    var bookId: String? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_main)
        val addData = findViewById<Button>(R.id.addData)
        val updateData = findViewById<Button>(R.id.updateData)
        val deleteData = findViewById<Button>(R.id.deleteData)
        val queryData = findViewById<Button>(R.id.queryData)
        addData.setOnClickListener {
            // 添加数据
            val uri = Uri.parse("content://SQLiteDatabaseTest.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85
            )

            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }
        queryData.setOnClickListener {
            //查询数据
            val uri = Uri.parse("content://SQLiteDatabaseTest.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                }
                close()
            }
        }
        updateData.setOnClickListener {
            // 更新数据 bookId?.let {
            val uri = Uri.parse("content://SQLiteDatabaseTest.provider/ book /$it")
            val values = contentValuesOf(
                "name" to "A Storm of Swords",
                "pages" to 1216, "price" to 24.05
            )
            contentResolver.update(uri, values, null, null)
        }
        deleteData.setOnClickListener {
            // 删除数据 bookId?.let {
            val uri = Uri.parse("content://SQLiteDatabaseTest.provider/ book/$it")
            contentResolver.delete(uri, null, null)
        }
    }


}