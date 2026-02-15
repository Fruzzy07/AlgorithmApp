package com.example.algorithmapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)

        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Барлық өрістерді толтырыңыз", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Email @gmail.com доменінде болуы керек", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Сохраняем данные пользователя
            val prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            prefs.edit()
                .putString("username", username)
                .putString("email", email)
                .putString("password", password)
                .putBoolean("isLoggedIn", true) // сразу залогиниваем
                .apply()

            Toast.makeText(this, "Тіркелу сәтті аяқталды!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
            finishAffinity()
        }

        tvGoToLogin.setOnClickListener {
            finish()
        }
    }
}
