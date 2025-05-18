package com.olympoburgers.backend.action;

import com.olympoburgers.backend.dao.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

@Component
public class EntregarPedidoAction {

    @Autowired
    private PedidoDAO pedidoDAO;

    public ResponseEntity<?> ejecutar(int id) {
        try {
            String gmail = pedidoDAO.entregarPedido(id);

            // Notificar por Make
            URL url = new URL("https://hook.eu2.make.com/tsuqwuya7omourue3nltoynpsoswza5y");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String json = String.format("""
                {
                    "mensaje": "Tu pedido #%d ha sido entregado con éxito.",
                    "destino": "%s"
                }
                """, id, gmail);

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }

            con.getResponseCode(); // for execution

            return ResponseEntity.ok("Pedido entregado y notificación enviada");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al entregar el pedido");
        }
    }
}
