package com.SenzaNome0;
public class Main {
    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML();
        int[] filenames = {5, 12, 50, 200, 2000, 10000};
        for (int i : filenames) {
            Grafo grafoTonatiuh = parserXML.getGrafo("Copy of PgAr_Map_" + i + ".xml");
            Grafo grafoMetztli = new Grafo(grafoTonatiuh);
            initDistances(grafoTonatiuh, false);
            initDistances(grafoTonatiuh, true);
            System.out.println(grafoTonatiuh);
            System.out.println(grafoMetztli);

            break; //TODO DEL ME
        }
    }

    private static void initDistances(Grafo grafo, boolean is) {

    }
}