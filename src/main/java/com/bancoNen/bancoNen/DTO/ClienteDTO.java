package com.bancoNen.bancoNen.DTO;
import com.bancoNen.bancoNen.entidades.Producto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    private String usuario;
    private int edad;
    private String mail;
    private List<Producto> productos;

    public ClienteDTO(String usuario, int edad, String mail, List<Producto> productos) {
        this.usuario = usuario;
        this.edad = edad;
        this.mail = mail;
        this.productos = productos;
    }
}
