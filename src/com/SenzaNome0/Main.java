package com.SenzaNome0;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML();
        int[] filenames = {5, 12, 50, 200, 2000, 10000};
        for (int i : filenames) {
            try {
                Grafo grafoTonatiuh = parserXML.getGrafo("Copy of PgAr_Map_" + i + ".xml");
                Grafo grafoMetztli = new Grafo(grafoTonatiuh);
                initDistances(grafoTonatiuh, false);
                initDistances(grafoTonatiuh, true);
                System.out.println(grafoTonatiuh);
                System.out.println(grafoMetztli);

                break; // TODO: DEL ME
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initDistances(Grafo grafo, boolean is) {

    }
}