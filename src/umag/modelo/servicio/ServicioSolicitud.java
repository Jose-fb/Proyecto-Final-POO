package umag.modelo.servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import umag.modelo.entidad.Estudiante;
import umag.modelo.entidad.Publicacion;
import umag.modelo.entidad.Solicitud;
import umag.modelo.persistencia.DaoSolicitud;

public class ServicioSolicitud {
    
    DaoSolicitud dao = new DaoSolicitud();

    public boolean guardar(String id, Estudiante estudiante, Publicacion publicacion, String mensaje) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: id vacío.");
            return false;
        }
        if (estudiante == null) {
            System.out.println("Error: estudiante no puede ser nulo.");
            return false;
        }
        if (publicacion == null) {
            System.out.println("Error: publicación no puede ser nula.");
            return false;
        }
        if (!publicacion.isActiva()) {
            System.out.println("Error: la publicación no está activa.");
            return false;
        }
        if (mensaje == null || mensaje.trim().isEmpty()) {
            System.out.println("Error: el mensaje no puede estar vacío.");
            return false;
        }
        try {
            Solicitud s = new Solicitud(id, estudiante, publicacion, mensaje);
            dao.guardar(s);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar solicitud: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean borrar(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: id vacío.");
            return false;
        }
        try {
            dao.borrar(id);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al borrar solicitud: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean aceptar(String idSolicitud) {
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getId().equals(idSolicitud)) {
                    if (!s.getEstado().equals(Solicitud.ESTADO_PENDIENTE)) {
                        System.out.println("Solo se pueden aceptar solicitudes PENDIENTES.");
                        return false;
                    }
                    s.aceptar();
                    return dao.guardar(s);
                }
            }
            System.out.println("Solicitud no encontrada: " + idSolicitud);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al aceptar solicitud: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean rechazar(String idSolicitud, String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            System.out.println("Error: debe indicar un motivo de rechazo.");
            return false;
        } try {
            for (Solicitud s : dao.listar()) {
                if (s.getId().equals(idSolicitud)) {
                    if (!s.getEstado().equals(Solicitud.ESTADO_PENDIENTE)) {
                        System.out.println("Solo se pueden rechazar solicitudes PENDIENTES.");
                        return false;
                    }
                    s.rechazar(motivo);
                    return dao.guardar(s);
                }
            }
            System.out.println("Solicitud no encontrada: " + idSolicitud);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al rechazar solicitud: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean cancelar(String idSolicitud) {
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getId().equals(idSolicitud)) {
                    if (!s.getEstado().equals(Solicitud.ESTADO_PENDIENTE)) {
                        System.out.println("Solo se pueden cancelar solicitudes PENDIENTES.");
                        return false;
                    }
                    s.cancelar();
                    return dao.guardar(s);
                }
            }
            System.out.println("Solicitud no encontrada: " + idSolicitud);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al cancelar solicitud: " + ex.getMessage());
        }
        return false;
    }
    
    public List<Solicitud> listar() {
        try {
            return dao.listar();
        } catch (IOException ex) {
            System.out.println("Error al listar solicitudes: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: " + ex.getMessage());
        }
        return new ArrayList<>();
    }
    
    public List<Solicitud> listarPorEstado(String estado) {
        List<Solicitud> resultado = new ArrayList<>();
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getEstado().equals(estado)) {
                    resultado.add(s);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al listar por estado: " + ex.getMessage());
        }
        return resultado;
    }
    
    public List<Solicitud> listarPorPublicacion(String idPublicacion) {
        List<Solicitud> resultado = new ArrayList<>();
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getPublicacion() != null && s.getPublicacion().getId().equals(idPublicacion)) {
                    resultado.add(s);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al listar por publicación: " + ex.getMessage());
        }
        return resultado;
    }
    
    public List<Solicitud> listarPorEstudiante(String idEstudiante) {
        List<Solicitud> resultado = new ArrayList<>();
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getEstudiante() != null && s.getEstudiante().getId().equals(idEstudiante)) {
                    resultado.add(s);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al listar por estudiante: " + ex.getMessage());
        }
        return resultado;
    }
    
    public Solicitud buscarPorId(String id) {
        try {
            for (Solicitud s : dao.listar()) {
                if (s.getId().equals(id)) {
                    return s;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al buscar solicitud: " + ex.getMessage());
        }
        return null;
    }
}