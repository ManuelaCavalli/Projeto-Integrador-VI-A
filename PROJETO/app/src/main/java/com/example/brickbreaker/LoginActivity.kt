
package com.example.brickbreaker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private lateinit var bancoHelper: BancoHelper
    private lateinit var jogadorDAO: JogadorDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa o banco de dados e o DAO
        bancoHelper = BancoHelper(this)
        jogadorDAO = JogadorDAO(bancoHelper)

        val email = findViewById<EditText>(R.id.edtEmail)
        val btnComecar = findViewById<Button>(R.id.btnComecar)

        btnComecar.setOnClickListener {

            val endereco = email.text.toString().trim()

            // Verifica se o e-mail é válido
            if (android.util.Patterns.EMAIL_ADDRESS
                    .matcher(endereco).matches()) {

                // Obtém a data atual
                val dataCadastro = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).format(Date())

                // Busca o jogador ou cadastra se não existir
                val jogadorId = jogadorDAO.obterOuCriarJogador(
                    endereco,
                    dataCadastro
                )

                // Abre a tela principal enviando o ID do jogador
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("jogador_id", jogadorId)

                startActivity(intent)
                finish()

            } else {
                email.error = "Digite um e-mail válido"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::bancoHelper.isInitialized) {
            bancoHelper.close()
        }
    }
}
