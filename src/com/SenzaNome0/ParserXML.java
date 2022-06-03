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

    private Nodo getCitta(Element element) {
        int id = Integer.parseInt(element.getAttribute("id"));
        String nome = element.getAttribute("name");
        int x = Integer.parseInt(element.getAttribute("x"));
        int y = Integer.parseInt(element.getAttribute("y"));
        int h = Integer.parseInt(element.getAttribute("h"));

        return new Nodo(id, nome, x, y, h);
    }

    public Grafo getGrafo(String filename) throws ParserConfigurationException, IOException, SAXException {
        // Creiamo il grafo
        Grafo grafo = new Grafo();

        // Apri il documento XML in base al FILENAME
        Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename));

        // Prendi l'elemento radice, ovvero la mappa
        Element mappa = documento.getDocumentElement();

        // Prendi la lista dei nodi facenti parte della mappa (ovvero le città)
        NodeList listaCitta = mappa.getElementsByTagName("city");

        for (int i = 0; i < listaCitta.getLength(); i++) {
            Node cityNode = listaCitta.item(i);

            if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                // Aggiunta di un nodo al grafo, senza tenere conto dei riferimenti
                Element cityElement = (Element) cityNode;

                Nodo citta = getCitta(cityElement);
                grafo.addNodo(citta);

                // Aggiunta di ogni singolo riferimento per il nodo corrente
                NodeList listaRiferimenti = cityElement.getElementsByTagName("link");

                for (int j = 0; j < listaRiferimenti.getLength(); j++) {
                    Node linkNode = listaRiferimenti.item(j);

                    if (linkNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element linkElement = (Element) linkNode;
                        int idDestinazione = Integer.parseInt(linkElement.getAttribute("to"));

                        grafo.addEdge(citta.getId(), idDestinazione);
                    }
                }
            }
        }

        return grafo;
    }

}
