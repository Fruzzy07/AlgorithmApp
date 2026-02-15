package com.example.algorithmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class AlgorithmDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm_detail)

        val type = intent.getStringExtra("TYPE")
        val titleText = findViewById<TextView>(R.id.tvDetailTitle)
        val descriptionText = findViewById<TextView>(R.id.tvDetailDescription)
        val exampleText = findViewById<TextView>(R.id.tvDetailExample)
        val iconImage = findViewById<ImageView>(R.id.ivAlgorithmIcon)

        when (type) {
            "linear" -> {
                titleText.text = "Сызықтық алгоритм"
                descriptionText.text = """
                    Сызықтық алгоритм - бұл командалар тізбегі ретімен орындалатын алгоритм түрі. 
                    
                    Әрбір команда бір рет орындалады және келесі командаға өтеді.
                    
                    Мысалы: тамақ дайындау рецепті, математикалық есептеу.
                """.trimIndent()
                exampleText.text = """
                    Мысал:
                    1. Бастау
                    2. a = 5 деген мәнін беру
                    3. b = 10 деген мәнін беру
                    4. c = a + b есептеу
                    5. c мәнін шығару
                    6. Аяқтау
                """.trimIndent()
                iconImage.setImageResource(R.drawable.ic_linear)
            }
            "branching" -> {
                titleText.text = "Тармақталған алгоритм"
                descriptionText.text = """
                    Тармақталған алгоритм - шарт бойынша әртүрлі жолдармен жүретін алгоритм.
                    
                    Белгілі бір шартқа байланысты бір немесе басқа командалар орындалады.
                    
                    IF-ELSE конструкциясы қолданылады.
                """.trimIndent()
                exampleText.text = """
                    Мысал:
                    Бастау
                        number-ге мән беру
                        IF number > 0
                            PRINT "Оң сан"
                        ELSE
                            PRINT "Теріс сан немесе нөл"
                        END IF
                    Аяқтау
                """.trimIndent()
                iconImage.setImageResource(R.drawable.ic_branching)
            }
            "cyclic" -> {
                titleText.text = "Циклдік алгоритм"
                descriptionText.text = """
                    Циклдік алгоритм - белгілі бір командалар тобы бірнеше рет қайталанатын алгоритм.
                    
                    Цикл шарт орындалғанша немесе белгілі санда қайталанады.
                    
                    FOR, WHILE циклдары қолданылады.
                """.trimIndent()
                exampleText.text = """
                    Мысал (FOR циклы):
                    Бастау
                        FOR i = 1 TO 5
                            PRINT "Сан: " + i
                        NEXT i
                    Аяқтау

                    Мысал (WHILE циклы):
                    Бастау
                        k = 1
                        WHILE k <= 3
                            PRINT "Қайталау: " + k
                            k = k + 1
                        END WHILE
                    Аяқтау
                """.trimIndent()
                iconImage.setImageResource(R.drawable.ic_cyclic)
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
