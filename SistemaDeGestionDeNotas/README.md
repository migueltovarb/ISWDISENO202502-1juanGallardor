# Sistema de Gestión de Evaluaciones (SGE)

Sistema académico desarrollado con Spring Boot y MongoDB para la gestión de usuarios, cursos, evaluaciones y notas en una institución educativa.

## Tecnologías Utilizadas

- **Java**
- **Spring Boot**
- **MongoDB** (NoSQL)
- **Spring Data MongoDB**
- **Maven**

## Arquitectura del Sistema

### Arquitectura de Software (4 Capas Lógicas)

1. **Controllers** - Capa de presentación (API REST)
2. **Services** - Capa de lógica de negocio
3. **Repositories** - Capa de acceso a datos
4. **MongoDB** - Capa de persistencia

### Arquitectura de Despliegue (3 Niveles Físicos)

1. **Cliente** - Navegador web o aplicación frontend
2. **Servidor de Aplicación** - Spring Boot (contiene las 4 capas lógicas)
3. **Servidor de Base de Datos** - MongoDB

## Estructura del Proyecto

```
NotasSpringYMongo/
├── SistemaDeGestionDeNotas/
│   └── src/main/java/com/universidad/sge/
│       ├── controller/          # Controladores REST
│       ├── service/              # Lógica de negocio
│       ├── repository/           # Acceso a datos
│       ├── model/
│       │   ├── entity/          # Entidades del dominio
│       │   └── enums/           # Enumeraciones
│       ├── dto/                 # Data Transfer Objects
│       └── config/              # Configuración
├── DiagrmasDelSistema/          # Diagramas SVG y guiones
└── README.md
```

## Funcionalidades Implementadas

El sistema implementa las siguientes historias de usuario:

### HU01 - Autenticación de Usuarios
- Login con username o email
- Validación de credenciales
- Gestión de sesiones con tokens
- Control de intentos fallidos (bloqueo después de 5 intentos)
- Sesiones con expiración de 60 minutos

### HU02 - Gestión de Usuarios
- Creación de usuarios (Admin, Profesor, Estudiante)
- Sistema de roles con permisos específicos
- Polimorfismo: cada rol tiene permisos diferentes
- Activación/desactivación de usuarios

### HU03 - Gestión de Cursos
- Creación y configuración de cursos
- Asignación de profesores
- Inscripción de estudiantes

### HU04 - Gestión de Evaluaciones
- Creación de evaluaciones por curso
- Validación de porcentajes (total no debe exceder 100%)
- Control de fechas y descripciones

### HU05 - Registro de Notas
- Registro de calificaciones (escala 0.0 - 5.0)
- Cálculo automático de aporte al promedio
- Clasificación automática (BAJO, MEDIO, ALTO, EXCELENTE)
- Registro de profesor que califica

### HU06 - Consulta de Notas
- Consulta de notas por estudiante
- Consulta de notas por curso
- Cálculo de promedios por curso
- Cálculo de promedio general

## Características Técnicas

### Polimorfismo
La clase abstracta `Usuario` define el método `getPermisosEspecificos()` que cada subclase implementa de forma diferente:
- **Administrador**: 6 permisos (gestión completa del sistema)
- **Profesor**: 5 permisos (gestión de evaluaciones y notas)
- **Estudiante**: 3 permisos (consulta de notas y promedios)

### Encapsulamiento
- Todos los atributos son privados
- Acceso controlado mediante getters/setters
- Métodos de validación para reglas de negocio

### Clasificación Automática
El sistema clasifica automáticamente las notas:
- **BAJO**: 0.0 - 2.9
- **MEDIO**: 3.0 - 3.9
- **ALTO**: 4.0 - 4.5
- **EXCELENTE**: 4.6 - 5.0
- **SIN_CALIFICAR**: Sin nota asignada

## Instalación y Ejecución

### Prerrequisitos
- Java 11 o superior
- Maven 3.6+
- MongoDB 4.0+ corriendo en localhost:27017

### Pasos

1. Clonar el repositorio:
```bash
git clone https://github.com/juangallardo19/NotasSpringYMongo.git
cd NotasSpringYMongo/SistemaDeGestionDeNotas
```

2. Compilar el proyecto:
```bash
mvn clean install -DskipTests
```

3. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Datos de Prueba

El sistema carga automáticamente usuarios de prueba al iniciar:

### Administrador
- **Username**: `admin`
- **Password**: `admin123`
- **Email**: `admin@universidad.edu.co`

### Profesor
- **Username**: `maria.gonzalez`
- **Password**: `profesor123`
- **Email**: `maria.gonzalez@universidad.edu.co`

### Estudiante
- **Username**: `juan.perez`
- **Password**: `estudiante123`
- **Email**: `juan.perez@universidad.edu.co`

## Endpoints Principales

### Autenticación
```
POST /api/usuarios/login
Body: {
  "username": "admin",
  "password": "admin123"
}
```

### Usuarios
```
GET    /api/usuarios              # Listar usuarios
POST   /api/usuarios              # Crear usuario
GET    /api/usuarios/{id}         # Obtener usuario
PUT    /api/usuarios/{id}         # Actualizar usuario
DELETE /api/usuarios/{id}         # Eliminar usuario
```

### Cursos
```
GET    /api/cursos                # Listar cursos
POST   /api/cursos                # Crear curso
GET    /api/cursos/{id}           # Obtener curso
PUT    /api/cursos/{id}           # Actualizar curso
```

### Notas
```
GET    /api/notas                 # Listar notas
POST   /api/notas                 # Registrar nota
GET    /api/notas/estudiante/{id} # Notas de estudiante
GET    /api/notas/curso/{id}      # Notas de curso
```

## Documentación

### Diagramas
Los diagramas del sistema están en la carpeta `DiagrmasDelSistema/`:
- **DiagramaClasesSistemaNotas.svg** - Diagrama de clases conceptual
- **DiagramaClasesDesarrollo.svg** - Diagrama de clases de desarrollo
- **DiagramaComponentes.svg** - Diagrama de componentes
- **DiagramaDespliegue.svg** - Diagrama de despliegue

### Guiones de Exposición
- **guion_diagrama_clases_desarrollo.md** - Explica herencia, polimorfismo y encapsulamiento
- **guion_diagrama_componentes.md** - Explica arquitectura de 4 capas
- **guion_diagrama_despliegue.md** - Explica arquitectura de 3 niveles

### Video y Wiki
Ver `VIDEO_README.md` para acceder al video de demostración y documentación completa en la wiki.


## Licencia

Este proyecto es de uso académico.

