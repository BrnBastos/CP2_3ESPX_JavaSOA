<<<<<<< HEAD
# Meeting Room API

Nome: Bruno Silva Bastos 
RM: rm550416

## Descrição

API desenvolvida em Java com Spring Boot para gerenciar salas de reunião e reservas.

O sistema te deixa criar, listar, buscar, atualizar e remover salas e também criar, listar e cancelar reservas.

A API está validando o conflito entre os horários para não ser possível duas reservas estarem arivas na mesma sala e no mesmo tempo.

## O Que Utilizei:

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- H2 Database
- Swagger / OpenAPI
- Maven
- JUnit

# Como executar e testar

Abra o projeto no IntelliJ e rode a classe:

```txt
MeetingRoomApiApplication
```

A API roda em:

```txt
http://localhost:8080
```

Antes de testar os endpoints, faça login:

```txt
POST /auth/login
```

```json
{
  "username": "admin",
  "password": "123456"
}
```

Depois copie o token e use no header das outras chamadas:

```txt
Authorization: Bearer SEU_TOKEN
```

Endpoints usados no teste:

```txt
POST /salas
GET /salas
GET /salas/1
PUT /salas/1
DELETE /salas/1

POST /reservas
GET /reservas
GET /reservas?salaId=1
PATCH /reservas/1/cancelar
```

Exemplo de sala:

```json
{
  "nome": "Sala Azul",
  "capacidade": 10,
  "localizacao": "Bloco A"
}
```

Exemplo de reserva:

```json
{
  "salaId": 1,
  "dataHoraInicio": "2026-04-27T10:00:00",
  "dataHoraFim": "2026-04-27T11:00:00",
  "responsavel": "Bruno"
}
```

Swagger:

```txt
http://localhost:8080/swagger-ui.html
```

H2:

```txt
http://localhost:8080/h2-console
```