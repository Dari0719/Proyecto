package co.edu.poli.actividad5.servicios;

import co.edu.poli.actividad5.model.ObraArte;

public class ImplementacionOperacionCRUD implements OperacionCRUD {
    
    /** Arreglo estático para almacenar las obras de arte */
    private static ObraArte[] obras;
    /** Tamaño inicial del arreglo */
    private static final int TAMANIO_INICIAL = 100;
    
    /**
     * Constructor que inicializa el arreglo de obras
     */
    public ImplementacionOperacionCRUD() {
        if (obras == null) {
            obras = new ObraArte[TAMANIO_INICIAL];
        }
    }
    
    /**
     * {@inheritDoc}
     * Busca el primer espacio null de izquierda a derecha para insertar.
     * Si no hay espacio, expande el arreglo automáticamente.
     */
    @Override
    public boolean create(ObraArte obra) {
        if (obra == null) {
            System.out.println("Error: No se puede crear una obra nula.");
            return false;
        }
        
        if (obra.getId() == null || obra.getId().trim().isEmpty()) {
            System.out.println("Error: La obra debe tener un ID válido.");
            return false;
        }
        
        // Verificar si ya existe una obra con ese ID
        if (read(obra.getId()) != null) {
            System.out.println("Error: Ya existe una obra con el ID: " + obra.getId());
            return false;
        }
        
        // Buscar el primer espacio null
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] == null) {
                obras[i] = obra;
                System.out.println("Obra creada exitosamente con ID: " + obra.getId());
                return true;
            }
        }
        
        // Si no hay espacio, expandir el arreglo
        expandirArreglo();
        // Intentar nuevamente
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] == null) {
                obras[i] = obra;
                System.out.println("Obra creada exitosamente con ID: " + obra.getId() + 
                                 " (arreglo expandido)");
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * {@inheritDoc}
     * Busca la obra por ID en todo el arreglo.
     */
    @Override
    public ObraArte read(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: El ID no puede ser nulo o vacío.");
            return null;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && id.equals(obras[i].getId())) {
                return obras[i];
            }
        }
        
        return null;
    }
    
    /**
     * {@inheritDoc}
     * Encuentra la obra por ID y actualiza sus datos.
     */
    @Override
    public boolean update(String id, ObraArte obra) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: El ID no puede ser nulo o vacío.");
            return false;
        }
        
        if (obra == null) {
            System.out.println("Error: Los nuevos datos de la obra no pueden ser nulos.");
            return false;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && id.equals(obras[i].getId())) {
                // Mantener el ID original
                obra.setId(id);
                obras[i] = obra;
                System.out.println("Obra actualizada exitosamente con ID: " + id);
                return true;
            }
        }
        
        System.out.println("Error: No se encontró una obra con el ID: " + id);
        return false;
    }
    
    /**
     * {@inheritDoc}
     * Encuentra la obra por ID y la elimina (asigna null).
     */
    @Override
    public boolean delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: El ID no puede ser nulo o vacío.");
            return false;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && id.equals(obras[i].getId())) {
                System.out.println("Obra eliminada: " + obras[i].getNombre() + 
                                 " (ID: " + id + ")");
                obras[i] = null;
                return true;
            }
        }
        
        System.out.println("Error: No se encontró una obra con el ID: " + id);
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ObraArte[] listAll() {
        return obras;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int count() {
        int contador = 0;
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null) {
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Expande el arreglo al doble de su tamaño actual
     */
    private void expandirArreglo() {
        ObraArte[] nuevoArreglo = new ObraArte[obras.length * 2];
        System.arraycopy(obras, 0, nuevoArreglo, 0, obras.length);
        obras = nuevoArreglo;
        System.out.println("Arreglo expandido a " + obras.length + " posiciones.");
    }
    
    /**
     * Lista solo las obras que no son null
     * @return Arreglo con obras válidas
     */
    public ObraArte[] listValidObras() {
        ObraArte[] obrasValidas = new ObraArte[count()];
        int index = 0;
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null) {
                obrasValidas[index++] = obras[i];
            }
        }
        
        return obrasValidas;
    }
}