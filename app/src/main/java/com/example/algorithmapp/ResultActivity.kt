package com.example.algorithmapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 10)
        val percentage = (score.toFloat() / total.toFloat() * 100).toInt()

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvPercentage = findViewById<TextView>(R.id.tvPercentage)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val btnAnswers = findViewById<Button>(R.id.btnAnswers)
        val btnHome = findViewById<Button>(R.id.btnHome)

        tvScore.text = "$score / $total"
        tvPercentage.text = "$percentage%"
        
        tvMessage.text = when {
            percentage >= 90 -> "Өте жақсы! Сіз алгоритмдерді тамаша білесіз! 🎉"
            percentage >= 70 -> "Жақсы нәтиже! Сіз материалды игердіңіз! 👍"
            percentage >= 50 -> "Жаман емес! Бірақ тағы жаттығу керек. 📚"
            else -> "Материалды қайта оқып шығыңыз. Сіз мықтысыз! 💪"
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAnswers.setOnClickListener {
            val intent = Intent(this, AnswersActivity::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
