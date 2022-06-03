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
    private static final String FILENAME = "./Copy of PgAr_Map_5.xml";

    public Grafo getGrafo() {
        try {
            // Creiamo il grafo
            Grafo grafo = new Grafo();

            // Apri il documento XML in base al FILENAME
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FILENAME));

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
                    int z = Integer.parseInt(element.getAttribute("z"));

                    Nodo citta = new Nodo(id, nome, x, y, z);

                    grafo.addNodo(citta);
                }

            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        new ParserXML().getGrafo();
    }
}
