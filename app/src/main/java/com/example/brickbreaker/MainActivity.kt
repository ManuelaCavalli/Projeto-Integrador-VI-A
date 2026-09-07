package com.example.brickbreaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        setContentView(R.layout.activity_main)

        val btnIntegrantes = findViewById<Button>(R.id.btnIntegrantes)
        val btnIniciarJogo = findViewById<Button>(R.id.btnIniciarJogo)
        val btnConfiguracoes = findViewById<Button>(R.id.btnConfiguracoes)

        btnIntegrantes.setOnClickListener {
            val intent = Intent(this, MembersActivity::class.java)
            startActivity(intent)
        }

        btnIniciarJogo.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        btnConfiguracoes.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}