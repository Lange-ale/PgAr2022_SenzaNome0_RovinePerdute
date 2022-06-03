package com.SenzaNome0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private final Map<Integer, Nodo> idToNodo;
    private final Map<Integer, Map<Integer, Double>> grafo;

    public Grafo() {
        idToNodo = new HashMap<>();
        grafo = new HashMap<>();
    }

    public Grafo(Grafo grafo) {
        this.idToNodo = new HashMap<>();
        this.idToNodo.putAll(grafo.idToNodo);

        this.grafo = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Double>> entry1 : grafo.grafo.entrySet()) {
            Map<Integer, Double> temp = new HashMap<>(entry1.getValue());
            this.grafo.put(entry1.getKey(), temp);
        }
    }

    public void addNodo(Nodo nodo) {
        idToNodo.put(nodo.getId(), nodo);
        grafo.put(nodo.getId(), new HashMap<>());
    }

    public void addEdge(int idPart, int idDest) {
        grafo.get(idPart).put(idDest, Double.MAX_VALUE);
    }

    public void setDist(int idPart, int idDest, double dist) {
        grafo.get(idPart).put(idDest, dist);
    }
    
    public void initDistances(boolean isTonatiuh) {
        for (Map.Entry<Integer, Map<Integer, Double>> grafoEntry : grafo.entrySet()) {
            int idPartenza = grafoEntry.getKey();
            Nodo partenza = idToNodo.get(idPartenza);

            for (Map.Entry<Integer, Double> linkEntry : grafoEntry.getValue().entrySet()) {
                int idDestinazione = linkEntry.getKey();
                Nodo destinazione = idToNodo.get(idDestinazione);

                if (grafo.get(idPartenza).get(idDestinazione) == Double.MAX_VALUE) {
                    double distanza = Double.MAX_VALUE;

                    if (isTonatiuh) {
                        distanza = Math.sqrt(Math.pow((destinazione.getX() - partenza.getX()), 2) + Math.pow((destinazione.getY() - partenza.getY()), 2));
                    } else {
                        distanza = Math.abs(destinazione.getH() - partenza.getH());
                    }

                    setDist(idPartenza, idDestinazione, distanza);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "idToNodo=" + idToNodo +
                ", grafo=" + grafo +
                '}';
    }
    
    /*
    public Map AlberoCamminiMinimi(){
        Map<Integer, Integer> distanze = new HashMap<>();
        Map<Integer, Integer> padri = new HashMap<>();
        for (int i = 0; i < idToNodo.size(); i++) {
            distanze.put(i, Integer.MAX_VALUE);
            padri.put(i, -1);
        }
        distanze.put(0, 0);
        for (int i = 0; i < idToNodo.size(); i++) {
            int nodoMin = -1;
            int distanzaMin = Integer.MAX_VALUE;
            for (int j = 0; j < idToNodo.size(); j++) {
                if (distanze.get(j) < distanzaMin && !distanze.containsKey(j)) {
                    nodoMin = j;
                    distanzaMin = distanze.get(j);
                }
            }
            if (nodoMin == -1)
                break;
            for (Edge edge : grafo.get(nodoMin)) {
                int idDest = edge.getIdDest();
                int distanza = edge.getDistanza();
                if (distanze.get(nodoMin) + distanza < distanze.get(idDest)) {
                    distanze.put(idDest, distanze.get(nodoMin) + distanza);
                    padri.put(idDest, nodoMin);
                }
            }
        }
        return padri;
    }

     */
}
