package Clases.Canciones;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class CargarCanciones {

    public static ListaDobleEnlazada leerListaCanciones(){
        ListaDobleEnlazada listaDobleEnlazadaGuardar = new ListaDobleEnlazada();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("XML/canciones.xml"));
            NodeList listaCanciones = document.getElementsByTagName("cancion");

            for (int i = 0; i < listaCanciones.getLength(); i++) {
                Node cancion = listaCanciones.item(i);
                Canciones cancionGuardar = new Canciones("","","","","","","");
                if(cancion.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) cancion;
                    NodeList datos = element.getChildNodes();
                    for (int j = 0; j < datos.getLength(); j++) {
                        Node dato = datos.item(j);
                        if (dato.getNodeType()==Node.ELEMENT_NODE){
                            if (dato.getNodeName().equals("id")){
                                cancionGuardar.setId(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("nombre")){
                                cancionGuardar.setNombre(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("genero")){
                                cancionGuardar.setGenero(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("artista")){
                                cancionGuardar.setArtista(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("album")){
                                cancionGuardar.setAlbum(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("anio")){
                                cancionGuardar.setAnio(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("letra")){
                                cancionGuardar.setLetra(dato.getTextContent());
                            }
                            if (dato.getNodeName().equals("path")){
                                cancionGuardar.setPath(dato.getTextContent());
                            }
                        }
                    }
                    listaDobleEnlazadaGuardar.insertarFinal(cancionGuardar);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return listaDobleEnlazadaGuardar;
    }
}
