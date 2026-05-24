package umag.modelo.persistencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Publicacion;

public class DaoPublicacion {
    
    private String archivo = "publicaciones.dat";
    
    public boolean guardar(Publicacion publicacion) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Publicacion> lista = listar();
        lista.add(publicacion);
        FileOutputStream file = new FileOutputStream(archivo);
        ObjectOutputStream fil = new ObjectOutputStream(file);
        fil.writeObject(lista);
        fil.close();
        file.close();
        return true;
    }
    
    public boolean borrar(String id) throws IOException, ClassNotFoundException {
        List<Publicacion> lista = listar();
        for (Publicacion p : lista) {
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
    
    public List<Publicacion> listar() throws IOException, ClassNotFoundException {
        List<Publicacion> lista = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream entrada = new ObjectInputStream(file);
            lista = (List<Publicacion>) entrada.readObject();
            entrada.close();
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No existe archivo: " + archivo);
        }
        return lista;
    }
    
    public List<Publicacion> listarActivas() throws IOException, ClassNotFoundException {
        List<Publicacion> activas = new ArrayList<>();
        for (Publicacion p : listar()) {
            if (p.isActiva()) activas.add(p);
        }
        return activas;
    }
}