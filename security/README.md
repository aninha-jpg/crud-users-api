# User API Security

API REST desenvolvida com **Java e Spring Boot** para fins de estudo.

O projeto realiza o gerenciamento básico de usuários e, atualmente, permite listar e cadastrar usuários.

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- Lombok

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

## 📌 Próximos passos

- [x] Criar entidade de usuários
- [x] Configurar banco de dados H2
- [x] Implementar GET para listar usuários
- [x] Implementar POST para cadastrar usuários
- [x] Utilizar DTOs
- [ ] Implementar PUT
- [ ] Implementar DELETE
- [ ] Implementar autenticação e segurança