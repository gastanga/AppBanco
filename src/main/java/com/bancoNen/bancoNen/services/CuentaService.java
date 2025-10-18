package com.bancoNen.bancoNen.services;
import com.bancoNen.bancoNen.entidades.Cuenta;
import com.bancoNen.bancoNen.entidades.Producto;
import com.bancoNen.bancoNen.repositorio.RepoCuenta;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CuentaService {
    private final RepoCuenta repoCuenta;

    public CuentaService(RepoCuenta repoCuenta) {
        this.repoCuenta = repoCuenta;
    }

    public Long depositar (int idCuenta, Long monto) {
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
        return cuenta.getSaldo();
    }

    public Long retirar (int idCuenta, Long monto) {
        if(monto == null || monto <=0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }

        Optional <Cuenta> cuentaOpt = repoCuenta.findById(idCuenta);
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }
        Cuenta cuenta = cuentaOpt.get();
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Fondos insuficientes. Saldo actual: " + cuenta.getSaldo());
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        repoCuenta.save(cuenta);
        System.out.println("Retiro realizado exitosamente. Nuevo saldo: " + cuenta.getSaldo());
        return cuenta.getSaldo();
    }


    public void pagar(int idCuenta, Long precio, Producto.TipoProducto tipoProducto, String vendedor) {
        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("El monto a pagar debe ser mayor a cero.");
        }
        if (tipoProducto == null) {
            throw new IllegalArgumentException("Debe especificarse el tipo de producto utilizado para el pago.");
        }
        if (vendedor == null || vendedor.isBlank()) {
            throw new IllegalArgumentException("Debe especificarse el nombre del vendedor o proveedor.");
        }
        Optional <Cuenta> cuentaOpt = repoCuenta.findById(idCuenta);
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("La cuenta con ID " + idCuenta + " no existe.");
        }
        Cuenta cuenta = cuentaOpt.get();
        if (cuenta.getSaldo() < precio) {
            throw new IllegalArgumentException("Fondos insuficientes. Saldo actual: " + cuenta.getSaldo());
        }
        if (cuenta.getSaldo()*0.50 < precio) {
            throw new IllegalArgumentException("No se puede realizar el pago. El monto excede el 50% del saldo actual.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - precio);
        repoCuenta.save(cuenta);
        System.out.println("Pago realizado exitosamente. Nuevo saldo: " + cuenta.getSaldo());
    }

}
