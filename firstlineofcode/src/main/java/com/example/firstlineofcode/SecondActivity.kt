package com.example.firstlineofcode

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineofcode.util.ToastUtil

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btnBack = findViewById<Button>(R.id.back)
        btnBack.setOnClickListener(this)
        findViewById<Button>(R.id.show_result).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> finish()
            R.id.show_result->{
                val data = intent.getStringExtra("extra_data")
                ToastUtil.show(this,data)
            }
        }
    }
}