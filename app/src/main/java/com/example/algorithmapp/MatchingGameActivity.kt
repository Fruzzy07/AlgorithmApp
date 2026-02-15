package com.example.algorithmapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MatchingGameActivity : AppCompatActivity() {

    private lateinit var termsLayout: LinearLayout
    private lateinit var definitionsLayout: LinearLayout
    private lateinit var btnCheck: Button
    private lateinit var resultLayout: LinearLayout
    private lateinit var btnRetry: Button
    private lateinit var btnHome: Button

    private val terms = listOf("Алгоритм", "Сызықтық", "Тармақталған", "Циклдік")
    private val definitions = listOf(
        "Белгілі бір мақсатқа жету үшін жасалатын әрекеттердің реттелген тізбегі",
        "Командалар бірінен соң бірі рет-ретімен орындалады",
        "Шарттың орындалуына байланысты әртүрлі әрекеттерді таңдау",
        "Белгілі бір командалар тобы бірнеше рет қайталанады"
    )

    private val shuffledDefinitions by lazy { definitions.shuffled() }
    private val pairs = terms.zip(definitions).toMap()

    private var selectedTerm: TextView? = null
    private var selectedDefinition: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_game)

        termsLayout = findViewById(R.id.termsLayout)
        definitionsLayout = findViewById(R.id.definitionsLayout)
        btnCheck = findViewById(R.id.btnCheckMatching)
        resultLayout = findViewById(R.id.resultLayout)
        btnRetry = findViewById(R.id.btnRetry)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnCheck.setOnClickListener { checkMatch() }
        btnRetry.setOnClickListener { recreate() }
        btnBack.setOnClickListener { finish() }
        btnHome = findViewById(R.id.btnHome)

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        populateViews()
    }

    private fun populateViews() {
        terms.forEach { term ->
            val termView = createTextView(term)
            termView.setOnClickListener { select(it, true) }
            termsLayout.addView(termView)
        }

        shuffledDefinitions.forEach { definition ->
            val definitionView = createTextView(definition)
            definitionView.setOnClickListener { select(it, false) }
            definitionsLayout.addView(definitionView)
        }
    }

    private fun createTextView(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 16f
            setPadding(24, 24, 24, 24)
            setBackgroundResource(R.drawable.button_home)
            setTextColor(Color.BLACK)
            isClickable = true
            isFocusable = true

            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(8, 8, 8, 8) }
        }
    }

    private fun select(view: View, isTerm: Boolean) {
        val textView = view as TextView

        if (isTerm) {
            selectedTerm?.setBackgroundResource(R.drawable.button_home)
            selectedTerm = textView
        } else {
            selectedDefinition?.setBackgroundResource(R.drawable.button_home)
            selectedDefinition = textView
        }

        textView.setBackgroundColor(Color.parseColor("#FFEB3B"))
    }

    private fun checkMatch() {
        if (selectedTerm == null || selectedDefinition == null) {
            Toast.makeText(this, "Термин мен анықтаманы таңдаңыз", Toast.LENGTH_SHORT).show()
            return
        }

        val term = selectedTerm!!.text.toString()
        val definition = selectedDefinition!!.text.toString()

        if (pairs[term] == definition) {
            Toast.makeText(this, "Дұрыс!", Toast.LENGTH_SHORT).show()
            selectedTerm!!.visibility = View.INVISIBLE
            selectedDefinition!!.visibility = View.INVISIBLE
            selectedTerm = null
            selectedDefinition = null

            if (allMatched()) showFinalResult()

        } else {
            Toast.makeText(this, "Қате, қайталап көріңіз", Toast.LENGTH_SHORT).show()
            selectedTerm!!.setBackgroundResource(R.drawable.button_home)
            selectedDefinition!!.setBackgroundResource(R.drawable.button_home)
            selectedTerm = null
            selectedDefinition = null
        }
    }

    private fun allMatched(): Boolean {
        for (i in 0 until termsLayout.childCount) {
            if (termsLayout.getChildAt(i).visibility == View.VISIBLE) return false
        }
        return true
    }

    private fun showFinalResult() {
        resultLayout.visibility = View.VISIBLE
        btnCheck.visibility = View.GONE
        termsLayout.visibility = View.GONE
        definitionsLayout.visibility = View.GONE
    }
}
