package umag.modelo.entidad;
import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
    
    private String cedula;
    private String direccion;
    private String cuentaBancaria;
    private List<Habitacion> habitaciones;
    private List<Publicacion> publicaciones;
    
    public Propietario() {
        super();
        this.habitaciones  = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
    }
    
    public Propietario(String id, String nombre, String correo, String password, String telefono, String cedula, String direccion) {
        super(id, nombre, correo, password, telefono);
        this.cedula         = cedula;
        this.direccion      = direccion;
        this.habitaciones   = new ArrayList<>();
        this.publicaciones  = new ArrayList<>();
    }
    
    public void agregarHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            getHabitaciones().add(habitacion);
            System.out.println("Habitación registrada: " + habitacion.getDireccion());
        }
    }
    
    public void editarHabitacion(String id, Habitacion nueva) {
        for (int i = 0; i < getHabitaciones().size(); i++) {
            if (getHabitaciones().get(i).getId().equals(id)) {
                getHabitaciones().set(i, nueva);
                System.out.println("Habitación actualizada: " + id);
                return;
            }
        }
        System.out.println("Habitación no encontrada: " + id);
    }
    
    public void eliminarHabitacion(String id) {
        getHabitaciones().removeIf(h -> h.getId().equals(id));
        System.out.println("Habitación eliminada: " + id);
    }
    
    public void publicar(Publicacion publicacion) {
        if (publicacion != null) {
            getPublicaciones().add(publicacion);
            publicacion.activar();
            System.out.println("Publicación creada: " + publicacion.getId());
        }
    }
    
    public void editarPublicacion(String id, Publicacion nueva) {
        for (int i = 0; i < getPublicaciones().size(); i++) {
            if (getPublicaciones().get(i).getId().equals(id)) {
                getPublicaciones().set(i, nueva);
                System.out.println("Publicación editada: " + id);
                return;
            }
        }
        System.out.println("Publicación no encontrada: " + id);
    }
    
    public void eliminarPublicacion(String id) {
        getPublicaciones().removeIf(p -> p.getId().equals(id));
        System.out.println("Publicación eliminada: " + id);
    }
    
    public void responderSolicitud(Solicitud solicitud, boolean aceptar) {
        if (solicitud != null) {
            if (aceptar) {
                solicitud.aceptar();
                if (solicitud.getPublicacion() != null && solicitud.getPublicacion().getHabitacion() != null) {
                    solicitud.getPublicacion().getHabitacion().actualizarDisponibilidad(false);
                }
            } else {
                solicitud.rechazar();
            }
        }
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the cuentaBancaria
     */
    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * @param cuentaBancaria the cuentaBancaria to set
     */
    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    /**
     * @return the habitaciones
     */
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * @param habitaciones the habitaciones to set
     */
    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    /**
     * @return the publicaciones
     */
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    /**
     * @param publicaciones the publicaciones to set
     */
    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
