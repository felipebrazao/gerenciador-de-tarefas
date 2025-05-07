# ✅ Gerenciador de Tarefas com Autenticação JWT

Um sistema completo de gerenciamento de tarefas desenvolvido em **Java com Spring Boot**, utilizando **JWT (JSON Web Token)** para autenticação e autorização baseada em **roles (papéis de usuário)**.  
Conta com **testes unitários** para garantir a estabilidade do código e uma arquitetura modular, fácil de manter e escalar.

---

## 🚀 Funcionalidades

- 📌 Cadastro de usuários com roles (`ADMIN`, `USER`)
- 🔐 Autenticação com JWT
- 🛡️ Autorização baseada em papéis
- ✅ CRUD de tarefas
- 📅 Marcar tarefas como concluídas
- 📋 Listar tarefas por usuário
- 🧪 Testes unitários com JUnit e Mockito
- 🧰 Documentação da API com Swagger

---

## 🔐 Segurança

A segurança é implementada com:

- ✅ Autenticação JWT (token gerado no login)
- ✅ Filtros de segurança para proteger endpoints
- ✅ Acesso controlado por roles:
  - `ROLE_ADMIN`: Acesso total
  - `ROLE_USER`: Acesso restrito às próprias tarefas

---

## 💡 Exemplo de Login com Role

```json
{
  "username": "joao",
  "password": "123456",
  "role": "ROLE_USER"
}
```

Tokens JWT devem ser incluídos no header das requisições:

```
Authorization: Bearer <token>
```

---

## 📁 Estrutura do Projeto

```
src/
├── config/          # Configuração do Spring Security e JWT
├── controller/      # Endpoints REST (Auth, Tarefa)
├── dto/             # Data Transfer Objects
├── exception/       # Tratamento de exceções personalizadas
├── model/           # Entidades (Usuário, Tarefa, Role)
├── repository/      # Interfaces de persistência (JPA)
├── security/        # JWT provider, filtros e configs
├── service/         # Lógica de negócio
└── tests/           # Testes unitários
```

---

## 🧪 Testes

Os testes foram escritos com JUnit 5 e Mockito, abrangendo:

- ✅ Services
- ✅ Regras de negócio
- ✅ Integração básica com segurança

### ▶️ Executar testes

```bash
./mvnw test
```

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (jjwt)
- H2 Database
- Lombok
- Swagger (OpenAPI)
- Maven
- JUnit 5 + Mockito

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven

### Passos

```bash
git clone https://github.com/seuusuario/gerenciador-de-tarefas.git
cd gerenciador-de-tarefas
./mvnw spring-boot:run
```

Acesse a API em:  
📍 `http://localhost:8080`

---

## 🔎 Documentação com Swagger

Acesse a documentação da API:

📄 `http://localhost:8080/swagger-ui/index.html`

---

## 🛢️ Console do Banco H2

Para fins de teste, o projeto utiliza banco em memória:

- 🌐 URL: `http://localhost:8080/h2-console`
- JDBC: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: _(em branco)_

---

## 📌 Possíveis Melhorias Futuras

- 🔄 Refresh token para reautenticação
- 🌍 Internacionalização (i18n)
- 🧑‍🤝‍🧑 Relacionamento entre usuários (tarefas compartilhadas)
- ☁️ Deploy em nuvem (Heroku, Vercel, etc.)
- 📧 Notificações por e-mail

---

## 👨‍💻 Autor

Desenvolvido por **Felipe Brazão**  
