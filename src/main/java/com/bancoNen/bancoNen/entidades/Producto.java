package com.bancoNen.bancoNen.entidades;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Cliente clienteAsociado;
    private String nombre;
    private Long precio;
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;
    public enum TipoProducto {AHORRO, CORRIENTE, TARJETA_CREDITO, TARJETA_DEBITO, PRESTAMO}

}
