package com.example.firstlineofcode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri
import com.example.firstlineofcode.databinding.ActivityMainBinding
import com.example.firstlineofcode.util.ToastUtil

class FirstKotlinActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin)
        val btn_test = findViewById<Button>(R.id.btn_test)
        btn_test.setOnClickListener {
            Toast.makeText(this, "Hello Kotlin", Toast.LENGTH_SHORT).show()
        }
        val btnJump = findViewById<Button>(R.id.jump)
        btnJump.setOnClickListener(this)
        val implicitJump = findViewById<Button>(R.id.implicit_jump)
        implicitJump.setOnClickListener(this)
        findViewById<Button>(R.id.open_browser).setOnClickListener(this)
        findViewById<Button>(R.id.call).setOnClickListener(this)
        findViewById<Button>(R.id.jump_with_data).setOnClickListener(this)
        findViewById<Button>(R.id.jump_return_data).setOnClickListener(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.jump -> {
                //显式意图跳转
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }

            R.id.implicit_jump -> {
                //隐式意图跳转
                val intent = Intent("com.example.firstlineofcode.ACTION_START")
                intent.addCategory("com.example.firstlineofcode.MY_CATEGORY")
                startActivity(intent)

            }

            R.id.open_browser -> {
                //开启浏览器
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = "https://www.baidu.com".toUri()
                startActivity(intent)

            }

            R.id.call -> {
                //拨打电话
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = "tel:100000".toUri()
                startActivity(intent)
            }

            R.id.jump_with_data -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("extra_data", "Hello")
                startActivity(intent)
            }

            R.id.jump_return_data -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //requestCode:状态码-用于确定数据来源
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                ToastUtil.show(this,returnedData)
            }
        }
    }
}