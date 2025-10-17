package co.edu.poli.parcial2.model;

/**
 * Clase que representa una escultura.
 * Extiende ObraArte e incluye el material del que está hecha.
 */
public class Escultura extends ObraArte {
    private static final long serialVersionUID = 1L;
    
    /** Material del que está hecha (mármol, bronce, etc.) */
    private String material;
    
    /**
     * Constructor por defecto
     */
    public Escultura() {
        super();
    }
    
    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param titulo Título
     * @param fechaCreacion Fecha de creación
     * @param dimensiones Dimensiones
     * @param artista Artista
     * @param material Material empleado
     */
    public Escultura(String codigo, String titulo, String fechaCreacion, 
                     String dimensiones, Artista artista, String material) {
        super(codigo, titulo, fechaCreacion, dimensiones, artista);
        this.material = material;
    }
    
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    @Override
    public String getTipoObra() {
        return "ESCULTURA";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Material: " + material;
    }
}