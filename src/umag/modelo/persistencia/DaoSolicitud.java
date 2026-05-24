package umag.modelo.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Solicitud;

public class DaoSolicitud {
    
    private String archivo = "solicitudes.dat";
 
    public boolean guardar(Solicitud  solicitud) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Solicitud> lista = listar();
        lista.add(solicitud);
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
    
    public boolean borrar(String id) throws IOException, ClassNotFoundException {
        List<Solicitud> lista = listar();
        for (Solicitud s : lista) {
            if (s.getId().equals(id)) {
                lista.remove(s);
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
    
    public List<Solicitud> listar() throws IOException, ClassNotFoundException {
        List<Solicitud> lista = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(file);
            lista = (List<Solicitud>) entrada.readObject();
            entrada.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No existe archivo: " + archivo);
        }
        return lista;
    }
}