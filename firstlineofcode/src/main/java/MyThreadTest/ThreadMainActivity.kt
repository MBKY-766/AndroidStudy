package MyThreadTest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineofcode.R
import kotlin.concurrent.thread

class ThreadMainActivity : AppCompatActivity() {
    val updateText = 1
    lateinit var textView: TextView
    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText -> textView.text = "Nice to meet you"
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_main)
        val changeTextBtn = findViewById<Button>(R.id.changeTextBtn)
        textView = findViewById(R.id.textView)
        changeTextBtn.setOnClickListener {
            thread{
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg) // 将Message对象发送出去
            }
        }

    }
}