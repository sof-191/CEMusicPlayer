package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.Canciones;
import Clases.Canciones.CargarCanciones;
import Clases.Canciones.ListaDobleEnlazada;
import Clases.Canciones.Nodo;
import Clases.Usuarios.CargarUsuarios;
import Clases.Usuarios.ListaSimple;
import Clases.Usuarios.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEditarBiblioteca {
    private JTextField textField1;
    private JTable table1;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JPanel jPanel;
    private JButton guardarButton;
    private JButton regresarButton;
    private JComboBox comboBox1;
    private JFrame jFrame;
    private Clases.Usuarios.ListaSimple listaUsuarios;
    private Clases.Bibliotecas.ListaSimple listaBibliotecas;
    private Usuarios usuario;
    private Bibliotecas biblioteca;
    private ListaDobleEnlazada listaCanciones;

    /***
     * Método que evita que la columna de ID pueda ser editable.
     */

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex,int columnIndex){
            if (columnIndex == 0){
                return false;
            }
            return true;
        }
    };
    /***
     * Método que abre la ventana de editar y permite que las canciones y las bibliotecas editadas se guarden bien en la lista de bibliotecas y despues en la lista de los usuarios
     * @param listaUsuarios lista de usuarios para que la modificación en la biblioteca quede guardada en su respectivo lugar
     * @param usuario usuario al que le corresponde la modificación
     * @param biblioteca biblioteca a editar
     */
    public VentanaEditarBiblioteca(ListaSimple listaUsuarios, Usuarios usuario, Bibliotecas biblioteca) {
        this.listaUsuarios = listaUsuarios;
        this.usuario = usuario;
        this.biblioteca = biblioteca;
        this.listaBibliotecas = this.usuario.getListaDeBibliotecas();
        initComponents();
        /***
         * Método para guardar los cambios en la biblioteca (que esta abajo)
         */
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarBiblioteca();
            }
        });
        /***
         * Método para eliminar una canción
         */
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.removeRow(table1.getSelectedRow());
            }
        });
        /***
         * Método para regresar a la ventana de bibliotecas
         */
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBibliotecas ventanaBibliotecas = new VentanaBibliotecas(listaUsuarios,usuario.getCorreoElectronico());
                jFrame.setVisible(false);
            }
        });
        /***
         * Método para agregar una canción
         * https://stackoverflow.com/questions/51496151/addrow-method-in-defaulttablemodel-is-not-adding-rows
         */
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) comboBox1.getSelectedItem();
                id = id.substring(0,2);
                Canciones cancion = listaCanciones.buscarId(id);
                defaultTableModel.addRow(new Object[]{cancion.getId(),cancion.getNombre(),cancion.getGenero(),cancion.getArtista(),cancion.getAlbum(),cancion.getAnio(),cancion.getLetra()});
                ListaDobleEnlazada listaDobleEnlazada = biblioteca.getListaDeCanciones();
                listaDobleEnlazada.insertarFinal(cancion);
                biblioteca.setListaDeCanciones(listaDobleEnlazada);
            }
        });
    }
    /***
     * Método que permite desplegar las canciones en el combobox
     * https://www.youtube.com/watch?v=1cEcR4lEoG8
     */
    public void initComponents(){
        textField1.setText(this.biblioteca.getNombre());
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Editar biblioteca");
        jFrame.pack();
        jFrame.setVisible(true);
        actualizarTabla(biblioteca.getListaDeCanciones());
        cargarComboBox();
    }

    private void cargarComboBox() {
        listaCanciones = CargarCanciones.leerListaCanciones();
        Nodo inicio = listaCanciones.getStart();
        Nodo temp = listaCanciones.getStart();
        if (temp!=null){
            do {
                comboBox1.addItem(temp.getData().getId()+"-"+temp.getData().getNombre());
                temp = temp.getNext();
            }while (temp!=inicio);
        }
    }
    /***
     * Método actualiza la lista de canciones en la biblioteca
     * @param canciones la listade canciones en la biblioteca
     */
    private void actualizarTabla(Clases.Canciones.ListaDobleEnlazada canciones){
        defaultTableModel = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                if (columnIndex == 0){
                    return false;
                }
                return true;
            }
        };
        defaultTableModel.addColumn("Id");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Genero");
        defaultTableModel.addColumn("Artista");
        defaultTableModel.addColumn("Album");
        defaultTableModel.addColumn("Anio");
        defaultTableModel.addColumn("Letra");


        Nodo temp = canciones.getStart();
        Nodo inicio = canciones.getStart();
        if (temp!=null){
            do {
                defaultTableModel.addRow(new Object[]{temp.getData().getId(),temp.getData().getNombre(),temp.getData().getGenero(),temp.getData().getArtista(),temp.getData().getAlbum(),temp.getData().getAnio(),temp.getData().getLetra()});
                temp=temp.getNext();
            }while (temp!=inicio);
        }

        table1.setModel(defaultTableModel);
    }
    /***
     * Método que permite guardar los cambios realizar cambios en la biblioteca
     */
    public void guardarBiblioteca(){
        Clases.Canciones.ListaDobleEnlazada listaGuadar = new ListaDobleEnlazada();
        for (int i = 0; i < table1.getRowCount(); i++) {
            listaGuadar.insertarFinal(new Canciones(table1.getValueAt(i,0).toString(),table1.getValueAt(i,1).toString(),table1.getValueAt(i,2).toString(),table1.getValueAt(i,3).toString(),table1.getValueAt(i,4).toString(),table1.getValueAt(i,5).toString(),table1.getValueAt(i,6).toString(),biblioteca.getListaDeCanciones().buscarId(table1.getValueAt(i,0).toString()).getPath()));
        }
        this.biblioteca.setListaDeCanciones(listaGuadar);
        this.biblioteca.setNombre(textField1.getText());
        this.listaBibliotecas.eliminarId(this.biblioteca.getId());
        this.listaBibliotecas.insertarFinal(this.biblioteca);
        this.usuario.setListaDeBibliotecas(listaBibliotecas);
        this.listaUsuarios.eliminarCorreo(usuario.getCorreoElectronico());
        this.listaUsuarios.insertarFinal(usuario);
        CargarUsuarios.guardarListaUsuarios(listaUsuarios);
    }
}
