package com.appBanco.appBanco.restController;
import com.appBanco.appBanco.DTO.LoginRequest;
import com.appBanco.appBanco.DTO.LoginResponse;
import com.appBanco.appBanco.services.ClienteService;
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
