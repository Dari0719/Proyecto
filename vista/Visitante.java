package co.edu.poli.actividad5.vista;

import co.edu.poli.actividad5.model.*;
import co.edu.poli.actividad5.servicios.*;

/**
 * Clase principal que contiene el método main para probar el sistema.
 * Demuestra todas las operaciones CRUD disponibles.
 * 
 * @author Sistema de Gestión de Arte
 * @version 1.0
 */
public class Visitante {
    
    /**
     * Método principal que ejecuta las pruebas del sistema
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE OBRAS DE ARTE ===\n");
        
        // Crear instancia del servicio CRUD
        ImplementacionOperacionCRUD servicio = new ImplementacionOperacionCRUD();
        
        // Crear datos de prueba
        crearDatosPrueba(servicio);
        
        // Probar operación READ
        probarOperacionRead(servicio);
        
        // Probar operación UPDATE
        probarOperacionUpdate(servicio);
        
        // Probar operación DELETE
        probarOperacionDelete(servicio);
        
        // Mostrar estadísticas finales
        mostrarEstadisticasFinales(servicio);
    }
    
    /**
     * Crea datos de prueba para el sistema
     * @param servicio Servicio CRUD a utilizar
     */
    private static void crearDatosPrueba(ImplementacionOperacionCRUD servicio) {
        System.out.println("=== PROBANDO OPERACIÓN CREATE ===");
        
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
        
        // Intentar crear las obras
        servicio.create(obraMonalisa);
        servicio.create(obraGuernica);
        servicio.create(obraMujerSentada);
        
        // Intentar crear una obra con ID duplicado (debe fallar)
        PinturaObra obraDuplicada = new PinturaObra("Obra Duplicada", louvre, 2000, "OBRA001", monalisa);
        servicio.create(obraDuplicada);
        
        // Intentar crear una obra sin ID (debe fallar)
        PinturaObra obraSinId = new PinturaObra("Sin ID", louvre, 2000, null, monalisa);
        servicio.create(obraSinId);
        
        System.out.println("\nObras creadas exitosamente: " + servicio.count());
        System.out.println("----------------------------------------\n");
    }
    
    /**
     * Prueba las operaciones de lectura
     * @param servicio Servicio CRUD a utilizar
     */
    private static void probarOperacionRead(ImplementacionOperacionCRUD servicio) {
        System.out.println("=== PROBANDO OPERACIÓN READ ===");
        
        // Buscar obras existentes
        ObraArte obra1 = servicio.read("OBRA001");
        if (obra1 != null) {
            System.out.println("Obra encontrada: " + obra1);
            System.out.println("Edad de la obra: " + obra1.calcularEdad() + " años");
            System.out.println("Lugar: " + obra1.getLugar().getPais() + ", " + obra1.getLugar().getCiudad());
        }
        
        ObraArte obra2 = servicio.read("OBRA002");
        if (obra2 != null) {
            System.out.println("Obra encontrada: " + obra2);
        }
        
        ObraArte obra3 = servicio.read("OBRA003");
        if (obra3 != null) {
            System.out.println("Obra encontrada: " + obra3);
            if (obra3.getEscultura() != null) {
                System.out.println("Detalles escultura: " + obra3.getEscultura());
            }
        }
        
        // Buscar obra inexistente
        ObraArte obraInexistente = servicio.read("OBRA999");
        if (obraInexistente == null) {
            System.out.println("Búsqueda de OBRA999: No encontrada (comportamiento esperado)");
        }
        
        // Buscar con ID nulo
        servicio.read(null);
        
        System.out.println("----------------------------------------\n");
    }
    
    /**
     * Prueba las operaciones de actualización
     * @param servicio Servicio CRUD a utilizar
     */
    private static void probarOperacionUpdate(ImplementacionOperacionCRUD servicio) {
        System.out.println("=== PROBANDO OPERACIÓN UPDATE ===");
        
        // Crear una nueva obra para actualizar
        Galeria nuevaGaleria = new Galeria("Italia", "Florencia", "Piazzale degli Uffizi", "alta");
        Autor nuevoAutor = new Autor("1475-03-06", "Michelangelo Buonarroti", "MIC001", 13);
        Pintura nuevaPintura = new Pintura("La Gioconda (Actualizada)", nuevoAutor, "80cm x 60cm", 
                                         "Versión actualizada", "ML001-UPD", 0.48);
        
        PinturaObra obraActualizada = new PinturaObra("La Mona Lisa - Versión Actualizada", 
                                                    nuevaGaleria, 1503, "OBRA001", nuevaPintura);
        
        // Actualizar obra existente
        boolean actualizada = servicio.update("OBRA001", obraActualizada);
        if (actualizada) {
            ObraArte obraVerificar = servicio.read("OBRA001");
            System.out.println("Obra después de actualización: " + obraVerificar);
        }
        
        // Intentar actualizar obra inexistente
        servicio.update("OBRA999", obraActualizada);
        
        // Intentar actualizar con ID nulo
        servicio.update(null, obraActualizada);
        
        // Intentar actualizar con obra nula
        servicio.update("OBRA002", null);
        
        System.out.println("----------------------------------------\n");
    }
    
