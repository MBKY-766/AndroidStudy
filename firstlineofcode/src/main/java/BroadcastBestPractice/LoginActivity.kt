package BroadcastBestPractice

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.firstlineofcode.R

class LoginActivity : BaseActivity() {
    private lateinit var accountEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var rememberPass: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accountEdit = findViewById(R.id.accountEdit)
        passwordEdit = findViewById(R.id.passwordEdit)
        rememberPass = findViewById(R.id.rememberPass)
        val prefs = getPreferences(MODE_PRIVATE)
        val login = findViewById<Button>(R.id.login)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            //将账号密码设置到输入框中，并且保持记住密码
            accountEdit.setText(prefs.getString("account", ""))
            passwordEdit.setText(prefs.getString("password", ""))
            rememberPass.isChecked = true
        }
        //如果账号是admin密码是123456，就认为登陆成功
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            if (account == "admin" && password == "123456") {
                val editor = prefs.edit()
                /*val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()*/
                if (rememberPass.isChecked) {
                    //如果选中，就保存账号和密码
                    editor.putBoolean("remember_password",true)
                    editor.putString("account",account)
                    editor.putString("password",password)
                } else {
                    //否则就清空
                    editor.clear()
                }
                editor.apply()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
            }
        }

    }

}