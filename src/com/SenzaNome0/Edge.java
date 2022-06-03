package com.SenzaNome0;

public class Edge {
    private int distanza;
    private int idDest;

    public Edge(int distanza, int idDest) {
        this.distanza = distanza;
        this.idDest = idDest;
    }

    public Edge(int idDest) {
        this.idDest = idDest;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public int getIdDest() {
        return idDest;
    }

    public void setIdDest(int idDest) {
        this.idDest = idDest;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "distanza=" + distanza +
                ", idDest=" + idDest +
                '}';
    }
}
