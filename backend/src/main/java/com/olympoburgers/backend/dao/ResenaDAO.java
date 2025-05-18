package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.ResenaDTO;
import com.olympoburgers.backend.model.Resena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResenaDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Resena> obtenerResenasPorLocal(int idLocal) {
        String sql = """
            SELECT r.estrellas, r.comentario, u.nombre
            FROM resena r
            JOIN usuarios u ON r.id_usuario = u.id
            WHERE r.id_local = ?
            ORDER BY r.fecha DESC
        """;

        return jdbc.query(sql, (rs, rowNum) -> {
            Resena r = new Resena();
            r.setEstrellas(rs.getInt("estrellas"));
            r.setComentario(rs.getString("comentario"));
            r.setNombre(rs.getString("nombre"));
            return r;
        }, idLocal);
    }
    public void guardarOActualizarResena(ResenaDTO dto) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                dto.getGmail()
        );

        String sql = """
        INSERT INTO resena (id_usuario, id_local, estrellas, comentario, fecha)
        VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
        ON CONFLICT (id_usuario, id_local)
        DO UPDATE SET estrellas = EXCLUDED.estrellas,
                      comentario = EXCLUDED.comentario,
                      fecha = CURRENT_TIMESTAMP
    """;

        jdbc.update(sql, idUsuario, dto.getIdLocal(), dto.getEstrellas(), dto.getComentario());
    }

}
