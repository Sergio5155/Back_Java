package com.olympoburgers.backend.controller;

import com.olympoburgers.backend.action.*;
import com.olympoburgers.backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FrontController {

    @Autowired
    private RegistroAction registroAction;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO dto) {
        return registroAction.ejecutar(dto);
    }

    @Autowired
    private LoginAction loginAction;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        return loginAction.ejecutar(dto);
    }
    @Autowired
    private LoginEmpleadoAction loginEmpleadoAction;

    @PostMapping("/login-empleado")
    public ResponseEntity<?> loginEmpleado(@RequestBody LoginEmpleadoDTO dto) {
        return loginEmpleadoAction.ejecutar(dto);
    }
    @Autowired
    private ObtenerMenuAction obtenerMenuAction;

    @GetMapping("/menu")
    public ResponseEntity<?> obtenerMenu() {
        return obtenerMenuAction.ejecutar();
    }
    @Autowired
    private AñadirProductoAction añadirProductoAction;

    @PostMapping("/menu")
    public ResponseEntity<?> añadirProducto(@RequestBody ComidaDTO dto) {
        return añadirProductoAction.ejecutar(dto);
    }
    @Autowired
    private EditarProductoAction editarProductoAction;

    @PutMapping("/menu/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable int id, @RequestBody ComidaDTO dto) {
        return editarProductoAction.ejecutar(id, dto);
    }
    @Autowired
    private EliminarProductoAction eliminarProductoAction;

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        return eliminarProductoAction.ejecutar(id);
    }
    @Autowired
    private ObtenerCarritoAction obtenerCarritoAction;

    @GetMapping("/carrito/{gmail}")
    public ResponseEntity<?> obtenerCarrito(@PathVariable String gmail) {
        return obtenerCarritoAction.ejecutar(gmail);
    }
    @Autowired
    private AñadirCarritoAction añadirCarritoAction;

    @PostMapping("/carrito")
    public ResponseEntity<?> añadirAlCarrito(@RequestBody CarritoDTO dto) {
        return añadirCarritoAction.ejecutar(dto);
    }
    @Autowired
    private ModificarCarritoAction modificarCarritoAction;

    @PutMapping("/carrito")
    public ResponseEntity<?> modificarCarrito(@RequestBody ModificarCarritoDTO dto) {
        return modificarCarritoAction.ejecutar(dto);
    }
    @Autowired
    private RealizarPedidoAction realizarPedidoAction;

    @PostMapping("/pedido")
    public ResponseEntity<?> realizarPedido(@RequestBody PedidoDTO dto) {
        return realizarPedidoAction.ejecutar(dto);
    }
    @Autowired
    private ObtenerPedidosClienteAction obtenerPedidosClienteAction;

    @GetMapping("/pedidos/{gmail}")
    public ResponseEntity<?> obtenerPedidosCliente(@PathVariable String gmail) {
        return obtenerPedidosClienteAction.ejecutar(gmail);
    }
    @Autowired
    private ObtenerTodosLosPedidosAction obtenerTodosLosPedidosAction;

    @GetMapping("/pedidos")
    public ResponseEntity<?> obtenerTodosLosPedidos() {
        return obtenerTodosLosPedidosAction.ejecutar();
    }
    @Autowired
    private EntregarPedidoAction entregarPedidoAction;

    @PutMapping("/pedidos/{id}/entregar")
    public ResponseEntity<?> entregarPedido(@PathVariable int id) {
        return entregarPedidoAction.ejecutar(id);
    }
    @Autowired
    private ObtenerLocalesAction obtenerLocalesAction;

    @GetMapping("/locales")
    public ResponseEntity<?> obtenerLocales() {
        return obtenerLocalesAction.ejecutar();
    }
    @Autowired
    private ObtenerResenasAction obtenerResenasAction;

    @GetMapping("/locales/{id}/resenas")
    public ResponseEntity<?> obtenerResenas(@PathVariable int id) {
        return obtenerResenasAction.ejecutar(id);
    }
    @Autowired
    private GuardarResenaAction guardarResenaAction;

    @PostMapping("/resenas")
    public ResponseEntity<?> guardarResena(@RequestBody ResenaDTO dto) {
        return guardarResenaAction.ejecutar(dto);
    }
    @Autowired
    private CrearReservaAction crearReservaAction;

    @PostMapping("/reservas")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO dto) {
        return crearReservaAction.ejecutar(dto);
    }
    @Autowired
    private ObtenerReservasClienteAction obtenerReservasClienteAction;

    @GetMapping("/reservas/{gmail}")
    public ResponseEntity<?> obtenerReservasCliente(@PathVariable String gmail) {
        return obtenerReservasClienteAction.ejecutar(gmail);
    }
    @Autowired
    private ObtenerReservasEmpleadoAction obtenerReservasEmpleadoAction;

    @GetMapping("/reservas")
    public ResponseEntity<?> obtenerReservasEmpleado() {
        return obtenerReservasEmpleadoAction.ejecutar();
    }
    @Autowired
    private CancelarReservaAction cancelarReservaAction;

    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable int id) {
        return cancelarReservaAction.ejecutar(id);
    }
    @Autowired
    private ObtenerUsuarioAction obtenerUsuarioAction;

    @GetMapping("/usuarios/{gmail}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable String gmail) {
        return obtenerUsuarioAction.ejecutar(gmail);
    }




}
