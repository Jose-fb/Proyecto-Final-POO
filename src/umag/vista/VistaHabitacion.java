package umag.vista;
import umag.control.ControlHabitacion;
import umag.modelo.entidad.Habitacion;
import javax.swing.JOptionPane;
import java.util.List;

public class VistaHabitacion {
    ControlHabitacion control = new ControlHabitacion();
    public void menu() {
        String[] opciones = {"Guardar", "Listar todas", "Listar disponibles", "Buscar por ID", "Borrar", "Volver"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE HABITACIONES ===\nSeleccione una opción:",
                    "Habitaciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0:
                    guardar();
                    break;
                case 1:
                    listar();
                    break;
                case 2:
                    listarDisponibles();
                    break;
                case 3:
                    buscarPorId();
                    break;
                case 4:
                    borrar();
                    break;
                default:
                    break;
            }
        } while (opcion != -1 && opcion != 5);
    }
    
    private void guardar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID:", "Guardar Habitación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección:", "Guardar Habitación", JOptionPane.QUESTION_MESSAGE);
        if (direccion == null) return;
        String precioStr = JOptionPane.showInputDialog(null, "Ingrese precio mensual:", "Guardar Habitación", JOptionPane.QUESTION_MESSAGE);
        if (precioStr == null) return;
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] tipos = {"Individual", "Compartida", "Estudio"};
        int tipoIdx = JOptionPane.showOptionDialog(null, "Seleccione tipo de habitación:",
                "Guardar Habitación", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipoIdx == -1) return;
        String tipo = tipos[tipoIdx];
        boolean res = control.guardar(id, direccion, precio, tipo);
        if (res) {
            JOptionPane.showMessageDialog(null, "Habitación guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar habitación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listar() {
        List<Habitacion> lista = control.listar();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay habitaciones registradas.", "Listar Habitaciones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== HABITACIONES REGISTRADAS ===\n\n");
        for (Habitacion h : lista) {
            sb.append("ID: ").append(h.getId()).append("\n");
            sb.append("Dirección: ").append(h.getDireccion()).append("\n");
            sb.append("Precio: $").append(h.getPrecio()).append("\n");
            sb.append("Tipo: ").append(h.getTipo()).append("\n");
            sb.append("Disponible: ").append(h.isDisponible() ? "Sí" : "No").append("\n");
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Listar Habitaciones", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void listarDisponibles() {
        List<Habitacion> lista = control.listarDisponibles();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles.", "Habitaciones Disponibles", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== HABITACIONES DISPONIBLES ===\n\n");
        for (Habitacion h : lista) {
            sb.append("ID: ").append(h.getId()).append("\n");
            sb.append("Dirección: ").append(h.getDireccion()).append("\n");
            sb.append("Precio: $").append(h.getPrecio()).append("\n");
            sb.append("Tipo: ").append(h.getTipo()).append("\n");
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Habitaciones Disponibles", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void buscarPorId() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la habitación:", "Buscar Habitación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        Habitacion h = control.buscarPorId(id);
        if (h != null) {
            String info = "ID: " + h.getId() + "\n"
                    + "Dirección: " + h.getDireccion() + "\n"
                    + "Precio: $" + h.getPrecio() + "\n"
                    + "Tipo: " + h.getTipo() + "\n"
                    + "Disponible: " + (h.isDisponible() ? "Sí" : "No");
            JOptionPane.showMessageDialog(null, info, "Habitación Encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Habitación no encontrada.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void borrar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la habitación a borrar:", "Borrar Habitación", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        boolean res = control.borrar(id);
        if (res) {
            JOptionPane.showMessageDialog(null, "Habitación borrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al borrar habitación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
