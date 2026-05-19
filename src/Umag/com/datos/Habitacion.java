/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Umag.com.datos;

/**
 *
 * @author UNIMAGDALENA
 */
public class Habitacion {
    private int id;
    private String direccion;
    private double precio;
    private boolean disponible;
    private Propietario propietario;

    public Habitacion(int id, String direccion, double precio, Propietario propietario) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
        this.propietario = propietario;
        this.disponible = true;
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void actualizarPrecio(double precio) {
        this.precio = precio;
    }

    public double calcularCosto(int meses) {
        return precio * meses;
    }
}

