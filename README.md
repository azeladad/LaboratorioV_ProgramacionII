# Laboratorio V - APIs REST con Spring Framework

**Universidad Mariano Gálvez de Guatemala**
Facultad de Ingeniería
Carrera: Ingeniería en Sistemas de Información y Ciencias de la Computación

**Estudiante:** Angel Alexander Zelada Donado
**Carné:** 0900-24-19888
**Curso:** Programación II — Sección B

## Descripción

Proyecto Spring Boot con Maven que implementa 10 APIs REST independientes, aplicando operaciones CRUD completas (GET, POST, PUT, PATCH, DELETE) sobre listas en memoria.

## APIs incluidas

1. Productos — `/api/productos`
2. Estudiantes — `/api/estudiantes`
3. Libros — `/api/libros`
4. Empleados — `/api/empleados`
5. Peliculas — `/api/peliculas`
6. Cursos — `/api/cursos`
7. Vehiculos — `/api/vehiculos`
8. Tareas — `/api/tareas`
9. Clientes — `/api/clientes`
10. Pedidos — `/api/pedidos`

Cada API cuenta con 5 registros de ejemplo precargados en memoria.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Maven
- Postman (para pruebas)

## Estructura del proyecto

    spring-apis-lab/
    ├── pom.xml
    └── src/main/java/com/lab/spring_apis_lab/
        ├── SpringApisLabApplication.java
        ├── controller/
        │   ├── ProductoController.java
        │   ├── EstudianteController.java
        │   ├── LibroController.java
        │   ├── EmpleadoController.java
        │   ├── PeliculaController.java
        │   ├── CursoController.java
        │   ├── VehiculoController.java
        │   ├── TareaController.java
        │   ├── ClienteController.java
        │   └── PedidoController.java
        └── model/
            ├── Producto.java
            ├── Estudiante.java
            ├── Libro.java
            ├── Empleado.java
            ├── Pelicula.java
            ├── Curso.java
            ├── Vehiculo.java
            ├── Tarea.java
            ├── Cliente.java
            └── Pedido.java

## Como ejecutar el proyecto

    ./mvnw spring-boot:run

El servidor arranca en `http://localhost:8080`

## Endpoints disponibles (por cada API)

    GET     /api/{recurso}
    GET     /api/{recurso}/{id}
    POST    /api/{recurso}
    PUT     /api/{recurso}/{id}
    PATCH   /api/{recurso}/{id}
    DELETE  /api/{recurso}/{id}
