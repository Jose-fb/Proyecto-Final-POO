package com.proyecto.vista;
import Controlador.UsuarioCtrl;
import com.proyecto.controlador.PlataformaCtrl;
import com.proyecto.controlador.PublicacionCtrl;
import com.proyecto.controlador.SolicitudCtrl;
import com.proyecto.modelo.*;
import javax.swing.JOptionPane; // Asegúrate que termine en punto y coma

public class VistasJOP {

    public static void mostrarLogin(UsuarioCtrl ctrlUser, PublicacionCtrl ctrlPub, SolicitudCtrl ctrlSol) {
        // Usamos PLAIN_MESSAGE que nunca falla
        String correo = JOptionPane.showInputDialog(null, "--- LOGIN ROOMIEU ---\nCorreo:", "Acceso", JOptionPane.PLAIN_MESSAGE);
        if (correo == null) return;

        String pass = JOptionPane.showInputDialog(null, "Contraseña:", "Acceso", JOptionPane.PLAIN_MESSAGE);
        if (pass == null) return;

        Usuario user = ctrlUser.login(correo, pass);

        if (user != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre());
            if (user.getRol().equalsIgnoreCase("ESTUDIANTE")) {
                menuEstudiante(user, ctrlPub, ctrlSol);
            } else if (user.getRol().equalsIgnoreCase("PROPIETARIO")) {
                menuPropietario(user, ctrlPub, ctrlSol);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * VISTA 2: PANEL ESTUDIANTE
     * Permite ver habitaciones, filtrar y enviar solicitudes.
     */
    public static void menuEstudiante(Usuario est, PublicacionCtrl ctrlPub, SolicitudCtrl ctrlSol) {
        boolean salir = false;
        while (!salir) {
            String[] opciones = {"Ver Disponibles", "Filtrar por Precio", "Ver mis Solicitudes", "Cerrar Sesión"};
            int sel = JOptionPane.showOptionDialog(null, "Panel de Estudiante", "RoomieU",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            if (sel == 0 || sel == 1) {
                double max = (sel == 1) ? Double.parseDouble(JOptionPane.showInputDialog("Precio máximo:")) : Double.MAX_VALUE;
                
                StringBuilder lista = new StringBuilder("HABITACIONES:\n");
                for (Publicacion p : ctrlPub.getPublicaciones()) {
                    if (p.getHabitacion().isDisponible() && p.getHabitacion().getPrecio() <= max) {
                        lista.append("ID: ").append(p.getId()).append(" | ").append(p.getHabitacion().getDireccion())
                             .append(" | $").append(p.getHabitacion().getPrecio()).append("\n");
                    }
                }

                String idSol = JOptionPane.showInputDialog(lista + "\nIngrese ID para solicitar (o cancele):");
                if (idSol != null && !idSol.isEmpty()) {
                    Publicacion p = ctrlPub.buscarPorId(idSol);
                    if (p != null) {
                        ctrlSol.registrarSolicitud(new Solicitud("S-" + System.currentTimeMillis(), (Estudiante) est, p));
                        JOptionPane.showMessageDialog(null, "Solicitud enviada exitosamente.");
                    }
                }
            } else if (sel == 2) {
                StringBuilder misSols = new StringBuilder("MIS SOLICITUDES:\n");
                for (Solicitud s : ctrlSol.getTodasLasSolicitudes()) {
                    if (s.getEstudiante().getId().equals(est.getId())) {
                        misSols.append("- ").append(s.getPublicacion().getHabitacion().getDireccion())
                               .append(" [").append(s.getEstado()).append("]\n");
                    }
                }
                JOptionPane.showMessageDialog(null, misSols);
            } else {
                salir = true;
            }
        }
    }

    /**
     * VISTA 3: PANEL PROPIETARIO
     * Gestiona publicaciones y aceptación de inquilinos.
     */
    public static void menuPropietario(Usuario prop, PublicacionCtrl ctrlPub, SolicitudCtrl ctrlSol) {
        boolean salir = false;
        while (!salir) {
            String[] opciones = {"Ver Solicitudes Recibidas", "Publicar Habitación", "Cerrar Sesión"};
            int sel = JOptionPane.showOptionDialog(null, "Panel de Propietario", "RoomieU",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (sel == 0) {
                StringBuilder listaSols = new StringBuilder("SOLICITUDES PENDIENTES:\n");
                for (Solicitud s : ctrlSol.getTodasLasSolicitudes()) {
                    if (s.getPublicacion().getPropietario().getId().equals(prop.getId()) && s.getEstado().equals("PENDIENTE")) {
                        listaSols.append("ID: ").append(s.getId()).append(" | De: ").append(s.getEstudiante().getNombre())
                                 .append("\nPropiedad: ").append(s.getPublicacion().getHabitacion().getDireccion()).append("\n\n");
                    }
                }
                String idAcc = JOptionPane.showInputDialog(listaSols + "Ingrese ID para ACEPTAR:");
                if (idAcc != null && ctrlSol.aceptarSolicitud(idAcc)) {
                    JOptionPane.showMessageDialog(null, "Solicitud aceptada. Habitación ocupada.");
                }
            } else if (sel == 1) {
                try {
                    String dir = JOptionPane.showInputDialog("Dirección:");
                    double pre = Double.parseDouble(JOptionPane.showInputDialog("Precio mensual:"));
                    String desc = JOptionPane.showInputDialog("Descripción corta:");

                    Habitacion h = new Habitacion("H-" + System.currentTimeMillis(), dir, pre, true);
                    // ORDEN: ID, Descripcion, Habitacion, Propietario
                    Publicacion p = new Publicacion("P-" + System.currentTimeMillis(), desc, h, (Propietario) prop);
                    
                    ctrlPub.crearPublicacion(p);
                    JOptionPane.showMessageDialog(null, "Publicación creada.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos.");
                }
            } else {
                salir = true;
            }
        }
    }
}