package com.olympoburgers.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModificarCarritoDTO {
    private String gmail;


    @JsonProperty("id_comida")
    private int idComida;

    @JsonProperty("cantidad")
    private int cantidad;

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public int getIdComida() { return idComida; }
    public void setIdComida(int idComida) { this.idComida = idComida; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
