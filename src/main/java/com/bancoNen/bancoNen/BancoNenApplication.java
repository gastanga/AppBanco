package com.bancoNen.bancoNen;
import com.bancoNen.bancoNen.entidades.Cliente;
import com.bancoNen.bancoNen.entidades.Cuenta;
import com.bancoNen.bancoNen.entidades.Producto;
import com.bancoNen.bancoNen.repositorio.RepoCliente;
import com.bancoNen.bancoNen.repositorio.RepoCuenta;
import com.bancoNen.bancoNen.repositorio.RepoProducto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BancoNenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoNenApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RepoCliente repoCliente,
						   RepoCuenta repoCuenta,
						   RepoProducto repoProducto) {
		System.out.println("🚀 init corriendo...");
		return args -> {

			// 1️⃣ Crear Cliente
			Cliente cliente = new Cliente();
			cliente.setUsuario("gaston91");
			cliente.setContrasena("1234");
			cliente.setEdad(30);
			cliente.setMail("gaston@example.com");

			// 2️⃣ Crear Cuenta y asociar a Cliente
			Cuenta cuenta = new Cuenta();
			cuenta.setNumeroCuenta("ACC12345");
			cuenta.setSaldo(1000L);
			cuenta.setClienteAsociado(cliente);
			cliente.setCuenta(cuenta);

			// 3️⃣ Crear Producto y asociar al Cliente
			Producto producto = new Producto();
			producto.setNombre("Tarjeta Crédito");
			producto.setPrecio(500L);
			producto.setTipo(Producto.TipoProducto.TARJETA_CREDITO);
			producto.setClienteAsociado(cliente);

			// Asociar producto al cliente
			cliente.setProductos(List.of(producto));

			// 4️⃣ Guardar en la base de datos
			repoCliente.save(cliente); // cascada guarda Cuenta y Productos automáticamente
			System.out.println("💾 Cliente guardado: " + cliente.getId());


			// 5️⃣ Recuperar todos los Clientes y mostrar info
			List<Cliente> clientes = repoCliente.findAll();
			clientes.forEach(c -> {
				System.out.println("Cliente: " + c.getUsuario() + " - Cuenta: " + c.getCuenta().getNumeroCuenta());
				c.getProductos().forEach(p -> System.out.println("Producto: " + p.getNombre() + " Tipo: " + p.getTipo()));
			});
		};
	}

}
