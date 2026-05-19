package com.proyecto.modelo;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private String id;
    private String descripcion;
    private Habitacion habitacion;
    private Propietario propietario;
    private List<String> fotos;

    public Publicacion(String id, String descripcion, Habitacion habitacion, Propietario propietario) {
        this.id = id;
        this.descripcion = descripcion;
        this.habitacion = habitacion;
        this.propietario = propietario;
        this.fotos = new ArrayList<>();
    }

    public void agregarFoto(String url) { this.fotos.add(url); }

    // Getters
    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Habitacion getHabitacion() { return habitacion; }
    public Propietario getPropietario() { return propietario; }
}