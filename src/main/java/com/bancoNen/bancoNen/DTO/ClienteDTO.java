package com.bancoNen.bancoNen.DTO;
import com.bancoNen.bancoNen.entidades.Producto;
import lombok.Getter;

import java.util.List;

@Getter
@
public class ClienteDTO {
    private String usuario;
    private int edad;
    private String mail;
    private List<Producto> productos;



}
