package com.example.desenvolvedor.dcc196_trabalho3.Modelo;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private int dificuldade;
    private String estado;//A fazer , Em Execução, Bloqueada e Concluida

    public Tarefa(int id, String titulo, String descricao, int dificuldade, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.estado = estado;
    }


    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao, int dificuldade, String estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.estado = estado;
    }

    public int getId() {
            return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return titulo;
    }
}