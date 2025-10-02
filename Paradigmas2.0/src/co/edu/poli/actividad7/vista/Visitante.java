
package co.edu.poli.actividad7.vista;

import co.edu.poli.actividad7.model.*;
import co.edu.poli.actividad7.servicios.*;
import java.util.Scanner;
import java.io.*;

/**
 * Clase principal que contiene el método main para probar el sistema.
 * Proporciona un menú interactivo para las operaciones CRUD y manejo de archivos.
 * 
 * @author Sistema de Gestión de Arte
 * @version 1.0
 */
public class Visitante {
    
    private static Scanner scanner = new Scanner(System.in);
    private static ImplementacionOperacionCRUD servicio = new ImplementacionOperacionCRUD();
    private static final String ARCHIVO_DATOS = "obras_arte.txt";
    
    /**
     * Método principal que ejecuta el sistema
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE OBRAS DE ARTE                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        
        System.out.println("\n¡Gracias por usar el sistema! Hasta pronto.");
        scanner.close();
    }
    
    /**
     * Muestra el menú principal del sistema
     */
    private static void mostrarMenu() {
        System.out.println("\n┌────────────────── MENÚ PRINCIPAL ──────────────────┐");
        System.out.println("│  1. Crear nueva obra (CREATE)                      │");
        System.out.println("│  2. Consultar obra por ID (READ)                   │");
        System.out.println("│  3. Actualizar obra (UPDATE)                       │");
        System.out.println("│  4. Eliminar obra (DELETE)                         │");
        System.out.println("│  5. Listar todas las obras                         │");
        System.out.println("│  6. Guardar obras en archivo                       │");
        System.out.println("│  7. Cargar obras desde archivo                     │");
        System.out.println("│  8. Estadísticas del sistema                       │");
        System.out.println("│  9. Crear datos de prueba                          │"); // NUEVA OPCIÓN
        System.out.println("│  0. Salir                                          │");
        System.out.println("└────────────────────────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Lee la opción seleccionada por el usuario
     * @return Opción válida seleccionada
     */
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Procesa la opción seleccionada del menú
     * @param opcion Opción a procesar
     */
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
                listarTodasObras();
                break;
            case 6:
                guardarEnArchivo();
                break;
            case 7:
                cargarDesdeArchivo();
                break;
            case 8:
                mostrarEstadisticas();
                break;
            case 9: // NUEVO CASE
                crearDatosPrueba();
                break;
            case 0:
                break;
            default:
                System.out.println("❌ Opción inválida. Intente nuevamente.");
        }
    }
    
