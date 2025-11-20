# AppBanco

Aplicación bancaria desarrollada en Java 21 + Spring Boot, orientada a la gestión de clientes, cuentas y productos financieros.  
El proyecto sigue una arquitectura por capas clara y mantenible, aplicando buenas prácticas de desarrollo, validaciones y manejo centralizado de excepciones.

# Tecnologías utilizadas
- Java 21
- Spring Boot 3
- Spring Web / Spring MVC
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- Postman

# Objetivo del proyecto
Implementar una API REST sólida y extensible que refleje procesos comunes de una plataforma bancaria: registro de usuarios, validación de credenciales, gestión de cuentas y administración de productos.

# Funcionalidades destacadas
- Autenticación y validación de credenciales (BCrypt).
- CRUD completo de clientes, cuentas y productos.
- Endpoints REST con respuestas JSON.
- DTOs para separar modelos, peticiones y respuestas.
- Manejo global de excepciones mediante ControllerAdvice.
- Arquitectura por capas (Controller → Service → Repository).

#Estructura del proyecto
/DTO → Objetos de transferencia (request/response)
/entidades → Modelos JPA
/repositorio → Interfaces Spring Data
/services → Lógica de negocio
/restController → Endpoints REST
/config → Seguridad y configuraciones

# Algunos aspectos técnicos
- Uso de JPA/Hibernate para persistencia.
- Validaciones básicas en datos de entrada.
- Manejo de errores consistente y mensajes claros para el cliente.
- Servicios independientes para favorecer una futura escalabilidad.

# Estado
Proyecto en desarrollo activo y abierto a mejoras, pensado para demostrar conocimientos aplicados