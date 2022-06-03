package com.SenzaNome0;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class CamminoMinimo {
    private final ArrayList<Double> distanze;
    private final Map<Integer, Integer> precedenti;
    private final Stack<Integer> percorsoMinimo = new Stack<>();

    public CamminoMinimo(ArrayList<Double> distanze, Map<Integer, Integer> precedenti) {
        this.distanze = distanze;
        this.precedenti = precedenti;

        initStack();
    }

    private void initStack() {
        int curr = distanze.size()-1; // prendo l'ultimo elemento

        while (curr != 0) {
            percorsoMinimo.push(curr);
            curr = precedenti.get(curr);
        }
    }

    public double getDistanzaPercorsa() {
        return distanze.get(distanze.size()-1);
    }

    public int getNumeroCitta() {
        return percorsoMinimo.size();
    }

    public Stack<Integer> getPercorsoMinimo() {
        return percorsoMinimo;
    }
}
