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

    @PostMapping("/depositar/{idCliente}")
    public ResponseEntity<Long> depositar(@PathVariable int idCliente, @RequestParam Long monto) {
        Long saldoActualizado = cuentaService.depositar(idCliente, monto);
        return ResponseEntity.ok(saldoActualizado);
    }

    @PostMapping("/retirar/{idCliente}")
    public ResponseEntity<Long> retirar(@PathVariable int idCliente, @RequestParam Long monto) {
        Long saldoActualizado = cuentaService.retirar(idCliente, monto);
        return ResponseEntity.ok(saldoActualizado);
    }
}
