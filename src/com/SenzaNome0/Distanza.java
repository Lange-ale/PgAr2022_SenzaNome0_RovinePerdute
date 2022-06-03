package com.SenzaNome0;

public class Distanza implements Comparable{
    private int id;
    private double distanza;

    public Distanza(int id, double distanza) {
        this.id = id;
        this.distanza = distanza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistanza() {
        return distanza;
    }

    public void setDistanza(double distanza) {
        this.distanza = distanza;
    }

    @Override
    public int compareTo(Object o) {
        Distanza d = (Distanza) o;
        return Double.compare(this.distanza, d.distanza);
    }

    @Override
    public String toString() {
        return "Distanza{" +
                "id=" + id +
                ", distanza=" + distanza +
                '}';
    }
}
