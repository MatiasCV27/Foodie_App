package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)
        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)

        cerrarFaq.setOnClickListener {
            // Cerrar la actividad al hacer clic en la ImageView cerrarFaq
            finish()
        }

    }
}