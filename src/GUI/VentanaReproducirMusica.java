package GUI;

import Clases.Bibliotecas.Bibliotecas;
import Clases.Canciones.ListaDobleEnlazada;
import Clases.Canciones.Nodo;
import Clases.Canciones.ReproducirMusica;
import Clases.Usuarios.ListaSimple;
import Clases.Usuarios.Usuarios;

import javax.swing.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

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
    private JFrame jFrame;
    private Usuarios usuario;
    private Clases.Usuarios.ListaSimple listaUsuarios;
    private Bibliotecas biblioteca;
    private MediaPlayer mediaPlayer;
    private Nodo inicio, temp;
    private ReproducirMusica reproducirMusica;

    public VentanaReproducirMusica(Usuarios usuario, ListaSimple listaUsuarios, Bibliotecas biblioteca) {
        this.usuario = usuario;
        this.listaUsuarios = listaUsuarios;
        this.biblioteca = biblioteca;
        initComponents();
        reproducirMusica.Reproducir();
        reproducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirMusica.Reproducir();
            }
        });
        pausarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirMusica.Pausar();
            }
        });
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirMusica.Anterior();
            }
        });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirMusica.Siguiente();
            }
        });
    }

    private void initComponents() {
        jFrame = new JFrame();
        jFrame.setContentPane(jPanel);
        jFrame.setTitle("Bibliotecas");
        jFrame.pack();
        jFrame.setVisible(true);
        ListaDobleEnlazada listaDobleEnlazada = biblioteca.getListaDeCanciones();
        inicio = listaDobleEnlazada.getStart();
        temp = listaDobleEnlazada.getStart();
        reproducirMusica = new ReproducirMusica(biblioteca);


    }



}
