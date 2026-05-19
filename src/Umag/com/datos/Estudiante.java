
package Umag.com.datos;

import java.util.ArrayList;
import java.util.List;

class Estudiante extends Usuario {
private String carrera;
private int edad;
    private List<Solicitud> solicitudes;

        public Estudiante(String carrera, int edad, List<Solicitud> solicitudes, int id, String nombre, String email, String contraseña) {
            super(id, nombre, email, contraseña);
            this.carrera = carrera;
            this.edad = edad;
            this.solicitudes = solicitudes;
        }

    public Solicitud solicitarHabitacion(Habitacion h) {
        Solicitud s = new Solicitud(getSolicitudes().size() + 1, this, h);
        getSolicitudes().add(s);
        return s;
    }

    public List<Habitacion> filtrarPorPrecio(List<Habitacion> lista, double maxPrecio) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion h : lista) {
            if (h.getPrecio() <= maxPrecio && h.isDisponible()) {
                resultado.add(h);
            }
        }
        return resultado;
    }

    public void verSolicitudes() {
        for (Solicitud s : getSolicitudes()) {
            System.out.println("Solicitud " + s.getId() + " → " + s.getEstado());
        }
    }

    public void calificar(Propietario p, int puntuacion, String comentario) {
        Calificacion c = new Calificacion(this, p, puntuacion, comentario);
        System.out.println("Calificación enviada: " + c.getPuntuacion());
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the solicitudes
     */
    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    /**
     * @param solicitudes the solicitudes to set
     */
    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
    
    
}


