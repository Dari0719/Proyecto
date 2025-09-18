package ejercicio1.model;

public class Rueda {

	private String serial;
    private String presion;
    private String tamanio;
    private double diametro;
    private String material;
    private String color;
    
    public Rueda() {
		super();
		
	}
	public Rueda(String serial, String presion, String tamanio, double diametro, String material, String color) {
		super();
		this.serial = serial;
		this.presion = presion;
		this.tamanio = tamanio;
		this.diametro = diametro;
		this.material = material;
		this.color = color;
	}
	@Override
	public String toString() {
		return "Rueda [serial=" + serial + ", presion=" + presion + ", tamanio=" + tamanio + ", diametro=" + diametro
				+ ", material=" + material + ", color=" + color + ", getSerial()=" + getSerial() + ", getPresion()="
				+ getPresion() + ", getTamanio()=" + getTamanio() + ", getDiametro()=" + getDiametro()
				+ ", getMaterial()=" + getMaterial() + ", getColor()=" + getColor() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getPresion() {
		return presion;
	}
	public void setPresion(String presion) {
		this.presion = presion;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public double getDiametro() {
		return diametro;
	}
	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
    
    
		

	}


