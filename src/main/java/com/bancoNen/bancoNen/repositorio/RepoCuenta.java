package com.bancoNen.bancoNen.repositorio;
import com.bancoNen.bancoNen.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCuenta extends JpaRepository <Cuenta, Integer> {
    boolean existsByNumeroCuenta(String numeroCuenta);

}
