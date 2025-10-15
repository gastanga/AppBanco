package com.bancoNen.bancoNen.entidades;
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
    private Cliente clienteAsociado;
    private Long saldo;
    @Column(unique = true)
    private String numeroCuenta;

    public Cuenta() {
    }

}
