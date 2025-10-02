package co.edu.poli.actividad7.model;

public class EsculturaObra extends ObraArte {
    /** Escultura específica */
    private Escultura escultura;

    /**
     * Constructor por defecto
     */
    public EsculturaObra() {}

    /**
     * Constructor con parámetros
     * @param nombre Nombre de la obra
     * @param lugar Lugar donde se encuentra
     * @param anioCreacion Año de creación
     * @param id Identificador único
     * @param escultura Escultura asociada
     */
    public EsculturaObra(String nombre, Lugar lugar, int anioCreacion, String id, Escultura escultura) {
        super(nombre, lugar, anioCreacion, id);
        this.escultura = escultura;
    }

    @Override
    public Escultura getEscultura() { return escultura; }
    @Override
    public void setEscultura(Escultura escultura) { this.escultura = escultura; }
}
