package umag.control;
import java.util.List;
import umag.modelo.entidad.Habitacion;
import umag.modelo.servicio.ServicioHabitacion;

public class ControlHabitacion {
    ServicioHabitacion servicio = new ServicioHabitacion();
    public boolean guardar(String id, String direccion, double precio, String tipo) {
        boolean res = servicio.guardar(id, direccion, precio, tipo);
        return res;
    }
    
    public boolean borrar(String id) {
        boolean res = servicio.borrar(id);
        return res;
    }
    
    public List<Habitacion> listar() {
        return servicio.listar();
    }
    
    public List<Habitacion> listarDisponibles() {
        return servicio.listarDisponibles();
    }
    
    public Habitacion buscarPorId(String id) {
        return servicio.buscarPorId(id);
    }
}
