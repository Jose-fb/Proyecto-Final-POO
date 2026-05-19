package com.proyecto.modelo;

public class Propietario extends Usuario {
    private String telefono;

    public Propietario(String id, String nombre, String correo, String password, String telefono) {
        super(id, nombre, correo, password, "PROPIETARIO");
        this.telefono = telefono;
    }

    public String getTelefono()
    { return telefono; }
    
    public void setTelefono(String telefono)
    { this.telefono = telefono; }
}
