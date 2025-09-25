package co.edu.poli.actividad5.model;

/**
 * Clase abstracta que representa una obra de arte genérica.
 * Sirve como clase padre para diferentes tipos de obras de arte.
 * 
 * @author Sistema de Gestión de Arte
 * @version 1.0
 */
public abstract class ObraArte {
    /** Pintura asociada a la obra */
    private Pintura pintura;
    /** Escultura asociada a la obra */
    private Escultura escultura;
    /** Nombre de la obra de arte */
    private String nombre;
    /** Lugar donde se encuentra la obra */
    private Lugar lugar;
    /** Horarios de visita */
    private Horario[] horario;
    /** Año de creación de la obra */
    private int anioCreacion;
    /** ID único de la obra */
    private String id;

    /**
     * Constructor por defecto
     */
    public ObraArte() {
        this.horario = new Horario[10]; // Inicializar con capacidad para 10 horarios
    }

    /**
     * Constructor con parámetros
     * @param nombre Nombre de la obra
     * @param lugar Lugar donde se encuentra
     * @param anioCreacion Año de creación
     * @param id Identificador único
     */
    public ObraArte(String nombre, Lugar lugar, int anioCreacion, String id) {
        this();
        this.nombre = nombre;
        this.lugar = lugar;
        this.anioCreacion = anioCreacion;
        this.id = id;
    }

    /**
     * Calcula la edad de la obra de arte
     * @return Edad en años
     */
    public int calcularEdad() {
        return java.time.Year.now().getValue() - anioCreacion;
    }

    // Getters y Setters
    public Pintura getPintura() { return pintura; }
    public void setPintura(Pintura pintura) { this.pintura = pintura; }
    
    public Escultura getEscultura() { return escultura; }
    public void setEscultura(Escultura escultura) { this.escultura = escultura; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public Lugar getLugar() { return lugar; }
    public void setLugar(Lugar lugar) { this.lugar = lugar; }
    
    public Horario[] getHorario() { return horario; }
    public void setHorario(Horario[] horario) { this.horario = horario; }
    
    public int getAnioCreacion() { return anioCreacion; }
    public void setAnioCreacion(int anioCreacion) { this.anioCreacion = anioCreacion; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return "ObraArte{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anioCreacion=" + anioCreacion +
                ", edad=" + calcularEdad() + " años" +
                '}';
    }
}

