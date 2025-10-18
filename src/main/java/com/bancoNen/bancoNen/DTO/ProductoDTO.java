package com.bancoNen.bancoNen.DTO;
import com.bancoNen.bancoNen.entidades.Producto;
import lombok.Data;

@Data
public class ProductoDTO {
    private int id;
    private String nombre;
    private Producto.TipoProducto tipo;
}
