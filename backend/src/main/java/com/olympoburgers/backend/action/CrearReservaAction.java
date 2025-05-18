package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ReservaDAO;
import com.olympoburgers.backend.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CrearReservaAction {

    @Autowired
    private ReservaDAO reservaDAO;

    public ResponseEntity<?> ejecutar(ReservaDTO dto) {
        try {
            if (dto.getPersonas() < 1 || dto.getPersonas() > 20) {
                return ResponseEntity.badRequest().body("Número de personas inválido");
            }

            reservaDAO.crearReserva(dto);
            return ResponseEntity.status(201).body("Reserva creada con éxito");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear la reserva");
        }
    }
}
