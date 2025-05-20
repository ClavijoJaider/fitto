package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ejemplo.fitapp.MainActivity  // Ajusta esto al nombre de tu Activity de destino

class SeleccionFavoritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccion_favorito)

        // Edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Referencia al TextView
        val tvSkip = findViewById<TextView>(R.id.tv_skip)
        // 2. Listener que arranca MainActivity
        tvSkip.setOnClickListener {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }

        val btnStart: Button = findViewById(R.id.btn_next)

        // 2. Listener: al pulsar, arranca MenuPrincipalActivity
        btnStart.setOnClickListener {
            val intent = Intent(this, EdadActivity::class.java)
            startActivity(intent)
            // Opcional: si no quieres volver a BienvenidaActivity al pulsar "Atrás":
            finish()
        }
    }
}
