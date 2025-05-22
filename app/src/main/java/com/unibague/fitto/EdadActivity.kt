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
        // Apunta al layout activity_edad.xml
        setContentView(R.layout.activity_edad)

        // 1) Referencia al NumberPicker
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker).apply {
            minValue = 10
            maxValue = 100
            value = 27
        }

        // 2) Botón Siguiente
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        nextButton.setOnClickListener {
            val age = numberPicker.value
            // Pasa a PesoActivity
            val intent = Intent(this, PesoActivity::class.java).apply {
                putExtra("AGE", age)
            }
            startActivity(intent)
        }

        // 3) Saltar
        findViewById<TextView>(R.id.btnSkip).setOnClickListener {
            // Si quieres saltar este paso y avanzar de todas formas:
            startActivity(Intent(this, PesoActivity::class.java))
        }

        // 4) Atrás
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
