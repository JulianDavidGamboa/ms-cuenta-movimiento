# ms-cuenta-movimiento
**Microservicio de Cuenta y Movimiento - Prueba Técnica Arquitectura Microservicios (Senior)**

## Descripción
Microservicio responsable de gestionar **Cuenta** y **Movimiento** del banco.

**Nivel alcanzado: Senior**  
Cumple con separación de responsabilidades, arquitectura hexagonal, patrones Repository, y comunicación asíncrona hacia `ms-cuenta-movimiento`.

## Documentacion Postman
https://documenter.getpostman.com/view/8056845/2sB3Wtre3N

## Como ejecutar el microservicio
1. Existen dos microservicios **ms-cliente-persona** y **ms-cuenta-movimiento**.
2. Primero debe ejecutar el microservicio **ms-cliente-persona** con el siguiente comando:
   ```bash
   docker compose up -d
   ```
   **NOTA**: Se hace de esta forma porque este microservicio contiene el zookeeper, kafka, redis, las bases de datos y el microservicio ms-cliente-persona.
3. Despues de leventar el microservicio **ms-cliente-persona**, debe dirigirse a este repositorio ejecutar el microservicio con el siguiente comando:
   ```bash
   docker compose up -d
   ```

## Coleccion de Postman
La coleccion de postman se encuentra en la ruta: src/main/resources/request/BANK.postman_collection.json

## Tecnologías
- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- Redis
- Spring Kafka
- PostgreSQL
- Maven
- Docker

## Arquitectura
```plaintext
src/main/java/com/mybank/client/
└───msclientepersona
    ├───application
    │   ├───dto
    │   ├───mapper
    │   ├───rest
    │   └───service
    ├───domain
    │   ├───event
    │   ├───exception
    │   ├───model
    │   ├───port
    │   │   └───out
    │   └───usecase
    └───infrastructure
        └───adapter
            └───jpa
                ├───entity
                └───repository
```



## Endpoints (F1)
| Método | Endpoint           | Descripción               |
|--------|--------------------|---------------------------|
| POST   | `/clients`         | Crear cliente             |
| GET    | `/clients/{id}`    | Obtener cliente por ID    |
| PUT    | `/clients/{id}`    | Actualizar cliente        |
| DELETE | `/clients/{id}`    | Eliminar cliente          |
| GET    | `/clients`         | Listar todos los clientes |

## Comunicación Asíncrona (Senior)
Al crear, actualizar o eliminar un cliente, se publica un evento en Kafka:
```json
{
  "eventId": "uuid",
  "eventType": "CREATED|UPDATED|DELETED",
  "clientId": "string",
  "name": "Jose Lema"
}
