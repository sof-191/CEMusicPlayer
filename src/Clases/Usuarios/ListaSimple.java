package Clases.Usuarios;

import Clases.Bibliotecas.Bibliotecas;

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
     * @param usuario biblioteca a insertar
     */
    public void insertarFinal(Usuarios usuario){
        Nodo nuevo = new Nodo(usuario);
        if (this.start == null){
            this.start=nuevo;
            this.last=nuevo;
        }else {
            this.last = start;
            while (last.getNext()!=null){
                last = last.getNext();
            }
            this.last.setNext(nuevo);
            this.last = last.getNext();
        }
    }
    /***
     * Método que permite saber si un correo existe o no
     * @param correo característica de un usuario
     * @return
     */
    public Usuarios buscarCorreo(String correo){
        Nodo temp = start;
        while(temp!=null){
            if (temp.getData().getCorreoElectronico().equals(correo)){
                return temp.getData();
            }
            temp = temp.getNext();
        }
        return null;
    }
    /***
     * Método para eliminar un usuario
     * @param correo
     * @return
     */
    public Usuarios eliminarCorreo(String correo){
        Nodo current = this.start;
        Nodo previous = this.start ;
        while ( current != null ) {
            if (current.getData().getCorreoElectronico().equals(correo)){
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
