package com.SenzaNome0;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

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

                CamminoMinimo camminoMinimoTonatiuh = grafoTonatiuh.getCamminoMinimo();
                CamminoMinimo camminoMinimoMetztli = grafoMetztli.getCamminoMinimo();

                scriviDocumentoFinale("./Routes" + i +  ".xml", camminoMinimoTonatiuh, camminoMinimoMetztli, grafoTonatiuh);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scriviDocumentoFinale(String nomeFile, CamminoMinimo camminoMinimoTonatiuh, CamminoMinimo camminoMinimoMetztli, Grafo grafo) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("routes");

            // create data elements and place them under root
            Element route1 = dom.createElement("route");
            route1.setAttribute("team", "Tonathiu");
            route1.setAttribute("cost", String.valueOf(camminoMinimoTonatiuh.getDistanzaPercorsa()));
            route1.setAttribute("cities", String.valueOf(camminoMinimoTonatiuh.getNumeroCitta()));

            Element e = null;
            while (!camminoMinimoTonatiuh.getPercorsoMinimo().isEmpty()) {
                e = dom.createElement("city");
                int id = camminoMinimoTonatiuh.getPercorsoMinimo().pop();
                e.setAttribute("id", String.valueOf(id));
                e.setAttribute("name", grafo.getNodo(id).getNome());

                route1.appendChild(e);
            }

            Element route2 = dom.createElement("route");
            route2.setAttribute("team", "Metztli");
            route2.setAttribute("cost", String.valueOf(camminoMinimoMetztli.getDistanzaPercorsa()));
            route2.setAttribute("cities", String.valueOf(camminoMinimoMetztli.getNumeroCitta()));

            while (!camminoMinimoMetztli.getPercorsoMinimo().isEmpty()) {
                e = dom.createElement("city");
                int id = camminoMinimoMetztli.getPercorsoMinimo().pop();
                e.setAttribute("id", String.valueOf(id));
                e.setAttribute("name", grafo.getNodo(id).getNome());

                route2.appendChild(e);
            }

            rootEle.appendChild(route1);
            rootEle.appendChild(route2);

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(nomeFile)));
            } catch (TransformerException | IOException te) {
                te.printStackTrace();
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }
}