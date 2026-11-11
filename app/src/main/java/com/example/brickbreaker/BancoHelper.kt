
package com.example.brickbreaker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "BrickBreaker.db"
        private const val DATABASE_VERSION = 1
    }

    // Ativa as chaves estrangeiras
    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    // Cria as tabelas na primeira execução
    override fun onCreate(db: SQLiteDatabase) {

        // 1. Tabela JOGADOR
        db.execSQL("""
            CREATE TABLE JOGADOR (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                data_cadastro TEXT NOT NULL
            )
        """)

        // 2. Tabela FASE
        db.execSQL("""
            CREATE TABLE FASE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                nivel INTEGER NOT NULL
            )
        """)

        // 3. Tabela PARTIDA
        db.execSQL("""
            CREATE TABLE PARTIDA (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                jogador_id INTEGER NOT NULL,
                pontuacao INTEGER NOT NULL,
                FOREIGN KEY (jogador_id)
                    REFERENCES JOGADOR(id)
                    ON DELETE CASCADE
            )
        """)

        // 4. Tabela RANKING
        db.execSQL("""
            CREATE TABLE RANKING (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome_jogador TEXT NOT NULL,
                pontuacao INTEGER NOT NULL
            )
        """)
    }

    // Gerencia futuras atualizações do banco
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Implementar migrações quando necessário
    }
}
