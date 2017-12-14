
package com.carmona.pillbox.Models;


public class Notificaciones {

    private int  id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String fecha;
    private String mensaje;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
