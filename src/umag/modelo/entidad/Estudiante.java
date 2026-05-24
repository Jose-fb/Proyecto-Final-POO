package umag.modelo.entidad;
import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario {
    
    private String universidad;
    private List<Solicitud> solicitudes;
    
    public Estudiante() {
        super();
        this.solicitudes = new ArrayList<>();
    }
    
    public Estudiante(String id, String nombre, String correo, String password, String telefono, String universidad) {
        super(id, nombre, correo, password, telefono);
        this.universidad        = universidad;
        this.solicitudes        = new ArrayList<>();
    }
    
    public List<Habitacion> buscarHabitacion(List<Publicacion> publicaciones) {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (p.isActiva() && p.getHabitacion()!= null && p.getHabitacion().isDisponible()) {
                disponibles.add(p.getHabitacion());
            }
        }
        return disponibles;
    }
    public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones, double precioMax, String ubicacion) {
        List<Publicacion> resultado = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (p.isActiva()) {
                if (precioMax > 0 && p.getPrecio() > precioMax) {
                    
                } else if (ubicacion != null && !ubicacion.isEmpty() && (p.getHabitacion() == null || !p.getHabitacion().getDireccion().contains(ubicacion))) {      
            } else {    
                resultado.add(p);
            }
            }
        }
        return resultado;
    }
    
    public Solicitud solicitarAlquiler(Publicacion publicacion, String mensaje) {
        if (publicacion == null || !publicacion.isActiva()) {
            System.out.println("La publicación no está disponible.");
            return null;
        }
        String idSolicitud = "SOL-1";
        Solicitud s = new Solicitud(idSolicitud, this, publicacion, mensaje);
        solicitudes.add(s);
        System.out.println("Solicitud enviada con ID: " + idSolicitud);
        return s;
    }
    
    @Override
    public String toString() {
        return "Estudiante{" + "universidad=" + universidad + ", solicitudes=" + solicitudes + '}';
    }
    
    public String getTipoUsuario() { return "Estudiante"; }
    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }
    
    public List<Solicitud> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}