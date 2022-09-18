package GUI;

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

    public VentanRegister() {
        initComponents();
        volverALogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaLogIn ventanaLogIn = new VentanaLogIn();
                jFrame.setVisible(false);
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
