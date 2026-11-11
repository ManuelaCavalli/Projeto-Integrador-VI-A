
package com.example.brickbreaker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RankingActivity : AppCompatActivity() {

    private lateinit var bancoHelper: BancoHelper
    private lateinit var rankingDAO: RankingDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ranking)

        bancoHelper = BancoHelper(this)
        rankingDAO = RankingDAO(bancoHelper)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val txtPrimeiro = findViewById<TextView>(R.id.txtPrimeiro)
        val txtSegundo = findViewById<TextView>(R.id.txtSegundo)
        val txtTerceiro = findViewById<TextView>(R.id.txtTerceiro)

        btnVoltar.setOnClickListener {
            finish()
        }

        // Consultar ranking no SQLite
        val resultados = rankingDAO.listarRanking()

        txtPrimeiro.text = resultados.getOrNull(0)?.let {
            "1º  $it"
        } ?: "1º  Nenhum jogador"

        txtSegundo.text = resultados.getOrNull(1)?.let {
            "2º  $it"
        } ?: "2º  Nenhum jogador"

        txtTerceiro.text = resultados.getOrNull(2)?.let {
            "3º  $it"
        } ?: "3º  Nenhum jogador"
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::bancoHelper.isInitialized) {
            bancoHelper.close()
        }
    }
}
