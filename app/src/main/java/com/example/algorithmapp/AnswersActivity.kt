package com.example.algorithmapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnswersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAnswers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val questionsWithExplanations = listOf(
            AnswersAdapter.QuestionWithExplanation(
                "Алгоритм дегеніміз не?",
                listOf("Есепті шешудің нақты реті", "Компьютерлік бағдарлама", "Математикалық формула", "Мәліметтер базасы"),
                0,
                "Алгоритм – бұл белгілі бір мақсатқа жету үшін жасалатын әрекеттердің реттелген тізбегі."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Қай алгоритм түрінде командалар тек бір рет орындалады?",
                listOf("Циклдік", "Сызықтық", "Тармақталған", "Рекурсивті"),
                1,
                "Сызықтық алгоритмде командалар бірінен соң бірі рет-ретімен орындалады."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "IF-ELSE конструкциясы қай алгоритмде қолданылады?",
                listOf("Сызықтық", "Циклдік", "Тармақталған", "Барлығында"),
                2,
                "IF-ELSE шартты операторы тармақталған алгоритмдерде шарттың орындалуына немесе орындалмауына байланысты әртүрлі әрекеттерді таңдау үшін қолданылады."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Циклдік алгоритмнің басты ерекшелігі не?",
                listOf("Бір рет орындалады", "Командалар қайталанады", "Шарт қолданылады", "Жылдам жұмыс істейді"),
                1,
                "Циклдік алгоритмдерде белгілі бір шарт орындалғанша немесе белгілі бір сан рет командалар блогы қайталанады."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Тармақталған алгоритмде не тексеріледі?",
                listOf("Цикл саны", "Шарт", "Айнымалы түрі", "Программа коды"),
                1,
                "Тармақталған алгоритмде белгілі бір шарт тексеріліп, оның нәтижесіне (ақиқат/жалған) байланысты әрекеттер орындалады."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "FOR циклы қай алгоритм түріне жатады?",
                listOf("Сызықтық", "Тармақталған", "Циклдік", "Қарапайым"),
                2,
                "FOR циклі белгілі бір сандық аралықта әрекеттерді қайталау үшін қолданылатын циклдік алгоритмнің бір түрі."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Сызықтық алгоритмнің мысалы қайсы?",
                listOf("Санды тексеру", "1-ден 10-ға дейін санау", "Екі санды қосу", "Массивті іздеу"),
                2,
                "Екі санды қосу – бұл әрекеттердің тізбектей орындалуын қажет ететін қарапайым сызықтық алгоритм."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Қай алгоритм шарттан тәуелді орындалады?",
                listOf("Сызықтық", "Тармақталған", "Барлық алгоритмдер", "Ешқайсысы"),
                1,
                "Тармақталған алгоритмнің орындалу барысы шарттың нәтижесіне тікелей тәуелді."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "WHILE циклы не үшін қолданылады?",
                listOf("Шартты тексеру", "Командаларды қайталау", "Мәнді сақтау", "Есепті аяқтау"),
                1,
                "WHILE циклі белгілі бір шарт ақиқат болған кезде командалар блогын қайталап орындау үшін қолданылады."
            ),
            AnswersAdapter.QuestionWithExplanation(
                "Қандай алгоритм ең қарапайым болып саналады?",
                listOf("Циклдік", "Тармақталған", "Сызықтық", "Рекурсивті"),
                2,
                "Сызықтық алгоритмде тармақталу немесе қайталану болмағандықтан, ол ең қарапайым алгоритм болып есептеледі."
            )
        )

        val adapter = AnswersAdapter(questionsWithExplanations)
        recyclerView.adapter = adapter

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
