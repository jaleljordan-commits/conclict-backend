# Global Conflict Monitor - Backend Spring Boot

API REST construida con Spring Boot para gestión de conflictos globales.

## Arquitectura

- **Framework**: Spring Boot 3.1.0
- **Base de Datos**: PostgreSQL (producción) / H2 (desarrollo)
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven

## Endpoints API

### Conflictos
- `GET /api/v1/conflicts` - Listar todos los conflictos
- `GET /api/conflicts/{id}` - Obtener conflicto por ID
- `POST /api/conflicts` - Crear nuevo conflicto
- `PUT /api/conflicts/{id}` - Actualizar conflicto
- `DELETE /api/conflicts/{id}` - Eliminar conflicto

### Países
- `GET /api/countries` - Listar países
- `GET /api/countries/{id}` - Obtener país por ID
- `POST /api/countries` - Crear país
- `PUT /api/countries/{id}` - Actualizar país
- `DELETE /api/countries/{id}` - Eliminar país

### Eventos
- `GET /api/events` - Listar eventos
- `GET /api/conflicts/{id}/events` - Eventos de un conflicto

## Configuración

### Variables de Entorno

Para producción, configura estas variables:

- `DATABASE_URL`: URL de conexión PostgreSQL (ej: postgresql://user:pass@host:port/db)
- `DB_DRIVER`: org.postgresql.Driver
- `DB_USERNAME`: Usuario de BD
- `DB_PASSWORD`: Contraseña de BD
- `JPA_DIALECT`: org.hibernate.dialect.PostgreSQLDialect
- `DDL_AUTO`: validate (para producción)
- `CORS_ALLOWED_ORIGINS`: URLs permitidas separadas por coma (ej: https://tu-frontend.vercel.app)
- `PORT`: Puerto del servidor (8080 por defecto)

### Desarrollo Local

Para desarrollo, usa H2 en memoria. Las variables por defecto apuntan a H2.

## Despliegue

Desplegado en Railway con configuración automática desde GitHub.

## Modificaciones para Producción

- Agregada dependencia PostgreSQL en `pom.xml`
- Configuración de BD con variables de entorno en `application.properties`
- Clase `CorsConfig.java` para configuración CORS dinámica
- Inicialización de datos con `DataInitializer.java` (solo si BD está vacía)