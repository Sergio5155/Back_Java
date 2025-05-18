package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CancelarReservaAction {

    @Autowired
    private ReservaDAO reservaDAO;

    public ResponseEntity<?> ejecutar(int idReserva) {
        try {
            reservaDAO.cancelarReserva(idReserva);
            return ResponseEntity.ok("Reserva cancelada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al cancelar la reserva");
        }
    }
}
