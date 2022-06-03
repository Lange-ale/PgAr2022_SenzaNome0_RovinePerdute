package com.SenzaNome0;

public class Nodo {
    private int id;
    private String nome;
    private int x, y, h;

    public Nodo(int id, String nome, int x, int y, int h) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nodo)) return false;
        Nodo nodo = (Nodo) o;
        return id == nodo.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", h=" + h +
                '}';
    }
}
