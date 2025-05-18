package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ComidaDAO;
import com.olympoburgers.backend.dto.ComidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EditarProductoAction {

    @Autowired
    private ComidaDAO comidaDAO;

    public ResponseEntity<?> ejecutar(int id, ComidaDTO dto) {
        if (dto.getNombre() == null || dto.getImagen() == null || dto.getTipo() == null || dto.getDescripcion() == null) {
            return ResponseEntity.badRequest().body("Todos los campos excepto alérgenos son obligatorios");
        }

        comidaDAO.actualizarProducto(id, dto);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }
}
