package com.bancoNen.bancoNen.services;
import com.bancoNen.bancoNen.entidades.Cliente;
import com.bancoNen.bancoNen.entidades.Cuenta;
import com.bancoNen.bancoNen.entidades.Producto;
import com.bancoNen.bancoNen.repositorio.RepoCliente;
import com.bancoNen.bancoNen.repositorio.RepoCuenta;
import com.bancoNen.bancoNen.repositorio.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private RepoCliente repoCliente;

    @Autowired
    private RepoCuenta repoCuenta;

    @Autowired
    private RepoProducto repoProducto;

    public Cliente crearCliente(Cliente cliente) {
        String dni = cliente.getDNI();
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe contener exactamente 8 dígitos numéricos.");
        }
        Cliente clienteGuardado = repoCliente.save(cliente);

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(generarNumeroCuentaUnico());
        cuenta.setClienteAsociado(clienteGuardado);
        repoCuenta.save(cuenta);

        Producto producto = new Producto();
        producto.setNombre("Cuenta Bancaria " + cuenta.getNumeroCuenta());
        producto.setClienteAsociado(clienteGuardado);
        producto.setTipo(Producto.TipoProducto.CORRIENTE);
        repoProducto.save(producto);

        return clienteGuardado;
    }

    private String generarNumeroCuentaUnico() {
        String numeroCuenta;
        do {
            numeroCuenta = String.format("%016d", new java.util.Random().nextLong(1_0000_0000_0000_0000L));
        } while (repoCuenta.existsByNumeroCuenta(numeroCuenta));
        return numeroCuenta;
    }

    public List<Cliente> listarClientes() {
        return repoCliente.findAll();
    }

    public Cliente buscarPorId (int idCliente) {
        return repoCliente.findById(idCliente).
                orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + idCliente + " no existe."));
    }

    public void eliminarCliente (int idCliente) {
        if (!repoCliente.existsById(idCliente)) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " no existe.");
        }
        repoCliente.deleteById(idCliente);
    }

    //Asignar cuenta al Cliente

    public void asignarCuenta(int idCliente, Cuenta cuenta) {
        Cliente cliente = buscarPorId(idCliente);
        cliente.setCuenta(cuenta);
        cuenta.setClienteAsociado(cliente);
        repoCuenta.save(cuenta);
        repoCliente.save(cliente);
    }

    public void asignarProducto (int idCliente, int idProducto) {
        Cliente cliente = buscarPorId(idCliente);
        Producto producto = repoProducto.findById(idProducto)
                        .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + idProducto + " no existe."));
        if (producto.getClienteAsociado() != null) {
            throw new IllegalArgumentException("El producto con ID " + idProducto + " ya está asignado al cliente." + producto.getClienteAsociado().getUsuario());
        }
        producto.setClienteAsociado(cliente);
        cliente.getProductos().add(producto);
        repoProducto.save(producto);
        repoCliente.save(cliente);
        System.out.println("Producto " + producto.getId() + " " + producto.getNombre() + " asignado correctamente al cliente " + cliente.getUsuario());
    }


    public void asignarNuevoProducto (int idCliente, Producto productoNuevo) {
        Cliente cliente = repoCliente.findById(idCliente)
                        .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + idCliente + " no existe."));
        productoNuevo.setClienteAsociado(cliente);
        cliente.getProductos().add(productoNuevo);
        repoProducto.save(productoNuevo);
        repoCliente.save(cliente);
    }

    public Cliente iniciarSesion (String usuario, String contrasena) {
        Cliente cliente = repoCliente.findByUsuario(usuario)
                        .orElseThrow(() -> new IllegalArgumentException("El usuario " + usuario + " no existe."));
                if (!cliente.getContrasena().equals(contrasena)) {
            throw new IllegalArgumentException("Contraseña incorrecta para el usuario " + usuario + ".");
                }
        System.out.println("Inicio de sesión exitoso para el usuario " + usuario + ".");
        return cliente;
    }

    public Long consultarSaldo (int idCliente) {
        Cliente cliente = buscarPorId(idCliente);
        Cuenta cuenta = cliente.getCuenta();
        if (cuenta == null) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " no tiene una cuenta asociada.");
        }
        System.out.println("El saldo de la cuenta del cliente " + cliente.getUsuario() + " es: " + cuenta.getSaldo());
        return cuenta.getSaldo();
    }

}
