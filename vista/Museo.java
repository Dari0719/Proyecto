package co.edu.poli.parcial2.vista;
import co.edu.poli.parcial2.model.*;
import co.edu.poli.parcial2.servicios.*;
import java.util.Scanner;

/**
 * Clase principal del sistema de gestión del museo.
 * Proporciona un menú interactivo para gestionar la colección de obras.
 * 
 * @author Sistema de Gestión de Museo
 * @version 1.0
 */
public class Museo {
    
    private static Scanner scanner = new Scanner(System.in);
    private static ImplementacionOperacionCRUD servicio = new ImplementacionOperacionCRUD();
    
    // Artistas predefinidos
    private static Artista[] artistasPredefinidos = {
        new Artista("ART001", "Leonardo da Vinci", "Italiano"),
        new Artista("ART002", "Pablo Picasso", "Español"),
        new Artista("ART003", "Vincent van Gogh", "Holandés"),
        new Artista("ART004", "Michelangelo Buonarroti", "Italiano"),
        new Artista("ART005", "Auguste Rodin", "Francés"),
        new Artista("ART006", "Frida Kahlo", "Mexicana"),
        new Artista("ART007", "Diego Velázquez", "Español"),
        new Artista("ART008", "Fernando Botero", "Colombiano")
    };
    
