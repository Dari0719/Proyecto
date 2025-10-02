package co.edu.poli.actividad7.model;

public class Galeria extends Lugar {
    /** Nivel de autenticidad de la galería */
    private String autenticidad;

    /**
     * Constructor por defecto
     */
    public Galeria() {}

    /**
     * Constructor con parámetros
     * @param pais País
     * @param ciudad Ciudad
     * @param direccion Dirección
     * @param autenticidad Nivel de autenticidad
     */
    public Galeria(String pais, String ciudad, String direccion, String autenticidad) {
        super(pais, ciudad, direccion);
        this.autenticidad = autenticidad;
    }

    @Override
    protected boolean esAutentico() {
        return "alta".equalsIgnoreCase(autenticidad);
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
    public String getAutenticidad() { return autenticidad; }
    public void setAutenticidad(String autenticidad) { this.autenticidad = autenticidad; }
}
