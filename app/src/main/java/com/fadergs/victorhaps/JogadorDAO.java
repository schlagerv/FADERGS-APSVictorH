package com.fadergs.victorhaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {
    public static void inserirJogador(Context contexto, Jogador jogador) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.getNome());
        valores.put("idTime", jogador.getIdTime());
        valores.put("numero", jogador.getCamisa());

        db.insert("jogadores", null, valores);
    }

    public static void editarJogador(Context contexto, Jogador jogador) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", jogador.getNome());
        valores.put("idTime", jogador.getIdTime());
        valores.put("numero", jogador.getCamisa());

        db.update("jogadores", valores,
                " idJogador = " + jogador.getId(), null);

    }

    public static void excluirJogador(Context contexto, int idJogador) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("jogadores", " idJogador = " + idJogador,
                null);
    }


    public static List<Jogador> getJogadores(Context contexto, int idTime) {
        List<Jogador> listaJogador = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();


        String sql = "SELECT idJogador,nome,numero FROM jogadores WHERE idTime = " + idTime + " ORDER BY numero ";
        Cursor cursor = db.rawQuery( sql ,null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Jogador jogador = new Jogador();
                jogador.setId(cursor.getInt(0));
                jogador.setNome(cursor.getString(1));
                jogador.setCamisa(cursor.getInt(2));
                listaJogador.add(jogador);
            } while (cursor.moveToNext());
        }
        return listaJogador;
    }

    public static Jogador getJogadorById(Context contexto, int idJogador) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM jogadores WHERE idJogador = " + idJogador;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Jogador jogador = new Jogador();
            jogador.setId(cursor.getInt(0));
            jogador.setNome(cursor.getString(1));
            jogador.setCamisa(cursor.getInt(2));

            return jogador;
        } else {
            return null;
        }
    }
}
