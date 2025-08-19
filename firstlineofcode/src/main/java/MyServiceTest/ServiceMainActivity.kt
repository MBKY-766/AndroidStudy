package MyServiceTest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineofcode.R

class ServiceMainActivity : AppCompatActivity() {
    lateinit var downloadBinder: MyService.DownloadBinder
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }
        override fun onServiceDisconnected(name: ComponentName) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_main)
        val startServiceBtn = findViewById<Button>(R.id.startServiceBtn)
        val stopServiceBtn = findViewById<Button>(R.id.stopServiceBtn)
        val bindServiceBtn = findViewById<Button>(R.id.bindServiceBtn)
        val unbindServiceBtn = findViewById<Button>(R.id.unbindServiceBtn)
        bindServiceBtn.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent, connection, BIND_AUTO_CREATE) // 绑定Service
        }
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
        startServiceBtn.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            startService(intent) // 启动Service
        }
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent) // 停止Service
        }

    }
}