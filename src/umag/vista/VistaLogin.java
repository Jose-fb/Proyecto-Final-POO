package umag.vista;
import umag.control.ControlPropietario;
import umag.control.ControlEstudiante;
import umag.modelo.entidad.Propietario;
import umag.modelo.entidad.Estudiante;
import javax.swing.JOptionPane;

public class VistaLogin {
    ControlPropietario controlProp = new ControlPropietario();
    ControlEstudiante controlEst = new ControlEstudiante();
    
    public Object[] menuLogin() {
        String[] opciones = {"Iniciar Sesión", "Registrarse", "Salir"};
        int opcion = -1;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "=== UNIROOM PLATAFORMA DE ARRENDAMIENTO ===\n"      
                    + "Bienvenido al sistema de alquiler de habitaciones UniRoom.\n\n"
                    + "Seleccione una opción:",
                    "Login",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);
            switch (opcion) {
                case 0:
                    Object[] resultado = login();
                    if (resultado != null) return resultado;
                    break;
                case 1:
                    registrar();
                    break;
                default:
                    return null;
            }
        } while (opcion != -1 && opcion != 2);
        return null;
    }
    
    private Object[] login() {
        String[] tipos = {"Propietario", "Estudiante"};
        int tipoIdx = JOptionPane.showOptionDialog(null, "¿Qué tipo de usuario es?",
                "Iniciar Sesión", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipoIdx == -1) return null;
        String correo = JOptionPane.showInputDialog(null, "Ingrese correo:", "Iniciar Sesión", JOptionPane.QUESTION_MESSAGE);
        if (correo == null) return null;
        String password = JOptionPane.showInputDialog(null, "Ingrese contraseña:", "Iniciar Sesión", JOptionPane.QUESTION_MESSAGE);
        if (password == null) return null;
        if (tipoIdx == 0) {
            Propietario p = controlProp.login(correo, password);
            if (p != null) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido, " + p.getNombre() + "!\nTipo: Propietario", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return new Object[]{"PROPIETARIO", p};
            }
        } else {
            Estudiante e = controlEst.login(correo, password);
            if (e != null) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido, " + e.getNombre() + "!\nTipo: Estudiante", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return new Object[]{"ESTUDIANTE", e};
            }
        }
        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Error de Login", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    
    private void registrar() {
        String[] tipos = {"Propietario", "Estudiante"};
        int tipoIdx = JOptionPane.showOptionDialog(null, "¿Qué tipo de usuario desea registrar?",
                "Registro", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipoIdx == -1) return;
        if (tipoIdx == 0) {
            registrarPropietario();
        } else {
            registrarEstudiante();
        }
    }
    
    private void registrarPropietario() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) return;
        String correo = JOptionPane.showInputDialog(null, "Ingrese correo:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (correo == null) return;
        String password = JOptionPane.showInputDialog(null, "Ingrese contraseña:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (password == null) return;
        String telefono = JOptionPane.showInputDialog(null, "Ingrese teléfono:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (telefono == null) return;
        String cedula = JOptionPane.showInputDialog(null, "Ingrese cédula:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (cedula == null) return;
        String direccion = JOptionPane.showInputDialog(null, "Ingrese dirección:", "Registro Propietario", JOptionPane.QUESTION_MESSAGE);
        if (direccion == null) return;
        boolean res = controlProp.guardar(id, nombre, correo, password, telefono, cedula, direccion);
        if (res) {
            JOptionPane.showMessageDialog(null, "Propietario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar propietario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarEstudiante() {
        String id = JOptionPane.showInputDialog(null, "Ingrese ID:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) return;
        String correo = JOptionPane.showInputDialog(null, "Ingrese correo:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (correo == null) return;
        String password = JOptionPane.showInputDialog(null, "Ingrese contraseña:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (password == null) return;
        String telefono = JOptionPane.showInputDialog(null, "Ingrese teléfono:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (telefono == null) return;
        String universidad = JOptionPane.showInputDialog(null, "Ingrese universidad:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (universidad == null) return;
        boolean res = controlEst.guardar(id, nombre, correo, password, telefono, universidad);
        if (res) {
            JOptionPane.showMessageDialog(null, "Estudiante registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}