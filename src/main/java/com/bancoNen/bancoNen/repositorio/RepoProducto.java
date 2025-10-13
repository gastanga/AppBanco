package com.bancoNen.bancoNen.repositorio;

import com.bancoNen.bancoNen.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProducto extends JpaRepository<Producto, Integer> {
    //Producto findByNombre(String nombre);
}
