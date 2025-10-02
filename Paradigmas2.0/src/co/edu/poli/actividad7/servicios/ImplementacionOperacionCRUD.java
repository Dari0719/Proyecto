package co.edu.poli.actividad7.servicios;

import co.edu.poli.actividad7.model.ObraArte;

/**
 * Implementación concreta de las operaciones CRUD para obras de arte.
 * Utiliza un arreglo estático en memoria para almacenar las obras.
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD {
    
    /** Arreglo estático para almacenar las obras de arte */
    private static ObraArte[] obras;
    /** Tamaño inicial del arreglo */
    private static final int TAMANIO_INICIAL = 100;
    /** Nombre del archivo binario para serialización */
    private static final String ARCHIVO_BINARIO = "obras_arte.dat";
    
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
    
    /**
     * Serializa (guarda) todas las obras en un archivo binario.
     * Utiliza ObjectOutputStream para escribir el arreglo completo.
     * 
     * @return true si se serializó exitosamente, false en caso contrario
     */
    public boolean serializar() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                new java.io.FileOutputStream(ARCHIVO_BINARIO))) {
            
            // Serializar el arreglo completo de obras
            oos.writeObject(obras);
            oos.flush();
            
            System.out.println("✅ Serialización exitosa!");
            System.out.println("   Archivo: " + ARCHIVO_BINARIO);
            System.out.println("   Obras guardadas: " + count());
            
            // Mostrar información del archivo
            java.io.File archivo = new java.io.File(ARCHIVO_BINARIO);
            System.out.println("   Tamaño del archivo: " + archivo.length() + " bytes");
            System.out.println("   Ruta completa: " + archivo.getAbsolutePath());
            
            return true;
            
        } catch (java.io.IOException e) {
            System.out.println("❌ Error al serializar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Deserializa (carga) las obras desde un archivo binario.
     * Utiliza ObjectInputStream para leer el arreglo completo.
     * 
     * @return true si se deserializó exitosamente, false en caso contrario
     */
    public boolean deserializar() {
        java.io.File archivo = new java.io.File(ARCHIVO_BINARIO);
        
        if (!archivo.exists()) {
            System.out.println("❌ El archivo binario no existe: " + ARCHIVO_BINARIO);
            System.out.println("   Primero debe serializar los datos.");
            return false;
        }
        
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.FileInputStream(ARCHIVO_BINARIO))) {
            
            // Guardar conteo previo
            int obrasAntes = count();
            
            // Deserializar el arreglo completo
            obras = (ObraArte[]) ois.readObject();
            
            int obrasDespues = count();
            
            System.out.println("✅ Deserialización exitosa!");
            System.out.println("   Archivo: " + ARCHIVO_BINARIO);
            System.out.println("   Obras antes: " + obrasAntes);
            System.out.println("   Obras cargadas: " + obrasDespues);
            System.out.println("   Tamaño del arreglo: " + obras.length);
            
            return true;
            
        } catch (java.io.IOException e) {
            System.out.println("❌ Error de entrada/salida al deserializar: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Clase no encontrada al deserializar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtiene el nombre del archivo binario utilizado
     * @return Nombre del archivo binario
     */
    public String getArchivoBinario() {
        return ARCHIVO_BINARIO;
    }
    
    /**
     * Verifica si existe el archivo binario
     * @return true si existe, false en caso contrario
     */
    public boolean existeArchivoBinario() {
        return new java.io.File(ARCHIVO_BINARIO).exists();
    }
    
    /**
     * Obtiene información detallada del archivo binario
     * @return String con información del archivo o mensaje de error
     */
    public String getInfoArchivoBinario() {
        java.io.File archivo = new java.io.File(ARCHIVO_BINARIO);
        
        if (!archivo.exists()) {
            return "El archivo binario no existe.";
        }
        
        StringBuilder info = new StringBuilder();
        info.append("Información del Archivo Binario:\n");
        info.append("  Nombre: ").append(archivo.getName()).append("\n");
        info.append("  Ruta: ").append(archivo.getAbsolutePath()).append("\n");
        info.append("  Tamaño: ").append(archivo.length()).append(" bytes\n");
        info.append("  Última modificación: ").append(
            new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new java.util.Date(archivo.lastModified()))).append("\n");
        info.append("  Puede leer: ").append(archivo.canRead() ? "Sí" : "No").append("\n");
        info.append("  Puede escribir: ").append(archivo.canWrite() ? "Sí" : "No");
        
        return info.toString();
    }
}
