package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ejemplo.fitapp.MainActivity

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)



        // 1. Referencia al botón btn_start
        val btnStart: Button = findViewById(R.id.btn_start)

        // 2. Listener: al pulsar, arranca MenuPrincipalActivity
        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Opcional: si no quieres volver a BienvenidaActivity al pulsar "Atrás":
            finish()
        }
    }
}
