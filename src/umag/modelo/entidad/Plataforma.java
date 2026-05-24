package umag.modelo.entidad;
import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    
    private static Plataforma instancia;
    private String nombre;
    private String version;
    private List<Publicacion> publicaciones;
    private List<Solicitud>   solicitudes;
    private ArrayList<Propietario> propietarios;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Habitacion> habitaciones;
    
    public Plataforma() {
    
        this.nombre        = "Plataforma de Arrendamiento";
        this.version       = "1.0";
        this.propietarios  = new ArrayList<>();
        this.estudiantes   = new ArrayList<>();
        this.habitaciones  = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.solicitudes   = new ArrayList<>();
    }
    
    public Plataforma(String nombre, String version) {
        this();
        this.nombre  = nombre;
        this.version = version;
    }
    
    public static Plataforma getPlataforma() {
        if (getInstancia() == null) {
            setInstancia(new Plataforma());
        }
        return getInstancia();
    }
   
    public void registrarPropietario(Propietario propietario) {
        if (propietario != null && !existeCorreo(propietario.getCorreo())) {
            getPropietarios().add(propietario);
            System.out.println("Propietario registrado: " + propietario.getNombre());
        } else {
            System.out.println("No se pudo registrar: correo ya existe o datos inválidos.");
        }
    }
    
    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante != null && !existeCorreo(estudiante.getCorreo())) {
            getEstudiantes().add(estudiante);
            System.out.println("Estudiante registrado: " + estudiante.getNombre());
        } else {
            System.out.println("No se pudo registrar: correo ya existe o datos inválidos.");
        }
    }
    
    public Usuario autenticar(String correo, String password) {
        for (Propietario p : getPropietarios()) {
            if (p.login(correo, password)) return p;
        }
        for (Estudiante e : getEstudiantes()) {
            if (e.login(correo, password)) return e;
        }
        System.out.println("Credenciales incorrectas.");
        return null;
    }
    
    public List<Usuario> listarUsuarios() {
        List<Usuario> todos = new ArrayList<>();
        todos.addAll(getPropietarios());
        todos.addAll(getEstudiantes());
        return todos;
    }
    
    public boolean actualizarPropietario(String id, Propietario actualizado) {
        for (int i = 0; i < getPropietarios().size(); i++) {
            if (getPropietarios().get(i).getId().equals(id)) {
                getPropietarios().set(i, actualizado);
                return true;
            }
        }
        return false;
    }
    
    public boolean actualizarEstudiante(String id, Estudiante actualizado) {
        for (int i = 0; i < getEstudiantes().size(); i++) {
            if (getEstudiantes().get(i).getId().equals(id)) {
                getEstudiantes().set(i, actualizado);
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminarPropietario(String id) {
        return getPropietarios().removeIf(p -> p.getId().equals(id));
    }
    
    public boolean eliminarEstudiante(String id) {
        return getEstudiantes().removeIf(e -> e.getId().equals(id));
    }
    
    public List<Publicacion> listarPublicacionesActivas() {
        List<Publicacion> activas = new ArrayList<>();
        for (Publicacion p : getPublicaciones()) {
            if (p.isActiva()) activas.add(p);
        }
        return activas;
    }
    
    public void agregarPublicacion(Publicacion publicacion) {
        if (publicacion != null) {
            getPublicaciones().add(publicacion);
            if (publicacion.getHabitacion() != null && !habitaciones.contains(publicacion.getHabitacion())) {
                getHabitaciones().add(publicacion.getHabitacion());
            }
        }
    }
    
    public void agregarSolicitud(Solicitud solicitud) {
        if (solicitud != null) getSolicitudes().add(solicitud);
    }
    
    public List<Solicitud> listarSolicitudes() { return getSolicitudes(); }

    private boolean existeCorreo(String correo) {
        for (Usuario u : listarUsuarios()) {
            if (u.getCorreo().equalsIgnoreCase(correo)) return true;
        }
        return false;
    }
    
    public void gestionar() {
        System.out.println("=== " + getNombre() + " v" + getVersion() + " ===");
        System.out.println("Propietarios: " + getPropietarios().size());
        System.out.println("Estudiantes:  " + getEstudiantes().size());
        System.out.println("Publicaciones activas: " + listarPublicacionesActivas().size());
        System.out.println("Solicitudes:  " + getSolicitudes().size());
    }
    
    /**
     * @return the instancia
     */
    public static Plataforma getInstancia() {
        return instancia;
    }

    /**
     * @param aInstancia the instancia to set
     */
    public static void setInstancia(Plataforma aInstancia) {
        instancia = aInstancia;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
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

    /**
     * @return the solicitudes
     */
    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    /**
     * @param solicitudes the solicitudes to set
     */
    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    /**
     * @return the propietarios
     */
    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    /**
     * @param propietarios the propietarios to set
     */
    public void setPropietarios(ArrayList<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    /**
     * @return the estudiantes
     */
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * @return the habitaciones
     */
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * @param habitaciones the habitaciones to set
     */
    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
 