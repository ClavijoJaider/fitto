package com.unibague.fitto

import android.content.Intent
import android.graphics.Color
import android.content.res.ColorStateList
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

        btnBeginner = findViewById(R.id.btnBeginner)
        btnIntermediate = findViewById(R.id.btnIntermediate)
        btnAdvanced = findViewById(R.id.btnAdvanced)

        val back = findViewById<TextView>(R.id.btnBack)
        val skip = findViewById<TextView>(R.id.btnSkip)
        val next = findViewById<MaterialButton>(R.id.nextButton)

        // Selección por defecto: PRINCIPIANTES
        selectedButton = btnBeginner
        updateButtonStyles()

        btnBeginner.setOnClickListener {
            selectedButton = btnBeginner
            updateButtonStyles()
        }

        btnIntermediate.setOnClickListener {
            selectedButton = btnIntermediate
            updateButtonStyles()
        }

        btnAdvanced.setOnClickListener {
            selectedButton = btnAdvanced
            updateButtonStyles()
        }

        back.setOnClickListener { finish() }
        skip.setOnClickListener { /* lógica opcional de omitir */ }

        next.setOnClickListener {
            val selectedText = selectedButton.text.toString()
            val intent = Intent(this, ObjectiveActivity::class.java)
            intent.putExtra("FITNESS_LEVEL", selectedText)
            startActivity(intent)
        }
    }

    private fun updateButtonStyles() {
        val black = Color.BLACK
        val white = Color.WHITE

        val allButtons = listOf(btnBeginner, btnIntermediate, btnAdvanced)

        for (button in allButtons) {
            if (button == selectedButton) {
                button.setBackgroundColor(black)
                button.setTextColor(white)
                button.strokeWidth = 0
            } else {
                button.setBackgroundColor(white)
                button.setTextColor(black)
                button.strokeWidth = 2
                button.strokeColor = ColorStateList.valueOf(black)
            }
        }
    }
}
