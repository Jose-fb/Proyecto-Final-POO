package umag.modelo.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Estudiante;

public class DaoEstudiante {
    
    private String archivo = "estudiantes.dat";
    
    public boolean guardar(Estudiante estudiante) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Estudiante> lista = listar();
        lista.add(estudiante);
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
    
    public boolean borrar(String id) throws IOException, ClassNotFoundException {
        List<Estudiante> lista = listar();
        for (Estudiante e : lista) {
            if (e.getId().equals(id)) {
                lista.remove(e);
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
    
    public List<Estudiante> listar() throws IOException, ClassNotFoundException {
        List<Estudiante> lista = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(file);
            lista = (List<Estudiante>) entrada.readObject();
            entrada.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No existe archivo: " + archivo);
        }
        return lista;
    }
}