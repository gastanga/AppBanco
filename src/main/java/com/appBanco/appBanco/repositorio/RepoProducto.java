package com.bancoNen.AppBanco.repositorio;
import com.bancoNen.AppBanco.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProducto extends JpaRepository<Producto, Integer> {

}
