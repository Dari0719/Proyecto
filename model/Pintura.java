package co.edu.poli.parcial2.model;

/**
 * Clase que representa una pintura.
 * Extiende ObraArte e incluye la técnica empleada.
 */
public class Pintura extends ObraArte {
    private static final long serialVersionUID = 1L;
    
    /** Técnica empleada (óleo, acuarela, etc.) */
    private String tecnica;
    
    /**
     * Constructor por defecto
     */
    public Pintura() {
        super();
    }
    
    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param titulo Título
     * @param fechaCreacion Fecha de creación
     * @param dimensiones Dimensiones
     * @param artista Artista
     * @param tecnica Técnica empleada
     */
    public Pintura(String codigo, String titulo, String fechaCreacion, 
                   String dimensiones, Artista artista, String tecnica) {
        super(codigo, titulo, fechaCreacion, dimensiones, artista);
        this.tecnica = tecnica;
    }
    
    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) { this.tecnica = tecnica; }
    
    @Override
    public String getTipoObra() {
        return "PINTURA";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Técnica: " + tecnica;
    }
}