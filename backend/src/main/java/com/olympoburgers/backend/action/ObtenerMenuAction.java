package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.ComidaDAO;
import com.olympoburgers.backend.model.Comida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObtenerMenuAction {

    @Autowired
    private ComidaDAO comidaDAO;

    public ResponseEntity<?> ejecutar() {
        List<Comida> lista = comidaDAO.obtenerTodoElMenu();
        return ResponseEntity.ok(lista);
    }
}
