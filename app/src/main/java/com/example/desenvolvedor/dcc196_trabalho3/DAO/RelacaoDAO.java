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

    public ArrayList<Tarefa> buscarTarefasAssociadas(Tag tag) {
        SQLiteDatabase db = getReadableDatabase();

        final String sql = "SELECT * FROM "+BibliotecaContract.Tarefa.TABLE_NAME+
                " INNER JOIN "+BibliotecaContract.Relacao.TABLE_NAME+" ON "+
                BibliotecaContract.Tarefa.TABLE_NAME+"."+BibliotecaContract.Tarefa._ID+" = "+
                BibliotecaContract.Relacao.TABLE_NAME+"."+BibliotecaContract.Relacao.COLUMN_TAREFA+
                " WHERE "+BibliotecaContract.Relacao.TABLE_NAME+"."+BibliotecaContract.Relacao.COLUMN_TAG+" = ?";


        String[] id = {String.valueOf(tag.getId())};

        Cursor cursor = db.rawQuery(sql, id);

        ArrayList<Tarefa> tarefas = new ArrayList<>();
        while(cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            tarefa.setId(cursor.getInt(cursor.getColumnIndex(BibliotecaContract.Tarefa._ID)));
            tarefa.setTitulo(cursor.getString(cursor.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_TITULO)));
            tarefa.setDescricao(cursor.getString(cursor.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DESCRICAO)));
            tarefa.setDificuldade(cursor.getInt(cursor.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DIFICULDADE)));
            tarefa.setEstado(cursor.getString(cursor.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_ESTADO)));

            tarefas.add(tarefa);
        }

        cursor.close();

        return tarefas;
    }


}
