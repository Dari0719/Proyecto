package co.edu.poli.actividad5.model;

public class Pintura {
    /** Nombre de la pintura */
    private String nombre;
    /** Autor de la pintura */
    private Autor autor;
    /** Tamaño de la pintura */
    private String tamanio;
    /** Inspiración de la pintura */
    private String inspiracion;
    /** Número serial */
    private String serial;
    /** Medida en metros cuadrados */
    private double medida;

    /**
     * Constructor por defecto
     */
    public Pintura() {}

    /**
     * Constructor con parámetros
     * @param nombre Nombre
     * @param autor Autor
     * @param tamanio Tamaño
     * @param inspiracion Inspiración
     * @param serial Serial
     * @param medida Medida
     */
    public Pintura(String nombre, Autor autor, String tamanio, String inspiracion, String serial, double medida) {
        this.nombre = nombre;
        this.autor = autor;
        this.tamanio = tamanio;
        this.inspiracion = inspiracion;
        this.serial = serial;
        this.medida = medida;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    
    public String getTamanio() { return tamanio; }
    public void setTamanio(String tamanio) { this.tamanio = tamanio; }
    
    public String getInspiracion() { return inspiracion; }
    public void setInspiracion(String inspiracion) { this.inspiracion = inspiracion; }
    
    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    
    public double getMedida() { return medida; }
    public void setMedida(double medida) { this.medida = medida; }

    @Override
    public String toString() {
        return "Pintura{" +
                "nombre='" + nombre + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "N/A") +
                ", tamanio='" + tamanio + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }
}
