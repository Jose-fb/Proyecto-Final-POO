package umag.control;
import java.util.List;
import umag.modelo.entidad.Propietario;
import umag.modelo.servicio.ServicioPropietario;

public class ControlPropietario {
    ServicioPropietario servicio = new ServicioPropietario();
    public boolean guardar(String id, String nombre, String correo, String password, String telefono, String cedula, String direccion) {
        boolean res = servicio.guardar(id, nombre, correo, password, telefono, cedula, direccion);
        return res;
    }
    
    public boolean borrar(String id) {
        boolean res = servicio.borrar(id);
        return res;
    }
    
    public List<Propietario> listar() {
        return servicio.listar();
    }
    
    public Propietario buscarPorId(String id) {
        return servicio.buscarPorId(id);
    }
    
    public Propietario login(String correo, String password) {
        return servicio.login(correo, password);
    }
}
