package Clases.Canciones;

public class ListaDobleEnlazada {
    Nodo start;

    /***
     * Metodo constructor de ListaDobleEnlazada
     */
    public ListaDobleEnlazada() {
        this.start = null;
    }

    /***
     * Inserta al final de la lista
     * @param cancion cancion a insertar
     */
    public void insertarFinal(Canciones cancion){
        if(start==null){
            Nodo nuevo = new Nodo(cancion);
            nuevo.setNext(nuevo);
            nuevo.setPrev(nuevo);
            this.start = nuevo;
        }else {
            Nodo last = start.getPrev();

            Nodo nuevo = new Nodo(cancion);
            last.setNext(nuevo);

            nuevo.setPrev(last);
            nuevo.setNext(this.start);

            this.start.setPrev(nuevo);
        }
    }

    public Nodo getStart() {
        return start;
    }

    public void setStart(Nodo start) {
        this.start = start;
    }
}
