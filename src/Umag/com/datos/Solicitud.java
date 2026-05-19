/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.com.datos;

/**
 *
 * @author UNIMAGDALENA
 */
public class Solicitud {
    private int id;
    private Estudiante estudiante;
    private Habitacion habitacion;
    private String estado;

    public Solicitud(int id, Estudiante estudiante, Habitacion habitacion) {
        this.id = id;
        this.estudiante = estudiante;
        this.habitacion = habitacion;
        this.estado = "pendiente";
    }

    public int getId() 
    {
        return id; }
    public String getEstado()
    {
        return estado; }
    public Habitacion getHabitacion()
    {
        return habitacion; }

    public void aceptar() {
        setEstado("aceptada");
        getHabitacion().setDisponible(false);
    }

    public void rechazar() {
        setEstado("rechazada");
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @param habitacion the habitacion to set
     */
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}

