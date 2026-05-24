package umag.modelo.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Propietario;
import umag.modelo.entidad.Habitacion;
import umag.modelo.entidad.Publicacion;

public class DaoPropietario {
    
    private String archivo = "propietarios.dat";
    
    public boolean guardar(Propietario propietario) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Propietario> lista = listar();
        lista.add(propietario);
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
    
    public boolean borrar(String id) throws IOException, ClassNotFoundException {
        List<Propietario> lista = listar();
        for (Propietario p : lista) {
            if (p.getId().equals(id)) {
                lista.remove(p);
                break;
            }
        }
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
   

    public List<Propietario> listar() throws IOException, ClassNotFoundException {
        List<Propietario> lista = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(file);
            lista = (List<Propietario>) entrada.readObject();
            entrada.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No existe archivo: " + archivo);
        }
        return lista;
    }
}