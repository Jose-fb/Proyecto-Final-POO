/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.com.datos;

/**
 *
 * @author UNIMAGDALENA
 */
public class Calificacion {
    private Estudiante estudiante;
    private Propietario propietario;
    private int puntuacion;
    private String comentario;

    public Calificacion(Estudiante estudiante, Propietario propietario, int puntuacion, String comentario) {
        this.estudiante = estudiante;
        this.propietario = propietario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    /**
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


}

