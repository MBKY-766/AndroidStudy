package NotificationTest

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.firstlineofcode.R

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val sendNotice = findViewById<Button>(R.id.sendNotice)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }
}