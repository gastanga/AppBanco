package com.bancoNen.AppBanco.repositorio;
import com.bancoNen.AppBanco.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepoCliente extends JpaRepository <Cliente, Integer> {
    Optional<Cliente> findByUsuario(String usuario);
}
