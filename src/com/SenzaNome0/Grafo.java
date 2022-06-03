package com.SenzaNome0;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
    private final Map<Integer, Nodo> idToNodo;
    private final Map<Integer, Set<Edge>> grafo;

    public Grafo(int nNodi) {
        idToNodo = new HashMap<>();
        grafo = new HashMap<>();
        for (int i = 0; i < nNodi; i++)
            grafo.put(i, new HashSet<>());
    }

    public void addNodo(Nodo nodo) {
        idToNodo.put(nodo.getId(), nodo);
        grafo.put(nodo.getId(), new HashSet<>());
    }

    public void addEdge(int idPart, int idDest, int distanza) {
        grafo.get(idPart).add(new Edge(distanza, idDest));
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "idToNodo=" + idToNodo +
                ", grafo=" + grafo +
                '}';
    }
}
