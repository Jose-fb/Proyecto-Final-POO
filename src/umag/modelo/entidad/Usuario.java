package umag.modelo.entidad;
import java.io.Serializable;

public abstract class Usuario implements Serializable {
    
    private String id;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private boolean activo;
    
    public Usuario() {
        this.activo = true;
    }
    
    public Usuario(String id, String nombre, String correo, String password, String telefono) {
        this.id        = id;
        this.nombre    = nombre;
        this.correo    = correo;
        this.password  = password;
        this.telefono  = telefono;
        this.activo    = true;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", telefono=" + telefono + ", activo=" + activo + '}';
    }
    
    public boolean login(String correo, String password) {
        return this.getCorreo().equals(correo) && this.getPassword().equals(password);
    }
    
    public void logout() {
        System.out.println("Usuario " + getNombre() + " ha cerrado sesión.");
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }   
}