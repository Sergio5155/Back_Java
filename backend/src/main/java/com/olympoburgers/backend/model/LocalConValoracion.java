package com.olympoburgers.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalConValoracion {

    @JsonProperty("id_local")
    private int idLocal;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String foto;
    private double mediaEstrellas;
    private int totalValoraciones;

    // Getters y setters
    public int getIdLocal() { return idLocal; }
    public void setIdLocal(int idLocal) { this.idLocal = idLocal; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public double getMediaEstrellas() { return mediaEstrellas; }
    public void setMediaEstrellas(double mediaEstrellas) { this.mediaEstrellas = mediaEstrellas; }

    public int getTotalValoraciones() { return totalValoraciones; }
    public void setTotalValoraciones(int totalValoraciones) { this.totalValoraciones = totalValoraciones; }
}
