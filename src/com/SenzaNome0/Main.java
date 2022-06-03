package com.SenzaNome0;

public class Main {
    public static void main(String[] args) {
        ParserXML parser = new ParserXML();
        Grafo grafo = parser.getGrafo("Copy of PgAr_Map_5.xml", false);
        grafo = new Grafo(5);
        grafo.addNodo(new Nodo(0, "A", 0, 0, 0));
        grafo.addNodo(new Nodo(1, "B", 0, 0, 0));
        grafo.addNodo(new Nodo(2, "C", 0, 0, 0));
        grafo.addNodo(new Nodo(3, "D", 0, 0, 0));
        grafo.addNodo(new Nodo(4, "E", 0, 0, 0));
    }
}