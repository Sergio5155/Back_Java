package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.LocalDAO;
import com.olympoburgers.backend.model.LocalConValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerLocalesAction {

    @Autowired
    private LocalDAO localDAO;

    public ResponseEntity<?> ejecutar() {
        try {
            List<LocalConValoracion> lista = localDAO.obtenerLocalesConValoracion();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener locales");
        }
    }
}
