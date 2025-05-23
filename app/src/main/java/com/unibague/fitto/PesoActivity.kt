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

class PesoActivity : AppCompatActivity() {

    private lateinit var unitToggle: MaterialButtonToggleGroup
    private lateinit var btnLbs: MaterialButton
    private lateinit var btnKg: MaterialButton
    private lateinit var weightInput: TextInputEditText
    private lateinit var nextButton: MaterialButton
    private lateinit var back: TextView
    private lateinit var skip: TextView

    private val blue = Color.parseColor("#2196F3")
    private val grayStroke = Color.parseColor("#CCCCCC")
    private val white = Color.WHITE
    private val grayText = Color.parseColor("#666666")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        // Referencias
        unitToggle  = findViewById(R.id.unitToggle)
        btnLbs      = findViewById(R.id.btnLbs)
        btnKg       = findViewById(R.id.btnKg)
        weightInput = findViewById(R.id.weightInput)
        nextButton  = findViewById(R.id.nextButton)
        back        = findViewById(R.id.btnBack)
        skip        = findViewById(R.id.btnSkip)

        // Selección por defecto
        unitToggle.check(btnKg.id)
        styleButtons(btnKg.id)

        unitToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) styleButtons(checkedId)
        }

        back.setOnClickListener { finish() }
        skip.setOnClickListener {
            Toast.makeText(this, "Saltaste este paso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AlturaActivity::class.java))
        }

        nextButton.setOnClickListener {
            val text = weightInput.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Ingresa tu peso", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val w = text.toFloat()
            val unit = if (unitToggle.checkedButtonId == btnKg.id) "KG" else "LBS"

            // Guardar
            val prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)
            prefs.edit()
                .putFloat("WEIGHT", w)
                .putString("UNIT_WEIGHT", unit)
                .apply()

            // Avanzar
            startActivity(Intent(this, AlturaActivity::class.java))
        }
    }

    private fun styleButtons(selectedId: Int) {
        if (selectedId == btnKg.id) {
            btnKg.apply {
                backgroundTintList = ColorStateList.valueOf(blue)
                strokeWidth = 0
                setTextColor(white)
            }
            btnLbs.apply {
                backgroundTintList = ColorStateList.valueOf(white)
                strokeWidth = 2
                strokeColor = ColorStateList.valueOf(grayStroke)
                setTextColor(grayText)
            }
        } else {
            btnLbs.apply {
                backgroundTintList = ColorStateList.valueOf(blue)
                strokeWidth = 0
                setTextColor(white)
            }
            btnKg.apply {
                backgroundTintList = ColorStateList.valueOf(white)
                strokeWidth = 2
                strokeColor = ColorStateList.valueOf(grayStroke)
                setTextColor(grayText)
            }
        }
    }
}
