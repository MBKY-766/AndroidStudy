package SharedPreferenceTest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineofcode.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val restoreButton = findViewById<Button>(R.id.restoreButton)
        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
            editor.putString("name", "Tom")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()
        }
        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)//第一个参数：文件名，第二个参数：访问模式 private：只有当前程序才能操作
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("SharedPreferencesTest","name is $name")
            Log.d("SharedPreferencesTest","age is $age")
            Log.d("SharedPreferencesTest","married is $married")

        }

    }
}