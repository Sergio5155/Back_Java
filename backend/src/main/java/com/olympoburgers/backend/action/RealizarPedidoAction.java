package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.PedidoDAO;
import com.olympoburgers.backend.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RealizarPedidoAction {

    @Autowired
    private PedidoDAO pedidoDAO;

    public ResponseEntity<?> ejecutar(PedidoDTO dto) {
        try {
            pedidoDAO.realizarPedido(dto);
            return ResponseEntity.ok("Pedido realizado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el pedido");
        }
    }
}
