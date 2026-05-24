package umag.vista;
 
import umag.control.ControlPropietario;
import umag.modelo.entidad.Propietario;
import javax.swing.JOptionPane;
import java.util.List;

public class VistaPropietario {
 
    private final String propietarioId;
    private boolean cuentaEliminada = false;
    ControlPropietario control = new ControlPropietario();
    
    public VistaPropietario(String propietarioId) {
        this.propietarioId = propietarioId;
    }
    
    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }
    
    public void menu() {
        String[] opciones = {"Listar", "Buscar por ID", "Borrar mi cuenta", "Volver"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE PROPIETARIOS ===\nSeleccione una opción:",
                    "Propietarios",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0: listar();      break;
                case 1: buscarPorId(); break;
                case 2: borrar();      break;
                default: break;
            }
            if (cuentaEliminada) return;
        } while (opcion != -1 && opcion != 3);
    }
    
    private void listar() {
        List<Propietario> lista = control.listar();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay propietarios registrados.",
                    "Listar Propietarios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== PROPIETARIOS REGISTRADOS ===\n\n");
        for (Propietario p : lista) {
            sb.append("ID: ").append(p.getId()).append("\n");
            sb.append("Nombre: ").append(p.getNombre()).append("\n");
            sb.append("Correo: ").append(p.getCorreo()).append("\n");
            sb.append("Teléfono: ").append(p.getTelefono()).append("\n");
            sb.append("Cédula: ").append(p.getCedula()).append("\n");
            sb.append("Dirección: ").append(p.getDireccion()).append("\n");
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Listar Propietarios", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void buscarPorId() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID del propietario:",
                "Buscar Propietario", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        Propietario p = control.buscarPorId(id);
        if (p != null) {
            String info = "ID: " + p.getId() + "\n"
                    + "Nombre: " + p.getNombre() + "\n"
                    + "Correo: " + p.getCorreo() + "\n"
                    + "Teléfono: " + p.getTelefono() + "\n"
                    + "Cédula: " + p.getCedula() + "\n"
                    + "Dirección: " + p.getDireccion();
            JOptionPane.showMessageDialog(null, info, "Propietario Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Propietario no encontrado.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void borrar() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.",
                "Borrar mi cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
 
        boolean res = control.borrar(propietarioId);
        if (res) {
            cuentaEliminada = true; // activa la señal de salida
            JOptionPane.showMessageDialog(null, "Tu cuenta ha sido eliminada. Cerrando sesión...",
                    "Cuenta eliminada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar la cuenta.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}