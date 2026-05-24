package umag.modelo.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Habitacion;

public class DaoHabitacion {
    
    private String archivo = "habitaciones.dat";
    
    public boolean guardar(Habitacion habitacion) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Habitacion> lista = listar();
        lista.add(habitacion);
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
    
    public boolean borrar(String id) throws IOException, ClassNotFoundException {
        List<Habitacion> lista = listar();
        for (Habitacion h : lista) {
            if (h.getId().equals(id)) {
                lista.remove(h);
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
    
    public List<Habitacion> listar() throws IOException, ClassNotFoundException {
        List<Habitacion> lista = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(file);
            lista = (List<Habitacion>) entrada.readObject();
            entrada.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No existe archivo: " + archivo);
        }
        return lista;
    }
}