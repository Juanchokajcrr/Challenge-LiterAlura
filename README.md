# Challenge LiterAlura

[![Java](https://img.shields.io/badge/Java-24-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.0-red)](https://maven.apache.org/)

Un proyecto desafiante de Alura para practicar Spring Boot, creando una API de librería (LiterAlura) que permite gestionar libros y autores.

## 📋 Descripción

Este proyecto es parte del desafío de Alura "Practicando Spring Boot: Challenge LiterAlura". La aplicación es una API RESTful desarrollada con Spring Boot que permite consultar y gestionar información sobre libros y autores, integrándose con una base de datos PostgreSQL.

## 🚀 Características

- **Gestión de Libros**: Agregar, consultar y gestionar libros con información detallada.
- **Gestión de Autores**: Manejar información de autores asociados a los libros.
- **API RESTful**: Endpoints para interactuar con la aplicación de manera programática.
- **Base de Datos PostgreSQL**: Persistencia de datos utilizando JPA/Hibernate.
- **Arquitectura Limpia**: Estructura modular y mantenible.

## 🛠️ Tecnologías Utilizadas

- **Java 24**: Lenguaje de programación principal.
- **Spring Boot 3.5.4**: Framework para el desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la capa de persistencia de datos.
- **PostgreSQL**: Sistema de gestión de base de datos relacional.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

## 📋 Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener instalados:

- **Java 24** o superior.
- **Maven 3.9.0** o superior.
- **PostgreSQL 15** o superior.
- Un IDE como IntelliJ IDEA, Eclipse o VS Code con extensiones para Java y Spring Boot.

## ⚙️ Instalación y Configuración

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/Juanchokajcrr/Challenge-LiterAlura.git
   cd Challenge-LiterAlura
   ```

2. **Configura la base de datos**:
   - Crea una base de datos PostgreSQL llamada `literalura`.
   - Actualiza las credenciales en `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Compila y ejecuta la aplicación**:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   O en Windows:
   ```cmd
   mvnw.cmd clean install
   mvnw.cmd spring-boot:run
   ```

4. **Verifica la ejecución**:
   La aplicación estará disponible en `http://localhost:8080`.

## 📖 Uso

Una vez que la aplicación esté ejecutándose, puedes interactuar con la API a través de los siguientes endpoints (ejemplos):

- **GET /libros**: Obtener lista de libros.
- **POST /libros**: Agregar un nuevo libro.
- **GET /autores**: Obtener lista de autores.
- **GET /autores/{id}**: Obtener detalles de un autor específico.

Utiliza herramientas como Postman o curl para probar los endpoints.

## 🧪 Pruebas

Para ejecutar las pruebas unitarias:

```bash
./mvnw test
```

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un fork del proyecto.
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`).
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`).
4. Push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## 👤 Autor

**Juanchokajcrr** - [GitHub](https://github.com/Juanchokajcrr)

---

¡Gracias por revisar este proyecto! Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue.
