# Sposób uruchomienia:
W users/src/main/resources/ należy skonfigurować połączenie z bazą. 
```
spring.datasource.url=jdbc:postgresql://host:5432/databasename
spring.datasource.username=username
spring.datasource.password=password
```

Po skonfigurowaniu połączenia z bazą PostgreSQL można już uruchomić lokalnie aplikację. 

# Sposób użycia API 
```
api/users/get-user/{userId}
```
Zwraca dane o wybranym użytkowniku.

```
/api/users/delete-user/{userId}
```
Usuwa wybranego użytkownika.

```
/api/users/new-user
```
```
{
	"name": "John",
	"surname": "Smith",
	"grade": 1,
	"salary": 2000
}
```
Tworzy nowego użytkownika.
```
/api/users/edit-user
```
```
{
	"id": 8,
	"name": "John",
	"surname": "Doe",
	"grade": 2,
	"salary": 2300
}
```
Edytuje użytkownika o wybranym id.

```
/api/users/search-user
```
```
api/users/search-user?salary=2300&name=David&surname=Test&grade=12
```
Zwraca poszukiwanego użytkownika w zależności od podanych query parameters.

