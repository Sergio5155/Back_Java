package com.olympoburgers.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResenaDTO {

    @JsonProperty("gmail")
    private String gmail;

    @JsonProperty("id_local")
    private int idLocal;

    @JsonProperty("estrellas")
    private int estrellas;


    @JsonProperty("comentario")
    private String comentario;

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public int getIdLocal() { return idLocal; }
    public void setIdLocal(int idLocal) { this.idLocal = idLocal; }

    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
