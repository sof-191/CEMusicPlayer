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
    /***
     * Método para mostrar la ventana del logIn.
     */
    public VentanaLogIn() {

        initComponents();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /***
                 * Método para ir a la ventana de registrarse
                 */
                VentanRegister ventanRegister = new VentanRegister(listaSimpleUsuarios);
                jFrame.setVisible(false);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /***
                 * Método que permite verificar si el correo existe, si el correo y la contraseña son iguales y si se puede entrar a las bibliotecas o tirar error.
                 */
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
    /***
     * Método para mostrar ventana de logIn con la lista de usuarios cargada..
     */
    public void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("LogIn");
        jFrame.pack();
        jFrame.setVisible(true);
        listaSimpleUsuarios = CargarUsuarios.cargarListaUsuarios();
    }
}
