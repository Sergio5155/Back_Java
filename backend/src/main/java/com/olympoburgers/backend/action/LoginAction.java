package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.UsuarioDAO;
import com.olympoburgers.backend.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginAction {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public ResponseEntity<?> ejecutar(LoginDTO dto) {
        if (dto.getGmail() == null || dto.getContrasena() == null) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios");
        }

        Map<String, Object> user = usuarioDAO.buscarPorGmail(dto.getGmail());
        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String hash = (String) user.get("contrasena");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(dto.getContrasena(), hash)) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        return ResponseEntity.ok(Map.of(
                "message", "Login exitoso",
                "user", Map.of(
                        "nombre", user.get("nombre"),
                        "gmail", user.get("gmail"),
                        "rol", user.get("rol")
                )
        ));
    }
}