    /**
     * Crea una nueva obra solicitando solo 5 datos primitivos
     */
    private static void crearObra() {
        System.out.println("═══════════════ CREAR NUEVA OBRA ═══════════════");
        System.out.println("Ingrese únicamente 5 datos básicos:\n");
        
        try {
            // 1. ID (String)
            System.out.print("1. ID de la obra: ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("❌ Error: El ID no puede estar vacío.");
                return;
            }
            
            // Verificar si ya existe
            if (servicio.read(id) != null) {
                System.out.println("❌ Error: Ya existe una obra con ese ID.");
                return;
            }
            
            // 2. Nombre (String)
            System.out.print("2. Nombre de la obra: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("❌ Error: El nombre no puede estar vacío.");
                return;
            }
            
            // 3. Año de creación (int)
            System.out.print("3. Año de creación: ");
            int anioCreacion = Integer.parseInt(scanner.nextLine().trim());
            if (anioCreacion < 1000 || anioCreacion > 2025) {
                System.out.println("❌ Error: Año inválido.");
                return;
            }
            
            // 4. Tipo (String: "pintura" o "escultura")
            System.out.print("4. Tipo (pintura/escultura): ");
            String tipo = scanner.nextLine().trim().toLowerCase();
            if (!tipo.equals("pintura") && !tipo.equals("escultura")) {
                System.out.println("❌ Error: Tipo inválido. Use 'pintura' o 'escultura'.");
                return;
            }
            
            // 5. País (String)
            System.out.print("5. País donde se encuentra: ");
            String pais = scanner.nextLine().trim();
            if (pais.isEmpty()) {
                System.out.println("❌ Error: El país no puede estar vacío.");
                return;
            }
            
            // Crear objetos con valores por defecto para los campos no solicitados
            Galeria lugar = new Galeria(pais, "Ciudad Principal", "Dirección Principal", "alta");
            Horario horario = new Horario("Lunes a Viernes: 9:00 AM - 6:00 PM");
            
            ObraArte obra;
            
            if (tipo.equals("pintura")) {
                Autor autor = new Autor("1900-01-01", "Autor Desconocido", "AUT" + id, 20);
                Pintura pintura = new Pintura(nombre, autor, "Mediano", "Sin especificar", 
                                            "SER-" + id, 1.0);
                obra = new PinturaObra(nombre, lugar, anioCreacion, id, pintura);
            } else {
                Autor autor = new Autor("1900-01-01", "Escultor Desconocido", "AUT" + id, 20);
                Escultura escultura = new Escultura(nombre, "Mediano", "Bronce", autor, 
                                                   "SER-" + id, "Sin referencia", 1.0);
                obra = new EsculturaObra(nombre, lugar, anioCreacion, id, escultura);
            }
            
            obra.getHorario()[0] = horario;
            
            if (servicio.create(obra)) {
                System.out.println("\n✅ Obra creada exitosamente!");
                System.out.println("   ID: " + id);
                System.out.println("   Nombre: " + nombre);
                System.out.println("   Tipo: " + tipo);
                System.out.println("   Año: " + anioCreacion);
                System.out.println("   Edad: " + obra.calcularEdad() + " años");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("❌ Error al crear la obra: " + e.getMessage());
        }
    }
    
    /**
     * Consulta una obra por su ID
     */
    private static void consultarObra() {
        System.out.println("═══════════════ CONSULTAR OBRA ═══════════════");
        System.out.print("Ingrese el ID de la obra: ");
        String id = scanner.nextLine().trim();
        
        ObraArte obra = servicio.read(id);
        
        if (obra == null) {
            System.out.println("❌ No se encontró ninguna obra con el ID: " + id);
        } else {
            mostrarDetallesObra(obra);
        }
    }
    
    /**
     * Actualiza una obra existente
     */
    private static void actualizarObra() {
        System.out.println("═══════════════ ACTUALIZAR OBRA ═══════════════");
        System.out.print("Ingrese el ID de la obra a actualizar: ");
        String id = scanner.nextLine().trim();
        
        ObraArte obraExistente = servicio.read(id);
        
        if (obraExistente == null) {
            System.out.println("❌ No se encontró ninguna obra con el ID: " + id);
            return;
        }
        
        System.out.println("\nObra actual:");
        mostrarDetallesObra(obraExistente);
        
        System.out.println("\n--- Ingrese los nuevos datos (5 campos) ---");
        
        try {
            // Nuevos datos
            System.out.print("1. Nuevo nombre: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("2. Nuevo año de creación: ");
            int anioCreacion = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("3. Nuevo tipo (pintura/escultura): ");
            String tipo = scanner.nextLine().trim().toLowerCase();
            
            System.out.print("4. Nuevo país: ");
            String pais = scanner.nextLine().trim();
            
            System.out.print("5. Nueva ciudad: ");
            String ciudad = scanner.nextLine().trim();
            
            // Crear la obra actualizada
            Galeria nuevoLugar = new Galeria(pais, ciudad, "Dirección Actualizada", "alta");
            Horario horario = new Horario("Horario actualizado");
            
            ObraArte obraActualizada;
            
            if (tipo.equals("pintura")) {
                Autor autor = new Autor("1900-01-01", "Autor Actualizado", "AUT" + id, 20);
                Pintura pintura = new Pintura(nombre, autor, "Mediano", "Actualizado", 
                                            "SER-" + id, 1.0);
                obraActualizada = new PinturaObra(nombre, nuevoLugar, anioCreacion, id, pintura);
            } else {
                Autor autor = new Autor("1900-01-01", "Escultor Actualizado", "AUT" + id, 20);
                Escultura escultura = new Escultura(nombre, "Mediano", "Mármol", autor, 
                                                   "SER-" + id, "Actualizado", 1.0);
                obraActualizada = new EsculturaObra(nombre, nuevoLugar, anioCreacion, id, escultura);
            }
            
            obraActualizada.getHorario()[0] = horario;
            
            if (servicio.update(id, obraActualizada)) {
                System.out.println("\n✅ Obra actualizada exitosamente!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar: " + e.getMessage());
        }
    }
    
    /**
     * Elimina una obra por su ID
     */
    private static void eliminarObra() {
        System.out.println("═══════════════ ELIMINAR OBRA ═══════════════");
        System.out.print("Ingrese el ID de la obra a eliminar: ");
        String id = scanner.nextLine().trim();
        
        ObraArte obra = servicio.read(id);
        
        if (obra == null) {
            System.out.println("❌ No se encontró ninguna obra con el ID: " + id);
            return;
        }
        
        System.out.println("\nObra a eliminar:");
        mostrarDetallesObra(obra);
        
        System.out.print("\n¿Está seguro de eliminar esta obra? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            if (servicio.delete(id)) {
                System.out.println("✅ Obra eliminada exitosamente.");
            }
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }
    
    /**
     * Lista todas las obras del sistema
     */
    private static void listarTodasObras() {
        System.out.println("═══════════════ LISTADO DE OBRAS ═══════════════");
        
        ObraArte[] obrasValidas = servicio.listValidObras();
        
        if (obrasValidas.length == 0) {
            System.out.println("No hay obras registradas en el sistema.");
            return;
        }
        
        System.out.println("Total de obras: " + obrasValidas.length + "\n");
        
        for (int i = 0; i < obrasValidas.length; i++) {
            System.out.println("─────────────────────────────────────────");
            System.out.println("Obra #" + (i + 1));
            mostrarDetallesObra(obrasValidas[i]);
        }
        System.out.println("─────────────────────────────────────────");
    }
    
    /**
     * Guarda todas las obras en un archivo de texto
     */
    private static void guardarEnArchivo() {
        System.out.println("═══════════════ GUARDAR EN ARCHIVO ═══════════════");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_DATOS))) {
            ObraArte[] obrasValidas = servicio.listValidObras();
            
            writer.println("OBRAS DE ARTE - SISTEMA DE GESTIÓN");
            writer.println("Fecha de guardado: " + java.time.LocalDateTime.now());
            writer.println("Total de obras: " + obrasValidas.length);
            writer.println("=====================================\n");
            
            for (ObraArte obra : obrasValidas) {
                writer.println("ID: " + obra.getId());
                writer.println("Nombre: " + obra.getNombre());
                writer.println("Año de creación: " + obra.getAnioCreacion());
                writer.println("Edad: " + obra.calcularEdad() + " años");
                
                if (obra instanceof PinturaObra) {
                    writer.println("Tipo: Pintura");
                    if (obra.getPintura() != null) {
                        writer.println("Serial: " + obra.getPintura().getSerial());
                    }
                } else if (obra instanceof EsculturaObra) {
                    writer.println("Tipo: Escultura");
                    if (obra.getEscultura() != null) {
                        writer.println("Material: " + obra.getEscultura().getMaterial());
                    }
                }
                
                if (obra.getLugar() != null) {
                    writer.println("País: " + obra.getLugar().getPais());
                    writer.println("Ciudad: " + obra.getLugar().getCiudad());
                }
                
                writer.println("-------------------------------------\n");
            }
            
            System.out.println("✅ Datos guardados exitosamente en: " + ARCHIVO_DATOS);
            System.out.println("   Total de obras guardadas: " + obrasValidas.length);
            
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Carga obras desde un archivo de texto
     */
    private static void cargarDesdeArchivo() {
        System.out.println("═══════════════ CARGAR DESDE ARCHIVO ═══════════════");
        
        File archivo = new File(ARCHIVO_DATOS);
        
        if (!archivo.exists()) {
            System.out.println("❌ El archivo '" + ARCHIVO_DATOS + "' no existe.");
            System.out.println("   Primero debe guardar datos usando la opción 6.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_DATOS))) {
            String linea;
            int lineasLeidas = 0;
            
            System.out.println("Contenido del archivo:\n");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                lineasLeidas++;
            }
            
            System.out.println("\n✅ Archivo leído exitosamente.");
            System.out.println("   Líneas leídas: " + lineasLeidas);
            System.out.println("\nNota: La carga automática de objetos requiere un formato");
            System.out.println("      estructurado. Actualmente se muestra el contenido.");
            
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Muestra estadísticas del sistema
     */
    private static void mostrarEstadisticas() {
        System.out.println("═══════════════ ESTADÍSTICAS DEL SISTEMA ═══════════════");
        
        int totalObras = servicio.count();
        int capacidadTotal = servicio.listAll().length;
        ObraArte[] obrasValidas = servicio.listValidObras();
        
        int pinturas = 0;
        int esculturas = 0;
        
        for (ObraArte obra : obrasValidas) {
            if (obra instanceof PinturaObra) {
                pinturas++;
            } else if (obra instanceof EsculturaObra) {
                esculturas++;
            }
        }
        
        System.out.println("Total de obras registradas: " + totalObras);
        System.out.println("  - Pinturas: " + pinturas);
        System.out.println("  - Esculturas: " + esculturas);
        System.out.println("\nCapacidad del arreglo: " + capacidadTotal);
        System.out.println("Espacios disponibles: " + (capacidadTotal - totalObras));
        System.out.println("Porcentaje de ocupación: " + 
                         String.format("%.2f", (totalObras * 100.0 / capacidadTotal)) + "%");
    }
    
    /**
     * Muestra los detalles de una obra
     * @param obra Obra a mostrar
     */
    private static void mostrarDetallesObra(ObraArte obra) {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│ ID: " + obra.getId());
        System.out.println("│ Nombre: " + obra.getNombre());
        System.out.println("│ Año de creación: " + obra.getAnioCreacion());
        System.out.println("│ Edad: " + obra.calcularEdad() + " años");
        
        if (obra instanceof PinturaObra) {
            System.out.println("│ Tipo: PINTURA");
            if (obra.getPintura() != null) {
                Pintura p = obra.getPintura();
                System.out.println("│   - Tamaño: " + p.getTamanio());
                System.out.println("│   - Serial: " + p.getSerial());
                if (p.getAutor() != null) {
                    System.out.println("│   - Autor: " + p.getAutor().getNombre());
                }
            }
        } else if (obra instanceof EsculturaObra) {
            System.out.println("│ Tipo: ESCULTURA");
            if (obra.getEscultura() != null) {
                Escultura e = obra.getEscultura();
                System.out.println("│   - Material: " + e.getMaterial());
                System.out.println("│   - Tamaño: " + e.getTamanio());
                if (e.getAutor() != null) {
                    System.out.println("│   - Autor: " + e.getAutor().getNombre());
                }
            }
        }
        
        if (obra.getLugar() != null) {
            Lugar lugar = obra.getLugar();
            System.out.println("│ Ubicación:");
            System.out.println("│   - País: " + lugar.getPais());
            System.out.println("│   - Ciudad: " + lugar.getCiudad());
            System.out.println("│   - Dirección: " + lugar.getDireccion());
        }
        
        System.out.println("└─────────────────────────────────────┘");
    }
    
    /**
     * Crea datos de prueba para el sistema - AHORA SE USA
     */
    private static void crearDatosPrueba() {
        System.out.println("═══════════════ CREANDO DATOS DE PRUEBA ═══════════════");
        
        // Crear países
        Pais colombia = new Pais("CO", "Colombia");
        Pais francia = new Pais("FR", "Francia");
        Pais españa = new Pais("ES", "España");
        
        // Crear autores
        Autor davinci = new Autor("1452-04-15", "Leonardo da Vinci", "LEO001", 20);
        davinci.getPais()[0] = francia;
        
        Autor picasso = new Autor("1881-10-25", "Pablo Picasso", "PAB001", 15);
        picasso.getPais()[0] = españa;
        
        Autor botero = new Autor("1932-04-19", "Fernando Botero", "FER001", 18);
        botero.getPais()[0] = colombia;
        
        // Crear lugares
        Galeria louvre = new Galeria("Francia", "París", "Rue de Rivoli", "alta");
        Galeria museoPrado = new Galeria("España", "Madrid", "Calle de Ruiz de Alarcón", "alta");
        Callejero plazaBotero = new Callejero("Colombia", "Medellín", "Plaza Botero", "centro");
        
        // Crear horarios
        Horario horarioMuseo = new Horario("Lunes a Viernes: 9:00 AM - 6:00 PM");
        Horario horarioPlaza = new Horario("24 horas, todos los días");
        
        // Crear pinturas
        Pintura monalisa = new Pintura("La Mona Lisa", davinci, "77cm x 53cm", 
                                     "Retrato de Lisa Gherardini", "ML001", 0.41);
        
        Pintura guernica = new Pintura("El Guernica", picasso, "349cm x 777cm", 
                                     "Horror de la guerra", "GU001", 2.71);
        
        // Crear esculturas
        Escultura mujerSentada = new Escultura("Mujer Sentada", "Grande", "Bronce", 
                                             botero, "MS001", "Escultura urbana", 15.2);
        
        // Crear obras de arte
        PinturaObra obraMonalisa = new PinturaObra("La Mona Lisa", louvre, 1503, "OBRA001", monalisa);
        obraMonalisa.getHorario()[0] = horarioMuseo;
        
        PinturaObra obraGuernica = new PinturaObra("El Guernica", museoPrado, 1937, "OBRA002", guernica);
        obraGuernica.getHorario()[0] = horarioMuseo;
        
        EsculturaObra obraMujerSentada = new EsculturaObra("Mujer Sentada", plazaBotero, 
                                                          1992, "OBRA003", mujerSentada);
        obraMujerSentada.getHorario()[0] = horarioPlaza;
        
        // Crear las obras
        servicio.create(obraMonalisa);
        servicio.create(obraGuernica);
        servicio.create(obraMujerSentada);
        
        System.out.println("✅ Datos de prueba creados exitosamente!");
        System.out.println("   Total de obras en el sistema: " + servicio.count());
    }
}