package co.edu.poli.actividad7.model;

/**
 * Clase que representa un horario de visita
 */
public class Horario implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /** Horario semanal */
    private String semanal;

    /**
     * Constructor por defecto
     */
    public Horario() {}

    /**
     * Constructor con parámetros
     * @param semanal Horario semanal
     */
    public Horario(String semanal) {
        this.semanal = semanal;
    }

    // Getters y Setters
    public String getSemanal() { return semanal; }
    public void setSemanal(String semanal) { this.semanal = semanal; }

    @Override
    public String toString() {
        return "Horario{semanal='" + semanal + "'}";
    }
}
