package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.PedidoDAO;
import com.olympoburgers.backend.model.PedidoCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerPedidosClienteAction {

    @Autowired
    private PedidoDAO pedidoDAO;

    public ResponseEntity<?> ejecutar(String gmail) {
        try {
            List<PedidoCompleto> pedidos = pedidoDAO.obtenerPedidosCliente(gmail);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener pedidos");
        }
    }
}
