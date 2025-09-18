// Archivo Main.java (fuera del paquete model, por ejemplo, en vista)
package co.edu.poli.actividad5.vista;

import co.edu.poli.actividad5.model.ObraArte;


public class Visitante {
    public static void main(String[] args) {
        // Creación de objetos usando las clases hijas
        ObraArte pintura = new ObraArte();
        ObraArte escultura = new ObraArte();
        
        // Asignación de un valor para el año de creación para el ejemplo
        // Esto sería parte de la inicialización real de los objetos
        pintura.setAnioCreacion("1980"); 
        escultura.setAnioCreacion("1850"); 
        
        // Invocación de los métodos sobreescritos
        pintura.calcularEdad();    // Llama a la versión de Pintura
        escultura.calcularEdad();  // Llama a la versión de Escultura
    }
}