package com.vanlang.kiemtra

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ánh xạ view bằng findViewById (không dùng ViewBinding)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvStatus = findViewById(R.id.tvStatus)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString()

            // Thứ tự kiểm tra: trống -> sai định dạng email -> mật khẩu < 6 ký tự
            val message = when {
                email.isEmpty() && password.isEmpty() -> R.string.error_both_empty
                email.isEmpty() -> R.string.error_email_empty
                password.isEmpty() -> R.string.error_password_empty
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> R.string.error_email_format
                password.length < 6 -> R.string.error_password_short
                else -> R.string.validation_success
            }
            tvStatus.text = getString(message)

            if (message == R.string.validation_success) {
                // Intent tường minh sang ProfileActivity, gửi kèm email qua putExtra
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(ProfileActivity.EXTRA_EMAIL, email)
                startActivity(intent)
            }
        }
    }
}
