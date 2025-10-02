package co.edu.poli.actividad7.model;

/**
 * Clase que representa un país
 */
public class Pais implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /** Código del país */
    private String codigo;
    /** Nombre del país */
    private String nombre;

    /**
     * Constructor por defecto
     */
    public Pais() {}

    /**
     * Constructor con parámetros
     * @param codigo Código del país
     * @param nombre Nombre del país
     */
    public Pais(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Pais{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}