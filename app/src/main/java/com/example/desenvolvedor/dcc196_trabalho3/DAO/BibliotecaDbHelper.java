package com.example.desenvolvedor.dcc196_trabalho3.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class BibliotecaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 2;

    BibliotecaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(BibliotecaContract.Tarefa.SQL_CREATE_TAREFA);
        db.execSQL(BibliotecaContract.Tag.SQL_CREATE_TAG);
        db.execSQL(BibliotecaContract.Relacao.SQL_CREATE_RELACAO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(BibliotecaContract.Tarefa.SQL_DROP_TAREFA);
        db.execSQL(BibliotecaContract.Tag.SQL_DROP_TAG);
        db.execSQL(BibliotecaContract.Relacao.SQL_CREATE_RELACAO);
        onCreate(db);
    }
}
