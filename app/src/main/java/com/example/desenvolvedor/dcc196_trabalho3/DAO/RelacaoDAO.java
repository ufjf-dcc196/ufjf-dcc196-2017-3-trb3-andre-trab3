package com.example.desenvolvedor.dcc196_trabalho3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;
import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tarefa;

import java.util.ArrayList;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class RelacaoDAO extends BibliotecaDbHelper {

        public RelacaoDAO(Context context) {
            super(context);
        }

        public void inserirAtribuicao(Tarefa tarefa, Tag tag) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Relacao.COLUMN_TAREFA, tarefa.getId());
            values.put(BibliotecaContract.Relacao.COLUMN_TAG, tag.getId());
            db.insert(BibliotecaContract.Relacao.TABLE_NAME, null, values);

        }

        public ArrayList<Integer> getTarefa(int idTag) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+BibliotecaContract.Relacao.COLUMN_TAREFA+" FROM " + BibliotecaContract.Relacao.TABLE_NAME + " WHERE " + BibliotecaContract.Relacao.COLUMN_TAG + "=" + idTag, null);


        ArrayList<Integer> tarefas = new ArrayList<>();
        while (cursor.moveToNext()) {

            tarefas.add(cursor.getInt(cursor.getColumnIndex(BibliotecaContract.Relacao.COLUMN_TAREFA)));

        }

        cursor.close();
        return tarefas;

        }

        public ArrayList<Integer> getTag ( int idTarefa){


            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT "+BibliotecaContract.Relacao.COLUMN_TAG+" FROM " + BibliotecaContract.Relacao.TABLE_NAME + " WHERE " + BibliotecaContract.Relacao.COLUMN_TAREFA + "=" + idTarefa, null);

            ArrayList<Integer> tags = new ArrayList<>();
            while (cursor.moveToNext()) {



                tags.add(cursor.getInt(cursor.getColumnIndex(BibliotecaContract.Relacao.COLUMN_TAG)));


            }

            cursor.close();

            return tags;
        }


    }



