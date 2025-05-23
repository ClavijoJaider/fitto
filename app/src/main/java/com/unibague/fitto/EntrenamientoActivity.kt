package com.unibague.fitto

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class EntrenamientoActivity : AppCompatActivity() {

    private lateinit var btnPrincipiante: Button
    private lateinit var btnIntermedio: Button
    private lateinit var btnAvanzado: Button

    private lateinit var cardEntrenamiento1: LinearLayout
    private lateinit var cardEntrenamiento2: LinearLayout
    private lateinit var cardEntrenamiento3: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamiento)

        // Botones
        btnPrincipiante = findViewById(R.id.btnPrincipiante)
        btnIntermedio = findViewById(R.id.btnIntermedio)
        btnAvanzado = findViewById(R.id.btnAvanzado)

        // Tarjetas
        cardEntrenamiento1 = findViewById(R.id.cardEntrenamiento1)
        cardEntrenamiento2 = findViewById(R.id.cardEntrenamiento2)
        cardEntrenamiento3 = findViewById(R.id.cardEntrenamiento3)

        btnPrincipiante.setOnClickListener {
            mostrarSoloNivel("principiante")
            actualizarBotones(btnPrincipiante)
        }

        btnIntermedio.setOnClickListener {
            mostrarSoloNivel("intermedio")
            actualizarBotones(btnIntermedio)
        }

        btnAvanzado.setOnClickListener {
            mostrarSoloNivel("avanzado")
            actualizarBotones(btnAvanzado)
        }
    }

    private fun mostrarSoloNivel(nivel: String) {
        cardEntrenamiento1.visibility =
            if (nivel == "principiante") View.VISIBLE else View.GONE
        cardEntrenamiento2.visibility =
            if (nivel == "intermedio") View.VISIBLE else View.GONE
        cardEntrenamiento3.visibility =
            if (nivel == "avanzado") View.VISIBLE else View.GONE
    }

    private fun actualizarBotones(botonActivo: Button) {
        val colorActivo = ContextCompat.getColor(this, R.color.azul_entrenamiento)
        val colorInactivo = Color.LTGRAY

        listOf(btnPrincipiante, btnIntermedio, btnAvanzado).forEach { button ->
            button.setBackgroundColor(if (button == botonActivo) colorActivo else colorInactivo)
        }
    }
}
