package Clases.Canciones;

import Clases.Bibliotecas.Bibliotecas;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ReproducirMusica {
    JFXPanel fxPanel = new JFXPanel();
    private Nodo inicio, temp;
    private static MediaPlayer mediaPlayer;
    private Bibliotecas biblioteca;

    public ReproducirMusica(Bibliotecas biblioteca) {
        this.biblioteca = biblioteca;
        this.inicio = biblioteca.getListaDeCanciones().getStart();
        this.temp = biblioteca.getListaDeCanciones().getStart();
        cargarBiblioteca();

    }

    private void cargarBiblioteca() {

        try {
            String path = "Canciones/" + temp.getData().getPath();
            File file = new File(path);
            Media media = new Media(file.toURI().toString());

            this.mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> {
                if (temp.getNext() != inicio) {
                    temp = temp.getNext();
                    cargarBiblioteca();
                }
            });
            Reproducir();
        } catch (Exception e){
            System.out.println(e.getMessage());
            temp = temp.getNext();
            cargarBiblioteca();
            Reproducir();
        }

    }
    public void Reproducir(){
        mediaPlayer.play();
    }
    public void Siguiente(){
        temp = temp.getNext();
        cargarBiblioteca();
    }
    public void Anterior(){
        temp = temp.getPrev();
        cargarBiblioteca();
    }
    public void Pausar(){
        mediaPlayer.pause();
    }
}
