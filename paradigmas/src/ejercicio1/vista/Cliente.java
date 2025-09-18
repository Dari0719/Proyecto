package ejercicio1.vista;

import ejercicio1.model.Bicicleta;
import ejercicio1.model.Rueda;

public class Cliente {

	public static void main(String[] args) {
		
		Rueda rueda1=new Rueda("HPTS32","27PSI","mediano", 25.5,"caucho","camuflado");
		Rueda rueda2=new Rueda("HPTS33","27PSI","mediano", 25.5,"caucho","camuflado");
		
		Rueda[] ruedas=new Rueda[2];
		ruedas[0]=rueda1;
		ruedas[1]=rueda2;
		
		Bicicleta bici=new Bicicleta("DCUCM","Roja","L",25,50.2,"titanio",ruedas);
		System.out.println(bici);

	}

}
