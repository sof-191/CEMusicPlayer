package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Usuarios.CargarUsuarios;
import Clases.Usuarios.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanRegister{
    private JTextField entryNombre;
    private JTextField entryCorreo;
    private JComboBox entryProvincia;
    private JTextField entryContrasena;
    private JButton registerButton;
    private JButton volverALogInButton;
    private JPanel jPanel;
    private JFrame jFrame;

    public VentanRegister(Clases.Usuarios.ListaSimple listaUsuarios) {
        initComponents();
        volverALogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaLogIn ventanaLogIn = new VentanaLogIn();
                jFrame.setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clases.Canciones.ListaDobleEnlazada listaCanciones = Clases.Canciones.CargarCanciones.leerListaCanciones();
                Clases.Bibliotecas.ListaSimple listaBibliotecas = new Clases.Bibliotecas.ListaSimple();

                listaBibliotecas.insertarFinal(new Bibliotecas("01","Canciones",listaCanciones));
                listaBibliotecas.insertarFinal(new Bibliotecas("02","Favoritas",new Clases.Canciones.ListaDobleEnlazada()));

                listaUsuarios.insertarFinal(new Usuarios(entryCorreo.getText(),entryNombre.getText(),(String) entryProvincia.getSelectedItem(),entryContrasena.getText(),listaBibliotecas));

                CargarUsuarios.guardarListaUsuarios(listaUsuarios);

            }
        });
    }
    public void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Register");
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
