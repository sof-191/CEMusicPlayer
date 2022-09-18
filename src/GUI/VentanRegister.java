package GUI;

import javax.swing.*;

public class VentanRegister extends JFrame{
    private JTextField entryNombre;
    private JTextField entryCorreo;
    private JComboBox entryProvincia;
    private JTextField entryContrasena;
    private JButton registerButton;
    private JButton volverALogInButton;
    private JPanel jPanel;

    public VentanRegister() {
        initComponents();
    }
    public void initComponents() {
        this.setContentPane(jPanel);
        this.setTitle("LogIn");
        this.pack();
        this.setVisible(true);
    }
}
