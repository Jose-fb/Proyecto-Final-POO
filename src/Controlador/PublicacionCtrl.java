package com.proyecto.controlador;

import com.proyecto.modelo.Publicacion;
import java.util.ArrayList;
import java.util.List;

public class PublicacionCtrl {
    private List<Publicacion> publicaciones;

    public PublicacionCtrl() {
        this.publicaciones = new ArrayList<>();
    }

    // ESTE ES EL MÉTODO QUE TE FALTA O TIENE OTRO NOMBRE
    public void crearPublicacion(Publicacion p) {
        this.publicaciones.add(p);
        System.out.println("Publicación creada con éxito.");
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    // Dentro de PublicacionCtrl.java
public Publicacion buscarPorId(String idBusqueda) {
    for (Publicacion p : publicaciones) {
        if (p.getId().equals(idBusqueda)) {
            return p;
        }
    }
    return null; // Si no la encuentra
}
// Para filtrar por presupuesto máximo (FR15)
public List<Publicacion> filtrarPorPrecio(double precioMax) {
    List<Publicacion> filtradas = new ArrayList<>();
    for (Publicacion p : publicaciones) {
        if (p.getHabitacion().getPrecio() <= precioMax && p.getHabitacion().isDisponible()) {
            filtradas.add(p);
        }
    }
    return filtradas;
}
}