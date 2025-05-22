package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Ya en Inicio, no hacemos nada
                    true
                }
                R.id.nav_dieta -> {
                    // Si tienes DietaActivity:
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                    true
                }
                R.id.nav_ejercitar -> {
                    // Si tienes EjercicioActivity:
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    // Aquí abrimos PerfilActivity
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
