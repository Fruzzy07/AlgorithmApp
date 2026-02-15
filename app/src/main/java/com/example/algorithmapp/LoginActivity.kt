package com.example.algorithmapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)

        // Автовход, если пользователь уже залогинен
        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvGoToRegister = findViewById<TextView>(R.id.tvGoToRegister)

        // Подставляем сохранённые email и password для автозаполнения
        etEmail.setText(prefs.getString("email", ""))
        etPassword.setText(prefs.getString("password", ""))

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email мен парольді енгізіңіз", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Email @gmail.com доменінде болуы керек", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedEmail = prefs.getString("email", "")
            val savedPassword = prefs.getString("password", "")

            if (email != savedEmail || password != savedPassword) {
                Toast.makeText(this, "Email немесе пароль дұрыс емес", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Устанавливаем флаг, что пользователь вошёл
            prefs.edit().putBoolean("isLoggedIn", true).apply()

            Toast.makeText(this, "Кіру сәтті аяқталды!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EMAIL", email)
            intent.putExtra("USERNAME", prefs.getString("username", "Пайдаланушы"))
            startActivity(intent)
            finish()
        }

        tvGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
