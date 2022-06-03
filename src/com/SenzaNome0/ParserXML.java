package com.SenzaNome0;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ParserXML {
    XMLInputFactory xmlif = null;

    public ParserXML() {
        xmlif = XMLInputFactory.newInstance();
    }

    public LinkedList<ArrayList<Integer>> getGrafo(String filename) throws XMLStreamException {
        LinkedList<ArrayList<Integer>> collegamenti = new LinkedList<>();
        XMLStreamReader xmlr = null;
        try {
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        //mappa di lista di edge
        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                    collegamenti.add(new Nodo(Integer.parseInt(xmlr.getAttributeValue(0)),xmlr.getAttributeLocalName(1), Integer.parseInt(xmlr.getAttributeValue(2)), Integer.parseInt(xmlr.getAttributeValue(3)), Integer.parseInt(xmlr.getAttributeValue(4) )));
                case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
                    if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
                        System.out.println("-> " + xmlr.getText());
                    break;
            }
            xmlr.next();
        }
        return collegamenti;
    }
}