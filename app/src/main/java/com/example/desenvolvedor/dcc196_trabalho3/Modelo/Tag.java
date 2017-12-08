package com.example.desenvolvedor.dcc196_trabalho3.Modelo;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class Tag {

    private int id;
    private Tarefa tarefa;

    public Tag(int id, Tarefa tarefa) {
        this.id = id;
        this.tarefa = tarefa;
    }

    public Tag(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
