package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.nav_home -> true
                R.id.nav_dieta -> {
                    startActivity(Intent(this, DietaSaludableActivity::class.java))
                    true
                }
                R.id.nav_ejercitar -> {
                    startActivity(Intent(this, EntrenamientoActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        //  Botón para abrir el mapa de gimnasios
        val btnBuscarGimnasios = findViewById<Button>(R.id.btnBuscarGimnasios)
        btnBuscarGimnasios.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}
