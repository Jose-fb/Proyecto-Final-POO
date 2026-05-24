package umag.modelo.servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Habitacion;
import umag.modelo.persistencia.DaoHabitacion;

public class ServicioHabitacion {
    
    DaoHabitacion dao = new DaoHabitacion();
    public boolean guardar(String id, String direccion, double precio, String tipo) {
        if (id.isEmpty() || direccion.isEmpty()) {
            System.out.println("Error: id, dirección o ciudad vacíos.");
            return false;
        }
        if (precio <= 0) {
            System.out.println("Error: el precio debe ser mayor a 0.");
            return false;
        } try {
            Habitacion h = new Habitacion(id, direccion, precio, true, tipo);
            dao.guardar(h);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar habitación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean borrar(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: id vacío.");
            return false;
        }
        try {
            dao.borrar(id);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al borrar habitación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    public List<Habitacion> listar() {
        try {
            return dao.listar();
        } catch (IOException ex) {
            System.out.println("Error al listar habitaciones: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public List<Habitacion> listarDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        try {
            for (Habitacion h : dao.listar()) {
                if (h.isDisponible()) {
                    disponibles.add(h);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al listar disponibles: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return disponibles;
    }
    
    public Habitacion buscarPorId(String id) {
        try {
            for (Habitacion h : dao.listar()) {
                if (h.getId().equals(id)) {
                    return h;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al buscar habitación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
}
