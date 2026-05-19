package modelo;

import com.proyecto.modelo.Estudiante;
import com.proyecto.modelo.Propietario;

public class Calificacion {
    private Estudiante estudiante;
    private Propietario propietario;
    private int puntaje;
    private String comentario;

    public Calificacion(Estudiante estudiante, Propietario propietario, int puntaje, String comentario) {
        this.estudiante = estudiante;
        this.propietario = propietario;
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Calificación: " + estudiante.getNombre() + " → " + propietario.getNombre() +
               " [" + puntaje + "] " + comentario;
    }
}

