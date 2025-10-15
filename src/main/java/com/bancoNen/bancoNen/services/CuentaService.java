package com.bancoNen.bancoNen.services;
import com.bancoNen.bancoNen.entidades.Cuenta;
import com.bancoNen.bancoNen.repositorio.RepoCuenta;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {
    private final RepoCuenta repoCuenta;

    public CuentaService(RepoCuenta repoCuenta) {
        this.repoCuenta = repoCuenta;
    }

    public void depositar (int idCuenta, Long monto) {
        if(monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }
        Optional <Cuenta> cuentaOpt = repoCuenta.findById(idCuenta);
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }
        Cuenta cuenta = cuentaOpt.get();
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        repoCuenta.save(cuenta);
        System.out.println("Depósito realizado exitosamente. Nuevo saldo: " + cuenta.getSaldo());
    }

}
