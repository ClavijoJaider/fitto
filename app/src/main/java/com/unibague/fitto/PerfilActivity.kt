package com.unibague.fitto

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.pow
import kotlin.math.sqrt

class PerfilActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)



        prefs = getSharedPreferences("fitto_prefs", MODE_PRIVATE)

        showBasicInfo()
        showMetabolism()
        showBodyComposition()
        showMacroRequirements()
        showAdvancedCalculations()
        setupBottomNav()
    }

    private fun showBasicInfo() {
        val age       = prefs.getInt("AGE", 25)
        val weight    = prefs.getFloat("WEIGHT", 70f)
        val height    = prefs.getFloat("HEIGHT", 170f)
        val level     = prefs.getString("FITNESS_LEVEL", "--")!!
        val objective = prefs.getString("OBJECTIVE", "--")!!

        findViewById<TextView>(R.id.tvAgeInfo).text       = "Edad: $age años"
        findViewById<TextView>(R.id.tvWeightInfo).text    = "Peso corporal: ${weight.toInt()} kg"
        findViewById<TextView>(R.id.tvHeightInfo).text    = "Estatura: ${height.toInt()} cm"
        findViewById<TextView>(R.id.tvLevelInfo).text     = "Nivel actividad: $level"
        findViewById<TextView>(R.id.tvObjectiveInfo).text = "Objetivo fitness: $objective"
    }

    private fun showMetabolism() {
        val age = prefs.getInt("AGE", 25)
        val w   = prefs.getFloat("WEIGHT", 70f)
        val h   = prefs.getFloat("HEIGHT", 170f)
        val male= prefs.getBoolean("IS_MALE", true)
        val lvl = prefs.getString("FITNESS_LEVEL", "--")!!

        val bmr = if (male)
            10f*w + 6.25f*h - 5f*age + 5f
        else
            10f*w + 6.25f*h - 5f*age - 161f

        val factor = when (lvl) {
            "PRINCIPIANTES" -> 1.2f
            "INTERMEDIO"    -> 1.55f
            "AVANZADO"      -> 1.9f
            else            -> 1.2f
        }
        val tdee = bmr * factor

        findViewById<TextView>(R.id.tvBmr).text  = "BMR: ${bmr.toInt()} kcal"
        findViewById<TextView>(R.id.tvTdee).text = "TDEE: ${tdee.toInt()} kcal"
    }

    private fun showBodyComposition() {
        val age    = prefs.getInt("AGE", 25)
        val w      = prefs.getFloat("WEIGHT", 70f)
        val h      = prefs.getFloat("HEIGHT", 170f)
        val male   = prefs.getBoolean("IS_MALE", true)

        val bmi = w / (h/100f).pow(2)
        val cat = when {
            bmi < 18.5 -> "Bajo peso"
            bmi < 25   -> "Normal"
            bmi < 30   -> "Sobrepeso"
            else       -> "Obesidad"
        }

        val bodyFat = 1.20f*bmi + 0.23f*age - 10.8f*(if (male) 1f else 0f) - 5.4f
        val lean    = w * (1 - bodyFat/100f)
        val water   = w * 0.6f

        findViewById<TextView>(R.id.tvBmi).text    = "IMC: ${"%.1f".format(bmi)}"
        findViewById<TextView>(R.id.tvBmiCat).text = "(Clasificación: $cat)"
        findViewById<TextView>(R.id.tvBodyFat).text= "% Grasa: ${"%.1f".format(bodyFat)} %"
        findViewById<TextView>(R.id.tvLeanMass).text="Masa Magra: ${"%.1f".format(lean)} kg"
        findViewById<TextView>(R.id.tvWater).text   ="Agua: ${"%.1f".format(water)} L"
    }

    private fun showMacroRequirements() {
        val w        = prefs.getFloat("WEIGHT", 70f)
        val tdeeText = findViewById<TextView>(R.id.tvTdee).text.toString()
        val tdee     = tdeeText.filter { it.isDigit() }.toFloatOrNull() ?: 0f

        val prot = 2f * w
        val fat  = (tdee * 0.25f) / 9f
        val carb = (tdee - prot*4f - fat*9f) / 4f

        findViewById<TextView>(R.id.tvProteinReq).text = "Proteínas: ${prot.toInt()} g/día"
        findViewById<TextView>(R.id.tvFatReq).text     = "Grasas: ${fat.toInt()} g/día"
        findViewById<TextView>(R.id.tvCarbsReq).text   = "Carbohidratos: ${carb.toInt()} g/día"
    }

    private fun showAdvancedCalculations() {
        val age = prefs.getInt("AGE", 25)
        val w   = prefs.getFloat("WEIGHT", 70f)
        val h   = prefs.getFloat("HEIGHT", 170f)

        // Peso ideal Devine
        val inchesOver5ft = (h - 152.4f) / 2.54f
        val ideal = if (prefs.getBoolean("IS_MALE", true))
            50f + 2.3f * inchesOver5ft
        else
            45.5f + 2.3f * inchesOver5ft

        // BSA Mosteller
        val bsa = sqrt((h * w) / 3600f)

        // HR Máx + Zonas
        val hrMax = 220 - age
        val zones = listOf(0.5f, 0.6f, 0.7f, 0.8f, 0.9f)
            .windowed(2)
            .joinToString(" | ") { (low, high) ->
                "${(hrMax * low).toInt()}–${(hrMax * high).toInt()} lpm"
            }

        findViewById<TextView>(R.id.tvIdealWeight).text = "Peso Ideal: ${"%.1f".format(ideal)} kg"
        findViewById<TextView>(R.id.tvBsa).text         = "BSA: ${"%.2f".format(bsa)} m²"
        findViewById<TextView>(R.id.tvHrMax).text       = "HR Máx: $hrMax lpm"
        findViewById<TextView>(R.id.tvHrZone).text      = "Zonas: $zones"
    }

    private fun setupBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MenuPrincipalActivity::class.java))
                    true
                }
                R.id.nav_dieta -> {
                    startActivity(Intent(this, DietaSaludableActivity::class.java))
                    true
                }
                R.id.nav_ejercitar -> {
                    startActivity(Intent(this, EntrenamientoActivity::class.java))
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}
