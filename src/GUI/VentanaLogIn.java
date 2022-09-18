package GUI;

import javax.swing.*;

public class VentanaLogIn extends JFrame{
    private JTextField entryCorreo;
    private JTextField entryContrasena;
    private JButton registerButton;
    private JButton logInButton;
    private JPanel jPanel;

    public VentanaLogIn() {
        initComponents();
    }
    public void initComponents() {
        this.setContentPane(jPanel);
        this.setTitle("LogIn");
        this.pack();
        this.setVisible(true);
    }
}
