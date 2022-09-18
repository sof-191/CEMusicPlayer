package Clases.Canciones;

public class Nodo {
    private Canciones data;
    private Nodo next;
    private Nodo prev;

    /***
     * Metodo constructor de Nodo
     * @param data Cancion a ingresar
     */
    public Nodo(Canciones data) {
        this.data = data;
    }

    public Canciones getData() {
        return data;
    }

    public void setData(Canciones data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public Nodo getPrev() {
        return prev;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }
}
