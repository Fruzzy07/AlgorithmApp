package com.example.algorithmapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SortCodeGameActivity : AppCompatActivity() {

    private lateinit var rvCodeLines: RecyclerView
    private lateinit var adapter: SortCodeAdapter

    private lateinit var btnCheckOrder: Button
    private lateinit var btnBack: ImageView

    // Result layout
    private lateinit var resultLayout: LinearLayout
    private lateinit var tvResultMessage: TextView
    private lateinit var btnRetry: Button
    private lateinit var btnHome: Button

    private var currentExampleIndex = 0

    // Примеры кода с правильным порядком
    private val examples = listOf(
        listOf("a = 5", "b = 10", "c = a + b", "PRINT c", "END"),
        listOf("Бастау", "a = 5", "b = 10", "c мәнін шығару", "Аяқтау"),
        listOf("x = 3", "y = 7", "z = x * y", "PRINT z", "END")
    )

    private lateinit var correctOrder: List<String>
    private lateinit var shuffledLines: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_code_game)

        rvCodeLines = findViewById(R.id.rvCodeLines)
        btnCheckOrder = findViewById(R.id.btnCheckOrder)
        btnBack = findViewById(R.id.btnBack)

        resultLayout = findViewById(R.id.resultLayout)
        tvResultMessage = findViewById(R.id.tvResultMessage)
        btnRetry = findViewById(R.id.btnRetry)
        btnHome = findViewById(R.id.btnHome)

        btnBack.setOnClickListener { finish() }

        startGame()

        btnCheckOrder.setOnClickListener { checkOrder() }

        btnRetry.setOnClickListener {
            resultLayout.visibility = View.GONE
            startGame()
        }

        btnHome.setOnClickListener { finish() }
    }

    private fun startGame() {
        // Выбираем случайный пример
        currentExampleIndex = (examples.indices).random()
        correctOrder = examples[currentExampleIndex]
        shuffledLines = correctOrder.shuffled().toMutableList()

        adapter = SortCodeAdapter(shuffledLines)
        rvCodeLines.layoutManager = LinearLayoutManager(this)
        rvCodeLines.adapter = adapter

        // ItemTouchHelper для перетаскивания
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                adapter.onItemMove(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        })

        itemTouchHelper.attachToRecyclerView(rvCodeLines)

        // Показываем игровой контент
        rvCodeLines.visibility = View.VISIBLE
        btnCheckOrder.visibility = View.VISIBLE
        resultLayout.visibility = View.GONE
    }

    private fun checkOrder() {
        if (shuffledLines == correctOrder) {
            // Скрываем игру и показываем результат
            rvCodeLines.visibility = View.GONE
            btnCheckOrder.visibility = View.GONE
            resultLayout.visibility = View.VISIBLE
            tvResultMessage.text = "🎉 Жарайсың!"
        } else {
            Toast.makeText(this, "Қате, қайталап көріңіз.", Toast.LENGTH_SHORT).show()
        }
    }
}
