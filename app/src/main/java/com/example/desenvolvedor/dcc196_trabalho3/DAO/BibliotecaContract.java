package com.example.desenvolvedor.dcc196_trabalho3.DAO;

import android.provider.BaseColumns;

/**
 * Created by Desenvolvedor on 07/12/2017.
 */

public class BibliotecaContract {




    private static final String TYPE_TEXT = " VARCHAR";
    private static final String TYPE_INT = " INTEGER";
    private static final String SEP = ", ";


    static class Tarefa implements BaseColumns {


        static final String TABLE_NAME = "tarefa";
        static final String COLUMN_TITULO = "titulo";
        static final String COLUMN_DESCRICAO = "descricao";
        static final String COLUMN_DIFICULDADE = "dificuldade";
        static final String COLUMN_ESTADO= "estado";

        static final String SQL_CREATE_TAREFA = "CREATE TABLE "+
                Tarefa.TABLE_NAME+" ("+
                Tarefa._ID+TYPE_INT+" PRIMARY KEY AUTOINCREMENT"+SEP+
                Tarefa.COLUMN_TITULO+TYPE_TEXT + SEP +
                Tarefa.COLUMN_DESCRICAO + TYPE_TEXT + SEP +
                Tarefa.COLUMN_DIFICULDADE+TYPE_INT+SEP+
                Tarefa.COLUMN_ESTADO + TYPE_TEXT + ")";

        static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS "+
                Tarefa.TABLE_NAME;

        static final String SQL_SELECT_TAREFAS = "SELECT * FROM "+
                Tarefa.TABLE_NAME + " ORDER BY "+Tarefa.COLUMN_ESTADO;
    }





    static class Tag implements BaseColumns {

        static final String TABLE_NAME = "tag";
        static final String COLUMN_TAG = "tag";

        static final String SQL_CREATE_TAG = "CREATE TABLE "+
                Tag.TABLE_NAME+" ("+
                Tag._ID+TYPE_INT+" PRIMARY KEY AUTOINCREMENT"+SEP+
                Tag.COLUMN_TAG+TYPE_TEXT+")";

        static final String SQL_DROP_TAG = "DROP TABLE IF EXISTS "+
                Tag.TABLE_NAME;

        static final String SQL_SELECT_TAGS = "SELECT * FROM "+
                Tag.TABLE_NAME;
    }

    static class Relacao implements  BaseColumns {
        static final String TABLE_NAME = "TagTarefa";
        static final String COLUMN_TAREFA = "tarefa";
        static final String COLUMN_TAG = "tag";

        static final String SQL_CREATE_RELACAO= "CREATE TABLE "+
                Relacao.TABLE_NAME+" ("+
                Relacao._ID+TYPE_INT+" PRIMARY KEY AUTOINCREMENT"+SEP+
                Relacao.COLUMN_TAREFA+TYPE_INT+" REFERENCES "+Tarefa.TABLE_NAME+" ON DELETE CASCADE"+SEP+
                Relacao.COLUMN_TAG+TYPE_INT+" REFERENCES "+Tag.TABLE_NAME+" ON DELETE CASCADE)";

        static final String SQL_DROP_RELACAO = "DROP TABLE IF EXISTS "+
                Relacao.TABLE_NAME;


    }













}