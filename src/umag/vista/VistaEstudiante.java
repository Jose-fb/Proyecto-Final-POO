package umag.vista;
 
import umag.control.ControlEstudiante;
import umag.modelo.entidad.Estudiante;
import javax.swing.JOptionPane;
import java.util.List;

public class VistaEstudiante {
 
    private final String estudianteId;
    private boolean cuentaEliminada = false;
    ControlEstudiante control = new ControlEstudiante();
 
    public VistaEstudiante(String estudianteId) {
        this.estudianteId = estudianteId;
    }
    
    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }
    
    public void menu() {
        String[] opciones = {"Listar", "Buscar por ID", "Borrar mi cuenta", "Volver"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE ESTUDIANTES ===\nSeleccione una opción:",
                    "Estudiantes",
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
        List<Estudiante> lista = control.listar();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.",
                    "Listar Estudiantes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTUDIANTES REGISTRADOS ===\n\n");
        for (Estudiante e : lista) {
            sb.append("ID: ").append(e.getId()).append("\n");
            sb.append("Nombre: ").append(e.getNombre()).append("\n");
            sb.append("Correo: ").append(e.getCorreo()).append("\n");
            sb.append("Teléfono: ").append(e.getTelefono()).append("\n");
            sb.append("Universidad: ").append(e.getUniversidad()).append("\n");
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Listar Estudiantes", JOptionPane.INFORMATION_MESSAGE);
    }
 
    private void buscarPorId() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID del estudiante:",
                "Buscar Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        Estudiante e = control.buscarPorId(id);
        if (e != null) {
            String info = "ID: " + e.getId() + "\n"
                    + "Nombre: " + e.getNombre() + "\n"
                    + "Correo: " + e.getCorreo() + "\n"
                    + "Teléfono: " + e.getTelefono() + "\n"
                    + "Universidad: " + e.getUniversidad();
            JOptionPane.showMessageDialog(null, info, "Estudiante Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
 
    private void borrar() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.",
                "Borrar mi cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
 
        boolean res = control.borrar(estudianteId);
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