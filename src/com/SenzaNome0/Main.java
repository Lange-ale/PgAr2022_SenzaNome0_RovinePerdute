package com.SenzaNome0;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML();
        int[] filenames = {5, 12, 50, 200, 2000, 10000};
        for (int i : filenames) {
            try {
                Grafo grafoTonatiuh = parserXML.getGrafo("Copy of PgAr_Map_" + i + ".xml");
                Grafo grafoMetztli = new Grafo(grafoTonatiuh);

                grafoTonatiuh.initDistances(true);
                grafoMetztli.initDistances(false);

                System.out.println(grafoMetztli.AlberoCamminiMinimi());
                System.out.println();
                System.out.println();
                System.out.println();
               // System.out.println(grafoTonatiuh);
                //System.out.println(grafoMetztli);

            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
    }
}