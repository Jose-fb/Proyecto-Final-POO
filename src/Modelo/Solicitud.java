package com.proyecto.modelo;

import java.util.Date;

public class Solicitud {
    private String id;
    private String estado; // PENDIENTE, ACEPTADA, RECHAZADA
    private Estudiante estudiante;
    private Publicacion publicacion;
    private Date fecha;

    public Solicitud(String id, Estudiante estudiante, Publicacion publicacion) {
        this.id = id;
        this.estudiante = estudiante;
        this.publicacion = publicacion;
        this.estado = "PENDIENTE";
        this.fecha = new Date();
    }

    // FR18: Lógica de negocio - Cambia disponibilidad automáticamente
    public void aprobarSolicitud() {
        this.estado = "ACEPTADA";
        // Al aprobar, la habitación de esa publicación deja de estar disponible
        this.publicacion.getHabitacion().setDisponible(false);
    }

    public void rechazarSolicitud() {
        this.estado = "RECHAZADA";
    }

    // Getters
    public String getId() { return id; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public Publicacion getPublicacion() { return publicacion; }
}