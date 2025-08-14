package BroadcastBestPractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.firstlineofcode.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val forceOffline=findViewById<Button>(R.id.forceOffline)
        forceOffline.setOnClickListener {
            val intent= Intent("BroadcastBestPractice.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

    }
}