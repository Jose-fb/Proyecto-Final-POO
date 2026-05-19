package com.proyecto.modelo;

public class Habitacion {
    private String id;
    private String direccion;
    private double precio;
    private boolean disponible;

    public Habitacion(String id, String direccion, double precio, boolean disponible) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.disponible = disponible;
    }

 

    // Getters y Setters
    public String getId() { return id; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
