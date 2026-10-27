package com.vanlang.kiemtra

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    companion object {
        // Key dùng chung với LoginActivity để tránh gõ sai chuỗi
        const val EXTRA_EMAIL = "extra_email"
    }

    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ánh xạ view bằng findViewById (không dùng ViewBinding)
        tvEmail = findViewById(R.id.tvEmail)
        btnLogout = findViewById(R.id.btnLogout)

        // Nhận email từ Intent do LoginActivity gửi sang
        tvEmail.text = intent.getStringExtra(EXTRA_EMAIL)

        // Log Out: đóng màn hình hiện tại, quay về LoginActivity
        btnLogout.setOnClickListener { finish() }
    }
}
