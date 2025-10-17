package com.bancoNen.bancoNen.services;
import com.bancoNen.bancoNen.entidades.Producto;
import com.bancoNen.bancoNen.repositorio.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private RepoProducto repoProducto;

    public Producto guardarProducto(Producto producto) {
        return repoProducto.save(producto);
    }

    // Listar todos
    public List<Producto> listarProductos() {
        return repoProducto.findAll();
    }

    // Buscar por ID
    public Producto buscarPorId(Integer id) {
        return repoProducto.findById(id).orElse(null);
    }

    // Eliminar por ID
    public void eliminarProducto(Integer id) {
        repoProducto.deleteById(id);
    }
}
