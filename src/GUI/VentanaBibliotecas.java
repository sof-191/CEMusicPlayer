package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Bibliotecas.ListaSimple;
import Clases.Bibliotecas.Nodo;
import Clases.Usuarios.CargarUsuarios;
import Clases.Usuarios.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBibliotecas {
    private JTable table1;
    private JButton reproducirButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JPanel jPanel;
    private JFrame jFrame;
    private Usuarios usuario;
    Clases.Usuarios.ListaSimple listaUsuarios;

    public VentanaBibliotecas(Clases.Usuarios.ListaSimple lista, String correo) {
        this.listaUsuarios = lista;
        this.usuario = listaUsuarios.buscarCorreo(correo);
        initComponents();
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0)).equals("01")&&!String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0)).equals("02")){
                    ListaSimple nuevaLista = usuario.getListaDeBibliotecas();
                    if (nuevaLista.eliminarId(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0))) != null) {
                        usuario.setListaDeBibliotecas(nuevaLista);
                        listaUsuarios.eliminarId(usuario.getCorreoElectronico());
                        listaUsuarios.insertarFinal(usuario);
                        CargarUsuarios.guardarListaUsuarios(listaUsuarios);
                        actualizarTabla(usuario.getListaDeBibliotecas());
                    }
                }

            }
        });
    }

    private void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Bibliotecas");
        jFrame.pack();
        actualizarTabla(usuario.getListaDeBibliotecas());
        jFrame.setVisible(true);
    }

    private void actualizarTabla(ListaSimple biblioteca){
        DefaultTableModel defaultTableModel = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        defaultTableModel.addColumn("Id");
        defaultTableModel.addColumn("Nombre");

        Nodo temp = biblioteca.getStart();

        while (temp!=null){
            defaultTableModel.addRow(new Object[]{temp.getData().getId(),temp.getData().getNombre()});
            temp=temp.getNext();
        }
        table1.setModel(defaultTableModel);
    }

}
