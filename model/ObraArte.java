package co.edu.poli.parcial2.model;

/**
 * Clase abstracta que representa una obra de arte genérica.
 * Contiene atributos comunes a todas las obras del museo.
 * 
 * @author Sistema de Gestión de Museo
 * @version 1.0
 */
public abstract class ObraArte implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Código único de la obra */
    private String codigo;
    /** Título de la obra */
    private String titulo;
    /** Fecha de creación de la obra */
    private String fechaCreacion;
    /** Dimensiones de la obra */
    private String dimensiones;
    /** Artista creador de la obra */
    private Artista artista;
    
    /**
     * Constructor por defecto
     */
    public ObraArte() {}
    
    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param titulo Título de la obra
     * @param fechaCreacion Fecha de creación
     * @param dimensiones Dimensiones
     * @param artista Artista asociado
     */
    public ObraArte(String codigo, String titulo, String fechaCreacion, 
                    String dimensiones, Artista artista) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.dimensiones = dimensiones;
        this.artista = artista;
    }
    
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public String getDimensiones() { return dimensiones; }
    public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }
    
    public Artista getArtista() { return artista; }
    public void setArtista(Artista artista) { this.artista = artista; }
    
    /**
     * Método abstracto para obtener el tipo de obra
     * @return Tipo de obra
     */
    public abstract String getTipoObra();
    
    @Override
    public String toString() {
        return "Código: " + codigo + ", Título: " + titulo + 
               ", Tipo: " + getTipoObra() + ", Artista: " + 
               (artista != null ? artista.getNombre() : "N/A");
    }
}