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
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;
    public enum TipoProducto { TARJETA_CREDITO,
        TARJETA_DEBITO,
        PRESTAMO,
        AHORRO,
        CORRIENTE}

}
