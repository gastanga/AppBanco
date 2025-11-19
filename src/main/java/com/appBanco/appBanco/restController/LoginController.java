package com.bancoNen.AppBanco.restController;
import com.bancoNen.AppBanco.DTO.*;
import com.bancoNen.AppBanco.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/auth")
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = clienteService.iniciarSesion(request);
        return ResponseEntity.ok(response);
    }
}
