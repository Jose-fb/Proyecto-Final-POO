package com.proyecto.modelo;

public class Estudiante extends Usuario {
    private String universidad;

    public Estudiante(String id, String nombre, String correo, String password, String universidad) {
        super(id, nombre, correo, password, "ESTUDIANTE");
        this.universidad = universidad;
    }

    // FR16: El estudiante crea una instancia de Solicitud
    public Solicitud solicitarArriendo(String idSol, Publicacion pub) {
        return new Solicitud(idSol, this, pub);
    }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }
}


