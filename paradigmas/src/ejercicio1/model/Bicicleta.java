package ejercicio1.model;

import java.util.Arrays;
import java.util.Objects;

public class Bicicleta {

	    private String serial;
	    private String color;
	    private String tamanio;
	    private int peso;
	    private double medida;
	    private String material;
	    private Rueda[] rueda;
	    
	    
	    
	    public String getSerial() {
			return serial;
		}



		public void setSerial(String serial) {
			this.serial = serial;
		}



		public String getColor() {
			return color;
		}



		public void setColor(String color) {
			this.color = color;
		}



		public String getTamanio() {
			return tamanio;
		}



		public void setTamanio(String tamanio) {
			this.tamanio = tamanio;
		}



		public int getPeso() {
			return peso;
		}



		public void setPeso(int peso) {
			this.peso = peso;
		}



		public double getMedida() {
			return medida;
		}



		public void setMedida(double medida) {
			this.medida = medida;
		}



		public String getMaterial() {
			return material;
		}



		public void setMaterial(String material) {
			this.material = material;
		}



		public Rueda[] getRueda() {
			return rueda;
		}



		public void setRueda(Rueda[] rueda) {
			this.rueda = rueda;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(rueda);
			result = prime * result + Objects.hash(color, material, medida, peso, serial, tamanio);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Bicicleta other = (Bicicleta) obj;
			return Objects.equals(color, other.color) && Objects.equals(material, other.material)
					&& Double.doubleToLongBits(medida) == Double.doubleToLongBits(other.medida) && peso == other.peso
					&& Arrays.equals(rueda, other.rueda) && Objects.equals(serial, other.serial)
					&& Objects.equals(tamanio, other.tamanio);
		}



		public Bicicleta(String serial, String color, String tamanio, int peso, double medida, String material,
				Rueda[] rueda) {
			super();
			this.serial = serial;
			this.color = color;
			this.tamanio = tamanio;
			this.peso = peso;
			this.medida = medida;
			this.material = material;
			this.rueda = rueda;
		}



		@Override
		public String toString() {
			return "Bicicleta [serial=" + serial + ", color=" + color + ", tamanio=" + tamanio + ", peso=" + peso
					+ ", medida=" + medida + ", material=" + material + ", rueda=" + Arrays.toString(rueda)
					+ ", getSerial()=" + getSerial() + ", getColor()=" + getColor() + ", getTamanio()=" + getTamanio()
					+ ", getPeso()=" + getPeso() + ", getMedida()=" + getMedida() + ", getMaterial()=" + getMaterial()
					+ ", getRueda()=" + Arrays.toString(getRueda()) + ", hashCode()=" + hashCode() + ", getClass()="
					+ getClass() + ", toString()=" + super.toString() + "]";
		}



		public double detreminarCadencia(double tamPinion) {
	    	return 0.0;
	    }

	}


