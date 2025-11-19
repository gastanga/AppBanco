package com.appBanco.appBanco.services;
import com.appBanco.appBanco.DTO.LoginRequest;
import com.appBanco.appBanco.DTO.LoginResponse;
import com.appBanco.appBanco.entidades.Cliente;
import com.appBanco.appBanco.entidades.Cuenta;
import com.appBanco.appBanco.entidades.Producto;
import com.appBanco.appBanco.repositorio.RepoCliente;
import com.appBanco.appBanco.repositorio.RepoCuenta;
import com.appBanco.appBanco.repositorio.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Cliente crearCliente(Cliente cliente) {
        String dni = cliente.getDNI();
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe contener exactamente 8 dígitos numéricos.");
        }

        repoCliente.findByUsuario(cliente.getUsuario()).ifPresent(c -> {
            throw new IllegalArgumentException("El usuario ya existe.");
        });

        cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));

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
            long randomNum = Math.abs(new java.util.Random().nextLong());
            numeroCuenta = String.format("%016d", randomNum);
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

    public LoginResponse iniciarSesion(LoginRequest request) {

        Cliente cliente = repoCliente.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new IllegalArgumentException("El usuario " + request.getUsuario() + " no existe."));

        if (!passwordEncoder.matches(request.getContrasena(), cliente.getContrasena())) {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }

        LoginResponse response = new LoginResponse();
        response.setMensaje("Login exitoso.");
        response.setIdCliente(cliente.getId());

        return response;
    }

    public Long consultarSaldo (int idCliente) {
        Cliente cliente = buscarPorId(idCliente);
        Cuenta cuenta = cliente.getCuenta();
        if (cuenta == null) {
            throw new IllegalArgumentException("El cliente con ID " + idCliente + " no tiene una cuenta asociada.");
        }
        return cuenta.getSaldo();
    }
}
