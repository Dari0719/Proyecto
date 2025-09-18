package co.edu.poli.actividad5.model;

// ============================
// SUPER SUPER CLASE
// ============================
public class ObraArte {
    // Atributo que no se puede cambiar (final)
    protected final String tipoObra = "GENERICA";

    protected String anioCreacion;

    // Método que puede ser sobreescrito por las clases hijas
    public int calcularEdad() {
        System.out.println("Cálculo de edad genérico para una obra de arte.");
        return 0;
    }

    // Método que no se puede sobreescribir (final)
    public final void mostrarTipoObra() {
        System.out.println("El tipo de obra es: " + tipoObra);
    }

    // Método polimórfico que recibe un parámetro
    public void mostrarDetalles(String mensaje) {
        System.out.println("Detalles de la obra: " + mensaje);
    }

    // Método polimórfico que retorna una supersuperclase
    public ObraArte crearNuevaObra() {
        System.out.println("Creando una obra genérica...");
        return new ObraArte();
    }

	public void setAnioCreacion(String string) {
		// TODO Auto-generated method stub
		
	}
}

// ============================
// SUBCLASES
// ============================
class Pintura extends ObraArte {
    public String nombre;

    @Override
    public int calcularEdad() {
        System.out.println("Cálculo de edad específico para una pintura.");
        return 2024 - Integer.parseInt(anioCreacion);
    }

    @Override
    public ObraArte crearNuevaObra() {
        System.out.println("Creando una pintura...");
        return new Pintura();
    }
}

class Escultura extends ObraArte {
    public String material;

    @Override
    public int calcularEdad() {
        System.out.println("Cálculo de edad específico para una escultura.");
        return 2024 - Integer.parseInt(anioCreacion);
    }

    @Override
    public ObraArte crearNuevaObra() {
        System.out.println("Creando una escultura...");
        return new Escultura();
    }
}

// Clase que no se puede heredar (final)
final class Fotografia extends ObraArte {
    public String formato;

    @Override
    public int calcularEdad() {
        System.out.println("Cálculo de edad específico para una fotografía.");
        return 2024 - Integer.parseInt(anioCreacion);
    }

    @Override
    public ObraArte crearNuevaObra() {
        System.out.println("Creando una fotografía...");
        return new Fotografia();
    }
}

// ============================
// CLASE PRINCIPAL
// ============================
class Principal {
    public static void main(String[] args) {
        // ==============================
        // 1. Crear un arreglo de ObraArte (super super clase)
        // ==============================
        ObraArte[] coleccion = new ObraArte[5];

        // 3 objetos de diferentes subclases
        Pintura p = new Pintura();
        p.anioCreacion = "2000";

        Escultura e = new Escultura();
        e.anioCreacion = "1995";

        Fotografia f = new Fotografia();
        f.anioCreacion = "2010";

        coleccion[0] = p;
        coleccion[1] = e;
        coleccion[2] = f;

        // ==============================
        // 2. Imprimir sobrescritura de métodos
        // ==============================
        for (ObraArte obra : coleccion) {
            if (obra != null) {
                System.out.println("Edad de la obra: " + obra.calcularEdad());
            }
        }

        // ==============================
        // 3. Invocar métodos con polimorfismo
        // ==============================
        ObraArte obra1 = new Pintura();
        ObraArte obra2 = new Escultura();

        // Método con parámetro
        obra1.mostrarDetalles("Es una pintura famosa.");
        obra2.mostrarDetalles("Es una escultura clásica.");

        // Método que retorna un tipo ObraArte
        ObraArte nueva1 = obra1.crearNuevaObra();
        ObraArte nueva2 = obra2.crearNuevaObra();

        // ==============================
        // 4. Uso de atributo final y método final
        // ==============================
        p.mostrarTipoObra(); // Método final (no puede ser sobrescrito)
        e.mostrarTipoObra();
        f.mostrarTipoObra();
    }
}
