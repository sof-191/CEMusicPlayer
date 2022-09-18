package Clases.Bibliotecas;

/***
 * Lista donde se almacenaran las bibliotecas
 */
public class ListaSimple {
    private Nodo start;
    private Nodo last;

    public ListaSimple() {
        this.start = null;
        this.last = null;
    }

    /***
     * Inserta una biblioteca al final de la lista
     * @param biblioteca biblioteca a insertar
     */
    public void insertarFinal(Bibliotecas biblioteca){
        Nodo nuevo = new Nodo(biblioteca);
        if (this.start == null){
            this.start=nuevo;
            this.last=nuevo;
        }else {
            this.last.setNext(nuevo);
            this.last = last.getNext();
        }
    }
}
