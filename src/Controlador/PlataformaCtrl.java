package com.proyecto.controlador;
import com.proyecto.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class PlataformaCtrl {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Solicitud> solicitudes = new ArrayList<>();

    // FR1: Registrar Usuarios
    public void registrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    // FR14: Listar publicaciones activas
    public List<Publicacion> obtenerPublicacionesActivas() {
        List<Publicacion> activas = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (p.getHabitacion().isDisponible()) {
                activas.add(p);
            }
        }
        return activas;
    }
    
    public void agregarPublicacion(Publicacion p) {
        publicaciones.add(p);
    }
    
    public void agregarSolicitud(Solicitud s) {
        solicitudes.add(s);
    }
}