package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.CarritoDTO;
import com.olympoburgers.backend.dto.ModificarCarritoDTO;
import com.olympoburgers.backend.model.ItemCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarritoDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<ItemCarrito> obtenerCarrito(String gmail) {
        String sql = """
            SELECT c.id_comida, c.cantidad, co.nombre, co.imagen, co.precio
            FROM carrito c
            JOIN comida co ON c.id_comida = co.id
            JOIN usuarios u ON c.id_usuario = u.id
            WHERE u.gmail = ?
        """;

        return jdbc.query(sql, (rs, rowNum) -> {
            ItemCarrito item = new ItemCarrito();
            item.setIdComida(rs.getInt("id_comida"));
            item.setCantidad(rs.getInt("cantidad"));
            item.setNombre(rs.getString("nombre"));
            item.setImagen(rs.getString("imagen"));
            item.setPrecio(rs.getDouble("precio"));
            return item;
        }, gmail);
    }
    public void añadirAlCarrito(CarritoDTO dto) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                dto.getGmail()
        );

        String sql = """
        INSERT INTO carrito (id_usuario, id_comida, cantidad, precio_unitario)
        VALUES (?, ?, 1, ?)
        ON CONFLICT (id_usuario, id_comida)
        DO UPDATE SET cantidad = carrito.cantidad + 1
    """;

        jdbc.update(sql, idUsuario, dto.getIdComida(), dto.getPrecioUnitario());
    }
    public void modificarCantidad(ModificarCarritoDTO dto) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                dto.getGmail()
        );

        if (dto.getCantidad() <= 0) {
            jdbc.update("DELETE FROM carrito WHERE id_usuario = ? AND id_comida = ?", idUsuario, dto.getIdComida());
        } else {
            jdbc.update("UPDATE carrito SET cantidad = ? WHERE id_usuario = ? AND id_comida = ?",
                    dto.getCantidad(), idUsuario, dto.getIdComida());
        }
    }


}
