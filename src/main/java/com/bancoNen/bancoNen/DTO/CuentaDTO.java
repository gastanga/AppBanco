package com.bancoNen.bancoNen.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDTO {
    private int id;
    private String clienteUsuario;
    private String numeroCuentaOculto;

    public CuentaDTO(int id, String clienteUsuario, String numeroCuentaOculto) {
        this.id = id;
        this.clienteUsuario = clienteUsuario;
        this.numeroCuentaOculto = numeroCuentaOculto;
    }
}
