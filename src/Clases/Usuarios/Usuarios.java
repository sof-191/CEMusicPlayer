package Clases.Usuarios;

import Clases.Bibliotecas.ListaSimple;


public class Usuarios {
    private String correoElectronico;
    private String nombre;
    private String provincia;
    private String contrasena;
    private Clases.Bibliotecas.ListaSimple listaDeBibliotecas;

    public Usuarios(String correoElectronico, String nombre, String provincia, String contrasena, ListaSimple listaDeBibliotecas) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.provincia = provincia;
        this.contrasena = contrasena;
        this.listaDeBibliotecas = listaDeBibliotecas;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ListaSimple getListaDeBibliotecas() {
        return listaDeBibliotecas;
    }

    public void setListaDeBibliotecas(ListaSimple listaDeBibliotecas) {
        this.listaDeBibliotecas = listaDeBibliotecas;
    }
}
