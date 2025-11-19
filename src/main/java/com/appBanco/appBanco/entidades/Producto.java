package com.bancoNen.AppBanco.entidades;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JsonBackReference
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
