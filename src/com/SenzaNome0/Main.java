package com.SenzaNome0;
public class Main {
    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML();
        int[] filenames = {5, 12, 50, 200, 2000, 10000};
        for (int i : filenames) {
            Grafo grafo = parserXML.getGrafo("Copy of PgAr_Map_" + i + ".xml");
            //initDistances(true);
            System.out.println(grafo);
            break; //TODO DEL ME
        }
    }

    private static void initDistances(boolean is) {

    }
}