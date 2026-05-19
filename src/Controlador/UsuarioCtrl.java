package Controlador;
import com.proyecto.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCtrl {
    private List<Usuario> usuarios = new ArrayList<>();

    public void registrarUsuario(Usuario u) { usuarios.add(u); }
    
    public Usuario login(String correo, String pass) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo) && u.getPassword().equals(pass)) return u;
        }
        return null;
    }
   

}