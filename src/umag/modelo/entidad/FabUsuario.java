package umag.modelo.entidad;

public class FabUsuario {    
    private static FabUsuario fabrica; 
    
    public static FabUsuario getFabrica() {
        if (fabrica == null) {
            fabrica = new FabUsuario();
        }
        return fabrica;
    }
   
    private FabUsuario() {
    
    }
    
    public Usuario getUsuario(String tipo) {
        switch (tipo.toUpperCase()) {
            case "PROPIETARIO":
                return new Propietario();
            case "ESTUDIANTE":
                return new Estudiante();
            default:
                System.out.println("Tipo de usuario desconocido: " + tipo);
                return null;
        }
    }
    
    public Usuario getUsuario(String tipo, String id, String nombre, String correo, String password, String telefono, String extra) {
        switch (tipo.toUpperCase()) {
            case "PROPIETARIO":
                return new Propietario(id, nombre, correo, password, telefono, extra, "");
            case "ESTUDIANTE":
                return new Estudiante(id, nombre, correo, password, telefono, extra);
            default:
                System.out.println("Tipo de usuario desconocido: " + tipo);
                return null;
        }
    }
}
