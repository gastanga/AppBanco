package com.bancoNen.AppBanco.DTO;
import com.bancoNen.AppBanco.entidades.Producto;
import lombok.Data;

@Data
public class ProductoDTO {
    private int id;
    private String nombre;
    private Producto.TipoProducto tipo;

    public ProductoDTO(Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.tipo = p.getTipo();
    }
}
