package Clases.Bibliotecas;

import Clases.Canciones.ListaDobleEnlazada;

/***
 * Clase donde se almacenaran las bibliotecas
 */
public class Bibliotecas {
    private String fecha;
    private String id;
    private String nombre;
    private Clases.Canciones.ListaDobleEnlazada listaDeCanciones;

    public Bibliotecas(String id, String nombre,ListaDobleEnlazada listaDeCanciones) {
        this.id = id;
        this.nombre = nombre;
        this.listaDeCanciones = listaDeCanciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ListaDobleEnlazada getListaDeCanciones() {
        return listaDeCanciones;
    }

    public void setListaDeCanciones(ListaDobleEnlazada listaDeCanciones) {
        this.listaDeCanciones = listaDeCanciones;
    }
}
