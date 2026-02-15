package com.example.algorithmapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FindErrorGameActivity : AppCompatActivity() {

    private lateinit var tvCodeSnippet: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnCheckError: Button
    private lateinit var btnNext: Button
    private lateinit var btnBack: ImageView
    private lateinit var tvResult: TextView

    // Result layout
    private lateinit var resultLayout: LinearLayout
    private lateinit var tvScore: TextView
    private lateinit var btnRetry: Button
    private lateinit var btnHome: Button

    private val algorithmSequence = listOf("linear", "branching", "cyclic")
    private var currentIndex = 0
    private var score = 0

    // Примеры кода по алгоритмам
    private val codeExamples = mapOf(
        "linear" to listOf(
            "a = 5\nb = 10\nc = a + b\nPRINT c\nEND",
            "x = 3\ny = 7\nz = x * y\nPRINT z\nEND"
        ),
        "branching" to listOf(
            "IF number > 0\n    PRINT 'Positive'\nELSE\n    PRINT 'Negative'\nIF"
        ),
        "cyclic" to listOf(
            "FOR i = 1 TO 5\n    PRINT 'Сан: ' + i\nEND FOR",
            "k = 1\nWHILE k <= 3\n    PRINT 'Қайталау: ' + k\n    k = k + 1\nEND WHILE"
        )
    )

    // Правильные ответы
    private val correctAnswers = mapOf(
        "linear" to listOf("NONE", "NONE"), // примеры без ошибки
        "branching" to listOf("END IF"),
        "cyclic" to listOf("NEXT i", "END WHILE")
    )

    private var currentExampleIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_error_game)

        tvCodeSnippet = findViewById(R.id.tvCodeSnippet)
        etAnswer = findViewById(R.id.etAnswer)
        btnCheckError = findViewById(R.id.btnCheckError)
        btnBack = findViewById(R.id.btnBack)
        tvResult = findViewById(R.id.tvResult)

        resultLayout = findViewById(R.id.resultLayout)
        tvScore = findViewById(R.id.tvScore)
        btnRetry = findViewById(R.id.btnRetry)
        btnHome = findViewById(R.id.btnHome)

        // Кнопка "Next Example"
        btnNext = Button(this).apply {
            text = "➡ Келесі"
            isEnabled = false
        }
        findViewById<LinearLayout>(R.id.rootLayout).addView(btnNext)

        btnBack.setOnClickListener { finish() }

        // Запуск первого примера
        startGame()

        btnCheckError.setOnClickListener { checkAnswer() }

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < algorithmSequence.size) {
                startGame()
            } else {
                showFinalScoreScreen()
            }
        }

        btnRetry.setOnClickListener {
            resultLayout.visibility = View.GONE
            startGame(resetScore = true)
        }

        btnHome.setOnClickListener { finish() }
    }

    private fun startGame(resetScore: Boolean = false) {
        if (resetScore) score = 0
        if (currentIndex >= algorithmSequence.size) currentIndex = 0

        val type = algorithmSequence[currentIndex]

        // Выбираем случайный пример из списка
        val examples = codeExamples[type]!!
        currentExampleIndex = (examples.indices).random()
        tvCodeSnippet.text = examples[currentExampleIndex]

        etAnswer.setText("")
        btnNext.isEnabled = false
        tvResult.text = ""
        btnCheckError.isEnabled = true

        // Показываем игровой контент
        tvCodeSnippet.visibility = View.VISIBLE
        etAnswer.visibility = View.VISIBLE
        btnCheckError.visibility = View.VISIBLE
        btnNext.visibility = View.VISIBLE
        tvResult.visibility = View.VISIBLE
    }

    private fun checkAnswer() {
        val type = algorithmSequence[currentIndex]
        val userAnswer = etAnswer.text.toString().trim()
        val correct = correctAnswers[type]!![currentExampleIndex]

        if (userAnswer.equals(correct, ignoreCase = true)) {
            Toast.makeText(this, "Дұрыс!", Toast.LENGTH_SHORT).show()
            score++
            btnNext.isEnabled = true
            btnCheckError.isEnabled = false
            tvResult.text = "✅ Дұрыс! Нұқсан түзетілді: $correct"
        } else {
            Toast.makeText(this, "Қате, қайталап көріңіз.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFinalScoreScreen() {
        // Скрываем игровой контент
        tvCodeSnippet.visibility = View.GONE
        etAnswer.visibility = View.GONE
        btnCheckError.visibility = View.GONE
        btnNext.visibility = View.GONE
        tvResult.visibility = View.GONE

        // Показываем экран результата
        resultLayout.visibility = View.VISIBLE
        tvScore.text = "🎉 Ойын аяқталды! Сіздің ұпайыңыз: $score / ${algorithmSequence.size}"
    }
}
