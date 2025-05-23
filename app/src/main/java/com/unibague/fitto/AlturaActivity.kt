package com.unibague.fitto

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText

class AlturaActivity : AppCompatActivity() {

    private lateinit var unitToggle: MaterialButtonToggleGroup
    private lateinit var btnCm: MaterialButton
    private lateinit var btnFt: MaterialButton
    private lateinit var heightInput: TextInputEditText
    private lateinit var nextButton: MaterialButton
    private lateinit var back: TextView
    private lateinit var skip: TextView

    private val blue = Color.parseColor("#2196F3")
    private val grayStroke = Color.parseColor("#CCCCCC")
    private val white = Color.WHITE
    private val grayText = Color.parseColor("#666666")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        // Referencias
        unitToggle  = findViewById(R.id.unitToggle)
        btnCm       = findViewById(R.id.btnCm)
        btnFt       = findViewById(R.id.btnFt)
        heightInput = findViewById(R.id.heightInput)
        nextButton  = findViewById(R.id.nextButton)
        back        = findViewById(R.id.btnBack)
        skip        = findViewById(R.id.btnSkip)

        // Selección por defecto: centímetros
        unitToggle.check(btnCm.id)
        styleButtons(btnCm.id)

        unitToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) styleButtons(checkedId)
        }

        back.setOnClickListener { finish() }
        skip.setOnClickListener {
            Toast.makeText(this, "Saltaste este paso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FitnessLevelActivity::class.java))
        }

        nextButton.setOnClickListener {
            val text = heightInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Ingresa tu altura", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val h = text.toFloat()

            // Guardar
            val prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)
            prefs.edit().putFloat("HEIGHT", h).apply()

            // Avanzar
            startActivity(Intent(this, FitnessLevelActivity::class.java))
        }
    }

    private fun styleButtons(selectedId: Int) {
        if (selectedId == btnCm.id) {
            // Centímetros selected
            btnCm.apply {
                backgroundTintList = ColorStateList.valueOf(blue)
                strokeWidth = 0
                setTextColor(white)
            }
            btnFt.apply {
                backgroundTintList = ColorStateList.valueOf(white)
                strokeWidth = 2
                strokeColor = ColorStateList.valueOf(grayStroke)
                setTextColor(grayText)
            }
        } else {
            // Pies selected
            btnFt.apply {
                backgroundTintList = ColorStateList.valueOf(blue)
                strokeWidth = 0
                setTextColor(white)
            }
            btnCm.apply {
                backgroundTintList = ColorStateList.valueOf(white)
                strokeWidth = 2
                strokeColor = ColorStateList.valueOf(grayStroke)
                setTextColor(grayText)
            }
        }
    }
}
