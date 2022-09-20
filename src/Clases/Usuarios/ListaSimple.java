package Clases.Usuarios;

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
            this.last.setNext(nuevo);
            this.last = last.getNext();
        }
    }

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
