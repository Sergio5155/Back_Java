package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.CarritoDAO;
import com.olympoburgers.backend.dto.ModificarCarritoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ModificarCarritoAction {

    @Autowired
    private CarritoDAO carritoDAO;

    public ResponseEntity<?> ejecutar(ModificarCarritoDTO dto) {
        try {
            carritoDAO.modificarCantidad(dto);
            return ResponseEntity.ok("Cantidad actualizada");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al actualizar carrito");
        }
    }
}
