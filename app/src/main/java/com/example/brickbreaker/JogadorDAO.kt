package com.example.brickbreaker

import android.content.ContentValues

class JogadorDAO(private val bancoHelper: BancoHelper) {

    // INSERIR JOGADOR
    fun inserirJogador(nome: String, dataCadastro: String): Long {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("nome", nome)
            put("data_cadastro", dataCadastro)
        }

        val resultado = db.insert("JOGADOR", null, valores)

        return resultado
    }

    // LISTAR JOGADORES
    fun listarJogadores(): List<String> {

        val db = bancoHelper.readableDatabase
        val jogadores = mutableListOf<String>()

        val cursor = db.rawQuery(
            "SELECT nome FROM JOGADOR",
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val nome = it.getString(0)
                jogadores.add(nome)
            }
        }

        return jogadores
    }

    // ATUALIZAR NOME
    fun atualizarNome(id: Long, novoNome: String): Int {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("nome", novoNome)
        }

        val resultado = db.update(
            "JOGADOR",
            valores,
            "id = ?",
            arrayOf(id.toString())
        )

        return resultado
    }

    // BUSCAR JOGADOR OU CADASTRAR SE NÃO EXISTIR
    fun obterOuCriarJogador(
        email: String,
        dataCadastro: String
    ): Long {

        val db = bancoHelper.writableDatabase

        val cursor = db.rawQuery(
            "SELECT id FROM JOGADOR WHERE nome = ?",
            arrayOf(email)
        )

        cursor.use {
            if (it.moveToFirst()) {
                return it.getLong(0)
            }
        }

        val valores = ContentValues().apply {
            put("nome", email)
            put("data_cadastro", dataCadastro)
        }

        return db.insert("JOGADOR", null, valores)
    }

    // OBTER NOME DO JOGADOR POR ID
    fun obterNomePorId(id: Long): String? {
        val db = bancoHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT nome FROM JOGADOR WHERE id = ?",
            arrayOf(id.toString())
        )

        cursor.use {
            if (it.moveToFirst()) {
                return it.getString(0)
            }
        }

        return null
    }
}
