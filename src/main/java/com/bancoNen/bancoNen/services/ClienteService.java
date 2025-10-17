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
        return repoCliente.save(cliente);
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
}
