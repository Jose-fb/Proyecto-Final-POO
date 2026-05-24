package umag.control;
import java.util.List;
import umag.modelo.entidad.Estudiante;
import umag.modelo.entidad.Publicacion;
import umag.modelo.entidad.Solicitud;
import umag.modelo.servicio.ServicioSolicitud;

public class ControlSolicitud {
    ServicioSolicitud servicio = new ServicioSolicitud();
    public boolean guardar(String id, Estudiante estudiante, Publicacion publicacion, String mensaje) {
        boolean res = servicio.guardar(id, estudiante, publicacion, mensaje);
        return res;
    }
    
    public boolean borrar(String id) {
        boolean res = servicio.borrar(id);
        return res;
    }
    
    public boolean aceptar(String idSolicitud) {
        boolean res = servicio.aceptar(idSolicitud);
        return res;
    }
    
    public boolean rechazar(String idSolicitud, String motivo) {
        boolean res = servicio.rechazar(idSolicitud, motivo);
        return res;
    }
    
    public boolean cancelar(String idSolicitud) {
        boolean res = servicio.cancelar(idSolicitud);
        return res;
    }
    
    public List<Solicitud> listar() {
        return servicio.listar();
    }
    
    public List<Solicitud> listarPorEstado(String estado) {
        return servicio.listarPorEstado(estado);
    }
    
    public List<Solicitud> listarPorPublicacion(String idPublicacion) {
        return servicio.listarPorPublicacion(idPublicacion);
    }
    
    public List<Solicitud> listarPorEstudiante(String idEstudiante) {
        return servicio.listarPorEstudiante(idEstudiante);
    }
    
    public Solicitud buscarPorId(String id) {
        return servicio.buscarPorId(id);
    }
}