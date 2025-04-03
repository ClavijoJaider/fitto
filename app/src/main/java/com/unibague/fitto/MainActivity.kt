package com.ejemplo.fitapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    // Vistas del login
    private lateinit var layoutLogin: View
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Vistas de la sección principal
    private lateinit var layoutMain: View
    private lateinit var tvContent: TextView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var toolbar: Toolbar

    // DrawerLayout y NavigationView para el menú desplegable
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos el Toolbar y el DrawerLayout
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Inicializamos las vistas del login
        layoutLogin = findViewById(R.id.layout_login)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        // Inicializamos las vistas de la sección principal
        layoutMain = findViewById(R.id.layout_main)
        tvContent = findViewById(R.id.tv_content)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Lógica de login (credenciales simuladas)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (email == "user@example.com" && password == "1234") {
                // Login correcto: ocultamos el login y mostramos la pantalla principal
                layoutLogin.visibility = View.GONE
                layoutMain.visibility = View.VISIBLE
                // Se muestra el Toolbar con el botón hamburguesa (ya configurado)
            } else {
                etEmail.error = "Credenciales incorrectas"
            }
        }

        // Configuramos la navegación inferior
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    tvContent.text = "Inicio - Bienvenido a FitApp"
                    true
                }
                R.id.nav_workout -> {
                    tvContent.text = "Entrenamientos disponibles"
                    true
                }
                else -> false
            }
        }

        // (Opcional) Configuración de las opciones del menú del Drawer
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    tvContent.text = "Perfil"
                    true
                }
                R.id.nav_settings -> {
                    tvContent.text = "Configuración"
                    true
                }
                else -> false
            }
        }
    }
}
