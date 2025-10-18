package com.bancoNen.bancoNen.DTO;
import com.bancoNen.bancoNen.entidades.Cliente;
import com.bancoNen.bancoNen.entidades.Producto;
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
    private List<Producto> productos;

    public ClienteDTO(String nombres, String apellidos, String usuario, int edad, String mail, List<Producto> productos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.edad = edad;
        this.mail = mail;
        this.productos = productos;
    }

    public ClienteDTO(Cliente cliente) {
        this.nombres = cliente.getNombres();
        this.apellidos = cliente.getApellidos();
        this.usuario = cliente.getUsuario();
        this.edad = cliente.getEdad();
        this.mail = cliente.getMail();
        this.productos = cliente.getProductos();
    }
}
