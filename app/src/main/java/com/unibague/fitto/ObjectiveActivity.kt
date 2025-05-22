package com.unibague.fitto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ObjectiveActivity : AppCompatActivity() {

    private lateinit var selectedButton: MaterialButton
    private var selectedObjective: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objective)

        val btn1       = findViewById<MaterialButton>(R.id.btnObjective1)
        val btn2       = findViewById<MaterialButton>(R.id.btnObjective2)
        val btn3       = findViewById<MaterialButton>(R.id.btnObjective3)
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)
        val back       = findViewById<TextView>(R.id.btnBack)
        val skip       = findViewById<TextView>(R.id.btnSkip)

        listOf(btn1, btn2, btn3).forEach { btn ->
            btn.setOnClickListener {
                // Reset
                listOf(btn1, btn2, btn3).forEach { b ->
                    b.setBackgroundColor(Color.TRANSPARENT)
                    b.setTextColor(Color.BLACK)
                }
                // Select
                btn.setBackgroundColor(Color.BLACK)
                btn.setTextColor(Color.WHITE)
                selectedButton = btn
                selectedObjective = btn.text.toString()
            }
        }

        back.setOnClickListener { finish() }
        skip.setOnClickListener {
            // Guarda null o default si quieres
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
        }

        nextButton.setOnClickListener {
            selectedObjective?.let {
                // Guardar
                val prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)
                prefs.edit().putString("OBJECTIVE", it).apply()
                // Ir a MenuPrincipal o Perfil
                startActivity(Intent(this, MenuPrincipalActivity::class.java))
            }
        }
    }
}
