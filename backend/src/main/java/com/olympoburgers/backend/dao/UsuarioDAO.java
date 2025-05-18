package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.RegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean existePorGmail(String gmail) {
        try {
            Integer count = jdbc.queryForObject(
                    "SELECT COUNT(*) FROM usuarios WHERE gmail = ?",
                    Integer.class,
                    gmail
            );
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void registrar(RegistroDTO dto) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hash = encoder.encode(dto.getContrasena());

            jdbc.update(
                    "INSERT INTO usuarios (nombre, gmail, contrasena, rol) VALUES (?, ?, ?, ?)",
                    dto.getNombre(),
                    dto.getGmail(),
                    hash,
                    "cliente"
            );

            System.out.println("✅ Usuario registrado con éxito: " + dto.getGmail());

        } catch (Exception e) {
            System.err.println("❌ Error en registrar(): " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Map<String, Object> buscarPorGmail(String gmail) {
        String sql = "SELECT * FROM usuarios WHERE gmail = ?";
        try {
            return jdbc.queryForMap(sql, gmail);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Map<String, Object> obtenerUsuarioPorGmail(String gmail) {
        String sql = "SELECT id, codigo_descuento_utilizado FROM usuarios WHERE gmail = ?";
        List<Map<String, Object>> resultados = jdbc.queryForList(sql, gmail);
        return resultados.isEmpty() ? null : resultados.get(0);
    }


}
