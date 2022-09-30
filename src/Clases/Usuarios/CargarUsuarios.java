package Clases.Usuarios;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.Canciones;
import Clases.Canciones.ListaDobleEnlazada;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class CargarUsuarios {
    public static void guardarListaUsuarios(Clases.Usuarios.ListaSimple listaSimpleUsuarios){
        /***
         * https://www.tabnine.com/code/java/methods/javax.xml.parsers.DocumentBuilderFactory/newInstance
         * http://www.javased.com/index.php?api=javax.xml.parsers.DocumentBuilderFactory
         * https://gist.github.com/bzdgn/1787e7ce85eafd65b66a
         */
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            DOMImplementation implementation = documentBuilder.getDOMImplementation();
            Document document = implementation.createDocument(null,"Usuarios",null);
            document.setXmlVersion("1.0");

            Clases.Usuarios.Nodo tempUsuario = listaSimpleUsuarios.getStart();

            while (tempUsuario!=null){
                Usuarios usuarioGuardar = tempUsuario.getData();
                Element usuario = document.createElement("usuario");

                Element emailUsuario = document.createElement("email");
                Text temailUsuario = document.createTextNode(usuarioGuardar.getCorreoElectronico());
                emailUsuario.appendChild(temailUsuario);

                Element nombreUsuario = document.createElement("nombre");
                Text tnombreUsuario = document.createTextNode(usuarioGuardar.getNombre());
                nombreUsuario.appendChild(tnombreUsuario);

                Element provinciaUsuario = document.createElement("provincia");
                Text tprovinciaUsuario = document.createTextNode(usuarioGuardar.getProvincia());
                provinciaUsuario.appendChild(tprovinciaUsuario);

                Element contrasenaUsuario = document.createElement("contrasena");
                Text tcontrasenaUsuario = document.createTextNode(usuarioGuardar.getContrasena());
                contrasenaUsuario.appendChild(tcontrasenaUsuario);

                Element bibliotecasUsuario = document.createElement("bibliotecas");

                Clases.Bibliotecas.ListaSimple listaSimpleBibliotecas = usuarioGuardar.getListaDeBibliotecas();
                Clases.Bibliotecas.Nodo tempBiblioteca = listaSimpleBibliotecas.getStart();

                while (tempBiblioteca!=null){
                    Bibliotecas bibliotecaGuardar = tempBiblioteca.getData();
                    Element biblioteca = document.createElement("biblioteca");

                    Element idBiblioteca = document.createElement("id");
                    Text tidBiblioteca = document.createTextNode(bibliotecaGuardar.getId());
                    idBiblioteca.appendChild(tidBiblioteca);

                    Element nombreBiblioteca = document.createElement("nombre");
                    Text tnombreBiblioteca = document.createTextNode(bibliotecaGuardar.getNombre());
                    nombreBiblioteca.appendChild(tnombreBiblioteca);

                    Element cancionesBiblioteca = document.createElement("canciones");

                    Clases.Canciones.ListaDobleEnlazada listaDobleEnlazadaCanciones = bibliotecaGuardar.getListaDeCanciones();
                    Clases.Canciones.Nodo inicioCanciones;
                    Clases.Canciones.Nodo tempCanciones = inicioCanciones = listaDobleEnlazadaCanciones.getStart();

                    do {
                        if (tempCanciones!=null){
                            Canciones cancionGuardar = tempCanciones.getData();
                            Element cancion = document.createElement("cancion");

                            Element idCancion = document.createElement("id");
                            Text tidCancion = document.createTextNode(cancionGuardar.getId());
                            idCancion.appendChild(tidCancion);

                            Element nombreCancion = document.createElement("nombre");
                            Text tnombreCancion = document.createTextNode(cancionGuardar.getNombre());
                            nombreCancion.appendChild(tnombreCancion);

                            Element generoCancion = document.createElement("genero");
                            Text tgeneroCancion = document.createTextNode(cancionGuardar.getGenero());
                            generoCancion.appendChild(tgeneroCancion);

                            Element artistaCancion = document.createElement("artista");
                            Text tartistaCancion = document.createTextNode(cancionGuardar.getArtista());
                            artistaCancion.appendChild(tartistaCancion);

                            Element albumCancion = document.createElement("album");
                            Text talbumCancion = document.createTextNode(cancionGuardar.getAlbum());
                            albumCancion.appendChild(talbumCancion);

                            Element anioCancion = document.createElement("anio");
                            Text tanioCancion = document.createTextNode(cancionGuardar.getAnio());
                            anioCancion.appendChild(tanioCancion);

                            Element letraCancion = document.createElement("letra");
                            Text tletraCancion = document.createTextNode(cancionGuardar.getLetra());
                            letraCancion.appendChild(tletraCancion);

                            Element pathCancion = document.createElement("path");
                            Text tpathCancion = document.createTextNode(cancionGuardar.getPath());
                            pathCancion.appendChild(tpathCancion);

                            cancion.appendChild(idCancion);
                            cancion.appendChild(nombreCancion);
                            cancion.appendChild(generoCancion);
                            cancion.appendChild(artistaCancion);
                            cancion.appendChild(albumCancion);
                            cancion.appendChild(anioCancion);
                            cancion.appendChild(letraCancion);
                            cancion.appendChild(pathCancion);

                            cancionesBiblioteca.appendChild(cancion);

                            tempCanciones = tempCanciones.getNext();
                        }
                    }while (tempCanciones!=inicioCanciones);

                    biblioteca.appendChild(idBiblioteca);
                    biblioteca.appendChild(nombreBiblioteca);
                    biblioteca.appendChild(cancionesBiblioteca);

                    bibliotecasUsuario.appendChild(biblioteca);

                    tempBiblioteca = tempBiblioteca.getNext();
                }

                usuario.appendChild(emailUsuario);
                usuario.appendChild(nombreUsuario);
                usuario.appendChild(provinciaUsuario);
                usuario.appendChild(contrasenaUsuario);
                usuario.appendChild(bibliotecasUsuario);

                document.getDocumentElement().appendChild(usuario);

                tempUsuario = tempUsuario.getNext();
            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("XML/users.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clases.Usuarios.ListaSimple cargarListaUsuarios(){
        Clases.Usuarios.ListaSimple lista = null;
        try {
            lista = new ListaSimple();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("XML/users.xml"));

            NodeList listaUsuarios = document.getElementsByTagName("usuario");

            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Node usuario = listaUsuarios.item(i);
                Usuarios usuarioGuardar = new Usuarios("","","","",new Clases.Bibliotecas.ListaSimple());
                if(usuario.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) usuario;
                    NodeList datosUsuario = element.getChildNodes();
                    for (int j = 0; j < datosUsuario.getLength(); j++) {
                        Node datoUsuario =  datosUsuario.item(j);
                        if (datoUsuario.getNodeType()==Node.ELEMENT_NODE){
                            if(datoUsuario.getNodeName().equals("email")){
                                usuarioGuardar.setCorreoElectronico(datoUsuario.getTextContent());
                            }
                            if(datoUsuario.getNodeName().equals("nombre")){
                                usuarioGuardar.setNombre(datoUsuario.getTextContent());
                            }
                            if(datoUsuario.getNodeName().equals("provincia")){
                                usuarioGuardar.setProvincia(datoUsuario.getTextContent());
                            }
                            if(datoUsuario.getNodeName().equals("contrasena")){
                                usuarioGuardar.setContrasena(datoUsuario.getTextContent());
                            }
                            if(datoUsuario.getNodeName().equals("bibliotecas")){
                                Clases.Bibliotecas.ListaSimple listaBibliotecaGuardar = new Clases.Bibliotecas.ListaSimple();
                                NodeList listaBiblioteca = datoUsuario.getChildNodes();
                                for (int k = 0; k < listaBiblioteca.getLength(); k++) {
                                    Node biblioteca = listaBiblioteca.item(k);
                                    Bibliotecas bibliotecaGuardar = new Bibliotecas("","",new ListaDobleEnlazada());
                                    if (biblioteca.getNodeType()==Node.ELEMENT_NODE){
                                        Element element1 = (Element) biblioteca;
                                        NodeList datosBiblioteca = biblioteca.getChildNodes();
                                        for (int l = 0; l < datosBiblioteca.getLength(); l++) {
                                            Node datoBiblioteca = datosBiblioteca.item(l);
                                            if (datoBiblioteca.getNodeType()==Node.ELEMENT_NODE){
                                                if (datoBiblioteca.getNodeName().equals("id")){
                                                    bibliotecaGuardar.setId(datoBiblioteca.getTextContent());
                                                }
                                                if (datoBiblioteca.getNodeName().equals("nombre")){
                                                    bibliotecaGuardar.setNombre(datoBiblioteca.getTextContent());
                                                }
                                                if (datoBiblioteca.getNodeName().equals("canciones")){
                                                    ListaDobleEnlazada listaCancionesGuardar = new ListaDobleEnlazada();
                                                    NodeList listaCanciones = datoBiblioteca.getChildNodes();
                                                    for (int m = 0; m < listaCanciones.getLength(); m++) {
                                                        Node cancion = listaCanciones.item(m);
                                                        Canciones cancionGuardar = new Canciones("","","","","","","","");
                                                        if(cancion.getNodeType() == Node.ELEMENT_NODE){
                                                            Element element3 = (Element) cancion;
                                                            NodeList datosCanciones = element3.getChildNodes();
                                                            for (int n = 0; n < datosCanciones.getLength(); n++) {
                                                                Node datoCanciones = datosCanciones.item(n);
                                                                if (datoCanciones.getNodeType()==Node.ELEMENT_NODE){
                                                                    if (datoCanciones.getNodeName().equals("id")){
                                                                        cancionGuardar.setId(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("nombre")){
                                                                        cancionGuardar.setNombre(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("genero")){
                                                                        cancionGuardar.setGenero(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("artista")){
                                                                        cancionGuardar.setArtista(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("album")){
                                                                        cancionGuardar.setAlbum(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("anio")){
                                                                        cancionGuardar.setAnio(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("letra")){
                                                                        cancionGuardar.setLetra(datoCanciones.getTextContent());
                                                                    }
                                                                    if (datoCanciones.getNodeName().equals("path")){
                                                                        cancionGuardar.setPath(datoCanciones.getTextContent());
                                                                    }
                                                                }
                                                            }
                                                            listaCancionesGuardar.insertarFinal(cancionGuardar);
                                                        }
                                                    }
                                                    bibliotecaGuardar.setListaDeCanciones(listaCancionesGuardar);
                                                }
                                            }
                                        }
                                        listaBibliotecaGuardar.insertarFinal(bibliotecaGuardar);
                                    }
                                }
                                usuarioGuardar.setListaDeBibliotecas(listaBibliotecaGuardar);
                            }
                        }
                    }
                }
                lista.insertarFinal(usuarioGuardar);
            }
            return lista;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
