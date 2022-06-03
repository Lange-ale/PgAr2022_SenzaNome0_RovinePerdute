package com.SenzaNome0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
    
    public ArrayList<Double> AlberoCamminiMinimi() {
        ArrayList<Double> distanze = new ArrayList<>();
        ArrayList<Boolean> considerati = new ArrayList<>();
        ArrayList<Integer> nPadri = new ArrayList<>();
        ArrayList<Integer> maxIdPassato = new ArrayList<>();
        PriorityQueue<Distanza> pq = new PriorityQueue<>();
        Map<Integer, Integer> precedenti = new HashMap<>();
        for (int i = 0; i < grafo.size(); i++) {
            distanze.add(Double.MAX_VALUE);
            considerati.add(false);
            nPadri.add(0);
            maxIdPassato.add(i);
        }
        precedenti.put(0, 0);
        distanze.set(0, 0.0);
        pq.add(new Distanza(0,0));

        while (!pq.isEmpty()){
            if (considerati.get(pq.peek().getId())){
                pq.poll();
                continue;
            }
            Distanza attuale = pq.poll();
            double distAttualeNodo;
            for (Integer idNodo: grafo.get(attuale.getId()).keySet()) {
                distAttualeNodo = grafo.get(attuale.getId()).get(idNodo);
                if (!considerati.get(idNodo)) {
                    if (attuale.getDistanza() + distAttualeNodo < distanze.get(idNodo))
                        setMinimoInNodo(distanze, nPadri, maxIdPassato, pq, precedenti, attuale, distAttualeNodo, idNodo);
                    else if (attuale.getDistanza() + distAttualeNodo == distanze.get(idNodo)) {
                        if (nPadri.get(idNodo) < nPadri.get(attuale.getId()))
                            setMinimoInNodo(distanze, nPadri, maxIdPassato, pq, precedenti, attuale, distAttualeNodo, idNodo);
                        else if (nPadri.get(idNodo) == nPadri.get(attuale.getId()))
                            if (maxIdPassato.get(idNodo) > maxIdPassato.get(attuale.getId()))
                                setMinimoInNodo(distanze, nPadri, maxIdPassato, pq, precedenti, attuale, distAttualeNodo, idNodo);
                    }
                }
            }

            considerati.set(attuale.getId(), true);
        }

        System.out.println(precedenti);

        return distanze;
    }

    private void setMinimoInNodo(ArrayList<Double> distanze, ArrayList<Integer> nPadri, ArrayList<Integer> maxIdPassato,
                                 PriorityQueue<Distanza> pq, Map<Integer, Integer> precedenti,
                                 Distanza attuale, double distAttualeNodo, Integer idNodo) {
        distanze.set(idNodo, attuale.getDistanza() + distAttualeNodo);
        pq.add(new Distanza(idNodo, distanze.get(idNodo)));
        nPadri.set(idNodo, nPadri.get(attuale.getId()) + 1);
        maxIdPassato.set(idNodo, Math.max(maxIdPassato.get(attuale.getId()), maxIdPassato.get(idNodo)));
        precedenti.put(idNodo, attuale.getId());
    }
}
