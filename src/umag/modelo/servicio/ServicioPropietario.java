package umag.modelo.servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Propietario;
import umag.modelo.persistencia.DaoPropietario;

public class ServicioPropietario {
    
    DaoPropietario dao = new DaoPropietario();
    
    public boolean guardar(String id, String nombre, String correo, String password, String telefono, String cedula, String direccion) {
        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            System.out.println("Error: campos obligatorios vacíos.");
            return false;
        }
        if (!correo.contains("@")) {
            System.out.println("Error: correo no válido.");
            return false;
        }
        if (telefono.length() < 7) {
            System.out.println("Error: teléfono muy corto.");
            return false;
        } try {
            Propietario p = new Propietario(id, nombre, correo, password, telefono, cedula, direccion);
            dao.guardar(p);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar propietario: " + ex.getMessage());
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
            System.out.println("Error al borrar propietario: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public List<Propietario> listar() {
        try {
            return dao.listar();
        } catch (IOException ex) {
            System.out.println("Error al listar propietarios: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public Propietario buscarPorId(String id) {
        try {
            List<Propietario> todos = dao.listar();
            for (Propietario p : todos) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al buscar propietario: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
    
    public Propietario login(String correo, String password) {
        if (correo.isEmpty() || password.isEmpty()) return null;
        try {
            for (Propietario p : dao.listar()) {
                if (p.login(correo, password)) return p;
            }
        } catch (IOException ex) {
            System.out.println("Error en login: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return null;
    }
}
