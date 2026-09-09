# User API Security

API REST desenvolvida com **Java e Spring Boot** para fins de estudo.

O projeto realiza o gerenciamento de usuários por meio de uma API REST e possui operações de criação, consulta, atualização e exclusão de usuários.

## 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* Lombok

## 📁 Estrutura do projeto

Atualmente, o projeto possui a seguinte organização:

```text
src/main/java
└── com.user_api.security.security
    ├── controllers
    │   └── UsersController
    ├── SecurityApplication
    ├── Users
    ├── UsersRepository
    ├── UsersRequestDTO
    └── UsersResponseDTO
```

## 📡 Endpoints

### Listar usuários

**GET**

```http
/users
```

Retorna todos os usuários cadastrados.

### Cadastrar usuário

**POST**

```http
/users
```

Exemplo de JSON:

```json
{
  "name": "Ana",
  "email": "ana@email.com"
}
```

Retorna **201 Created** após o cadastro.

### Atualizar usuário

**PUT**

```http
/users/{id}
```

Atualiza os dados de um usuário existente.

Exemplo de JSON:

```json
{
  "name": "Ana Lu",
  "email": "analú@email.com"
}
```

Retorna **200 OK** quando o usuário é atualizado e **404 Not Found** caso o usuário não exista.

### Excluir usuário

**DELETE**

```http
/users/{id}
```

Exclui um usuário existente.

Retorna **204 No Content** quando a exclusão é realizada e **404 Not Found** caso o usuário não exista.

## 🗄️ Banco de dados

O projeto utiliza o **H2 Database**, um banco de dados em memória utilizado durante o desenvolvimento da aplicação.

## ▶️ Como executar

Clone o repositório:

```bash
git clone https://github.com/aninha-jpg/api-users-crud.git
```

Entre na pasta do projeto:

```bash
cd user-api-crud
```

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

## 📌 Progresso do projeto

* [x] Criar entidade de usuários
* [x] Configurar banco de dados H2
* [x] Implementar GET para listar usuários
* [x] Implementar POST para cadastrar usuários
* [x] Utilizar DTOs
* [x] Implementar PUT
* [x] Implementar DELETE
* [ ] Implementar autenticação com JWT
* [ ] Configurar autorização e segurança
* [ ] Documentar o processo de autenticação JWT
