package com.example.desenvolvedor.dcc196_trabalho3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tarefa;

import java.util.ArrayList;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class TarefaDAO extends BibliotecaDbHelper {

    public TarefaDAO(Context context) {
        super(context);
    }

    public void inserirTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = retornaDadosTarefa(tarefa);
        db.insert(BibliotecaContract.Tarefa.TABLE_NAME, null, values);
    }

    @NonNull
    private ContentValues retornaDadosTarefa(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(BibliotecaContract.Tarefa.COLUMN_TITULO, tarefa.getTitulo());
        values.put(BibliotecaContract.Tarefa.COLUMN_DESCRICAO, tarefa.getDescricao());
        values.put(BibliotecaContract.Tarefa.COLUMN_DIFICULDADE, tarefa.getDificuldade());
        values.put(BibliotecaContract.Tarefa.COLUMN_ESTADO, tarefa.getEstado());
        return values;

    }

    public ArrayList<Tarefa> GetTodasTarefas() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(BibliotecaContract.Tarefa.SQL_SELECT_TAREFAS, null);


        ArrayList<Tarefa> tarefas = new ArrayList<>();

        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            tarefa.setId(c.getInt(c.getColumnIndex(BibliotecaContract.Tarefa._ID)));
            tarefa.setTitulo(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_TITULO)));
            tarefa.setDescricao(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DESCRICAO)));
            tarefa.setDificuldade(c.getInt(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DIFICULDADE)));
            tarefa.setEstado(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_ESTADO)));
            tarefas.add(tarefa);
        }

        c.close();

        return tarefas;
    }

    public Tarefa getTarefa(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+BibliotecaContract.Tarefa.TABLE_NAME+" WHERE id="+id,null);


            Tarefa tarefa = new Tarefa();

            tarefa.setId(c.getInt(c.getColumnIndex(BibliotecaContract.Tarefa._ID)));
            tarefa.setTitulo(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_TITULO)));
            tarefa.setDescricao(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DESCRICAO)));
            tarefa.setDificuldade(c.getInt(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_DIFICULDADE)));
            tarefa.setEstado(c.getString(c.getColumnIndex(BibliotecaContract.Tarefa.COLUMN_ESTADO)));



        c.close();

        return tarefa;
    }


    public void alterarTarefa(Tarefa tarefa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = retornaDadosTarefa(tarefa);
        String[] id ={String.valueOf(tarefa.getId())};
        db.update(BibliotecaContract.Tarefa.TABLE_NAME, values, BibliotecaContract.Tarefa._ID+" = ?", id);
    }

    public void removerTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        String[] id ={String.valueOf(tarefa.getId())};
        db.delete(BibliotecaContract.Tarefa.TABLE_NAME, BibliotecaContract.Tarefa._ID+" = ?", id);
    }


}