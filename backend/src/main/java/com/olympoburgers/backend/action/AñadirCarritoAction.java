package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.CarritoDAO;
import com.olympoburgers.backend.dto.CarritoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AñadirCarritoAction {

    @Autowired
    private CarritoDAO carritoDAO;

    public ResponseEntity<?> ejecutar(CarritoDTO dto) {
        try {
            carritoDAO.añadirAlCarrito(dto);
            return ResponseEntity.ok("Producto añadido o actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al añadir al carrito");
        }
    }
}
