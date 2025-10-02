package co.edu.poli.actividad7.model;

/**
 * Clase que representa un autor de obra de arte
 */
public class Autor implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /** Fecha de nacimiento */
    private String fecha;
    /** Países donde ha trabajado */
    private Pais[] pais;
    /** Nombre del autor */
    private String nombre;
    /** Número de identificación */
    private String num_id;
    /** Edad cuando comenzó a pintar */
    private int edad_pintar;

    /**
     * Constructor por defecto
     */
    public Autor() {
        this.pais = new Pais[5]; // Capacidad para 5 países
    }

    /**
     * Constructor con parámetros
     * @param fecha Fecha de nacimiento
     * @param nombre Nombre
     * @param num_id Número de ID
     * @param edad_pintar Edad al comenzar a pintar
     */
    public Autor(String fecha, String nombre, String num_id, int edad_pintar) {
        this();
        this.fecha = fecha;
        this.nombre = nombre;
        this.num_id = num_id;
        this.edad_pintar = edad_pintar;
    }

    // Getters y Setters
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    
    public Pais[] getPais() { return pais; }
    public void setPais(Pais[] pais) { this.pais = pais; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getNum_id() { return num_id; }
    public void setNum_id(String num_id) { this.num_id = num_id; }
    
    public int getEdad_pintar() { return edad_pintar; }
    public void setEdad_pintar(int edad_pintar) { this.edad_pintar = edad_pintar; }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", num_id='" + num_id + '\'' +
                ", edad_pintar=" + edad_pintar +
                '}';
    }
}
