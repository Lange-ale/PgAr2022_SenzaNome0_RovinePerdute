package com.SenzaNome0;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParserXML {
    public Grafo getGrafo(String filename) {
        try {
            // Creiamo il grafo
            Grafo grafo = new Grafo();

            // Apri il documento XML in base al FILENAME
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename));

            // Prendi l'elemento radice, ovvero la mappa
            Element mappa = documento.getDocumentElement();

            // Prendi la lista dei nodi facenti parte della mappa (ovvero le città)
            NodeList listaCitta = mappa.getElementsByTagName("city");

            for (int i = 0; i < listaCitta.getLength(); i++) {
                Node node = listaCitta.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getAttribute("id"));
                    String nome = element.getAttribute("name");
                    int x = Integer.parseInt(element.getAttribute("x"));
                    int y = Integer.parseInt(element.getAttribute("y"));
                    int h = Integer.parseInt(element.getAttribute("h"));

                    Nodo citta = new Nodo(id, nome, x, y, h);

                    grafo.addNodo(citta);
                }
            }
            return grafo;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
}
