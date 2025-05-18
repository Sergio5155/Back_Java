package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ObtenerUsuarioAction {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public ResponseEntity<?> ejecutar(String gmail) {
        try {
            Map<String, Object> usuario = usuarioDAO.obtenerUsuarioPorGmail(gmail);
            if (usuario == null) {
                return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado"));
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al obtener usuario"));
        }
    }
}
