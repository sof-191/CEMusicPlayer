package Clases.Usuarios;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.Canciones;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CargarUsuarios {
    public static void guardarListaUsuarios(Clases.Usuarios.ListaSimple listaSimpleUsuarios){


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

                    Element nombreBiblioteca = document.createElement("id");
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

                            Element anioCancion = document.createElement("album");
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
}
