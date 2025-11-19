package com.appBanco.appBanco.entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Cuenta {
    @Id
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn (name = "cliente_id")
    @JsonBackReference
    private Cliente clienteAsociado;
    private Long saldo = 0L;
    @Column(unique = true)
    private String numeroCuenta;

    public Cuenta() {
    }

}
