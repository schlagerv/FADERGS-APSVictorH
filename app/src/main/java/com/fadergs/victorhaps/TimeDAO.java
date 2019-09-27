package com.fadergs.victorhaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TimeDAO {
    public static void inserirTime(Context contexto, Time time) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", time.getNome());
        valores.put("pontos", time.getPontos());

        db.insert("times", null, valores);

    }

    public static void editarTime(Context contexto, Time time) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", time.getNome());
        valores.put("pontos", time.getPontos());

        db.update("times", valores,
                " idTime = " + time.getId(), null);

    }

    public static void excluirTime(Context contexto, int idTime) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("times", " idTime = " + idTime,
                null);
    }


    public static List<Time> getTimes(Context contexto) {
        List<Time> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM times ORDER BY idTime",
                null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Time time = new Time();
                time.setId(cursor.getInt(0));
                time.setNome(cursor.getString(1));
                time.setPontos(cursor.getInt(2));
                lista.add(time);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static Time getTimeById(Context contexto, int idTime) {
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM times WHERE idTime = " + idTime;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Time time = new Time();
            time.setId(cursor.getInt(0));
            time.setNome(cursor.getString(1));
            time.setPontos(cursor.getInt(2));

            return time;
        } else {
            return null;
        }
    }
}

