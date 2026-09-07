package com.example.brickbreaker

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Oculta a barra de status e a barra de navegação
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        // Carrega o layout da tela de configurações
        setContentView(R.layout.activity_settings)

        // Referências aos componentes da tela
        val spinnerColors = findViewById<Spinner>(R.id.spinnerColors)
        val spinnerSize = findViewById<Spinner>(R.id.spinnerSize)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Lista de opções de cores dos blocos
        val colors = arrayOf(
            "Clássico",
            "Arco-íris",
            "Azul",
            "Vermelho",
            "Verde"
        )

        // Lista de opções de tamanho dos blocos
        val sizes = arrayOf(
            "Pequeno",
            "Médio",
            "Grande"
        )

        // Preenche o Spinner de cores
        val colorAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            colors
        )
        colorAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerColors.adapter = colorAdapter

        // Preenche o Spinner de tamanhos
        val sizeAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            sizes
        )
        sizeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerSize.adapter = sizeAdapter

        // Acessa as configurações salvas do jogo
        val preferences = getSharedPreferences(
            "brickbreaker_settings",
            MODE_PRIVATE
        )

        // Carrega a cor salva anteriormente
        spinnerColors.setSelection(
            preferences.getInt("color", 0)
        )

        // Carrega o tamanho salvo anteriormente
        spinnerSize.setSelection(
            preferences.getInt("size", 1)
        )

        // Salva as configurações escolhidas
        btnSalvar.setOnClickListener {

            preferences.edit()
                .putInt("color", spinnerColors.selectedItemPosition)
                .putInt("size", spinnerSize.selectedItemPosition)
                .apply()

            // Fecha a tela após salvar
            finish()
        }

        // Fecha a tela sem alterar as configurações
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}