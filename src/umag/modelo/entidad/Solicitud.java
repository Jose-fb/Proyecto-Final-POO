package umag.modelo.entidad;
import java.io.Serializable;
import java.util.Date;

public class Solicitud implements Serializable {
    
    public static final String ESTADO_PENDIENTE  = "PENDIENTE";
    private static final String ESTADO_ACEPTADA   = "ACEPTADA";
    private static final String ESTADO_RECHAZADA  = "RECHAZADA";
    private static final String ESTADO_CANCELADA  = "CANCELADA";
    
    private String    id;
    private String    estado;
    private Date      fechaSolicitud;
    private String    mensaje;           
    private String    respuestaPropietario; 
    private Date      fechaRespuesta;
    private Estudiante estudiante;
    private Publicacion publicacion;
    
    public Solicitud() {
        this.estado         = ESTADO_PENDIENTE;
        this.fechaSolicitud = new Date();
    }
    
    public Solicitud(String id, Estudiante estudiante, Publicacion publicacion, String mensaje) {
        this.id             = id;
        this.estudiante     = estudiante;
        this.publicacion    = publicacion;
        this.mensaje        = mensaje;
        this.estado         = ESTADO_PENDIENTE;
        this.fechaSolicitud = new Date();
    }
    
    public void aceptar() {
        this.setEstado(getESTADO_ACEPTADA());
        this.setFechaRespuesta(new Date());
        System.out.println("Solicitud " + getId() + " ACEPTADA.");
    }
    
    public void rechazar(String motivo) {
        this.setEstado(getESTADO_RECHAZADA());
        this.setRespuestaPropietario(motivo);
        this.setFechaRespuesta(new Date());
        System.out.println("Solicitud " + getId() + " RECHAZADA. Motivo: " + motivo);
    }
    
    public void rechazar() {
        rechazar("Sin motivo especificado.");
    }
    
    public void cancelar() {
        if (getESTADO_PENDIENTE().equals(this.getEstado())) {
            this.setEstado(getESTADO_CANCELADA());
            System.out.println("Solicitud " + getId() + " CANCELADA por el estudiante.");
        } else {
            System.out.println("No se puede cancelar. Estado actual: " + getEstado());
        }
    }

    public void cambiarEstado(String nuevoEstado) {
        this.setEstado(nuevoEstado);
        System.out.println("Estado de solicitud " + getId() + " cambiado a: " + nuevoEstado);
    }
    
    @Override
    public String toString() {
        return "Solicitud{" + "id=" + getId() + ", estado=" + getEstado() + ", fechaSolicitud=" + getFechaSolicitud() + ", mensaje=" + getMensaje() + ", respuestaPropietario=" + getRespuestaPropietario() + ", fechaRespuesta=" + getFechaRespuesta() + ", estudiante=" + getEstudiante() + ", publicacion=" + getPublicacion() + '}';
    }

    /**
     * @return the ESTADO_PENDIENTE
     */
    public static String getESTADO_PENDIENTE() {
        return ESTADO_PENDIENTE;
    }

    /**
     * @return the ESTADO_ACEPTADA
     */
    public static String getESTADO_ACEPTADA() {
        return ESTADO_ACEPTADA;
    }

    /**
     * @return the ESTADO_RECHAZADA
     */
    public static String getESTADO_RECHAZADA() {
        return ESTADO_RECHAZADA;
    }

    /**
     * @return the ESTADO_CANCELADA
     */
    public static String getESTADO_CANCELADA() {
        return ESTADO_CANCELADA;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaSolicitud
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud the fechaSolicitud to set
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the respuestaPropietario
     */
    public String getRespuestaPropietario() {
        return respuestaPropietario;
    }

    /**
     * @param respuestaPropietario the respuestaPropietario to set
     */
    public void setRespuestaPropietario(String respuestaPropietario) {
        this.respuestaPropietario = respuestaPropietario;
    }

    /**
     * @return the fechaRespuesta
     */
    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    /**
     * @param fechaRespuesta the fechaRespuesta to set
     */
    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the publicacion
     */
    public Publicacion getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion the publicacion to set
     */
    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
