package NotificationTest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.firstlineofcode.R

class NotificationTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_test)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val sendNotice = findViewById<Button>(R.id.sendNotice)
        //判断当前版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "normal",
                "Normal",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        sendNotice.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.edit_bg)
                .setLargeIcon(
                    BitmapFactory.decodeResource(resources,
                        R.drawable.ic_launcher_background))
                .setContentIntent(pi)
                .setAutoCancel(true)//点击后自动关闭通知
                .build()
            manager.notify(1, notification)
        }
    }

}