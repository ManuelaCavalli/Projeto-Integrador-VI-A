package com.example.brickbreaker

import android.content.ContentValues

class RankingDAO(private val bancoHelper: BancoHelper) {

    // INSERIR NO RANKING
    fun inserirRanking(
        nomeJogador: String,
        pontuacao: Int
    ): Long {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("nome_jogador", nomeJogador)
            put("pontuacao", pontuacao)
        }

        val resultado = db.insert(
            "RANKING",
            null,
            valores
        )

        return resultado
    }

    // LISTAR RANKING
    fun listarRanking(): List<String> {

        val db = bancoHelper.readableDatabase
        val ranking = mutableListOf<String>()

        val cursor = db.rawQuery(
            """
            SELECT nome_jogador, pontuacao
            FROM RANKING
            ORDER BY pontuacao DESC
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

    // ATUALIZAR PONTUAÇÃO
    fun atualizarPontuacao(
        nomeJogador: String,
        novaPontuacao: Int
    ): Int {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("pontuacao", novaPontuacao)
        }

        val resultado = db.update(
            "RANKING",
            valores,
            "nome_jogador = ?",
            arrayOf(nomeJogador)
        )

        return resultado
    }

    // ATUALIZAR OU INSERIR NO RANKING (EVITANDO DUPLICADOS E MANTENDO MAIOR PONTUAÇÃO)
    fun atualizarOuInserirRanking(
        nomeJogador: String,
        pontuacao: Int
    ) {
        val db = bancoHelper.writableDatabase

        val cursor = db.rawQuery(
            "SELECT id, pontuacao FROM RANKING WHERE nome_jogador = ?",
            arrayOf(nomeJogador)
        )

        cursor.use {
            if (it.moveToFirst()) {
                val id = it.getLong(0)
                val pontuacaoAtual = it.getInt(1)

                if (pontuacao > pontuacaoAtual) {
                    val valores = ContentValues().apply {
                        put("pontuacao", pontuacao)
                    }
                    db.update("RANKING", valores, "id = ?", arrayOf(id.toString()))
                }
            } else {
                val valores = ContentValues().apply {
                    put("nome_jogador", nomeJogador)
                    put("pontuacao", pontuacao)
                }
                db.insert("RANKING", null, valores)
            }
        }
    }
}
