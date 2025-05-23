package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DietaSaludableActivity : AppCompatActivity() {

    private lateinit var tarjetaDesayuno: LinearLayout
    private lateinit var tarjetaAlmuerzo: LinearLayout
    private lateinit var tarjetaCena: LinearLayout

    private lateinit var btnDesayuno: Button
    private lateinit var btnAlmuerzo: Button
    private lateinit var btnCena: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieta_saludable)

        // Referencias a botones y tarjetas
        tarjetaDesayuno = findViewById(R.id.tarjetaDesayuno)
        tarjetaAlmuerzo = findViewById(R.id.tarjetaAlmuerzo)
        tarjetaCena      = findViewById(R.id.tarjetaCena)

        btnDesayuno = findViewById(R.id.btnDesayuno)
        btnAlmuerzo = findViewById(R.id.btnAlmuerzo)
        btnCena     = findViewById(R.id.btnCena)

        // Referencias a ImageViews dentro de cada tarjeta
        val imgDesayuno  = findViewById<ImageView>(R.id.imgDesayuno)
        val imgAlmuerzo  = findViewById<ImageView>(R.id.imgAlmuerzo)
        val imgCena      = findViewById<ImageView>(R.id.imgCena)

        // Click en imagen: abre detalle
        val abrirDetalle = View.OnClickListener {
            startActivity(Intent(this, DetalleComidaActivity::class.java))
        }
        imgDesayuno.setOnClickListener(abrirDetalle)
        imgAlmuerzo.setOnClickListener(abrirDetalle)
        imgCena.setOnClickListener(abrirDetalle)

        // Cambio de vista según botón seleccionado
        btnDesayuno.setOnClickListener { mostrarTarjeta("desayuno") }
        btnAlmuerzo.setOnClickListener { mostrarTarjeta("almuerzo") }
        btnCena.setOnClickListener { mostrarTarjeta("cena") }

        // BottomNavigationView (igual que en MenuPrincipalActivity)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_dieta
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                    true
                }
                R.id.nav_dieta -> true
                R.id.nav_ejercitar -> {
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun mostrarTarjeta(tipo: String) {
        tarjetaDesayuno.visibility = if (tipo == "desayuno") View.VISIBLE else View.GONE
        tarjetaAlmuerzo.visibility = if (tipo == "almuerzo") View.VISIBLE else View.GONE
        tarjetaCena.visibility      = if (tipo == "cena") View.VISIBLE else View.GONE
    }

}
