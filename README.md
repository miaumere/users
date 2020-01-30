# Sposób uruchomienia:

## Połączenie z bazą

W users/src/main/resources/ należy skonfigurować połączenie z bazą.

```
spring.datasource.url=jdbc:postgresql://host:5432/{{databasename}}
spring.datasource.username={{username}}
spring.datasource.password={{password}}
```

Po skonfigurowaniu połączenia z bazą PostgreSQL można już uruchomić lokalnie aplikację.

## Uruchomienie

Aby uruchomić aplikacje należy posiadać:

- [Java Dev Kit (w wersji min. 8)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Apache Maven](https://maven.apache.org/download.cgi)

Następnie w kontekście katalogu głównego uruchomić aplikacje komendą:

```
./mvnw spring-boot:run
```
# API

| Endpoint                          |                                    Opis                                     | Metoda |
| --------------------------------- | :-------------------------------------------------------------------------: | :----: |
| `/api/users/get-user/{userId}`    |                     Zwraca dane o wybranym użytkowniku.                     |  GET   |
| `/api/users/delete-user/{userId}` |                        Usuwa wybranego użytkownika.                         | DELETE |
| `/api/users/new-user`             |                         Tworzy nowego użytkownika.                          |  POST  |
| `/api/users/edit-user`            |                     Edytuje użytkownika o wybranym id.                      |  PUT   |
| `/api/users/search-user`          | Zwraca poszukiwanego użytkownika w zależności od podanych query parameters. |  GET  |

## Parametry / requets body dla endpointów (przykłady)

**Tworzenie nowego użytkownika**
_Request body:_

```
{
	"name": "John",
	"surname": "Smith",
	"grade": 1,
	"salary": 2000
}
```
---
**Edycja istniejącego użytkownika**

_Request body:_

```
{
	"id": 8,
	"name": "John",
	"surname": "Doe",
	"grade": 2,
	"salary": 2300
}
```

---

**Wyszukiwanie użytkownika**

_QueryParams:_

```
?salary=2300&name={{name}}&surname={{surname}}&grade={{grade}}
```


# Użyte technologie
PostgreSQL, Java 8, Spring Boot 2.2.4, Maven

