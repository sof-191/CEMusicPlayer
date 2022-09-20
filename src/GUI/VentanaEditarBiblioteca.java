package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.Canciones;
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
    private JFrame jFrame;
    private Clases.Usuarios.ListaSimple listaUsuarios;
    private Clases.Bibliotecas.ListaSimple listaBibliotecas;
    private Usuarios usuario;
    private Bibliotecas biblioteca;
    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex,int columnIndex){
            if (columnIndex == 0){
                return false;
            }
            return true;
        }
    };

    public VentanaEditarBiblioteca(ListaSimple listaUsuarios, Usuarios usuario, Bibliotecas biblioteca) {
        this.listaUsuarios = listaUsuarios;
        this.usuario = usuario;
        this.biblioteca = biblioteca;
        this.listaBibliotecas = this.usuario.getListaDeBibliotecas();
        initComponents();
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarBiblioteca();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel.removeRow(table1.getSelectedRow());
            }
        });
    }
    public void initComponents(){
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Editar biblioteca");
        jFrame.pack();
        jFrame.setVisible(true);
        actualizarTabla(biblioteca.getListaDeCanciones());
    }
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

    public void guardarBiblioteca(){
        Clases.Canciones.ListaDobleEnlazada listaGuadar = new ListaDobleEnlazada();
        for (int i = 0; i < table1.getRowCount(); i++) {
            listaGuadar.insertarFinal(new Canciones(table1.getValueAt(i,0).toString(),table1.getValueAt(i,1).toString(),table1.getValueAt(i,2).toString(),table1.getValueAt(i,3).toString(),table1.getValueAt(i,4).toString(),table1.getValueAt(i,5).toString(),table1.getValueAt(i,6).toString(),biblioteca.getListaDeCanciones().buscarId(table1.getValueAt(i,0).toString()).getPath()));
        }
        this.biblioteca.setListaDeCanciones(listaGuadar);
        this.listaBibliotecas.eliminarId(this.biblioteca.getId());
        this.listaBibliotecas.insertarFinal(this.biblioteca);
        this.usuario.setListaDeBibliotecas(listaBibliotecas);
        this.listaUsuarios.eliminarCorreo(usuario.getCorreoElectronico());
        this.listaUsuarios.insertarFinal(usuario);
        CargarUsuarios.guardarListaUsuarios(listaUsuarios);
    }
}
