package umag.modelo.entidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion implements Serializable {
    
    private String      id;
    private String      descripcion;
    private Date        fechaPublicacion;
    private double      precio;           
    private boolean     activa;
    private String      titulo;
    private List<String> fotos;           
    private String      propietarioId;  
    private Habitacion habitacion;
    
    public Publicacion() {
        this.fechaPublicacion = new Date();
        this.activa           = false;
        this.fotos            = new ArrayList<>();
    }
    
    public Publicacion(String id, String titulo, String descripcion, double precio, Habitacion habitacion, String propietarioId) {
        this.id               = id;
        this.titulo           = titulo;
        this.descripcion      = descripcion;
        this.precio           = precio;
        this.habitacion       = habitacion;
        this.propietarioId    = propietarioId;
        this.fechaPublicacion = new Date();
        this.activa           = false;
        this.fotos            = new ArrayList<>();
    }
    
    public void activar() {
        this.setActiva(true);
        System.out.println("Publicación '" + getTitulo() + "' ahora está ACTIVA.");
    }
    
    public void desactivar() {
        this.setActiva(false);
        System.out.println("Publicación '" + getTitulo() + "' desactivada.");
    }
    
    public void agregarFoto(String rutaFoto) {
        getFotos().add(rutaFoto);
    }
    
    @Override
    public String toString() {
        return "Publicacion{" + "id=" + getId() + ", descripcion=" + getDescripcion() + ", fechaPublicacion=" + getFechaPublicacion() + ", precio=" + getPrecio() + ", activa=" + isActiva() + ", titulo=" + getTitulo() + ", fotos=" + getFotos() + ", propietarioId=" + getPropietarioId() + ", habitacion=" + getHabitacion() + '}';
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the fotos
     */
    public List<String> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the propietarioId
     */
    public String getPropietarioId() {
        return propietarioId;
    }

    /**
     * @param propietarioId the propietarioId to set
     */
    public void setPropietarioId(String propietarioId) {
        this.propietarioId = propietarioId;
    }

    /**
     * @return the habitacion
     */
    public Habitacion getHabitacion() {
        return habitacion;
    }

    /**
     * @param habitacion the habitacion to set
     */
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
}