## Requisitos

- Maven
- Spring
- Java 8 (11 melhor)
- Hibernate
- JPA

Baixar as dependências: na raiz do projeto: executar o comando `mvn clean install`.

Para rodar a aplicação localmente, executar o metodo `main` da classe [TrainingApiApplication.java](https://github.com/GuillaumeFalourd/java-training-api/tree/main/src/main/java/br/com/training/TrainingApiApplication.java).

## Endpoint disponíveis (v0)

### Criar usuário

**POST:** `http://localhost:8080/users` com *body*:

```json
{
    "name":"Name",
    "cpf":"cpf",
    "email":"email",
    "birthDate":"1900-01-01"
}
```

### Obter usuário com CPF

**GET:** `http://localhost:8080/users/{cpf}` vai retornar:

```json
{
    "name":"Name",
    "cpf":"cpf",
    "email":"email",
    "birthDate":"1900-01-01"
}
```
