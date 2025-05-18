package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.CarritoDAO;
import com.olympoburgers.backend.model.ItemCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerCarritoAction {

    @Autowired
    private CarritoDAO carritoDAO;

    public ResponseEntity<?> ejecutar(String gmail) {
        List<ItemCarrito> carrito = carritoDAO.obtenerCarrito(gmail);
        return ResponseEntity.ok(carrito);
    }
}
