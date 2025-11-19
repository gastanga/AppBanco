package com.appBanco.appBanco.entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nombres;
    private String apellidos;
    private String DNI;
    private String usuario;
    private String contrasena;
    private int edad;
    private String mail;

    @OneToOne (mappedBy = "clienteAsociado", cascade = CascadeType.ALL)
    @JsonBackReference
    private Cuenta cuenta;
    @OneToMany (mappedBy = "clienteAsociado" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Producto> productos = new ArrayList<>();
    public Cliente() {
    }

}
