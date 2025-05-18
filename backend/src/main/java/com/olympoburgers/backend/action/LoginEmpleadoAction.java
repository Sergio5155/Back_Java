package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.UsuarioDAO;
import com.olympoburgers.backend.dto.LoginEmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginEmpleadoAction {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public ResponseEntity<?> ejecutar(LoginEmpleadoDTO dto) {
        if (dto.getGmail() == null || dto.getContrasena() == null) {
            return ResponseEntity.badRequest().body("Faltan campos");
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

        String rol = (String) user.get("rol");
        if (!"empleado".equalsIgnoreCase(rol)) {
            return ResponseEntity.status(403).body("Solo empleados pueden acceder");
        }

        return ResponseEntity.ok(Map.of(
                "message", "Login empleado exitoso",
                "user", Map.of(
                        "nombre", user.get("nombre"),
                        "gmail", user.get("gmail"),
                        "rol", user.get("rol")
                )
        ));
    }
}
