# Sistema completo para un ecommerce

Este proyecto desarrollado para gestionar un ecommerce de forma completa, desde el login de usuarios y administradores, la posibilidad de realizar compras de forma online a travez de usuarios registrados con un carrito de compras y un seguimiento de la entrega del producto por parte del cliente. En cuanto a la administración ofrece la capacidad de emitir facturas proforma, un CRUD de los productos y la ubicación de los mismos en la página principal, la administración de usuarios, datos de la empresa, creación de promociones y un seguimiento de las ventas y entrega de productos.

## Tecnologías utilizadas
- **Backend**:
  - Java
  - Spring Boot
  - JPA
  - MySQL
  - Java Mail Sender
- **Frontend**:
  - HTML
  - CSS
  - JavaScript
  - Bootstrap
  - Thymeleaf

## Objetivo del Proyecto

Este sistema tiene como finalidad gestionar las inscripciones de egresados en actividades como un acelerador, asignando mentores técnicos de acuerdo a configuraciones de equipo, como:
- Tamaño máximo de los equipos
- Perfiles de egresados por equipo
- Mentores técnicos asignados por tecnología

El sistema también permite manejar la comunicación vía e-mail entre los **candidatos** (egresados) y los **mentores** para hacer un seguimiento efectivo durante la mentoría.

## Funcionalidades principales
- Gestión de inscripciones y vinculación automática de mentores a los egresados
- ABM (Alta, Baja, Modificación) de egresados, mentores, actividades y configuraciones
- Control de los equipos según el tamaño y perfiles específicos
- Envío de e-mails automáticos para coordinar entre candidatos y mentores
- Seguimiento de la mentoría de los egresados
- Documentación de la API mediante OpenAPI/Swagger

## Requisitos del Sistema
- Java 11 o superior
- MySQL
- Node.js y npm (para el frontend)

## Instrucciones de instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Frere-Luz/squad11-2024-gestion-inscripciones
   ```

2. **Backend**:
   - Configurar la base de datos MySQL y modificar las propiedades en `application.properties` para establecer la conexión.
   - Ejecutar el backend:
     ```bash
     mvn spring-boot:run
     ```

3. **Frontend**:
   - Navegar a la carpeta del frontend:
     ```bash
     cd frontend
     ```
   - Instalar las dependencias:
     ```bash
     npm install
     ```
   - Ejecutar el frontend:
     ```bash
     npm start
     ```

4. Acceder a la aplicación  ` Front-end http://localhost:5173/ o ApiRest http://localhost:3000` y la API desde `http://localhost:8080/swagger-ui.html`.

## Mejores Prácticas
- Uso de **buenas prácticas UX/UI** para facilitar la experiencia del usuario.
- Implementación de **técnicas de QA** necesarias para garantizar la calidad del producto.
