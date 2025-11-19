package com.bancoNen.AppBanco.repositorio;
import com.bancoNen.AppBanco.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCuenta extends JpaRepository <Cuenta, Integer> {
    boolean existsByNumeroCuenta(String numeroCuenta);

}
