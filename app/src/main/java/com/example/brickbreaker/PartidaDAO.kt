
package com.example.brickbreaker

import android.content.ContentValues

class PartidaDAO(private val bancoHelper: BancoHelper) {

    // REGISTRAR PARTIDA
    fun registrarPartida(
        jogadorId: Long,
        pontuacao: Int
    ): Long {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("jogador_id", jogadorId)
            put("pontuacao", pontuacao)
        }

        val resultado = db.insert(
            "PARTIDA",
            null,
            valores
        )

        return resultado
    }

    // LISTAR PARTIDAS DE UM JOGADOR
    fun listarPartidas(jogadorId: Long): List<Int> {

        val db = bancoHelper.readableDatabase
        val pontuacoes = mutableListOf<Int>()

        val cursor = db.rawQuery(
            "SELECT pontuacao FROM PARTIDA WHERE jogador_id = ?",
            arrayOf(jogadorId.toString())
        )

        cursor.use {
            while (it.moveToNext()) {
                val pontuacao = it.getInt(0)
                pontuacoes.add(pontuacao)
            }
        }

        return pontuacoes
    }

    // LISTAR RANKING (MAIOR PONTUAÇÃO DE CADA JOGADOR)
    fun listarRanking(): List<String> {

        val db = bancoHelper.readableDatabase
        val ranking = mutableListOf<String>()

        val cursor = db.rawQuery(
            """
            SELECT JOGADOR.nome, MAX(PARTIDA.pontuacao)
            FROM PARTIDA
            INNER JOIN JOGADOR
            ON PARTIDA.jogador_id = JOGADOR.id
            GROUP BY JOGADOR.id, JOGADOR.nome
            ORDER BY MAX(PARTIDA.pontuacao) DESC
            """,
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val nome = it.getString(0)
                val pontuacao = it.getInt(1)

                ranking.add("$nome - $pontuacao pontos")
            }
        }

        return ranking
    }
}
