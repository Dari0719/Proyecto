package co.edu.poli.parcial2.servicios;
import co.edu.poli.parcial2.model.ObraArte;

/**
 * Interfaz que define las operaciones CRUD para obras de arte.
 * 
 * @author Sistema de Gestión de Museo
 * @version 1.0
 */
public interface OperacionCRUD {
    
    boolean create(ObraArte obra);
    ObraArte read(String codigo);
    boolean update(String codigo, ObraArte obra);
    boolean delete(String codigo);
    ObraArte[] listAll();
}