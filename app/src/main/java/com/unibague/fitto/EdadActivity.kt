package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EdadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edad)

        // Configuro el NumberPicker
        findViewById<NumberPicker>(R.id.np_age).apply {
            minValue = 16
            maxValue = 100
            value = 27
        }

        // "Saltar" va a MainActivity (o la que quieras)
        findViewById<TextView>(R.id.tv_skip).setOnClickListener {
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
        }

        // Botón de siguientes pasos: leo la edad y avanzo
        findViewById<MaterialButton>(R.id.btn_next).setOnClickListener {
            val edad = findViewById<NumberPicker>(R.id.np_age).value
            Intent(this, MenuPrincipalActivity::class.java).apply {
                putExtra("edad", edad)
                startActivity(this)
            }
        }
    }
}
