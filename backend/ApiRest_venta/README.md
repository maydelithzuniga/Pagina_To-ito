# ApiRest_venta

API REST en Spring Boot para la landing page de **To-ito**, un negocio que
vende estampados cristianos (versículos bíblicos, frases, vehículos de
predicación, etc.) en poleras y polos, además de artículos personalizados
elaborados en resina.

## Arquitectura

El proyecto sigue una arquitectura en capas:

```
controller/  -> Expone los endpoints HTTP REST (capa de presentación)
service/     -> Interfaces con las reglas de negocio
service/impl -> Implementación de las reglas de negocio
repository/  -> Repositorios Spring Data JPA (acceso a datos)
entity/      -> Entidades JPA persistidas en la base de datos
dto/         -> Objetos de transferencia (entrada/salida de la API)
mapper/      -> Conversión entre entidades y DTOs
config/      -> Seguridad (Spring Security) y carga de datos iniciales
exception/   -> Manejo global de errores
```

## Base de datos

Al ser una landing page no se necesita una base de datos persistente. Se usa
**H2 en memoria** junto con **Spring Data JPA**, de forma que igual se
trabaja con repositorios reales, pero sin necesidad de instalar ni
configurar un servidor de base de datos externo. El catálogo de productos y
los testimonios de ejemplo se cargan automáticamente al iniciar la
aplicación (`config/DataLoader.java`) y se reinician cada vez que la app se
reinicia.

## Seguridad

Se usa Spring Security con autenticación HTTP Basic:

- **Lectura pública** (sin autenticación): catálogo de productos,
  testimonios, y el enlace de contacto de WhatsApp — pensado para
  alimentar la landing page.
- **Escritura de testimonios pública**: cualquier comprador puede dejar su
  testimonio (`POST /api/testimonios`).
- **Administración protegida** (requiere el usuario administrador):
  crear/editar/eliminar productos y eliminar o listar testimonios no
  aprobados.

Usuario administrador por defecto (configurable por variables de entorno
`ADMIN_USERNAME` / `ADMIN_PASSWORD`):

```
usuario: admin
clave:   admin123
```

## Endpoints principales

| Método | Ruta                        | Acceso   | Descripción                                   |
|--------|-----------------------------|----------|------------------------------------------------|
| GET    | `/api/productos`            | Público  | Lista el catálogo disponible (filtro `?categoria=`) |
| GET    | `/api/productos/destacados` | Público  | Productos destacados para la landing page      |
| GET    | `/api/productos/{id}`       | Público  | Detalle de un producto                         |
| GET    | `/api/productos/admin`      | ADMIN    | Lista todos los productos (incluye no disponibles) |
| POST   | `/api/productos`            | ADMIN    | Crea un producto                               |
| PUT    | `/api/productos/{id}`       | ADMIN    | Actualiza un producto                          |
| DELETE | `/api/productos/{id}`       | ADMIN    | Elimina un producto                            |
| GET    | `/api/testimonios`          | Público  | Lista los testimonios aprobados                |
| GET    | `/api/testimonios/{id}`     | Público  | Detalle de un testimonio                       |
| GET    | `/api/testimonios/admin`    | ADMIN    | Lista todos los testimonios (moderación)       |
| POST   | `/api/testimonios`          | Público  | Un comprador deja su testimonio                |
| DELETE | `/api/testimonios/{id}`     | ADMIN    | Elimina/modera un testimonio                   |
| GET    | `/api/contacto/whatsapp`    | Público  | Enlace de WhatsApp para el call to action      |

## Ejecutar el proyecto

```bash
cd backend/ApiRest_venta
./mvnw spring-boot:run
```

La API queda disponible en `http://localhost:8080`. La consola de H2 está
habilitada en `http://localhost:8080/h2-console` (JDBC URL
`jdbc:h2:mem:toitodb`, usuario `sa`, sin contraseña) para inspeccionar los
datos en memoria durante el desarrollo.

## Variables de entorno

| Variable               | Descripción                                  | Valor por defecto |
|-------------------------|-----------------------------------------------|--------------------|
| `ADMIN_USERNAME`        | Usuario administrador                        | `admin`            |
| `ADMIN_PASSWORD`        | Contraseña del administrador                 | `admin123`         |
| `WHATSAPP_NUMERO`       | Número de WhatsApp (formato internacional)   | `51999999999`      |
| `WHATSAPP_MENSAJE`      | Mensaje predefinido del call to action       | ver `application.properties` |
| `CORS_ALLOWED_ORIGINS`  | Orígenes permitidos para el frontend         | `http://localhost:5173,http://localhost:3000` |
