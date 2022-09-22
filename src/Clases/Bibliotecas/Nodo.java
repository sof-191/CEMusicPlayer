package Clases.Bibliotecas;

/***
 * Nodo de bibliotecas
 */
public class Nodo {
    private Bibliotecas data;
    private Nodo next;

    /***
     * Metodo constructor de biblioteca
     * @param data
     */
    public Nodo(Bibliotecas data) {
        this.data = data;
        this.next = null;
    }

    public Bibliotecas getData() {
        return data;
    }

    public void setData(Bibliotecas data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
