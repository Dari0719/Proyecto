package co.edu.poli.actividad5.model;

public class PinturaObra extends ObraArte {
    /** Pintura específica */
    private Pintura pintura;

    /**
     * Constructor por defecto
     */
    public PinturaObra() {}

    /**
     * Constructor con parámetros
     * @param nombre Nombre de la obra
     * @param lugar Lugar donde se encuentra
     * @param anioCreacion Año de creación
     * @param id Identificador único
     * @param pintura Pintura asociada
     */
    public PinturaObra(String nombre, Lugar lugar, int anioCreacion, String id, Pintura pintura) {
        super(nombre, lugar, anioCreacion, id);
        this.pintura = pintura;
    }

    @Override
    public Pintura getPintura() { return pintura; }
    @Override
    public void setPintura(Pintura pintura) { this.pintura = pintura; }
}

