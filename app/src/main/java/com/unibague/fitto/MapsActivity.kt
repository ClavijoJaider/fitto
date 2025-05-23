package com.unibague.fitto

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.unibague.fitto.databinding.ActivityMapsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // Lista de gimnasios de plantilla (nombre y coordenadas)
    private val gimnasios = listOf(
        "SmartFit La Estacion" to LatLng(4.445833334542473, -75.20530526321225),
        "GatoBedoya" to LatLng(4.429831533142461, -75.18605463135378),
        "Optimus Gym" to LatLng(4.4434137330490655, -75.16663450314049),
        "Unibague Gym" to LatLng(4.448586952684709, -75.19931259889373),
        "Sede Deportiva Unibague" to LatLng(4.443370513508831, -75.15961232116857),
        "Spinning Center Gym" to LatLng(4.440687129214331, -75.20465537116998)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura la toolbar con flecha de regreso
        setSupportActionBar(binding.toolbarMap)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtiene el fragmento de mapa y registra el callback
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // FloatingActionButton para centrar en el primer gimnasio
        findViewById<FloatingActionButton>(R.id.fabCenter).setOnClickListener {
            if (::mMap.isInitialized) {
                val primero = gimnasios.first().second
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(primero, 14f))
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Añade un marcador para cada gimnasio
        gimnasios.forEach { (nombre, coords) ->
            mMap.addMarker(
                MarkerOptions()
                    .position(coords)
                    .title(nombre)
            )
        }

        // Centra la cámara en el primer gimnasio
        val inicio = gimnasios.first().second
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(inicio, 13f))
    }

    // Maneja la pulsación de la flecha de navegación (Up Button)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Cierra esta actividad y vuelve atrás
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
