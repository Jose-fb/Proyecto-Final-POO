/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.com.datos;

    import java.util.ArrayList;
import java.util.List;

public class Publicacion {

    private int id;
    private String titulo;
    private String descripcion;
    private Habitacion habitacion;
    private Propietario propietario;
    private boolean activa;

    private List<Solicitud> solicitudes;

    public Publicacion(int id, String titulo, String descripcion, Habitacion habitacion, Propietario propietario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.habitacion = habitacion;
        this.propietario = propietario;
        this.activa = true;
        this.solicitudes = new ArrayList<>();
    }

    // 🔹 Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Habitacion getHabitacion() { return habitacion; }
    public boolean isActiva() { return activa; }

    // 🔹 Métodos clave

    public void agregarSolicitud(Solicitud s) {
        solicitudes.add(s);
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void desactivar() {
        this.activa = false;
    }

    public void mostrarPublicacion() {
        System.out.println("Publicación: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: " + habitacion.getPrecio());
        System.out.println("Disponible: " + habitacion.isDisponible());
    }
}

