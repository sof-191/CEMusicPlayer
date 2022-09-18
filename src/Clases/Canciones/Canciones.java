package Clases.Canciones;

/***
 * Clase donde se almacenaran las canciones(Id,nombre,genero,artista,album,anio, letra)
 */
public class Canciones {
    private String id;
    private String nombre;
    private String genero;
    private String artista;
    private String album;
    private String anio;
    private String letra;

    /***
     * Clase constructora de Canciones
     * @param id identificador de la cancion
     * @param nombre nombre de la cancion
     * @param genero genero de la cancion
     * @param artista artista de la cancion
     * @param album album de la cancion
     * @param anio anio de la cancion
     * @param letra letras de la cancion
     */
    public Canciones(String id, String nombre, String genero, String artista, String album, String anio, String letra) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.artista = artista;
        this.album = album;
        this.anio = anio;
        this.letra = letra;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
