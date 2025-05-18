package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ComidaDAO;
import com.olympoburgers.backend.dto.ComidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AñadirProductoAction {

    @Autowired
    private ComidaDAO comidaDAO;

    public ResponseEntity<?> ejecutar(ComidaDTO dto) {
        try {
            if (dto.getNombre() == null || dto.getImagen() == null || dto.getTipo() == null || dto.getDescripcion() == null) {
                return ResponseEntity.badRequest().body("Todos los campos excepto alérgenos son obligatorios");
            }

            comidaDAO.insertarProducto(dto);
            return ResponseEntity.status(201).body("Producto añadido correctamente");

        } catch (Exception e) {
            e.printStackTrace(); // 🧨 Esto imprime el error en consola
            return ResponseEntity.status(500).body("Error interno al añadir producto");
        }
    }

}
