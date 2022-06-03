package com.SenzaNome0;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*representa un grafo diretto*/
public class Grafo {
    private final Map<Integer, String> idToNome;
    private final Map<Integer, Set<Nodo>> grafo;

    public Grafo() {
        idToNome = new HashMap<>();
        grafo = new HashMap<>();
    }

    public String getNome(int id) {
        return idToNome.get(id);
    }
}
