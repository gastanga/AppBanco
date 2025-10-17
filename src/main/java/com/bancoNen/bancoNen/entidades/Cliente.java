package com.bancoNen.bancoNen.entidades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String usuario;
    private String contrasena;
    private int edad;
    private String mail;

    @OneToOne (mappedBy = "clienteAsociado", cascade = CascadeType.ALL)
    private Cuenta cuenta;
    @OneToMany (mappedBy = "clienteAsociado" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();
    public Cliente() {
    }

}
