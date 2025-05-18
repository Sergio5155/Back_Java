package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ResenaDAO;
import com.olympoburgers.backend.model.Resena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerResenasAction {

    @Autowired
    private ResenaDAO resenaDAO;

    public ResponseEntity<?> ejecutar(int idLocal) {
        try {
            List<Resena> lista = resenaDAO.obtenerResenasPorLocal(idLocal);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener reseñas");
        }
    }
}
