package com.appBanco.appBanco.repositorio;
import com.appBanco.appBanco.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCuenta extends JpaRepository <Cuenta, Integer> {
    boolean existsByNumeroCuenta(String numeroCuenta);

}
