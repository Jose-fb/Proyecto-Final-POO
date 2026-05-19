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

class Propietario extends Usuario {

    private String telefono;
    private List<Habitacion> habitaciones;

    public Propietario(int id, String nombre, String email, String contraseña, String telefono) {
        super(id, nombre, email, contraseña);
        this.telefono = telefono;
        this.habitaciones = new ArrayList<>();
    }

    public Habitacion publicarHabitacion(int id, String direccion, double precio) {
        Habitacion h = new Habitacion(id, direccion, precio, this);
        habitaciones.add(h);
        return h;
    }

    public void editarHabitacion(Habitacion h, double nuevoPrecio) {
        h.actualizarPrecio(nuevoPrecio);
    }

    public void eliminarHabitacion(Habitacion h) {
        habitaciones.remove(h);
    }

    public void responderSolicitud(Solicitud s, String respuesta) {
        if (respuesta.equalsIgnoreCase("aceptada")) {
            s.aceptar();
        } else {
            s.rechazar();
        }
    }
}