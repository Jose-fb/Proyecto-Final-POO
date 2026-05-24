package umag.modelo.servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Habitacion;
import umag.modelo.entidad.Publicacion;
import umag.modelo.persistencia.DaoPublicacion;

public class ServicioPublicacion {
    
    DaoPublicacion dao = new DaoPublicacion();
  
    public boolean guardar(String id, String titulo, String descripcion, double precio, Habitacion habitacion, String propietarioId) {
        if (id.isEmpty() || titulo.isEmpty() || descripcion.isEmpty()) {
            System.out.println("Error: id, título o descripción vacíos.");
            return false;
        }
        if (titulo.length() < 5) {
            System.out.println("Error: el título debe tener al menos 5 caracteres.");
            return false;
        }
        if (precio <= 0) {
            System.out.println("Error: el precio debe ser mayor a 0.");
            return false;
        }
        if (habitacion == null) {
            System.out.println("Error: debe asignar una habitación.");
            return false;
        } try {
            Publicacion p = new Publicacion(id, titulo, descripcion, precio, habitacion, propietarioId);
            p.activar();
            dao.guardar(p);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar publicación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean borrar(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: id vacío.");
            return false;
        } try {
            dao.borrar(id);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al borrar publicación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public List<Publicacion> listar() {
        try {
            return dao.listar();
        } catch (IOException ex) {
            System.out.println("Error al listar publicaciones: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public List<Publicacion> listarActivas() {
        try {
            return dao.listarActivas();
        } catch (IOException ex) {
            System.out.println("Error al listar activas: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public List<Publicacion> filtrar(double precioMax, String ubicacion) {
        List<Publicacion> resultado = new ArrayList<>();
        try {
            List<Publicacion> todas = dao.listar();
            for (Publicacion p : todas) {
                boolean cumplePrecio = (precioMax <= 0) || (p.getPrecio() <= precioMax);
                boolean cumpleUbicacion = (ubicacion == null || ubicacion.isEmpty()) || (p.getHabitacion() != null && p.getHabitacion().getDireccion().contains(ubicacion));
            if (cumplePrecio && cumpleUbicacion) {
                resultado.add(p);
            }
            }
        } catch (IOException ex) {
            System.out.println("Error al filtrar publicaciones: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return resultado;
    }
    
    public Publicacion buscarPorId(String id) {
        try {
            List<Publicacion> todas = dao.listar();
            for (Publicacion p : todas) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al buscar publicación: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
}
