package co.edu.poli.actividad7.servicios;

/**
 * Clase de servicio especializada que extiende la funcionalidad CRUD
 * para lugares no reconocidos o problemáticos
 */
public class NoReconocido {
    /** Descripción del artista no reconocido */
    private String artista_no_reconocido;

    /**
     * Constructor por defecto
     */
    public NoReconocido() {}

    /**
     * Constructor con parámetros
     * @param artista_no_reconocido Descripción del artista no reconocido
     */
    public NoReconocido(String artista_no_reconocido) {
        this.artista_no_reconocido = artista_no_reconocido;
    }

    // Getters y Setters
    public String getArtista_no_reconocido() { return artista_no_reconocido; }
    public void setArtista_no_reconocido(String artista_no_reconocido) { 
        this.artista_no_reconocido = artista_no_reconocido; 
    }
}