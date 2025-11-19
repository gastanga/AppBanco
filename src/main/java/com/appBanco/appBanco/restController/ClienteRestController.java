package com.appBanco.appBanco.restController;
import com.appBanco.appBanco.DTO.ClienteDTO;
import com.appBanco.appBanco.DTO.ClienteRequest;
import com.appBanco.appBanco.DTO.CuentaDTO;
import com.appBanco.appBanco.entidades.Cliente;
import com.appBanco.appBanco.services.ClienteService;
import com.appBanco.appBanco.services.CuentaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody @Valid ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setUsuario(request.getUsuario());
        cliente.setContrasena(request.getContrasena());
        cliente.setDNI(request.getDNI());
        cliente.setMail(request.getMail());
        cliente.setEdad(request.getEdad());

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
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        List<CuentaDTO> cuentasDTO = cuentaService.obtenerTodas()
                .stream()
                .map(CuentaDTO::new)
                .toList();
        return ResponseEntity.ok(cuentasDTO);
    }

    // ✅ Eliminar una cuenta (por cascada)
    @DeleteMapping("/cuentas/{idCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable int idCuenta) {
        cuentaService.eliminarCuenta(idCuenta);
        return ResponseEntity.noContent().build();
    }

}
