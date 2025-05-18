package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ResenaDAO;
import com.olympoburgers.backend.dto.ResenaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GuardarResenaAction {

    @Autowired
    private ResenaDAO resenaDAO;

    public ResponseEntity<?> ejecutar(ResenaDTO dto) {
        try {
            resenaDAO.guardarOActualizarResena(dto);
            return ResponseEntity.ok("Reseña guardada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar reseña");
        }
    }
}
