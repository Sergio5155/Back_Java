package com.olympoburgers.backend.model;

public class ReservaCliente {
    private int idReserva;
    private String nombreLocal;
    private String ciudad;
    private String fecha;
    private String turno;
    private int personas;

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public String getNombreLocal() { return nombreLocal; }
    public void setNombreLocal(String nombreLocal) { this.nombreLocal = nombreLocal; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public int getPersonas() { return personas; }
    public void setPersonas(int personas) { this.personas = personas; }
}
