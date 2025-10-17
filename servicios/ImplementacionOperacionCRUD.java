package co.edu.poli.parcial2.servicios;
import co.edu.poli.parcial2.model.ObraArte;
/**
 * Implementación de las operaciones CRUD con almacenamiento en memoria.
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD {
    
    /** Arreglo estático para almacenar obras */
    private static ObraArte[] obras;
    /** Tamaño del arreglo */
    private static final int TAMANIO = 50;
    /** Archivo binario para serialización */
    private static final String ARCHIVO_BINARIO = "museo_obras.dat";
    
    /**
     * Constructor que inicializa el arreglo
     */
    public ImplementacionOperacionCRUD() {
        if (obras == null) {
            obras = new ObraArte[TAMANIO];
        }
    }
    
    @Override
    public boolean create(ObraArte obra) {
        if (obra == null || obra.getCodigo() == null || obra.getCodigo().trim().isEmpty()) {
            System.out.println("❌ Error: Obra o código inválido.");
            return false;
        }
        
        // Verificar si ya existe
        if (read(obra.getCodigo()) != null) {
            System.out.println("❌ Error: Ya existe una obra con el código: " + obra.getCodigo());
            return false;
        }
        
        // Buscar primer espacio null
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] == null) {
                obras[i] = obra;
                System.out.println("✅ Obra creada exitosamente con código: " + obra.getCodigo());
                return true;
            }
        }
        
        System.out.println("❌ Error: No hay espacio disponible en la colección.");
        return false;
    }
    
    @Override
    public ObraArte read(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && codigo.equals(obras[i].getCodigo())) {
                return obras[i];
            }
        }
        
        return null;
    }
    
    @Override
    public boolean update(String codigo, ObraArte obra) {
        if (codigo == null || obra == null) {
            System.out.println("❌ Error: Datos inválidos.");
            return false;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && codigo.equals(obras[i].getCodigo())) {
                obra.setCodigo(codigo); // Mantener código original
                obras[i] = obra;
                System.out.println("✅ Obra actualizada exitosamente.");
                return true;
            }
        }
        
        System.out.println("❌ Error: No se encontró la obra con código: " + codigo);
        return false;
    }
    
    @Override
    public boolean delete(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("❌ Error: Código inválido.");
            return false;
        }
        
        for (int i = 0; i < obras.length; i++) {
            if (obras[i] != null && codigo.equals(obras[i].getCodigo())) {
                System.out.println("✅ Obra eliminada: " + obras[i].getTitulo());
                obras[i] = null;
                return true;
            }
        }
        
        System.out.println("❌ Error: No se encontró la obra con código: " + codigo);
        return false;
    }
    
    @Override
    public ObraArte[] listAll() {
        return obras;
    }
    
    /**
     * Cuenta las obras válidas en el arreglo
     * @return Número de obras
     */
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
     * Obtiene solo las obras válidas (no null)
     * @return Arreglo con obras válidas
     */
    public ObraArte[] getObrasValidas() {
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
     * Serializa todas las obras en un archivo binario
     * @return true si se serializó correctamente
     */
    public boolean serializar() {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                new java.io.FileOutputStream(ARCHIVO_BINARIO))) {
            
            oos.writeObject(obras);
            oos.flush();
            
            java.io.File archivo = new java.io.File(ARCHIVO_BINARIO);
            System.out.println("\n✅ SERIALIZACIÓN EXITOSA");
            System.out.println("   Archivo: " + ARCHIVO_BINARIO);
            System.out.println("   Obras guardadas: " + count());
            System.out.println("   Tamaño: " + archivo.length() + " bytes");
            System.out.println("   Ruta: " + archivo.getAbsolutePath());
            
            return true;
            
        } catch (java.io.IOException e) {
            System.out.println("❌ Error al serializar: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deserializa las obras desde un archivo binario
     * @return true si se deserializó correctamente
     */
    public boolean deserializar() {
        java.io.File archivo = new java.io.File(ARCHIVO_BINARIO);
        
        if (!archivo.exists()) {
            System.out.println("❌ El archivo " + ARCHIVO_BINARIO + " no existe.");
            return false;
        }
        
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                new java.io.FileInputStream(ARCHIVO_BINARIO))) {
            
            int obrasAntes = count();
            obras = (ObraArte[]) ois.readObject();
            int obrasDespues = count();
            
            System.out.println("\n✅ DESERIALIZACIÓN EXITOSA");
            System.out.println("   Archivo: " + ARCHIVO_BINARIO);
            System.out.println("   Obras antes: " + obrasAntes);
            System.out.println("   Obras cargadas: " + obrasDespues);
            
            return true;
            
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al deserializar: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene el nombre del archivo binario
     * @return Nombre del archivo
     */
    public String getArchivoBinario() {
        return ARCHIVO_BINARIO;
    }
    
    /**
     * Verifica si existe el archivo binario
     * @return true si existe
     */
    public boolean existeArchivoBinario() {
        return new java.io.File(ARCHIVO_BINARIO).exists();
    }
}