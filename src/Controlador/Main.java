package com.proyecto;

import Controlador.UsuarioCtrl;
import com.proyecto.controlador.*;
import com.proyecto.modelo.*;
import com.proyecto.vista.VistasJOP;
import javax.swing.JOptionPane;

public class Main {
    private static UsuarioCtrl ctrlUser = new UsuarioCtrl();
    private static PublicacionCtrl ctrlPub = new PublicacionCtrl();
    private static SolicitudCtrl ctrlSol = new SolicitudCtrl();

    public static void main(String[] args) {
        boolean ejecutar = true;

        while (ejecutar) {
            String[] opcionesIniciales = {"Registrarse", "Iniciar Sesión", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "BIENVENIDO A ROOMIEU", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesIniciales, opcionesIniciales[0]);

            switch (seleccion) {
                case 0: // REGISTRO
                    registrarNuevoUsuario();
                    break;
                case 1: // LOGIN
                    hacerLogin();
                    break;
                default:
                    ejecutar = false;
                    break;
            }
        }
    }

    private static void registrarNuevoUsuario() {
        String[] roles = {"Propietario", "Estudiante"};
        int tipo = JOptionPane.showOptionDialog(null, "¿Cómo desea registrarse?", "Tipo de Cuenta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);

        if (tipo == -1) return; // Si cierra la ventana

        // Requerimientos comunes para ambos
        String id = JOptionPane.showInputDialog("ID:");
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String correo = JOptionPane.showInputDialog("Correo:");
        String pass = JOptionPane.showInputDialog("Contraseña:");

        if (tipo == 0) { // ES PROPIETARIO
            String telefono = JOptionPane.showInputDialog("Teléfono de contacto:");
            Propietario p = new Propietario(id, nombre, correo, pass, telefono);
            ctrlUser.registrarUsuario(p);
            JOptionPane.showMessageDialog(null, "Propietario registrado con éxito.");
            
        } else { // ES ESTUDIANTE
            String universidad = JOptionPane.showInputDialog("Nombre de la Universidad:");
            Estudiante e = new Estudiante(id, nombre, correo, pass, universidad);
            ctrlUser.registrarUsuario(e);
            JOptionPane.showMessageDialog(null, "Estudiante registrado con éxito.");
        }
    }

    private static void hacerLogin() {
        String correo = JOptionPane.showInputDialog("Correo:");
        String pass = JOptionPane.showInputDialog("Contraseña:");

        Usuario user = ctrlUser.login(correo, pass);

        if (user != null) {
            if (user instanceof Propietario) {
                VistasJOP.menuPropietario(user, ctrlPub, ctrlSol);
            } else if (user instanceof Estudiante) {
                VistasJOP.menuEstudiante(user, ctrlPub, ctrlSol);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Correo o contraseña incorrectos.");
        }
    }
}