package co.edu.poli.actividad5.model;


public class ObraArte {
    
    protected final String tipoObra = "GENERICA";

    protected String anioCreacion;

    
    public int calcularEdad() {
        System.out.println("Cálculo de edad genérico para una obra de arte.");
        return 0;
    }

        public final void mostrarTipoObra() {
        System.out.println("El tipo de obra es: " + tipoObra);
    }

    
    public void mostrarDetalles(String mensaje) {
        System.out.println("Detalles de la obra: " + mensaje);
    }

    
    public ObraArte crearNuevaObra() {
        System.out.println("Creando una obra genérica...");
        return new ObraArte();
    }

	public void setAnioCreacion(String string) {
		
		
	}
}


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


class Principal {
    public static void main(String[] args) {
        
        ObraArte[] coleccion = new ObraArte[5];

        
        Pintura p = new Pintura();
        p.anioCreacion = "2000";

        Escultura e = new Escultura();
        e.anioCreacion = "1995";

        Fotografia f = new Fotografia();
        f.anioCreacion = "2010";

        coleccion[0] = p;
        coleccion[1] = e;
        coleccion[2] = f;

  
        for (ObraArte obra : coleccion) {
            if (obra != null) {
                System.out.println("Edad de la obra: " + obra.calcularEdad());
            }
        }



        ObraArte obra1 = new Pintura();
        ObraArte obra2 = new Escultura();


        obra1.mostrarDetalles("Es una pintura famosa.");
        obra2.mostrarDetalles("Es una escultura clásica.");

        
        ObraArte nueva1 = obra1.crearNuevaObra();
        ObraArte nueva2 = obra2.crearNuevaObra();

        
        p.mostrarTipoObra(); 
        e.mostrarTipoObra();
        f.mostrarTipoObra();
    }
}
