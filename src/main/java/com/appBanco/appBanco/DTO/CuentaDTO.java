package com.bancoNen.AppBanco.DTO;
import com.bancoNen.AppBanco.entidades.Cuenta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDTO {
    private int id;
    private String clienteUsuario;
    private String numeroCuentaOculto;

    public CuentaDTO(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.clienteUsuario = cuenta.getClienteAsociado().getUsuario();
        this.numeroCuentaOculto = "****" + cuenta.getNumeroCuenta().substring(cuenta.getNumeroCuenta().length() - 4);
    }
}
