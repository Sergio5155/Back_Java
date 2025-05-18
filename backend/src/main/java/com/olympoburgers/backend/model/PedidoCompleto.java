package com.olympoburgers.backend.model;

import java.util.List;

public class PedidoCompleto {
    private int id;
    private String estado;
    private String fecha;
    private double total;
    private boolean descuentoAplicado;
    private List<LineaPedido> lineas;
    private String gmail;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(boolean descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }

    public List<LineaPedido> getLineas() { return lineas; }
    public void setLineas(List<LineaPedido> lineas) { this.lineas = lineas; }
    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
