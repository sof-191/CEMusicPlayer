package GUI;

import Clases.Usuarios.ListaSimple;

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

    public VentanaLogIn() {
        initComponents();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanRegister ventanRegister = new VentanRegister(new ListaSimple());
                jFrame.setVisible(false);
            }
        });
    }
    public void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("LogIn");
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
