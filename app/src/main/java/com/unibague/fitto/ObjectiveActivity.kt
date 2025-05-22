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

        val btnObjective1 = findViewById<MaterialButton>(R.id.btnObjective1)
        val btnObjective2 = findViewById<MaterialButton>(R.id.btnObjective2)
        val btnObjective3 = findViewById<MaterialButton>(R.id.btnObjective3)
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)

        val objectiveButtons = listOf(btnObjective1, btnObjective2, btnObjective3)

        fun updateSelection(button: MaterialButton) {
            objectiveButtons.forEach {
                it.setBackgroundColor(Color.TRANSPARENT)
                it.setTextColor(Color.BLACK)
            }
            button.setBackgroundColor(Color.BLACK)
            button.setTextColor(Color.WHITE)
            selectedButton = button
            selectedObjective = button.text.toString()
        }

        btnObjective1.setOnClickListener { updateSelection(btnObjective1) }
        btnObjective2.setOnClickListener { updateSelection(btnObjective2) }
        btnObjective3.setOnClickListener { updateSelection(btnObjective3) }

        nextButton.setOnClickListener {
            if (selectedObjective != null) {
                val intent = Intent(this, MenuPrincipalActivity::class.java)
                intent.putExtra("OBJECTIVE", selectedObjective)
                startActivity(intent)
            } else {
                // Puedes mostrar un mensaje si deseas
            }
        }

        findViewById<TextView>(R.id.btnSkip).setOnClickListener {
            // lógica de salto, por ejemplo:
            startActivity(Intent(this, MenuPrincipalActivity::class.java))
        }

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
