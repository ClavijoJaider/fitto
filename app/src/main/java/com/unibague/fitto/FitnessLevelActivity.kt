package com.unibague.fitto

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class FitnessLevelActivity : AppCompatActivity() {

    private lateinit var btnBeginner: MaterialButton
    private lateinit var btnIntermediate: MaterialButton
    private lateinit var btnAdvanced: MaterialButton
    private lateinit var selectedButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_level)

        btnBeginner     = findViewById(R.id.btnBeginner)
        btnIntermediate = findViewById(R.id.btnIntermediate)
        btnAdvanced     = findViewById(R.id.btnAdvanced)

        val back = findViewById<TextView>(R.id.btnBack)
        val skip = findViewById<TextView>(R.id.btnSkip)
        val next = findViewById<MaterialButton>(R.id.nextButton)

        // Selección por defecto
        selectedButton = btnBeginner
        updateButtonStyles()

        listOf(btnBeginner, btnIntermediate, btnAdvanced).forEach { btn ->
            btn.setOnClickListener {
                selectedButton = it as MaterialButton
                updateButtonStyles()
            }
        }

        back.setOnClickListener { finish() }
        skip.setOnClickListener {
            startActivity(Intent(this, ObjectiveActivity::class.java))
        }

        next.setOnClickListener {
            val level = selectedButton.text.toString()
            // Guardar
            val prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)
            prefs.edit().putString("FITNESS_LEVEL", level).apply()
            // Avanzar
            startActivity(Intent(this, ObjectiveActivity::class.java))
        }
    }

    private fun updateButtonStyles() {
        val black = Color.BLACK
        val white = Color.WHITE
        listOf(btnBeginner, btnIntermediate, btnAdvanced).forEach { btn ->
            if (btn == selectedButton) {
                btn.setBackgroundColor(black)
                btn.setTextColor(white)
                btn.strokeWidth = 0
            } else {
                btn.setBackgroundColor(white)
                btn.setTextColor(black)
                btn.strokeWidth = 2
                btn.strokeColor = ColorStateList.valueOf(black)
            }
        }
    }
}
