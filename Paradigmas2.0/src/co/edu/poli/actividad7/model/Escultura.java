package co.edu.poli.actividad7.model;

/**
 * Clase que representa una escultura
 */
public class Escultura implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /** Nombre de la escultura */
    private String nombre;
    /** Tamaño de la escultura */
    private String tamanio;
    /** Material de la escultura */
    private String material;
    /** Autor de la escultura */
    private Autor autor;
    /** Número serial */
    private String serial;
    /** Referencia de la escultura */
    private String referencia;
    /** Medida en metros cúbicos */
    private double medida;

    /**
     * Constructor por defecto
     */
    public Escultura() {}

    /**
     * Constructor con parámetros
     * @param nombre Nombre
     * @param tamanio Tamaño
     * @param material Material
     * @param autor Autor
     * @param serial Serial
     * @param referencia Referencia
     * @param medida Medida
     */
    public Escultura(String nombre, String tamanio, String material, Autor autor, 
                    String serial, String referencia, double medida) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.material = material;
        this.autor = autor;
        this.serial = serial;
        this.referencia = referencia;
        this.medida = medida;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getTamanio() { return tamanio; }
    public void setTamanio(String tamanio) { this.tamanio = tamanio; }
    
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    
    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    
    public double getMedida() { return medida; }
    public void setMedida(double medida) { this.medida = medida; }

    @Override
    public String toString() {
        return "Escultura{" +
                "nombre='" + nombre + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "N/A") +
                ", material='" + material + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }
}