    public static void main(String[] args) {
        mostrarBienvenida();
        
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 8);
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  Gracias por usar el Sistema del Museo ║");
        System.out.println("╚════════════════════════════════════════╝");
        scanner.close();
    }
    
    private static void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE GESTIÓN DE COLECCIÓN DE MUSEO    ║");
        System.out.println("║          Pinturas y Esculturas                   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
    
    private static void mostrarMenu() {
        System.out.println("\n┌─────────────── MENÚ PRINCIPAL ───────────────┐");
        System.out.println("│  1. Crear obra (CREATE)                      │");
        System.out.println("│  2. Consultar obra (READ)                    │");
        System.out.println("│  3. Actualizar obra (UPDATE)                 │");
        System.out.println("│  4. Eliminar obra (DELETE)                   │");
        System.out.println("│  5. Listar todas las obras                   │");
        System.out.println("│  6. Serializar (Guardar en archivo binario)  │");
        System.out.println("│  7. Deserializar (Cargar archivo binario)    │");
        System.out.println("│  8. Salir                                    │");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void procesarOpcion(int opcion) {
        System.out.println();
        
        switch (opcion) {
            case 1:
                crearObra();
                break;
            case 2:
                consultarObra();
                break;
            case 3:
                actualizarObra();
                break;
            case 4:
                eliminarObra();
                break;
            case 5:
                listarObras();
                break;
            case 6:
                serializarObras();
                break;
            case 7:
                deserializarObras();
                break;
            case 8:
                break;
            default:
                System.out.println("❌ Opción inválida. Intente nuevamente.");
        }
    }
    
    private static void crearObra() {
        System.out.println("══════════════ CREAR NUEVA OBRA ══════════════");
        
        try {
            System.out.print("Código de la obra: ");
            String codigo = scanner.nextLine().trim();
            if (codigo.isEmpty()) {
                System.out.println("❌ El código no puede estar vacío.");
                return;
            }
            
            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("❌ El título no puede estar vacío.");
                return;
            }
            
            System.out.print("Fecha de creación (ej: 1503, siglo XVI): ");
            String fechaCreacion = scanner.nextLine().trim();
            
            System.out.print("Dimensiones (ej: 77x53 cm): ");
            String dimensiones = scanner.nextLine().trim();
            
            System.out.print("Tipo (1=Pintura, 2=Escultura): ");
            int tipo = Integer.parseInt(scanner.nextLine().trim());
            
            Artista artista = seleccionarArtista();
            if (artista == null) {
                System.out.println("❌ Debe seleccionar un artista.");
                return;
            }
            
            ObraArte obra;
            
            if (tipo == 1) {
                System.out.print("Técnica (ej: Óleo, Acuarela, Tempera): ");
                String tecnica = scanner.nextLine().trim();
                obra = new Pintura(codigo, titulo, fechaCreacion, dimensiones, artista, tecnica);
                
            } else if (tipo == 2) {
                System.out.print("Material (ej: Mármol, Bronce, Madera): ");
                String material = scanner.nextLine().trim();
                obra = new Escultura(codigo, titulo, fechaCreacion, dimensiones, artista, material);
                
            } else {
                System.out.println("❌ Tipo inválido.");
                return;
            }
            
            servicio.create(obra);
            
        } catch (Exception e) {
            System.out.println("❌ Error al crear la obra: " + e.getMessage());
        }
    }
    
    private static Artista seleccionarArtista() {
        System.out.println("\n--- Seleccione un artista ---");
        for (int i = 0; i < artistasPredefinidos.length; i++) {
            System.out.println((i + 1) + ". " + artistasPredefinidos[i]);
        }
        System.out.print("Seleccione (1-" + artistasPredefinidos.length + "): ");
        
        try {
            int seleccion = Integer.parseInt(scanner.nextLine().trim());
            if (seleccion >= 1 && seleccion <= artistasPredefinidos.length) {
                return artistasPredefinidos[seleccion - 1];
            }
        } catch (NumberFormatException e) {
        }
        
        return null;
    }
    
    private static void consultarObra() {
        System.out.println("══════════════ CONSULTAR OBRA ══════════════");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine().trim();
        
        ObraArte obra = servicio.read(codigo);
        
        if (obra == null) {
            System.out.println("❌ No se encontró la obra con código: " + codigo);
        } else {
            mostrarDetallesObra(obra);
        }
    }
    
    private static void actualizarObra() {
        System.out.println("══════════════ ACTUALIZAR OBRA ══════════════");
        System.out.print("Ingrese el código de la obra a actualizar: ");
        String codigo = scanner.nextLine().trim();
        
        ObraArte obraExistente = servicio.read(codigo);
        
        if (obraExistente == null) {
            System.out.println("❌ No se encontró la obra con código: " + codigo);
            return;
        }
        
        System.out.println("\nObra actual:");
        mostrarDetallesObra(obraExistente);
        
        try {
            System.out.println("\n--- Ingrese los nuevos datos ---");
            
            System.out.print("Nuevo título: ");
            String titulo = scanner.nextLine().trim();
            
            System.out.print("Nueva fecha de creación: ");
            String fechaCreacion = scanner.nextLine().trim();
            
            System.out.print("Nuevas dimensiones: ");
            String dimensiones = scanner.nextLine().trim();
            
            Artista artista = seleccionarArtista();
            if (artista == null) {
                System.out.println("❌ Debe seleccionar un artista.");
                return;
            }
            
            ObraArte obraActualizada;
            
            if (obraExistente instanceof Pintura) {
                System.out.print("Nueva técnica: ");
                String tecnica = scanner.nextLine().trim();
                obraActualizada = new Pintura(codigo, titulo, fechaCreacion, 
                                            dimensiones, artista, tecnica);
            } else {
                System.out.print("Nuevo material: ");
                String material = scanner.nextLine().trim();
                obraActualizada = new Escultura(codigo, titulo, fechaCreacion, 
                                              dimensiones, artista, material);
            }
            
            servicio.update(codigo, obraActualizada);
            
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar: " + e.getMessage());
        }
    }
    
    private static void eliminarObra() {
        System.out.println("══════════════ ELIMINAR OBRA ══════════════");
        System.out.print("Ingrese el código de la obra a eliminar: ");
        String codigo = scanner.nextLine().trim();
        
        ObraArte obra = servicio.read(codigo);
        
        if (obra == null) {
            System.out.println("❌ No se encontró la obra con código: " + codigo);
            return;
        }
        
        System.out.println("\nObra a eliminar:");
        mostrarDetallesObra(obra);
        
        System.out.print("\n¿Está seguro de eliminar esta obra? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            servicio.delete(codigo);
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }
    
    private static void listarObras() {
        System.out.println("══════════════ LISTADO DE OBRAS ══════════════");
        
        ObraArte[] obrasValidas = servicio.getObrasValidas();
        
        if (obrasValidas.length == 0) {
            System.out.println("No hay obras en la colección.");
            return;
        }
        
        System.out.println("Total de obras: " + obrasValidas.length + "\n");
        
        int pinturas = 0, esculturas = 0;
        
        for (int i = 0; i < obrasValidas.length; i++) {
            System.out.println("─────────────────────────────────────");
            System.out.println("Obra #" + (i + 1));
            mostrarDetallesObra(obrasValidas[i]);
            
            if (obrasValidas[i] instanceof Pintura) {
                pinturas++;
            } else if (obrasValidas[i] instanceof Escultura) {
                esculturas++;
            }
        }
        
        System.out.println("─────────────────────────────────────");
        System.out.println("Resumen: " + pinturas + " pintura(s), " + 
                         esculturas + " escultura(s)");
    }
    
    private static void serializarObras() {
        System.out.println("══════════════ SERIALIZAR OBRAS ══════════════");
        
        int totalObras = servicio.count();
        
        if (totalObras == 0) {
            System.out.println("⚠️  No hay obras para serializar.");
            System.out.print("¿Desea crear el archivo vacío? (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                System.out.println("Operación cancelada.");
                return;
            }
        } else {
            System.out.println("Obras a serializar: " + totalObras);
        }
        
        System.out.print("\n¿Confirma la serialización? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            if (servicio.serializar()) {
                System.out.println("\n┌────────────────────────────────────────┐");
                System.out.println("│ 💾 ARCHIVO BINARIO CREADO              │");
                System.out.println("├────────────────────────────────────────┤");
                System.out.println("│ El archivo contiene:                   │");
                System.out.println("│ • " + totalObras + " obra(s) serializada(s)");
                System.out.println("│ • Todos los objetos relacionados       │");
                System.out.println("│ • Formato binario (.dat)               │");
                System.out.println("└────────────────────────────────────────┘");
                
                mostrarEvidenciaArchivo();
            }
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }
    
    private static void deserializarObras() {
        System.out.println("══════════════ DESERIALIZAR OBRAS ══════════════");
        
        if (!servicio.existeArchivoBinario()) {
            System.out.println("❌ No existe el archivo binario.");
            System.out.println("   Primero debe usar la opción 6 para serializar.");
            return;
        }
        
        int obrasActuales = servicio.count();
        System.out.println("Obras actuales en memoria: " + obrasActuales);
        
        if (obrasActuales > 0) {
            System.out.println("\n⚠️  ADVERTENCIA: Esto reemplazará las obras actuales.");
            System.out.print("¿Desea continuar? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();
            
            if (!confirmacion.equals("S")) {
                System.out.println("❌ Operación cancelada.");
                return;
            }
        }
        
        if (servicio.deserializar()) {
            int obrasNuevas = servicio.count();
            
            System.out.println("\n┌────────────────────────────────────────┐");
            System.out.println("│ 📂 OBRAS CARGADAS DESDE ARCHIVO        │");
            System.out.println("├────────────────────────────────────────┤");
            System.out.println("│ Obras antes:  " + obrasActuales);
            System.out.println("│ Obras ahora:  " + obrasNuevas);
            System.out.println("│ Diferencia:   " + (obrasNuevas - obrasActuales));
            System.out.println("└────────────────────────────────────────┘");
            
            if (obrasNuevas > 0) {
                System.out.print("\n¿Desea ver las obras cargadas? (S/N): ");
                String ver = scanner.nextLine().trim().toUpperCase();
                if (ver.equals("S")) {
                    listarObras();
                }
            }
        }
    }
    
    private static void mostrarEvidenciaArchivo() {
        java.io.File archivo = new java.io.File(servicio.getArchivoBinario());
        
        if (!archivo.exists()) {
            System.out.println("❌ El archivo no existe.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║       EVIDENCIA DEL ARCHIVO BINARIO           ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        System.out.println("\n📄 Información del archivo:");
        System.out.println("   Nombre: " + archivo.getName());
        System.out.println("   Ruta completa: " + archivo.getAbsolutePath());
        System.out.println("   Tamaño: " + archivo.length() + " bytes (" + 
                         String.format("%.2f", archivo.length() / 1024.0) + " KB)");
        System.out.println("   Puede leer: " + (archivo.canRead() ? "✓ Sí" : "✗ No"));
        System.out.println("   Puede escribir: " + (archivo.canWrite() ? "✓ Sí" : "✗ No"));
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("   Fecha de creación/modificación: " + 
                         sdf.format(new java.util.Date(archivo.lastModified())));
        
        System.out.println("\n📊 Contenido serializado:");
        System.out.println("   Total de obras: " + servicio.count());
        System.out.println("   Capacidad del arreglo: " + servicio.listAll().length);
        System.out.println("   Espacios ocupados: " + servicio.count());
        System.out.println("   Espacios libres: " + (servicio.listAll().length - servicio.count()));
        
        ObraArte[] obrasValidas = servicio.getObrasValidas();
        int pinturas = 0, esculturas = 0;
        for (ObraArte obra : obrasValidas) {
            if (obra instanceof Pintura) pinturas++;
            else if (obra instanceof Escultura) esculturas++;
        }
        
        System.out.println("\n🎨 Distribución por tipo:");
        System.out.println("   Pinturas: " + pinturas);
        System.out.println("   Esculturas: " + esculturas);
        
        System.out.println("\n💡 Nota técnica:");
        System.out.println("   • El archivo .dat es binario (no legible en texto)");
        System.out.println("   • Contiene objetos Java serializados");
        System.out.println("   • Solo puede leerse mediante deserialización");
        System.out.println("   • Incluye toda la jerarquía de objetos");
        
        System.out.println("\n🔍 Vista hexadecimal (primeros 50 bytes):");
        mostrarBytesArchivo(archivo, 50);
    }
    
    private static void mostrarBytesArchivo(java.io.File archivo, int cantidad) {
        try (java.io.FileInputStream fis = new java.io.FileInputStream(archivo)) {
            byte[] buffer = new byte[cantidad];
            int bytesLeidos = fis.read(buffer);
            
            System.out.print("   ");
            for (int i = 0; i < bytesLeidos; i++) {
                System.out.printf("%02X ", buffer[i]);
                if ((i + 1) % 16 == 0) {
                    System.out.print("\n   ");
                }
            }
            System.out.println("\n");
            
        } catch (java.io.IOException e) {
            System.out.println("   No se pudo leer el archivo.");
        }
    }
    
    private static void mostrarDetallesObra(ObraArte obra) {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│ Código: " + obra.getCodigo());
        System.out.println("│ Título: " + obra.getTitulo());
        System.out.println("│ Tipo: " + obra.getTipoObra());
        System.out.println("│ Fecha de creación: " + obra.getFechaCreacion());
        System.out.println("│ Dimensiones: " + obra.getDimensiones());
        
        if (obra.getArtista() != null) {
            System.out.println("│ Artista: " + obra.getArtista().getNombre());
            System.out.println("│ Nacionalidad: " + obra.getArtista().getNacionalidad());
        }
        
        if (obra instanceof Pintura) {
            Pintura p = (Pintura) obra;
            System.out.println("│ Técnica: " + p.getTecnica());
        } else if (obra instanceof Escultura) {
            Escultura e = (Escultura) obra;
            System.out.println("│ Material: " + e.getMaterial());
        }
        
        System.out.println("└────────────────────────────────────────────┘");
    }
}