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

    public Bibliotecas eliminarId(String id){
        Nodo current = this.start ;
        Nodo previous = this.start ;
        while ( current != null ) {
            if (current.getData().getId().equals(id)){
                if ( current == this.start ) {
                    this.start = this.start.getNext() ;
                } else {
                    previous.setNext(current.getNext());
                }
                return current.getData();
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        return null;
    }

    public Nodo getStart() {
        return start;
    }

    public void setStart(Nodo start) {
        this.start = start;
    }

    public Nodo getLast() {
        return last;
    }

    public void setLast(Nodo last) {
        this.last = last;
    }
}
