# Video de Demostración - Sistema de Gestión de Evaluaciones

## Acceso al Video

**Link al video:**

[VER VIDEO EN YOUTUBE](https://youtu.be/lg1ME7yxbJM)

---

## Contenido del Video

Este video contiene una **demostración completa y explicación detallada** de todo lo documentado en la wiki del proyecto. El video cubre los siguientes temas:

### 1. Introducción al Sistema
- Descripción general del Sistema de Gestión de Evaluaciones
- Objetivos y alcance del proyecto
- Tecnologías utilizadas (Spring Boot, MongoDB, Java 11)

### 2. Análisis de Diagramas

#### Diagrama de Clases (Desarrollo)
- **Herencia**: Jerarquía de Usuario → Administrador, Profesor, Estudiante
- **Polimorfismo**: Implementación del método `getPermisosEspecificos()` en cada clase
- **Encapsulamiento**: Atributos privados y métodos de validación
- **Relaciones entre packages**: controller, service, repository, model, dto

#### Diagrama de Componentes
- **Arquitectura de 4 capas lógicas**:
  1. Controllers - Capa de presentación (API REST)
  2. Services - Capa de lógica de negocio
  3. Repositories - Capa de acceso a datos
  4. MongoDB - Capa de persistencia
- **5 componentes funcionales**: Usuarios, Cursos, Evaluaciones, Notas, Reportes
- **Flujo de comunicación** entre componentes

#### Diagrama de Despliegue
- **Arquitectura de 3 niveles físicos**:
  1. Cliente (Navegador web)
  2. Servidor de Aplicación (Spring Boot)
  3. Servidor de Base de Datos (MongoDB)
- **Diferencia entre capas lógicas y niveles físicos**
- Justificación de la arquitectura elegida

### 3. Demostración de Código

#### Polimorfismo en Acción
- `Usuario.java` - Clase abstracta con método abstracto
- `Administrador.java` - 6 permisos específicos
- `Profesor.java` - 5 permisos específicos
- `Estudiante.java` - 3 permisos específicos

#### Encapsulamiento y Validaciones
- Atributos privados en todas las entidades
- Métodos de validación: `validarPassword()`, `isActivo()`, `validarNombre()`
- Control de acceso mediante getters/setters

#### Flujo Completo de una Funcionalidad
Ejemplo del proceso de **Login (HU01)**:
1. `UsuarioController.java` - Recibe petición HTTP
2. `UsuarioService.java` - Ejecuta lógica de validación
3. `UsuarioRepository.java` - Consulta MongoDB
4. `Sesion.java` - Crea y gestiona sesión
5. Respuesta con token JWT

#### Clasificación Automática de Notas
- `Nota.java` - Método `getClasificacion()`
- `Estudiante.java` - Método `actualizarClasificacion()`
- Lógica de clasificación: BAJO, MEDIO, ALTO, EXCELENTE

### 4. Historias de Usuario Implementadas

Demostración de las **6 historias de usuario** seleccionadas:

- **HU01**: Autenticación de usuarios con sesiones
- **HU02**: Gestión de usuarios por roles
- **HU03**: Gestión de cursos
- **HU04**: Gestión de evaluaciones con validación de porcentajes
- **HU05**: Registro de notas con clasificación automática
- **HU06**: Consulta de notas y cálculo de promedios

### 5. Pruebas y Demostración en Vivo

- Ejecución de la aplicación con `mvn spring-boot:run`
- Prueba de endpoints con ejemplos reales
- Consultas a MongoDB para verificar persistencia
- Demostración del flujo completo de cada historia de usuario

### 6. Estructura del Proyecto

Explicación de la organización del código:
```
com.universidad.sge/
├── controller/     → API REST endpoints
├── service/        → Lógica de negocio
├── repository/     → Acceso a datos
├── model/
│   ├── entity/    → Entidades del dominio
│   └── enums/     → Enumeraciones
├── dto/           → Data Transfer Objects
└── config/        → Configuración y DataLoader
```

### 7. Conclusiones
- Cumplimiento de objetivos
- Conceptos de POO aplicados (Herencia, Polimorfismo, Encapsulamiento)
- Arquitectura escalable y mantenible
- Buenas prácticas de desarrollo

---

## 📖 Documentación Relacionada

Para profundizar en los temas explicados en el video, consulta:

### Documentación en el Repositorio
- **README.md** - Guía principal del proyecto
- **DiagrmasDelSistema/guion_diagrama_clases_desarrollo.md** - Guión del diagrama de clases
- **DiagrmasDelSistema/guion_diagrama_componentes.md** - Guión del diagrama de componentes
- **DiagrmasDelSistema/guion_diagrama_despliegue.md** - Guión del diagrama de despliegue
- **DOCUMENTACION_DIAGRAMAS.md** - Descripción detallada de todos los diagramas

### Wiki del Proyecto
La wiki contiene información adicional sobre:
- Instalación y configuración del entorno
- Guías de desarrollo
- Estándares de código
- Casos de uso detallados
- Diagramas de secuencia
- Manual de usuario

---

## Audiencia del Video

Este video está diseñado para:
- Estudiantes de Ingeniería de Software
- Desarrolladores que aprenden Spring Boot y MongoDB
- Personas interesadas en arquitectura de software
- Revisores del proyecto académico

---



## Notas Importantes

1. El video complementa toda la documentación escrita en la wiki
2. Se recomienda tener el código abierto mientras se visualiza el video
3. Los timestamps están disponibles en la descripción del video
4. El código mostrado corresponde a la versión final del proyecto

---


## Registro de Actualizaciones

| Fecha | Versión | Cambios |
|-------|---------|---------|
| 2025-11-25 | 1.0 | Video inicial con demostración completa |




