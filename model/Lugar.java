package co.edu.poli.actividad5.model;

public abstract class Lugar {
    /** País donde se encuentra */
    private String pais;
    /** Ciudad donde se encuentra */
    private String ciudad;
    /** Dirección específica */
    private String direccion;

    /**
     * Constructor por defecto
     */
    public Lugar() {}

    /**
     * Constructor con parámetros
     * @param pais País
     * @param ciudad Ciudad
     * @param direccion Dirección
     */
    public Lugar(String pais, String ciudad, String direccion) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    /**
     * Verifica si el lugar es auténtico
     * @return true si es auténtico
     */
    protected abstract boolean esAutentico();

    /**
     * Verifica si el lugar es auténtico por país y ciudad
     * @param pais País a verificar
     * @param ciudad Ciudad a verificar
     * @return true si es auténtico
     */
    protected abstract boolean esAutentico(String pais, String ciudad);

    /**
     * Verifica si el lugar es auténtico por país, ciudad y dirección
     * @param pais País a verificar
     * @param ciudad Ciudad a verificar
     * @param direccion Dirección a verificar
     * @return true si es auténtico
     */
    protected abstract boolean esAutentico(String pais, String ciudad, String direccion);

    // Getters y Setters
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}

