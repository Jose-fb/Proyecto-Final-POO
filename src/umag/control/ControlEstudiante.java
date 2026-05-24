package umag.control;
import java.util.List;
import umag.modelo.entidad.Estudiante;
import umag.modelo.entidad.Publicacion;
import umag.modelo.servicio.ServicioEstudiante;

public class ControlEstudiante {
    ServicioEstudiante servicio = new ServicioEstudiante();
    public boolean guardar(String id, String nombre, String correo, String password, String telefono, String universidad) {
        boolean res = servicio.guardar(id, nombre, correo, password, telefono, universidad);
        return res;
    }
    
    public boolean borrar(String id) {
        boolean res = servicio.borrar(id);
        return res;
    }
    
    public List<Estudiante> listar() {
        return servicio.listar();
    }
    
    public Estudiante buscarPorId(String id) {
        return servicio.buscarPorId(id);
    }
    
    public Estudiante login(String correo, String password) {
        return servicio.login(correo, password);
    }
    
    public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones, double precioMax, String ubicacion) {
        return servicio.filtrarPublicaciones(publicaciones, precioMax, ubicacion);
    }
}