package com.example.firstlineofcode

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin)
        val btn_test = findViewById<Button>(R.id.btn_test)
        btn_test.setOnClickListener {
            Toast.makeText(this,"Hello Kotlin",Toast.LENGTH_SHORT).show()
        }
    }
}