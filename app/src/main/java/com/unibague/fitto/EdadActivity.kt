package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EdadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edad)

        // Selector de edad
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker).apply {
            minValue = 10
            maxValue = 100
            value = 27
        }

        // Botón Siguiente
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        nextButton.setOnClickListener {
            val age = numberPicker.value
            // Guardar
            val prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)
            prefs.edit().putInt("AGE", age).apply()
            // Avanzar
            startActivity(Intent(this, PesoActivity::class.java))
        }

        // Saltar
        findViewById<TextView>(R.id.btnSkip).setOnClickListener {
            // Guarda un valor por defecto si quieres, o simplemente avanza
            startActivity(Intent(this, PesoActivity::class.java))
        }

        // Atrás
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
