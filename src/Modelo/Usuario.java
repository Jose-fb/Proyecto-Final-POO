package com.proyecto.modelo;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String correo;
    protected String password;
    protected String rol;
    protected List<String> bandejaMensajes; // FR27 y FR30

    public Usuario(String id, String nombre, String correo, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.bandejaMensajes = new ArrayList<>();
    }

    // FR4: Validar credenciales
    public boolean login(String correo, String password) {
        return this.correo.equals(correo) && this.password.equals(password);
    }

    // FR26: Enviar mensaje
    public void enviarMensaje(Usuario destino, String contenido) {
        destino.recibirMensaje("De: " + this.nombre + " - Mensaje: " + contenido);
    }

    // FR27: Recibir mensaje
    public void recibirMensaje(String mensajeCompleto) {
        this.bandejaMensajes.add(mensajeCompleto);
    }

    // FR28: Eliminar mensajes antiguos
    public void limpiarBandeja() {
        this.bandejaMensajes.clear();
    }

    // Getters y Setters
    public String getId()
    { return id; }
    
    public String getNombre()
    { return nombre; }
    
    public void setNombre(String nombre)
    { this.nombre = nombre; }
    
    public String getCorreo()
    { return correo; }
    
    public void setCorreo(String correo)
    { this.correo = correo; }
    
    public String getPassword()
    { return password; }
    
    public void setPassword(String password)
    { this.password = password; }
    
    public String getRol()
    { return rol; }
    
    public List<String> getBandejaMensajes()
    { return bandejaMensajes; }
}
