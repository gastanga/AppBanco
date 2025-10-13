package com.bancoNen.bancoNen.entidades;
import jakarta.persistence.*;

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

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
}
