package com.appBanco.appBanco.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "Nombre obligatorio")
    private String nombres;

    @NotBlank(message = "Apellido obligatorio")
    private String apellidos;

    @NotBlank(message = "Usuario obligatorio")
    private String usuario;

    @NotBlank(message = "Contraseña obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasena;

    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String DNI;

    @Email(message = "Mail inválido")
    private String mail;

    @Min(value = 18, message = "Edad mínima 18")
    @Max(value = 120, message = "Edad máxima 120")
    private int edad;

}
