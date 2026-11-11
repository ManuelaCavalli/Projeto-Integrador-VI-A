
package com.example.brickbreaker

import android.content.ContentValues

class FaseDAO(private val bancoHelper: BancoHelper) {

    // CADASTRAR FASE
    fun inserirFase(
        nome: String,
        nivel: Int
    ): Long {

        val db = bancoHelper.writableDatabase

        val valores = ContentValues().apply {
            put("nome", nome)
            put("nivel", nivel)
        }

        val resultado = db.insert(
            "FASE",
            null,
            valores
        )

        return resultado
    }

    // LISTAR FASES
    fun listarFases(): List<String> {

        val db = bancoHelper.readableDatabase
        val fases = mutableListOf<String>()

        val cursor = db.rawQuery(
            "SELECT nome, nivel FROM FASE ORDER BY nivel ASC",
            null
        )

        cursor.use {
            while (it.moveToNext()) {

                val nome = it.getString(0)
                val nivel = it.getInt(1)

                fases.add("$nome - Nível $nivel")
            }
        }

        return fases
    }
}
