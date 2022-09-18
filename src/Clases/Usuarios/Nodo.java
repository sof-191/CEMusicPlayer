package Clases.Usuarios;

import Clases.Bibliotecas.Bibliotecas;

public class Nodo {
    private Usuarios data;
    private Nodo next;

    /***
     * Metodo constructor de usuarios
     * @param data
     */
    public Nodo(Usuarios data) {
        this.data = data;
    }

    public Usuarios getData() {
        return data;
    }

    public void setData(Usuarios data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
