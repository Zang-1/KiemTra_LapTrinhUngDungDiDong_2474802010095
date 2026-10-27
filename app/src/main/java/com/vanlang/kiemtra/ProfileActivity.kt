package com.vanlang.kiemtra

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var lvSkills: ListView

    // Dữ liệu mẫu: danh sách môn học / kỹ năng
    private val skills = listOf(
        "Kotlin Programming",
        "XML Layout Design",
        "Git/GitHub Management",
        "Intent Processing",
        "Android Architecture"
    )

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
        lvSkills = findViewById(R.id.lvSkills)

        // Nhận email từ Intent do LoginActivity gửi sang
        tvEmail.text = intent.getStringExtra(EXTRA_EMAIL)

        // Log Out: đóng màn hình hiện tại, quay về LoginActivity
        btnLogout.setOnClickListener { finish() }

        // Đổ dữ liệu vào ListView bằng ArrayAdapter với layout mẫu simple_list_item_1
        lvSkills.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, skills)

        // Chạm vào một môn -> Toast hiện đúng tên môn đó
        lvSkills.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, getString(R.string.skill_selected, skills[position]), Toast.LENGTH_SHORT).show()
        }
    }
}
