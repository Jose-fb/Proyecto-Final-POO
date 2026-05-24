package umag.modelo.entidad;
import java.io.Serializable;

public class Habitacion implements Serializable {
    
    private String  id;
    private String  direccion;
    private double  precio;
    private boolean disponible;
    private String  tipo;
    
    public Habitacion() {
        this.disponible = true;
    }
    
    public Habitacion(String id, String direccion,
                      double precio, String tipo) {
        this.id          = id;
        this.direccion   = direccion;
        this.precio      = precio;
        this.tipo        = tipo;
        this.disponible  = true;
    }
    
    public Habitacion(String id, String direccion, double precio, boolean disponible, String tipo) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.disponible = disponible;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", direccion=" + direccion + ", precio=" + precio + ", disponible=" + disponible + ", tipo=" + tipo + '}';
    }
    
    public void actualizarDisponibilidad(boolean disponible) {
        this.setDisponible(disponible);
        String estado;
        if (disponible) {
            estado = "Disponible";
        } else {
            estado = "Ocupada";
        }
        System.out.println("Habitación " + getId() + " ahora está: " + estado);
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
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}