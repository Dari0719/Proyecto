package co.edu.poli.parcial2.model;

/**
 * Clase que representa un artista.
 * Define las características básicas de un artista.
 * 
 * @author Sistema de Gestión de Museo
 * @version 1.0
 */
public class Artista implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    /** ID único del artista */
    private String id;
    /** Nombre completo del artista */
    private String nombre;
    /** Nacionalidad del artista */
    private String nacionalidad;
    
    /**
     * Constructor por defecto
     */
    public Artista() {}
    
    /**
     * Constructor con parámetros
     * @param id ID del artista
     * @param nombre Nombre del artista
     * @param nacionalidad Nacionalidad del artista
     */
    public Artista(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    
    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}
