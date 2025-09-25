package co.edu.poli.actividad5.model;

public class Callejero extends Lugar {
    /** Tipo de área urbana */
    private String urbano;

    /**
     * Constructor por defecto
     */
    public Callejero() {}

    /**
     * Constructor con parámetros
     * @param pais País
     * @param ciudad Ciudad
     * @param direccion Dirección
     * @param urbano Tipo urbano
     */
    public Callejero(String pais, String ciudad, String direccion, String urbano) {
        super(pais, ciudad, direccion);
        this.urbano = urbano;
    }

    @Override
    protected boolean esAutentico() {
        return "centro".equalsIgnoreCase(urbano) || "historico".equalsIgnoreCase(urbano);
    }

    @Override
    protected boolean esAutentico(String pais, String ciudad) {
        return this.getPais().equals(pais) && this.getCiudad().equals(ciudad) && esAutentico();
    }

    @Override
    protected boolean esAutentico(String pais, String ciudad, String direccion) {
        return this.getPais().equals(pais) && this.getCiudad().equals(ciudad) 
               && this.getDireccion().equals(direccion) && esAutentico();
    }

    // Getters y Setters
    public String getUrbano() { return urbano; }
    public void setUrbano(String urbano) { this.urbano = urbano; }
}
