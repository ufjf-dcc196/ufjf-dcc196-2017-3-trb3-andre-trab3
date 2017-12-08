package com.example.desenvolvedor.dcc196_trabalho3.Modelo;

/**
 * Created by Desenvolvedor on 08/12/2017.
 */

public class Tag {

    private int id;
    private String tag;

    public Tag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}
