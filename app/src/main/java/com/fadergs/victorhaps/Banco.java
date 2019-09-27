package com.fadergs.victorhaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "AppApsVictor";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS times ( " +
                " idTime INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT ," +
                " pontos INTEGER );" );
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS jogadores ( " +
                " idJogador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " idTime INTEGER," +
                " nome TEXT ," +
                " numero INTEGER ," +
                " FOREIGN KEY(idTime) REFERENCES times(idTime));" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
