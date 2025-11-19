package com.appBanco.appBanco.repositorio;
import com.appBanco.appBanco.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProducto extends JpaRepository<Producto, Integer> {

}
