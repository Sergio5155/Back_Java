package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.ComidaDTO;
import com.olympoburgers.backend.model.Comida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComidaDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Comida> obtenerTodoElMenu() {
        String sql = "SELECT * FROM comida";
        return jdbc.query(sql, (rs, rowNum) -> {
            Comida c = new Comida();
            c.setId(rs.getInt("id"));
            c.setNombre(rs.getString("nombre"));
            c.setImagen(rs.getString("imagen"));
            c.setTipo(rs.getString("tipo"));
            c.setDescripcion(rs.getString("descripcion"));
            c.setAlergenos(rs.getString("alergenos"));
            c.setPrecio(rs.getDouble("precio"));
            return c;
        });
    }
    public void insertarProducto(ComidaDTO dto) {
        String sql = "INSERT INTO comida (nombre, imagen, tipo, descripcion, alergenos, precio) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                dto.getNombre(),
                dto.getImagen(),
                dto.getTipo(),
                dto.getDescripcion(),
                dto.getAlergenos(),  // puede ser null
                dto.getPrecio()
        );
    }
    public void actualizarProducto(int id, ComidaDTO dto) {
        String sql = "UPDATE comida SET nombre = ?, imagen = ?, tipo = ?, descripcion = ?, alergenos = ?, precio = ? WHERE id = ?";
        jdbc.update(sql,
                dto.getNombre(),
                dto.getImagen(),
                dto.getTipo(),
                dto.getDescripcion(),
                dto.getAlergenos(),
                dto.getPrecio(),
                id
        );
    }
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM comida WHERE id = ?";
        jdbc.update(sql, id);
    }


}
