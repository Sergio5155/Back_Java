package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ComidaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EliminarProductoAction {

    @Autowired
    private ComidaDAO comidaDAO;

    public ResponseEntity<?> ejecutar(int id) {
        comidaDAO.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
