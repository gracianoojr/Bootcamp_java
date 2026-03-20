# 🚀 Gerenciamento de Usuários

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de usuários.

O projeto implementa operações CRUD completas com **validações de negócio, tratamento global de erros, testes automatizados e aplicação de princípios SOLID**.

---

# 📌 Tecnologias utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Jakarta Validation
- JUnit 5
- Mockito
- MockMvc
- Git / GitHub

---

# 🏗️ Arquitetura do projeto

O projeto segue arquitetura em camadas:

Controller
↓
Service
↓
Repository
↓
Database

## 📂 Estrutura de pacotes

app
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── service
└── validation


### Responsabilidade das camadas

**Controller**
- recebe requisições HTTP
- retorna respostas da API

**Service**
- coordena a lógica de negócio

**Repository**
- acessa o banco de dados com Spring Data JPA

**DTO**
- transporta dados entre API e aplicação

**Mapper**
- converte DTO ↔ entidade

**Validation**
- contém regras de validação da aplicação

**Exception**
- tratamento global de erros

---

# ⚙️ Funcionalidades

✔ Cadastrar usuário  
✔ Listar usuários  
✔ Buscar usuário por ID  
✔ Atualizar usuário  
✔ Deletar usuário  

✔ Validação de email  
✔ Validação de email duplicado  
✔ Validação de número  
✔ Validação de senha forte  

✔ Tratamento global de erros  
✔ Testes automatizados

---

# 🔐 Regras de validação

## Email
- obrigatório
- deve possuir formato válido
- não pode ser duplicado

## Número
- obrigatório
- apenas dígitos
- entre **8 a 15 caracteres**

## Senha forte
A senha deve possuir:

- mínimo **8 caracteres**
- pelo menos **1 letra maiúscula**
- pelo menos **1 letra minúscula**
- pelo menos **1 número**
- pelo menos **1 caractere especial**

---

# 🧱 Princípios SOLID aplicados

## SRP — Single Responsibility Principle

Cada classe possui uma única responsabilidade.

Exemplos:

- `UsuarioController` → camada HTTP  
- `UsuarioService` → lógica de negócio  
- `UsuarioMapper` → conversão DTO ↔ entidade  
- `validators` → validações de regras de negócio

---

## OCP — Open/Closed Principle

O sistema está **aberto para extensão e fechado para modificação**.

Novas validações podem ser adicionadas sem alterar o `UsuarioService`.

📡 Endpoints da API:

Criar usuário: POST /usuarios

Exemplo de body:
{
  "nome": "João",
  "email": "joao@email.com",
  "numero": "11999999999",
  "senha": "Senha@123"
}

Listar usuários: GET /usuarios

Buscar usuário por ID: GET /usuarios/{id}

Atualizar usuário: PUT /usuarios/{id}

Exemplo:
{
  "nome": "João Atualizado",
  "email": "joao@email.com",
  "numero": "11999999999",
  "senha": "Senha@123"
}

Deletar usuário: DELETE /usuarios/{id}

--------------------------------------
## 📂 Estrutura do projeto

Bootcamp_java_deloitte
├── src
│ ├── main
│ │ └── java/app
│ │ ├── controller
│ │ ├── dto
│ │ ├── exception
│ │ ├── mapper
│ │ ├── model
│ │ ├── repository
│ │ ├── service
│ │ └── validation
│ │
│ └── test
│ └── java/app
│ ├── controller
│ ├── mapper
│ ├── repository
│ ├── service
│ └── validation
│
├── pom.xml
└── README.md





