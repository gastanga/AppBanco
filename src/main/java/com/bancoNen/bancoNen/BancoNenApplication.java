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

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BancoNenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoNenApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RepoCliente repoCliente) {
		return args -> {
			try {
				System.out.println("🚀 Iniciando carga de datos...");

				// Crear cliente
				Cliente cliente = new Cliente();
				cliente.setUsuario("gaston91");
				cliente.setContrasena("1234");
				cliente.setEdad(30);
				cliente.setMail("gaston@example.com");

				// Crear cuenta
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroCuenta("ACC12345");
				cuenta.setSaldo(1000L);
				cuenta.setClienteAsociado(cliente);
				cliente.setCuenta(cuenta);

				// Crear productos
				Producto producto1 = new Producto();
				producto1.setNombre("Tarjeta Crédito Gold");
				producto1.setPrecio(500L);
				producto1.setTipo(Producto.TipoProducto.TARJETA_CREDITO);
				producto1.setClienteAsociado(cliente);

				Producto producto2 = new Producto();
				producto2.setNombre("Cuenta Ahorro");
				producto2.setPrecio(0L);
				producto2.setTipo(Producto.TipoProducto.AHORRO);
				producto2.setClienteAsociado(cliente);

				cliente.setProductos(Arrays.asList(producto1, producto2));

				// Guardar todo usando cascada desde el cliente
				cliente = repoCliente.save(cliente);

				System.out.println("✅ Cliente guardado con ID: " + cliente.getId());
				System.out.println("✅ Cuenta guardada con número: " + cliente.getCuenta().getNumeroCuenta());
				System.out.println("✅ Productos guardados: " + cliente.getProductos().size());

			} catch (Exception e) {
				System.err.println("❌ Error al guardar datos: " + e.getMessage());
				e.printStackTrace();
			}
		};
	}

}
