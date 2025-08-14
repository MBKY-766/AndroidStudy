package BroadcastBestPractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firstlineofcode.R

class LoginActivity : BaseActivity() {
    private lateinit var accountEdit: EditText
    private lateinit var passwordEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accountEdit = findViewById(R.id.accountEdit)
        passwordEdit = findViewById(R.id.passwordEdit)

        val login = findViewById<Button>(R.id.login)
        //如果账号是admin密码是123456，就认为登陆成功
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
            }
        }

    }

}