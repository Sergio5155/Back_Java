package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.model.LocalConValoracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<LocalConValoracion> obtenerLocalesConValoracion() {
        String sql = """
    SELECT l.id_local, l.nombre, l.direccion, l.ciudad, l.telefono, l.foto,
           ROUND(AVG(r.estrellas)::numeric, 1) AS media_estrellas,
           COUNT(r.*) AS total_valoraciones
    FROM local l
    LEFT JOIN resena r ON l.id_local = r.id_local
    GROUP BY l.id_local, l.nombre, l.direccion, l.ciudad, l.telefono, l.foto
""";



        return jdbc.query(sql, (rs, rowNum) -> {
            LocalConValoracion l = new LocalConValoracion();
            l.setIdLocal(rs.getInt("id_local"));
            l.setNombre(rs.getString("nombre"));
            l.setDireccion(rs.getString("direccion"));
            l.setCiudad(rs.getString("ciudad"));
            l.setTelefono(rs.getString("telefono"));
            l.setFoto(rs.getString("foto"));
            l.setMediaEstrellas(rs.getDouble("media_estrellas"));
            l.setTotalValoraciones(rs.getInt("total_valoraciones"));
            return l;
        });
    }
}
