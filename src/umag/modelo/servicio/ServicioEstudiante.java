package umag.modelo.servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Estudiante;
import umag.modelo.entidad.Publicacion;
import umag.modelo.entidad.Solicitud;
import umag.modelo.persistencia.DaoEstudiante;

public class ServicioEstudiante {
    
    DaoEstudiante dao = new DaoEstudiante();
    
    public boolean guardar(String id, String nombre, String correo, String password, String telefono, String universidad) {
        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            System.out.println("Error: campos obligatorios vacíos.");
            return false;
        }
        if (!correo.contains("@")) {
            System.out.println("Error: correo no válido.");
            return false;
        }
        try {
            Estudiante e = new Estudiante(id, nombre, correo, password, telefono, universidad);
            dao.guardar(e);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar estudiante: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean borrar(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: id vacío.");
            return false;
        }
        try {
            dao.borrar(id);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al borrar estudiante: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public List<Estudiante> listar() {
        try {
            return dao.listar();
        } catch (IOException ex) {
            System.out.println("Error al listar estudiantes: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public Estudiante buscarPorId(String id) {
        try {
            List<Estudiante> todos = dao.listar();
            for (Estudiante e : todos) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al buscar estudiante: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
    
    public Estudiante login(String correo, String password) {
        if (correo.isEmpty() || password.isEmpty()) return null;
        try {
            for (Estudiante e : dao.listar()) {
                if (e.login(correo, password)) return e;
            }
        } catch (IOException ex) {
            System.out.println("Error en login: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
    
    public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones, double precioMax, String ubicacion) {
        List<Publicacion> resultado = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (!p.isActiva()) continue;
            boolean cumplePrecio    = (precioMax <= 0) || (p.getPrecio() <= precioMax);
            boolean cumpleUbicacion = (ubicacion == null || ubicacion.isEmpty()) || (p.getHabitacion() != null && p.getHabitacion().getDireccion().toLowerCase().contains(ubicacion.toLowerCase()));
            if (cumplePrecio && cumpleUbicacion) resultado.add(p);
        }
        return resultado;
    }
}