    /**
     * Prueba las operaciones de eliminación
     * @param servicio Servicio CRUD a utilizar
     */
    private static void probarOperacionDelete(ImplementacionOperacionCRUD servicio) {
        System.out.println("=== PROBANDO OPERACIÓN DELETE ===");
        
        System.out.println("Obras antes de eliminar: " + servicio.count());
        
        // Eliminar obra existente
        boolean eliminada = servicio.delete("OBRA002");
        if (eliminada) {
            System.out.println("Obras después de eliminar OBRA002: " + servicio.count());
            
            // Verificar que realmente se eliminó
            ObraArte obraEliminada = servicio.read("OBRA002");
            if (obraEliminada == null) {
                System.out.println("Confirmación: OBRA002 ya no existe en el sistema");
            }
        }
        
        // Intentar eliminar obra inexistente
        servicio.delete("OBRA999");
        
        // Intentar eliminar con ID nulo
        servicio.delete(null);
        
        // Intentar eliminar la misma obra nuevamente
        servicio.delete("OBRA002");
        
        System.out.println("----------------------------------------\n");
    }
    
    /**
     * Muestra estadísticas finales del sistema
     * @param servicio Servicio CRUD a utilizar
     */
    private static void mostrarEstadisticasFinales(ImplementacionOperacionCRUD servicio) {
        System.out.println("=== ESTADÍSTICAS FINALES ===");
        
        System.out.println("Total de obras en el sistema: " + servicio.count());
        System.out.println("Tamaño del arreglo: " + servicio.listAll().length);
        
        System.out.println("\nLista de todas las obras válidas:");
        ObraArte[] obrasValidas = servicio.listValidObras();
        
        if (obrasValidas.length == 0) {
            System.out.println("No hay obras en el sistema.");
        } else {
            for (int i = 0; i < obrasValidas.length; i++) {
                System.out.println((i + 1) + ". " + obrasValidas[i]);
                
                // Mostrar detalles adicionales
                if (obrasValidas[i].getPintura() != null) {
                    System.out.println("   Tipo: Pintura - " + obrasValidas[i].getPintura());
                } else if (obrasValidas[i].getEscultura() != null) {
                    System.out.println("   Tipo: Escultura - " + obrasValidas[i].getEscultura());
                }
                
                if (obrasValidas[i].getLugar() instanceof Galeria) {
                    Galeria galeria = (Galeria) obrasValidas[i].getLugar();
                    System.out.println("   Ubicación: Galería - " + galeria.getPais() + ", " + 
                                     galeria.getCiudad() + " (Autenticidad: " + 
                                     galeria.getAutenticidad() + ")");
                } else if (obrasValidas[i].getLugar() instanceof Callejero) {
                    Callejero callejero = (Callejero) obrasValidas[i].getLugar();
                    System.out.println("   Ubicación: Espacio urbano - " + callejero.getPais() + ", " + 
                                     callejero.getCiudad() + " (Tipo: " + callejero.getUrbano() + ")");
                }
                
                System.out.println("   Edad: " + obrasValidas[i].calcularEdad() + " años");
                System.out.println();
            }
        }
        
        // Probar la expansión automática del arreglo
        System.out.println("=== PROBANDO EXPANSIÓN AUTOMÁTICA ===");
        System.out.println("Creando múltiples obras para probar la expansión...");
        
        for (int i = 4; i <= 10; i++) {
            Autor autorPrueba = new Autor("1900-01-01", "Autor " + i, "AUT" + String.format("%03d", i), 25);
            Galeria galeriaPrueba = new Galeria("País" + i, "Ciudad" + i, "Dirección " + i, "media");
            Pintura pinturaPrueba = new Pintura("Obra " + i, autorPrueba, "50x50cm", 
                                              "Inspiración " + i, "SER" + String.format("%03d", i), 2.5);
            
            PinturaObra obraPrueba = new PinturaObra("Obra de Prueba " + i, galeriaPrueba, 
                                                   2000 + i, "OBRA" + String.format("%03d", i), pinturaPrueba);
            
            servicio.create(obraPrueba);
        }
        
        System.out.println("Total de obras después de las pruebas: " + servicio.count());
        System.out.println("Tamaño final del arreglo: " + servicio.listAll().length);
        
        System.out.println("\n=== FIN DE LAS PRUEBAS ===");
        
        // Demostración de la clase NoReconocido
        System.out.println("\n=== DEMOSTRACIÓN CLASE NoReconocido ===");
        NoReconocido noReconocido = new NoReconocido("Artista callejero sin documentación oficial");
        System.out.println("Servicio para artistas no reconocidos: " + 
                         noReconocido.getArtista_no_reconocido());
    }
}