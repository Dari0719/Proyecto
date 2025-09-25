package co.edu.poli.actividad5.servicios;

import co.edu.poli.actividad5.model.ObraArte;

public interface OperacionCRUD {
    
    /**
     * Crea una nueva obra de arte en el sistema
     * @param obra La obra de arte a crear
     * @return true si se creó exitosamente, false en caso contrario
     */
    boolean create(ObraArte obra);
    
    /**
     * Lee/consulta una obra de arte por su ID
     * @param id Identificador único de la obra
     * @return La obra de arte encontrada o null si no existe
     */
    ObraArte read(String id);
    
    /**
     * Actualiza una obra de arte existente
     * @param id Identificador de la obra a actualizar
     * @param obra Nuevos datos de la obra
     * @return true si se actualizó exitosamente, false en caso contrario
     */
    boolean update(String id, ObraArte obra);
    
    /**
     * Elimina una obra de arte del sistema
     * @param id Identificador de la obra a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    boolean delete(String id);
    
    /**
     * Lista todas las obras de arte del sistema
     * @return Arreglo con todas las obras (incluyendo nulls)
     */
    ObraArte[] listAll();
    
    /**
     * Cuenta el número de obras registradas
     * @return Número total de obras no nulas
     */
    int count();
}
