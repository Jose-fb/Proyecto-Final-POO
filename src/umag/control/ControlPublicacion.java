package umag.control;
import java.util.List;
import umag.modelo.entidad.Habitacion;
import umag.modelo.entidad.Publicacion;
import umag.modelo.servicio.ServicioPublicacion;

public class ControlPublicacion {
    ServicioPublicacion servicio = new ServicioPublicacion();
    public boolean guardar(String id, String titulo, String descripcion, double precio, Habitacion habitacion, String propietarioId) {
        boolean res = servicio.guardar(id, titulo, descripcion, precio, habitacion, propietarioId);
        return res;
    }
    
    public boolean borrar(String id) {
        boolean res = servicio.borrar(id);
        return res;
    }
    
    public List<Publicacion> listar() {
        return servicio.listar();
    }
    
    public List<Publicacion> listarActivas() {
        return servicio.listarActivas();
    }
    
    public List<Publicacion> filtrar(double precioMax, String ubicacion) {
        return servicio.filtrar(precioMax, ubicacion);
    }
    
    public Publicacion buscarPorId(String id) {
        return servicio.buscarPorId(id);
    }
}