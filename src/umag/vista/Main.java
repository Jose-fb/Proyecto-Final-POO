package umag.vista;
 
import umag.modelo.entidad.Propietario;
import umag.modelo.entidad.Estudiante;
import javax.swing.JOptionPane;
 
public class Main {
 
    public static void main(String[] args) {
 
        VistaLogin vistaLogin = new VistaLogin();
        Object[] resultado = vistaLogin.menuLogin();
 
        if (resultado == null) {
            JOptionPane.showMessageDialog(null, "¡Hasta luego!", "Salir", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
 
        String tipoUsuario = (String) resultado[0];
 
        if ("PROPIETARIO".equals(tipoUsuario)) {
            Propietario p = (Propietario) resultado[1];
            menuPropietario(p.getId());
        } else if ("ESTUDIANTE".equals(tipoUsuario)) {
            Estudiante e = (Estudiante) resultado[1];
            menuEstudiante(e.getId());
        }
 
        JOptionPane.showMessageDialog(null, "¡Hasta luego!", "Salir", JOptionPane.INFORMATION_MESSAGE);
    }
 
    private static void menuPropietario(String propietarioId) {
        String[] opciones = {"Gestionar Propietarios", "Gestionar Habitaciones",
                             "Gestionar Publicaciones", "Gestionar Solicitudes",
                             "Cerrar Sesión"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== MENÚ PROPIETARIO ===\nSeleccione una opción:",
                    "Menú Principal - Propietario",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0:
                    VistaPropietario vp = new VistaPropietario(propietarioId);
                    vp.menu();
                    if (vp.isCuentaEliminada()) return; // salir de la app
                    break;
                case 1:
                    new VistaHabitacion().menu();
                    break;
                case 2:
                    new VistaPublicacion("propietario").menu();
                    break;
                case 3:
                    new VistaSolicitud("propietario", propietarioId).menu();
                    break;
                default:
                    break;
            }
        } while (opcion != -1 && opcion != 4);
    }
 
    private static void menuEstudiante(String estudianteId) {
        String[] opciones = {"Gestionar Estudiantes", "Ver Publicaciones",
                             "Gestionar Solicitudes", "Cerrar Sesión"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== MENÚ ESTUDIANTE ===\nSeleccione una opción:",
                    "Menú Principal - Estudiante",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0:
                    VistaEstudiante ve = new VistaEstudiante(estudianteId);
                    ve.menu();
                    if (ve.isCuentaEliminada()) return; // salir de la app
                    break;
                case 1:
                    new VistaPublicacion("estudiante").menu();
                    break;
                case 2:
                    new VistaSolicitud("estudiante", estudianteId).menu();
                    break;
                default:
                    break;
            }
        } while (opcion != -1 && opcion != 3);
    }
}