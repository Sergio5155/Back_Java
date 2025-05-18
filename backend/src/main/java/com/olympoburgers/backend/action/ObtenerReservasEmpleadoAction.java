package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ReservaDAO;
import com.olympoburgers.backend.model.ReservaCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerReservasEmpleadoAction {

    @Autowired
    private ReservaDAO reservaDAO;

    public ResponseEntity<?> ejecutar() {
        try {
            List<ReservaCliente> reservas = reservaDAO.obtenerTodasLasReservas();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener reservas");
        }
    }
}
