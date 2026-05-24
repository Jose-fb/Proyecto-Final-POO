package umag.vista;

import umag.control.ControlSolicitud;
import umag.control.ControlEstudiante;
import umag.control.ControlPublicacion;
import umag.modelo.entidad.Estudiante;
import umag.modelo.entidad.Publicacion;
import umag.modelo.entidad.Solicitud;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class VistaSolicitud {
 
    private final String rol;
    private final String usuarioId;
    ControlSolicitud control = new ControlSolicitud();
    ControlEstudiante controlEst = new ControlEstudiante();
    ControlPublicacion controlPub = new ControlPublicacion();
    
    public VistaSolicitud(String rol, String usuarioId) {
        this.rol = rol;
        this.usuarioId = usuarioId;
    }
    
    public void menu() {
        if (rol.equalsIgnoreCase("propietario")) {
            menuPropietario();
        } else {
            menuEstudiante();
        }
    }
    
    private void menuPropietario() {
        String[] opciones = {
            "Listar mis solicitudes", "Listar por estado",
            "Listar por publicación", "Listar por estudiante",
            "Aceptar", "Rechazar", "Cancelar", "Volver"
        };
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE SOLICITUDES ===\nSeleccione una opción:",
                    "Solicitudes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0: listarMias();               break;
                case 1: listarPorEstadoProp();      break;
                case 2: listarPorPublicacionProp(); break;
                case 3: listarPorEstudianteProp();  break;
                case 4: aceptar();                  break;
                case 5: rechazar();                 break;
                case 6: cancelar();                 break;
                default: break;
            }
        } while (opcion != -1 && opcion != 7);
    }
    
    private void menuEstudiante() {
        String[] opciones = {
            "Guardar", "Listar todas", "Listar por estado",
            "Listar por publicación", "Cancelar", "Volver"
        };
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== GESTIÓN DE SOLICITUDES ===\nSeleccione una opción:",
                    "Solicitudes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0: guardar();              break;
                case 1: listar();               break;
                case 2: listarPorEstado();      break;
                case 3: listarPorPublicacion(); break;
                case 4: cancelar();             break;
                default: break;
            }
        } while (opcion != -1 && opcion != 5);
    }
    
    private List<Solicitud> filtrarPorMisPubs(List<Solicitud> todas) {
        List<Solicitud> mias = new ArrayList<>();
        for (Solicitud s : todas) {
            if (s.getPublicacion() != null
                    && usuarioId.equals(s.getPublicacion().getPropietarioId())) {
                mias.add(s);
            }
        }
        return mias;
    }
    
    private void listarMias() {
        List<Solicitud> mias = filtrarPorMisPubs(control.listar());
        if (mias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tienes solicitudes en tus publicaciones.",
                    "Mis Solicitudes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(mias, "MIS SOLICITUDES");
    }
    
    private void listarPorEstadoProp() {
        String[] estados = {
            Solicitud.getESTADO_PENDIENTE(), Solicitud.getESTADO_ACEPTADA(), Solicitud.getESTADO_RECHAZADA(), Solicitud.getESTADO_CANCELADA()};
        int idx = JOptionPane.showOptionDialog(null, "Seleccione el estado:",
                "Listar por Estado", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);
        if (idx == -1) return;
        List<Solicitud> mias = filtrarPorMisPubs(control.listarPorEstado(estados[idx]));
        if (mias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes con estado: " + estados[idx],
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(mias, "MIS SOLICITUDES - " + estados[idx]);
    }
 
    private void listarPorPublicacionProp() {
        String idPub = JOptionPane.showInputDialog(null, "Ingrese ID de la publicación:",
                "Listar por Publicación", JOptionPane.QUESTION_MESSAGE);
        if (idPub == null) return;
        Publicacion pub = controlPub.buscarPorId(idPub);
        if (pub == null || !usuarioId.equals(pub.getPropietarioId())) {
            JOptionPane.showMessageDialog(null, "Esa publicación no te pertenece o no existe.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Solicitud> lista = control.listarPorPublicacion(idPub);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes para esa publicación.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(lista, "SOLICITUDES DE PUBLICACIÓN " + idPub);
    }
 
    private void listarPorEstudianteProp() {
        String idEst = JOptionPane.showInputDialog(null, "Ingrese ID del estudiante:",
                "Listar por Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (idEst == null) return;
        List<Solicitud> mias = filtrarPorMisPubs(control.listarPorEstudiante(idEst));
        if (mias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ese estudiante no tiene solicitudes en tus publicaciones.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(mias, "SOLICITUDES DEL ESTUDIANTE " + idEst);
    }
    
    private void guardar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la solicitud:",
                "Guardar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String estudianteId = JOptionPane.showInputDialog(null, "Ingrese ID del estudiante:",
                "Guardar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (estudianteId == null) return;
        Estudiante estudiante = controlEst.buscarPorId(estudianteId);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado con ID: " + estudianteId,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Publicacion> activas = controlPub.listarActivas();
        if (activas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay publicaciones activas disponibles.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] nombresPub = new String[activas.size()];
        for (int i = 0; i < activas.size(); i++) {
            Publicacion p = activas.get(i);
            nombresPub[i] = p.getId() + " - " + p.getTitulo() + " ($" + p.getPrecio() + ")";
        }
        int pubIdx = JOptionPane.showOptionDialog(null, "Seleccione la publicación:",
                "Guardar Solicitud", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, nombresPub, nombresPub[0]);
        if (pubIdx == -1) return;
        Publicacion publicacion = activas.get(pubIdx);
        String mensaje = JOptionPane.showInputDialog(null, "Ingrese mensaje para el propietario:",
                "Guardar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (mensaje == null) return;
        boolean res = control.guardar(id, estudiante, publicacion, mensaje);
        if (res) {
            JOptionPane.showMessageDialog(null, "Solicitud guardada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar solicitud.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void listar() {
        List<Solicitud> lista = control.listar();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes registradas.",
                    "Listar Solicitudes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(lista, "SOLICITUDES REGISTRADAS");
    }
 
    private void listarPorEstado() {
        String[] estados = {
            Solicitud.getESTADO_PENDIENTE(), Solicitud.getESTADO_ACEPTADA(), Solicitud.getESTADO_RECHAZADA(), Solicitud.getESTADO_CANCELADA()};
        int idx = JOptionPane.showOptionDialog(null, "Seleccione el estado:",
                "Listar por Estado", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);
        if (idx == -1) return;
        List<Solicitud> lista = control.listarPorEstado(estados[idx]);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes con estado: " + estados[idx],
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(lista, "SOLICITUDES - " + estados[idx]);
    }
 
    private void listarPorPublicacion() {
        String idPub = JOptionPane.showInputDialog(null, "Ingrese ID de la publicación:",
                "Listar por Publicación", JOptionPane.QUESTION_MESSAGE);
        if (idPub == null) return;
        List<Solicitud> lista = control.listarPorPublicacion(idPub);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay solicitudes para esa publicación.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mostrarSolicitudes(lista, "SOLICITUDES DE PUBLICACIÓN " + idPub);
    }
 
    private void aceptar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la solicitud a aceptar:",
                "Aceptar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        boolean res = control.aceptar(id);
        if (res) {
            JOptionPane.showMessageDialog(null, "Solicitud aceptada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al aceptar solicitud.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void rechazar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la solicitud a rechazar:",
                "Rechazar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String motivo = JOptionPane.showInputDialog(null, "Ingrese motivo del rechazo:",
                "Rechazar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (motivo == null) return;
        boolean res = control.rechazar(id, motivo);
        if (res) {
            JOptionPane.showMessageDialog(null, "Solicitud rechazada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al rechazar solicitud.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void cancelar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la solicitud a cancelar:",
                "Cancelar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        boolean res = control.cancelar(id);
        if (res) {
            JOptionPane.showMessageDialog(null, "Solicitud cancelada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al cancelar solicitud.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void borrar() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID de la solicitud a borrar:",
                "Borrar Solicitud", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        boolean res = control.borrar(id);
        if (res) {
            JOptionPane.showMessageDialog(null, "Solicitud borrada exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al borrar solicitud.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void mostrarSolicitudes(List<Solicitud> lista, String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(titulo).append(" ===\n\n");
        for (Solicitud s : lista) {
            sb.append("ID: ").append(s.getId()).append("\n");
            sb.append("Estado: ").append(s.getEstado()).append("\n");
            String nomEst = (s.getEstudiante() != null) ? s.getEstudiante().getNombre() : "Desconocido";
            sb.append("Estudiante: ").append(nomEst).append("\n");
            String titPub = (s.getPublicacion() != null) ? s.getPublicacion().getTitulo() : "Sin publicación";
            sb.append("Publicación: ").append(titPub).append("\n");
            sb.append("Mensaje: ").append(s.getMensaje()).append("\n");
            sb.append("Fecha: ").append(s.getFechaSolicitud()).append("\n");
            if (s.getRespuestaPropietario() != null) {
                sb.append("Respuesta: ").append(s.getRespuestaPropietario()).append("\n");
            }
            sb.append("─────────────────────────\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}