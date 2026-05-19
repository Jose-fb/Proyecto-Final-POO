/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.com.datos;

/**
 *
 * @author UNIMAGDALENA
 */
import java.util.ArrayList;
import java.util.List;

class Plataforma {

    private String nombre;

    private List<Usuario> usuarios;
    private List<Publicacion> publicaciones;
    private List<Solicitud> solicitudes;
    private List<Calificacion> calificaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    // 🔹 USUARIOS
    public void registrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    // 🔹 PUBLICACIONES
    public void agregarPublicacion(Publicacion p) {
        publicaciones.add(p);
    }

    public List<Publicacion> listarPublicaciones() {
        return publicaciones;
    }

    // 🔹 SOLICITUDES
    public void agregarSolicitud(Solicitud s) {
        solicitudes.add(s);
    }

    public List<Solicitud> listarSolicitudes() {
        return solicitudes;
    }

    // 🔹 CALIFICACIONES
    public void agregarCalificacion(Calificacion c) {
        calificaciones.add(c);
    }

    public List<Calificacion> listarCalificaciones() {
        return calificaciones;
    }

    // 🔹 MÉTODO GENERAL
    public void mostrarResumen() {
        System.out.println("Plataforma: " + nombre);
        System.out.println("Usuarios: " + usuarios.size());
        System.out.println("Publicaciones: " + publicaciones.size());
        System.out.println("Solicitudes: " + solicitudes.size());
    }
}