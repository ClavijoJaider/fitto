package com.unibague.fitto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.unibague.fitto.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajuste edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainRegistro) { v, insets ->
            val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }

        auth = Firebase.auth

        // Botón Registrar
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass  = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || pass.length < 6) {
                Toast.makeText(
                    this,
                    "Introduce un correo válido y contraseña ≥ 6 carácteres",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registro exitoso: $email", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MenuPrincipalActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Error de registro: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        // Enlace a Login
        binding.tvGoToLogin.setOnClickListener {
            startActivity(Intent(this, BienvenidaActivity::class.java))
            finish()
        }
    }
}
