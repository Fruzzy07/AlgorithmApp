package com.example.algorithmapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

class TestActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var score = 0
    
    private val questions = listOf(
        Question(
            "Алгоритм дегеніміз не?",
            listOf("Есепті шешудің нақты реті", "Компьютерлік бағдарлама", "Математикалық формула", "Мәліметтер базасы"),
            0
        ),
        Question(
            "Қай алгоритм түрінде командалар тек бір рет орындалады?",
            listOf("Циклдік", "Сызықтық", "Тармақталған", "Рекурсивті"),
            1
        ),
        Question(
            "IF-ELSE конструкциясы қай алгоритмде қолданылады?",
            listOf("Сызықтық", "Циклдік", "Тармақталған", "Барлығында"),
            2
        ),
        Question(
            "Циклдік алгоритмнің басты ерекшелігі не?",
            listOf("Бір рет орындалады", "Командалар қайталанады", "Шарт қолданылады", "Жылдам жұмыс істейді"),
            1
        ),
        Question(
            "Тармақталған алгоритмде не тексеріледі?",
            listOf("Цикл саны", "Шарт", "Айнымалы түрі", "Программа коды"),
            1
        ),
        Question(
            "FOR циклы қай алгоритм түріне жатады?",
            listOf("Сызықтық", "Тармақталған", "Циклдік", "Қарапайым"),
            2
        ),
        Question(
            "Сызықтық алгоритмнің мысалы қайсы?",
            listOf("Санды тексеру", "1-ден 10-ға дейін санау", "Екі санды қосу", "Массивті іздеу"),
            2
        ),
        Question(
            "Қай алгоритм шарттан тәуелді орындалады?",
            listOf("Сызықтық", "Тармақталған", "Барлық алгоритмдер", "Ешқайсысы"),
            1
        ),
        Question(
            "WHILE циклы не үшін қолданылады?",
            listOf("Шартты тексеру", "Командаларды қайталау", "Мәнді сақтау", "Есепті аяқтау"),
            1
        ),
        Question(
            "Қандай алгоритм ең қарапайым болып саналады?",
            listOf("Циклдік", "Тармақталған", "Сызықтық", "Рекурсивті"),
            2
        )
    )

    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnNext: Button
    private lateinit var radioButtons: List<RadioButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnNext = findViewById(R.id.btnNext)
        
        radioButtons = listOf(
            findViewById(R.id.radioOption1),
            findViewById(R.id.radioOption2),
            findViewById(R.id.radioOption3),
            findViewById(R.id.radioOption4)
        )

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        loadQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        tvQuestionNumber.text = "Сұрақ ${currentQuestionIndex + 1}/10"
        tvQuestion.text = question.question
        
        question.options.forEachIndexed { index, option ->
            radioButtons[index].text = option
        }
        
        radioGroup.clearCheck()
        btnNext.text = if (currentQuestionIndex == questions.size - 1) "Аяқтау" else "Келесі"
    }

    private fun checkAnswer() {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            return
        }

        val selectedIndex = radioButtons.indexOfFirst { it.id == selectedId }
        if (selectedIndex == questions[currentQuestionIndex].correctAnswer) {
            score++
        }

        currentQuestionIndex++
        
        if (currentQuestionIndex < questions.size) {
            loadQuestion()
        } else {
            showResults()
        }
    }

    private fun showResults() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)
        finish()
    }
}
