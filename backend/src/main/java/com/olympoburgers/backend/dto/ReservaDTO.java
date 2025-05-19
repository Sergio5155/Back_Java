package com.olympoburgers.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservaDTO {
    private String gmail;

    @JsonProperty("id_local")
    private int idLocal;
    private String fecha;   // formato YYYY-MM-DD
    private String turno;    // formato HH:MM
    private int personas;

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public int getIdLocal() { return idLocal; }
    public void setIdLocal(int idLocal) { this.idLocal = idLocal; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public int getPersonas() { return personas; }
    public void setPersonas(int personas) { this.personas = personas; }
}
