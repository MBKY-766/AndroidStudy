package com.example.firstlineofcode

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }

            R.id.implicit_jump ->{
                val intent = Intent("com.example.firstlineofcode.ACTION_START")
                intent.addCategory("com.example.firstlineofcode.MY_CATEGORY")
                startActivity(intent)

            }
        }
    }
}