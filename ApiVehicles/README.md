# Vehicle API

API REST para la gestión de vehículos desarrollada con Spring Boot y MongoDB.

## Descripción

Sistema de gestión de inventario de vehículos que permite realizar operaciones CRUD completas, búsquedas avanzadas y validaciones de datos. La API está diseñada para manejar información detallada de vehículos incluyendo marca, modelo, año, placa, tipo de combustible, kilometraje y precio.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.12**
- **Spring Data MongoDB**
- **MongoDB Atlas**
- **Maven**
- **Swagger/OpenAPI 3.0** (Documentación)
- **Bean Validation** (Validación de datos)

## Requisitos Previos

- JDK 17 o superior
- Maven 3.6+
- Conexión a Internet (para acceder a MongoDB Atlas)

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/juangallardo19/ApiSpting.git
cd ApiSpting
```

### 2. Configurar MongoDB

La aplicación está configurada para conectarse a MongoDB Atlas. La configuración se encuentra en `src/main/resources/application.properties`.

### 3. Compilar el proyecto

```bash
mvn clean package
```

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O ejecutar el JAR generado:

```bash
java -jar target/vehicle-api-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8080`

## Documentación API

Una vez iniciada la aplicación, accede a la documentación interactiva Swagger:

```
http://localhost:8080/swagger-ui.html
```

## Endpoints Principales

### Operaciones CRUD

- `GET /api/vehicles` - Obtener todos los vehículos
- `GET /api/vehicles/{id}` - Obtener vehículo por ID
- `POST /api/vehicles` - Crear nuevo vehículo
- `PUT /api/vehicles/{id}` - Actualizar vehículo completo
- `PATCH /api/vehicles/{id}` - Actualizar parcialmente
- `DELETE /api/vehicles/{id}` - Eliminar vehículo

### Búsquedas

- `GET /api/vehicles/placa/{placa}` - Buscar por placa
- `GET /api/vehicles/marca/{marca}` - Filtrar por marca
- `GET /api/vehicles/color/{color}` - Filtrar por color
- `GET /api/vehicles/combustible/{tipo}` - Filtrar por tipo de combustible
- `GET /api/vehicles/precio?min=X&max=Y` - Rango de precios
- `GET /api/vehicles/years?startYear=X&endYear=Y` - Rango de años
- `GET /api/vehicles/search` - Búsqueda multi-criterio

### Utilidades

- `GET /api/vehicles/check-placa/{placa}` - Verificar disponibilidad de placa
- `GET /api/vehicles/stats/marca/{marca}` - Estadísticas por marca

## Ejemplo de Uso

### Crear un vehículo

```bash
POST http://localhost:8080/api/vehicles
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "año": 2023,
  "color": "Rojo",
  "placa": "ABC123",
  "tipoCombustible": "GASOLINA",
  "kilometraje": 5000,
  "precio": 80000000,
  "descripcion": "Vehículo en excelente estado"
}
```

### Respuesta exitosa

```json
{
  "id": "507f1f77bcf86cd799439011",
  "marca": "TOYOTA",
  "modelo": "COROLLA",
  "año": 2023,
  "color": "ROJO",
  "placa": "ABC123",
  "tipoCombustible": "GASOLINA",
  "kilometraje": 5000.0,
  "precio": 80000000.0,
  "descripcion": "Vehículo en excelente estado",
  "disponible": true,
  "fechaCreacion": "2025-11-20T17:00:00",
  "fechaActualizacion": "2025-11-20T17:00:00"
}
```

## Validaciones

- **Marca**: 2-50 caracteres, obligatorio
- **Modelo**: 1-50 caracteres, obligatorio
- **Año**: Número positivo, obligatorio
- **Color**: 3-20 caracteres, obligatorio
- **Placa**: Formato ABC-123 o ABC123, único
- **Tipo Combustible**: GASOLINA | DIESEL | ELECTRICO | HIBRIDO
- **Kilometraje**: Número positivo
- **Precio**: Número positivo

## Características

- Validación automática de datos de entrada
- Normalización de texto a mayúsculas
- Control de placas duplicadas (índice único)
- Manejo centralizado de errores
- Respuestas JSON consistentes
- Documentación OpenAPI/Swagger
- Soporte CORS
- Auditoría de fechas (creación y actualización)

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/example/vehicleapi/
│   │   ├── controller/      # Controladores REST
│   │   ├── service/         # Lógica de negocio
│   │   ├── repository/      # Acceso a datos
│   │   ├── model/           # Modelos de datos
│   │   └── exception/       # Manejo de excepciones
│   └── resources/
│       └── application.properties
└── test/
```

## Manejo de Errores

La API retorna códigos HTTP estándar:

- `200 OK` - Operación exitosa
- `201 Created` - Recurso creado
- `400 Bad Request` - Datos inválidos
- `404 Not Found` - Recurso no encontrado
- `409 Conflict` - Placa duplicada
- `500 Internal Server Error` - Error del servidor

## Autor

**Juan Pablo Gallardo Rojas**
Diseño de Software - 4to Semestre

## Repositorio

[https://github.com/juangallardo19/ApiSpting](https://github.com/juangallardo19/ApiSpting)
