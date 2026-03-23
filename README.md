# Icesi Fit — Backend de Actividad Física

Backend desarrollado con **Spring Boot 3.4.4** para gestionar actividad física en la Universidad Icesi. Emplea **Spring Data JPA** con **Hibernate** para el consumo de datos desde una base de datos H2 en memoria.

## Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.4.4 |
| Spring Data JPA / Hibernate | 6.6.11 |
| H2 Database | En memoria |
| Lombok | Última |
| JUnit 5 + Mockito | Incluidos en `spring-boot-starter-test` |
| JaCoCo | 0.8.12 |
| Maven Wrapper | Incluido (`mvnw`) |

## Estructura del Proyecto

```
GymApp/
├── src/main/java/com/icesi/fit/
│   ├── model/            # Entidades JPA
│   │   ├── Usuario.java
│   │   ├── Rol.java          # Entidad con @ManyToMany → Permiso
│   │   ├── Permiso.java      # Entidad con @ManyToMany ← Rol
│   │   ├── Ejercicio.java
│   │   ├── Rutina.java
│   │   ├── RutinaEjercicio.java   # Tabla intermedia (PK compuesta)
│   │   ├── Progreso.java
│   │   ├── Notificacion.java
│   │   ├── Recomendacion.java
│   │   ├── Evento.java
│   │   ├── UsuarioEvento.java     # Tabla intermedia (PK compuesta)
│   │   ├── RutinaEjercicioId.java # @Embeddable
│   │   ├── UsuarioEventoId.java   # @Embeddable
│   │   ├── TipoEjercicio.java     # Enum
│   │   └── TipoNotificacion.java  # Enum
│   ├── repository/       # Repositorios JPA para todas las entidades
│   ├── service/           # Servicios: Usuario, Rol, Permiso + otros
│   ├── controller/        # Controladores REST
│   └── config/            # WebSocket config
├── src/main/resources/
│   ├── application.properties
│   └── import.sql         # Datos iniciales
├── src/test/java/com/icesi/fit/service/
│   ├── UsuarioServiceTest.java   # 15 tests
│   ├── RolServiceTest.java       # 11 tests
│   └── PermisoServiceTest.java   #  9 tests
└── pom.xml
```

## Requisitos Previos

- **Java 17** o superior instalado
- **Git** (para clonar el repositorio)
- No se necesita instalar Maven — el proyecto incluye **Maven Wrapper** (`mvnw`)

## Cómo Ejecutar la Aplicación

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd ProyectoComp2-Gym/GymApp
```

### 2. Ejecutar en modo desarrollo

```bash
# En Windows
.\mvnw.cmd spring-boot:run

# En Linux/Mac
./mvnw spring-boot:run
```

La aplicación se levantará en **http://localhost:8080**.

### 3. Acceder a los endpoints

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/api/usuarios` | GET | Listar todos los usuarios |
| `/api/usuarios/{id}` | GET | Obtener usuario por ID |
| `/api/usuarios` | POST | Crear usuario (requiere rol) |
| `/api/usuarios/{id}` | PUT | Actualizar usuario |
| `/api/usuarios/{id}` | DELETE | Eliminar usuario |
| `/api/usuarios/{id}/entrenador/{entrenadorId}` | PUT | Asignar entrenador |
| `/api/usuarios/{entrenadorId}/asignados/progreso` | GET | Ver progreso de asignados |
| `/api/ejercicios` | GET | Listar ejercicios |
| `/api/rutinas` | GET | Listar rutinas |
| `/api/eventos` | GET | Listar eventos |
| `/api/progresos` | GET | Listar progresos |
| `/api/notificaciones` | GET | Listar notificaciones |

### 4. Consola H2 (explorar la base de datos)

Acceder a: **http://localhost:8080/h2-console**

| Campo | Valor |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:icesifit` |
| User | `sa` |
| Password | *(dejar vacío)* |

## Cómo Probar la Aplicación

### Ejecutar pruebas unitarias

```bash
.\mvnw.cmd test
```

Resultado esperado: **35 tests, 0 failures, 0 errors**

| Clase de Test | Tests | Descripción |
|---------------|-------|-------------|
| `UsuarioServiceTest` | 15 | CRUD + asignar entrenador + progreso de asignados |
| `RolServiceTest` | 11 | CRUD + validación "Evite Roles sin permisos" |
| `PermisoServiceTest` | 9 | CRUD + validación de nombre requerido |

### Verificar cobertura con JaCoCo

```bash
.\mvnw.cmd verify
```

- Genera reporte de cobertura en: `target/site/jacoco/index.html`
- Verifica que la cobertura de línea del paquete `service` sea ≥ 80%
- Resultado esperado: `All coverage checks have been met.`

### Generar el WAR para despliegue

```bash
.\mvnw.cmd clean package
```

El archivo WAR se genera en: `target/icesi-fit-0.0.1-SNAPSHOT.war`

## Despliegue en Tomcat (IAsLab)

1. Copiar `target/icesi-fit-0.0.1-SNAPSHOT.war` al directorio `webapps/` de Tomcat
2. Renombrarlo a `icesi-fit.war` para una URL más limpia
3. Tomcat lo despliega automáticamente
4. Acceder a: `http://<IP_DEL_SERVIDOR>:8080/icesi-fit/api/usuarios`

## Modelo de Datos

### Entidades y Relaciones

- **Usuario** → `@ManyToOne` **Rol** (un usuario tiene un rol obligatorio)
- **Rol** ↔ **Permiso** (relación `@ManyToMany` vía tabla `rol_permiso`)
- **Usuario** → `@ManyToOne` **Usuario** (estudiante asignado a entrenador)
- **Rutina** ↔ **Ejercicio** (relación `@ManyToMany` vía `rutina_ejercicio` con atributos extra)
- **Usuario** ↔ **Evento** (relación `@ManyToMany` vía `usuario_evento` con fecha de inscripción)
- **Progreso**, **Notificacion**, **Recomendacion** → `@ManyToOne` **Usuario**

### Datos Iniciales (import.sql)

| Entidad | Cantidad |
|---------|----------|
| Permisos | 9 |
| Roles | 3 (ADMIN, ENTRENADOR, ESTUDIANTE) |
| Usuarios | 6 (1 admin, 2 entrenadores, 3 estudiantes) |
| Ejercicios | 5 |
| Rutinas | 2 (con 4 ejercicios asignados) |
| Progresos | 4 |
| Eventos | 1 (con 2 inscripciones) |
| Notificaciones | 3 |
| Recomendaciones | 2 |

## Reglas de Negocio

- **Evite usuarios sin Rol**: `UsuarioService.saveUsuario()` lanza excepción si el rol es nulo o no existe.
- **Evite Roles sin permisos**: `RolService.saveRol()` lanza excepción si la lista de permisos está vacía.
- **Validación de entrenador**: Solo usuarios con rol `ENTRENADOR` pueden ser asignados como entrenador.

## Integrantes

| Nombre | Correo |
|--------|--------|
| | |
| | |
| | |
