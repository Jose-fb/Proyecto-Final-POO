package com.proyecto.controlador;

import com.proyecto.modelo.Solicitud;
import java.util.ArrayList;
import java.util.List;

public class SolicitudCtrl {
    private List<Solicitud> solicitudes;

    public SolicitudCtrl() {
        this.solicitudes = new ArrayList<>();
    }

    // FR16: Registrar solicitud enviada por el estudiante
    public void registrarSolicitud(Solicitud s) {
        this.solicitudes.add(s);
    }

    // FR17: El propietario acepta la solicitud por ID
    public boolean aceptarSolicitud(String idBusqueda) {
        for (Solicitud s : solicitudes) {
            if (s.getId().equals(idBusqueda)) {
                s.aprobarSolicitud(); // Llama al FR18 del modelo
                return true;
            }
        }
        return false;
    }

    public List<Solicitud> getTodasLasSolicitudes() {
        return solicitudes;
    }
}