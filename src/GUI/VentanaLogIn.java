package GUI;

import Clases.Usuarios.CargarUsuarios;
import Clases.Usuarios.ListaSimple;
import Clases.Usuarios.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogIn{
    private JTextField entryCorreo;
    private JButton registerButton;
    private JButton logInButton;
    private JPanel jPanel;
    private JPasswordField entryContrasena;
    private JFrame jFrame;
    ListaSimple listaSimpleUsuarios;

    public VentanaLogIn() {

        initComponents();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanRegister ventanRegister = new VentanRegister(listaSimpleUsuarios);
                jFrame.setVisible(false);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios usuario = listaSimpleUsuarios.buscarCorreo(entryCorreo.getText());
                if(usuario!=null){
                    if(usuario.getContrasena().equals(entryContrasena.getText())){
                        VentanaBibliotecas ventanaBibliotecas = new VentanaBibliotecas(listaSimpleUsuarios,entryCorreo.getText());
                        jFrame.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(null,"Contrasena incorrecta","Datos erroneos",JOptionPane.WARNING_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Correo no existe, debe de registrarse","Datos erroneos",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    public void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("LogIn");
        jFrame.pack();
        jFrame.setVisible(true);
        listaSimpleUsuarios = CargarUsuarios.cargarListaUsuarios();
    }
}
