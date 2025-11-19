/*package com.bancoNen.bancoNen;
import com.bancoNen.bancoNen.entidades.Cliente;
import com.bancoNen.bancoNen.repositorio.RepoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordMigrationRunner implements CommandLineRunner {
    @Autowired
    private RepoCliente repoCliente;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Cliente> todos = repoCliente.findAll();
        for (Cliente c : todos) {
            String pass = c.getContrasena();
            if (pass == null) continue;
            // detectar si ya está hasheada (BCrypt empieza con $2a$ / $2b$ / $2y$)
            if (!(pass.startsWith("$2a$") || pass.startsWith("$2b$") || pass.startsWith("$2y$"))) {
                String hashed = passwordEncoder.encode(pass);
                c.setContrasena(hashed);
                repoCliente.save(c);
                System.out.println("Password migrada para usuario " + c.getUsuario());
            }
        }
    }
}
*/