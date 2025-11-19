package com.appBanco.appBanco.DTO;
import com.appBanco.appBanco.entidades.Cliente;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    private String nombres;
    private String apellidos;
    private String usuario;
    private int edad;
    private String mail;
    private List<ProductoDTO> productos;
    private CuentaDTO cuenta;

    public ClienteDTO(Cliente cliente) {
        this.nombres = cliente.getNombres();
        this.apellidos = cliente.getApellidos();
        this.usuario = cliente.getUsuario();
        this.edad = cliente.getEdad();
        this.mail = cliente.getMail();

        if (cliente.getProductos() != null) {
            this.productos = cliente.getProductos()
                    .stream()
                    .map(ProductoDTO::new)
                    .toList();
        }

        if (cliente.getCuenta() != null) {
            this.cuenta = new CuentaDTO(cliente.getCuenta());
        }
    }

}
