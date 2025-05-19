package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.ReservaDTO;
import com.olympoburgers.backend.excepciones.PlazasInsuficientesException;
import com.olympoburgers.backend.model.ReservaCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.olympoburgers.backend.model.ReservaCliente;
import java.util.List;

@Repository
public class ReservaDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void crearReserva(ReservaDTO dto) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                dto.getGmail()
        );

        Integer total = jdbc.queryForObject(
                "SELECT COALESCE(SUM(personas), 0) FROM reserva WHERE id_local = ? AND fecha = CAST(? AS DATE) AND turno = ?",
                Integer.class,
                dto.getIdLocal(), dto.getFecha(), java.sql.Time.valueOf(dto.getTurno() + ":00")

        );

        if (total + dto.getPersonas() > 20) {
            int disponibles = 20 - total;
            throw new PlazasInsuficientesException("Solo quedan " + disponibles + " plazas disponibles para ese turno");
        }



        jdbc.update(
                "INSERT INTO reserva (id_usuario, id_local, fecha, turno, personas) VALUES (?, ?, ?, ?, ?)",
                idUsuario, dto.getIdLocal(), java.sql.Date.valueOf(dto.getFecha())
                , java.sql.Time.valueOf(dto.getTurno() + ":00")
                , dto.getPersonas()
        );
    }


    public List<ReservaCliente> obtenerReservasPorGmail(String gmail) {
        String sql = """
        SELECT r.id_reserva, l.nombre, l.ciudad, r.fecha, r.turno, r.personas
        FROM reserva r
        JOIN usuarios u ON r.id_usuario = u.id
        JOIN local l ON r.id_local = l.id_local
        WHERE u.gmail = ?
        ORDER BY r.fecha, r.turno
    """;

        return jdbc.query(sql, (rs, rowNum) -> {
            ReservaCliente rc = new ReservaCliente();
            rc.setIdReserva(rs.getInt("id_reserva"));
            rc.setNombreLocal(rs.getString("nombre"));
            rc.setCiudad(rs.getString("ciudad"));
            rc.setFecha(rs.getDate("fecha").toString());
            rc.setTurno(rs.getTime("turno").toString());
            rc.setPersonas(rs.getInt("personas"));
            return rc;
        }, gmail);
    }
    public List<ReservaCliente> obtenerTodasLasReservas() {
        String sql = """
      SELECT r.id_reserva, l.nombre AS nombre_local, l.ciudad, r.fecha, r.turno, r.personas, u.nombre AS nombre_usuario
                  FROM reserva r
                  JOIN local l ON r.id_local = l.id_local
                  JOIN usuarios u ON r.id_usuario = u.id
                  ORDER BY r.fecha DESC
    """;

        return jdbc.query(sql, (rs, rowNum) -> {
            ReservaCliente rc = new ReservaCliente();
            rc.setIdReserva(rs.getInt("id_reserva"));
            rc.setNombreLocal(rs.getString("nombre_local"));
            rc.setCiudad(rs.getString("ciudad"));
            rc.setFecha(rs.getDate("fecha").toString());
            rc.setTurno(rs.getTime("turno").toString());
            rc.setPersonas(rs.getInt("personas"));
            rc.setNombreUsuario(rs.getString("nombre_usuario")); // 👈 esta línea faltaba

            return rc;
        });
    }
    public void cancelarReserva(int idReserva) {
        jdbc.update("DELETE FROM reserva WHERE id_reserva = ?", idReserva);
    }

}
