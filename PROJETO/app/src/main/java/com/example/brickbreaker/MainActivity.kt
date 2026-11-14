
package com.example.brickbreaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var jogadorId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recebe o ID enviado pelo LoginActivity
        jogadorId = intent.getLongExtra("jogador_id", -1L)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        setContentView(R.layout.activity_main)

        val btnIntegrantes = findViewById<Button>(R.id.btnIntegrantes)
        val btnIniciarJogo = findViewById<Button>(R.id.btnIniciarJogo)
        val btnConfiguracoes = findViewById<Button>(R.id.btnConfiguracoes)
        val btnRanking = findViewById<Button>(R.id.btnRanking)

        btnIntegrantes.setOnClickListener {
            val intent = Intent(this, MembersActivity::class.java)
            startActivity(intent)
        }

        btnIniciarJogo.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)

            // Encaminha o ID do jogador para o jogo
            intent.putExtra("jogador_id", jogadorId)

            startActivity(intent)
        }

        btnConfiguracoes.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        btnRanking.setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }
    }
}
