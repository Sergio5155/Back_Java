package com.olympoburgers.backend.dao;

import com.olympoburgers.backend.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.olympoburgers.backend.model.PedidoCompleto;
import com.olympoburgers.backend.model.LineaPedido;

import java.util.List;
import java.util.Map;

@Repository
public class PedidoDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void realizarPedido(PedidoDTO dto) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                dto.getGmail()
        );

        List<Map<String, Object>> carrito = jdbc.queryForList(
                "SELECT id_comida, cantidad, precio_unitario FROM carrito WHERE id_usuario = ?",
                idUsuario
        );

        if (carrito.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        double total = carrito.stream().mapToDouble(item ->
                ((int) item.get("cantidad")) * ((Number) item.get("precio_unitario")).doubleValue()
        ).sum();

        if (dto.isDescuento()) {
            total *= 0.9;
        }

        // Insertar pedido
        Map<String, Object> pedidoInsertado = jdbc.queryForMap(
                "INSERT INTO pedidos (id_usuario, total, estado, descuento_aplicado) VALUES (?, ?, 'en entrega', ?) RETURNING id",
                idUsuario, total, dto.isDescuento()
        );
        int idPedido = (int) pedidoInsertado.get("id");

        // Insertar líneas de pedido
        for (Map<String, Object> item : carrito) {
            jdbc.update("INSERT INTO lineas_pedido (id_pedido, id_comida, cantidad, precio_unitario) VALUES (?, ?, ?, ?)",
                    idPedido,
                    item.get("id_comida"),
                    item.get("cantidad"),
                    item.get("precio_unitario")
            );
        }

        // Vaciar carrito
        jdbc.update("DELETE FROM carrito WHERE id_usuario = ?", idUsuario);

        // Marcar descuento como usado si aplica
        if (dto.isDescuento()) {
            jdbc.update("UPDATE usuarios SET codigo_descuento_utilizado = TRUE WHERE id = ?", idUsuario);
        }
    }
    public List<PedidoCompleto> obtenerPedidosCliente(String gmail) {
        Integer idUsuario = jdbc.queryForObject(
                "SELECT id FROM usuarios WHERE gmail = ?",
                Integer.class,
                gmail
        );

        List<PedidoCompleto> pedidos = jdbc.query(
                "SELECT * FROM pedidos WHERE id_usuario = ? ORDER BY fecha DESC",
                (rs, rowNum) -> {
                    PedidoCompleto p = new PedidoCompleto();
                    p.setId(rs.getInt("id"));
                    p.setEstado(rs.getString("estado"));
                    p.setFecha(rs.getTimestamp("fecha").toString());
                    p.setTotal(rs.getDouble("total"));
                    p.setDescuentoAplicado(rs.getBoolean("descuento_aplicado"));
                    return p;
                },
                idUsuario
        );

        for (PedidoCompleto pedido : pedidos) {
            List<LineaPedido> lineas = jdbc.query(
                    """
                    SELECT lp.cantidad, lp.precio_unitario, c.nombre
                    FROM lineas_pedido lp
                    JOIN comida c ON lp.id_comida = c.id
                    WHERE lp.id_pedido = ?
                    """,
                    (rs, rowNum) -> {
                        LineaPedido l = new LineaPedido();
                        l.setCantidad(rs.getInt("cantidad"));
                        l.setPrecioUnitario(rs.getDouble("precio_unitario"));
                        l.setNombre(rs.getString("nombre"));
                        return l;
                    },
                    pedido.getId()
            );
            pedido.setLineas(lineas);
        }

        return pedidos;
    }
    public List<PedidoCompleto> obtenerTodosLosPedidos() {
        List<PedidoCompleto> pedidos = jdbc.query(
                """
                SELECT p.*, u.gmail
                FROM pedidos p
                JOIN usuarios u ON p.id_usuario = u.id
                ORDER BY p.fecha DESC
                """,
                (rs, rowNum) -> {
                    PedidoCompleto p = new PedidoCompleto();
                    p.setId(rs.getInt("id"));
                    p.setEstado(rs.getString("estado"));
                    p.setFecha(rs.getTimestamp("fecha").toString());
                    p.setTotal(rs.getDouble("total"));
                    p.setDescuentoAplicado(rs.getBoolean("descuento_aplicado"));
                    p.setGmail(rs.getString("gmail"));
                    return p;
                }
        );

        for (PedidoCompleto pedido : pedidos) {
            List<LineaPedido> lineas = jdbc.query(
                    """
                    SELECT lp.cantidad, lp.precio_unitario, c.nombre
                    FROM lineas_pedido lp
                    JOIN comida c ON lp.id_comida = c.id
                    WHERE lp.id_pedido = ?
                    """,
                    (rs, rowNum) -> {
                        LineaPedido l = new LineaPedido();
                        l.setCantidad(rs.getInt("cantidad"));
                        l.setPrecioUnitario(rs.getDouble("precio_unitario"));
                        l.setNombre(rs.getString("nombre"));
                        return l;
                    },
                    pedido.getId()
            );
            pedido.setLineas(lineas);
        }

        return pedidos;
    }
    public String entregarPedido(int idPedido) {
        // Cambiar estado
        jdbc.update("UPDATE pedidos SET estado = ? WHERE id = ?", "entregado", idPedido);

        // Obtener gmail
        return jdbc.queryForObject(
                """
                SELECT u.gmail
                FROM pedidos p
                JOIN usuarios u ON p.id_usuario = u.id
                WHERE p.id = ?
                """,
                String.class,
                idPedido
        );
    }

}
