package umag.vista;
 
import umag.control.ControlPublicacion;
import umag.control.ControlHabitacion;
import umag.modelo.entidad.Habitacion;
import umag.modelo.entidad.Publicacion;
import javax.swing.JOptionPane;
import java.util.List;

public class VistaPublicacion {
 
    private final String rol;
    ControlPublicacion control = new ControlPublicacion();
    ControlHabitacion controlHab = new ControlHabitacion();
    
    public VistaPublicacion(String rol) {
        this.rol = rol;
    }
 
    public void menu() {
        if (rol.equalsIgnoreCase("propietario")) {
            menuPropietario();
        } else {
            menuEstudiante();
        }
    }
    
    private void menuPropietario() {
        String[] opciones = {"Guardar", "Listar todas", "Listar activas",
                             "Filtrar", "Buscar por ID", "Borrar", "Volver"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE PUBLICACIONES ===\nSeleccione una opción:",
                    "Publicaciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0: guardar();       break;
                case 1: listar();        break;
                case 2: listarActivas(); break;
                case 3: filtrar();       break;
                case 4: buscarPorId();   break;
                case 5: borrar();        break;
                default: break;
            }
        } while (opcion != -1 && opcion != 6);
    }
    
    private void menuEstudiante() {
        String[] opciones = {"Listar todas", "Listar activas",
                             "Filtrar", "Buscar por ID", "Volver"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== PUBLICACIONES ===\nSeleccione una opción:",
                    "Publicaciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0: listar();        break;
                case 1: listarActivas(); break;
                case 2: filtrar();       break;
                case 3: buscarPorId();   break;
                default: break;
            }
        } while (opcion != -1 && opcion != 4);
    }
    
    private void guardar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la publicación:",
                "Guardar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String titulo = JOptionPane.showInputDialog(null, "Ingrese título (mínimo 5 caracteres):",
                "Guardar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (titulo == null) return;
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese descripción:",
                "Guardar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (descripcion == null) return;
        String precioStr = JOptionPane.showInputDialog(null, "Ingrese precio:",
                "Guardar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (precioStr == null) return;
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Habitacion> habitaciones = controlHab.listar();
        if (habitaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay habitaciones registradas.\nDebe crear una habitación primero.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] nombresHab = new String[habitaciones.size()];
        for (int i = 0; i < habitaciones.size(); i++) {
            Habitacion h = habitaciones.get(i);
            nombresHab[i] = h.getId() + " - " + h.getDireccion() + " ($" + h.getPrecio() + ")";
        }
        int habIdx = JOptionPane.showOptionDialog(null,
                "Seleccione la habitación para esta publicación:",
                "Guardar Publicación", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, nombresHab, nombresHab[0]);
        if (habIdx == -1) return;
        Habitacion habitacion = habitaciones.get(habIdx);
        String propietarioId = JOptionPane.showInputDialog(null, "Ingrese ID del propietario:",
                "Guardar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (propietarioId == null) return;
        boolean res = control.guardar(id, titulo, descripcion, precio, habitacion, propietarioId);
        if (res) {
            JOptionPane.showMessageDialog(null, "Publicación guardada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar publicación.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listar() {
        List<Publicacion> lista = control.listar();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay publicaciones registradas.",
                    "Listar Publicaciones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarPublicaciones(lista, "PUBLICACIONES REGISTRADAS");
    }
    
    private void listarActivas() {
        List<Publicacion> lista = control.listarActivas();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay publicaciones activas.",
                    "Publicaciones Activas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarPublicaciones(lista, "PUBLICACIONES ACTIVAS");
    }
    
    private void filtrar() {
        String precioStr = JOptionPane.showInputDialog(null, "Precio máximo (0 = sin límite):",
                "Filtrar Publicaciones", JOptionPane.QUESTION_MESSAGE);
        if (precioStr == null) return;
        double precioMax;
        try {
            precioMax = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String ubicacion = JOptionPane.showInputDialog(null,
                "Ubicación (dejar vacío = cualquiera):", "Filtrar Publicaciones", JOptionPane.QUESTION_MESSAGE);
        if (ubicacion == null) return;
        List<Publicacion> lista = control.filtrar(precioMax, ubicacion);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron publicaciones con esos filtros.",
                    "Filtrar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarPublicaciones(lista, "PUBLICACIONES FILTRADAS");
    }
    
    private void buscarPorId() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la publicación:",
                "Buscar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        Publicacion p = control.buscarPorId(id);
        if (p != null) {
            String habInfo = (p.getHabitacion() != null)
                    ? p.getHabitacion().getDireccion() : "Sin habitación";
            String info = "ID: " + p.getId() + "\n"
                    + "Título: " + p.getTitulo() + "\n"
                    + "Descripción: " + p.getDescripcion() + "\n"
                    + "Precio: $" + p.getPrecio() + "\n"
                    + "Activa: " + (p.isActiva() ? "Sí" : "No") + "\n"
                    + "Habitación: " + habInfo + "\n"
                    + "Propietario ID: " + p.getPropietarioId();
            JOptionPane.showMessageDialog(null, info, "Publicación Encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Publicación no encontrada.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void borrar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la publicación a borrar:",
                "Borrar Publicación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        boolean res = control.borrar(id);
        if (res) {
            JOptionPane.showMessageDialog(null, "Publicación borrada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al borrar publicación.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarPublicaciones(List<Publicacion> lista, String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(titulo).append(" ===\n\n");
        for (Publicacion p : lista) {
            sb.append("ID: ").append(p.getId()).append("\n");
            sb.append("Título: ").append(p.getTitulo()).append("\n");
            sb.append("Precio: $").append(p.getPrecio()).append("\n");
            sb.append("Activa: ").append(p.isActiva() ? "Sí" : "No").append("\n");
            String habInfo = (p.getHabitacion() != null)
                    ? p.getHabitacion().getDireccion() : "Sin habitación";
            sb.append("Habitación: ").append(habInfo).append("\n");
            sb.append("Propietario ID: ").append(p.getPropietarioId()).append("\n");
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}