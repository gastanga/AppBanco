package com.bancoNen.bancoNen.restController;
import com.bancoNen.bancoNen.DTO.ClienteDTO;
import com.bancoNen.bancoNen.entidades.Cliente;
import com.bancoNen.bancoNen.entidades.Cuenta;
import com.bancoNen.bancoNen.entidades.Producto;
import com.bancoNen.bancoNen.services.ClienteService;
import com.bancoNen.bancoNen.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CuentaService cuentaService;

    // POST para crear un cliente
    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(new ClienteDTO(clienteGuardado));
    }

    @PostMapping("/cuentas/{idCuenta}/depositar")
    public ResponseEntity<Long> depositar(@PathVariable int idCuenta, @RequestParam Long monto) {
        Long saldoActualizado = cuentaService.depositar(idCuenta, monto);
        return ResponseEntity.ok(saldoActualizado);
    }

    @PostMapping("/cuentas/{idCuenta}/retirar")
    public ResponseEntity<Long> retirar(@PathVariable int idCuenta, @RequestParam Long monto) {
        Long saldoActualizado = cuentaService.retirar(idCuenta, monto);
        return ResponseEntity.ok(saldoActualizado);
    }

    @GetMapping("/cuentas/{idCuenta}/saldo")
    public ResponseEntity<Long> mostrarSaldo(@PathVariable int idCuenta) {
        Long saldo = cuentaService.obtenerSaldo(idCuenta);
        return ResponseEntity.ok(saldo);
    }

    // ✅ Listar todas las cuentas
    @GetMapping("/cuentas")
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerTodas();
        return ResponseEntity.ok(cuentas);
    }

    // ✅ Eliminar una cuenta (por cascada)
    @DeleteMapping("/cuentas/{idCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable int idCuenta) {
        cuentaService.eliminarCuenta(idCuenta);
        return ResponseEntity.noContent().build();
    }

}
