package com.olympoburgers.backend.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarritoDTO {
    private String gmail;

    @JsonProperty("id_comida")
    private int idComida;

    @JsonProperty("precio_unitario")
    private double precioUnitario;

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public int getIdComida() { return idComida; }
    public void setIdComida(int idComida) { this.idComida = idComida; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}
