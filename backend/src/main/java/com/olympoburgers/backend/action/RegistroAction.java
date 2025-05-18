package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.UsuarioDAO;
import com.olympoburgers.backend.dto.RegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegistroAction {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public ResponseEntity<?> ejecutar(RegistroDTO dto) {
        if (dto.getNombre() == null || dto.getGmail() == null || dto.getContrasena() == null) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }

        if (dto.getContrasena().length() < 8) {
            return ResponseEntity.badRequest().body("La contraseña debe tener al menos 8 caracteres");
        }

        if (usuarioDAO.existePorGmail(dto.getGmail())) {
            return ResponseEntity.status(409).body("Ese correo ya está registrado");
        }

        usuarioDAO.registrar(dto);
        return ResponseEntity
                .status(201)
                .body(Map.of("message", "Registro exitoso"));

    }
}
