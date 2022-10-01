package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.ListaDobleEnlazada;
import Clases.Canciones.Nodo;
import Clases.Usuarios.ListaSimple;
import Clases.Usuarios.Usuarios;
import comunicacionserial.ArduinoExcepcion;
import comunicacionserial.ComunicacionSerial_Arduino;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VentanaReproducirMusica {
    private JTextField textField1;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton reproducirButton;
    private JButton pausarButton;
    private JButton loopButton;
    private JButton bajarVolumenButton;
    private JButton subirVolumenButton;
    private JPanel jPanel;
    private JButton regresarButton;

    private JButton favoritaButton;
    private JFrame jFrame;
    private Usuarios usuario;
    private Clases.Usuarios.ListaSimple listaUsuarios;
    private Bibliotecas biblioteca;
    private Nodo inicio, temp;
    private JFXPanel fxPanel = new JFXPanel();
    private static MediaPlayer mediaPlayer;

    private boolean continua = false;

    public VentanaReproducirMusica(Usuarios usuario, ListaSimple listaUsuarios, Bibliotecas biblioteca) {
        this.usuario = usuario;
        this.listaUsuarios = listaUsuarios;
        this.biblioteca = biblioteca;
        try {
            conexion.arduinoRXTX("COM5", 9600, listen);
        } catch (ArduinoExcepcion e) {
            throw new RuntimeException(e);
        }
        initComponents();
        Reproducir();
        reproducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reproducir();
            }
        });
        pausarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pausar();
            }
        });
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Anterior();
                textField1.setText(getCancion());

            }
        });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Siguiente();
                textField1.setText(getCancion());

            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBibliotecas ventanaBibliotecas = new VentanaBibliotecas(listaUsuarios, usuario.getCorreoElectronico());
                jFrame.setVisible(false);
                mediaPlayer.stop();
            }
        });
        loopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarContinua();

            }
        });
        bajarVolumenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.setVolume(mediaPlayer.getVolume()-0.1);
            }
        });

        subirVolumenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.setVolume(mediaPlayer.getVolume()+0.1);
            }
        });

    }
    ComunicacionSerial_Arduino conexion = new ComunicacionSerial_Arduino();
    SerialPortEventListener listen = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                if (conexion.isMessageAvailable()) {
                    String dato = conexion.printMessage();
                    Orden(dato);
                    //TextArea.append(dato + "\n");
                }
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            } catch (ArduinoExcepcion e) {
                throw new RuntimeException(e);
            }
        }
        private void Orden(String dato) {
            switch (dato) {
                case "fav": {
                    System.out.println("fav");
                    break;
                }
                case "nofav": {
                    System.out.println("nofav");
                    break;
                }
                case "cont": {
                    System.out.println("cont");
                    cambiarContinua();
                    break;
                }
                case "nocont": {
                    System.out.println("nocont");
                    cambiarContinua();
                    break;
                }
                case "anterior": {
                    System.out.println("anterior");
                    Anterior();
                    textField1.setText(getCancion());
                    break;
                }
                case "play": {
                    System.out.println("play");
                    mediaPlayer.play();
                    break;
                }
                case "pause": {
                    System.out.println("pause");
                    mediaPlayer.stop();
                    break;
                }
                case "siguiente": {
                    System.out.println("siguiente");
                    Siguiente();
                    textField1.setText(getCancion());
                    break;
                }
                case "0": {
                    mediaPlayer.setVolume(0);
                    break;
                }
                case "1": {
                    mediaPlayer.setVolume(20);
                    break;
                }
                case "2": {
                    mediaPlayer.setVolume(40);
                    break;
                }
                case "3": {
                    mediaPlayer.setVolume(60);
                    break;
                }
                case "4": {
                    mediaPlayer.setVolume(80);
                    break;
                }
                case "5": {
                    mediaPlayer.setVolume(100);
                    break;
                }
            }
        }
    };


    private void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Bibliotecas");
        jFrame.pack();
        jFrame.setVisible(true);
        ListaDobleEnlazada listaDobleEnlazada = biblioteca.getListaDeCanciones();
        this.inicio = biblioteca.getListaDeCanciones().getStart();
        this.temp = biblioteca.getListaDeCanciones().getStart();
        cargarBiblioteca();
        textField1.setText(getCancion());


    }

    private void cargarBiblioteca() {

        String path = "Canciones/" + temp.getData().getPath();
        File file = new File(path);
        Media media = new Media(file.toURI().toString());

        this.mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnEndOfMedia(() -> {
            if (temp.getNext() != inicio || continua) {
                textField1.setText(getCancion());
                temp = temp.getNext();
                cargarBiblioteca();
            }
        });
        Reproducir();

    }
    public void Reproducir(){
        mediaPlayer.play();
    }
    public void Siguiente(){
        mediaPlayer.stop();
        temp = temp.getNext();
        cargarBiblioteca();
    }
    public void Anterior(){
        mediaPlayer.stop();
        temp = temp.getPrev();
        cargarBiblioteca();
    }
    public void Pausar(){
        mediaPlayer.pause();
    }
    public void cambiarContinua(){
        this.continua = !continua;
    }
    public String getCancion(){
        return temp.getData().getNombre();
    }

}
