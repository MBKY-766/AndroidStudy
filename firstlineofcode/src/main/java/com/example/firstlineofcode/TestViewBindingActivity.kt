package com.example.firstlineofcode

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstlineofcode.databinding.ActivityMainBinding
import com.example.firstlineofcode.databinding.ActivityTestViewBindingBinding

class TestViewBindingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTestViewBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnControl.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnControl -> {
                binding.pbTest.progress += 10
            }
        }
    }
}