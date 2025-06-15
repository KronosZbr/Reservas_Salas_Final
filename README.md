# 🏢 Sistema de Reservas de Salas – Arquitetura com Microserviços

Projeto desenvolvido com foco em **microserviços desacoplados**, permitindo o gerenciamento independente de usuários, salas e reservas. A arquitetura utiliza contêineres Docker para empacotamento e orquestração dos serviços.

## Tecnologias Utilizadas
- **Java 17 + Spring Boot 3.4.3**
- **PostgreSQL** (um banco para cada serviço)
- **Docker & Docker Compose**
- **Adminer** (para gerenciar os bancos de dados)
- **RabbitMQ** (para comunicação assincrona)

## Estrutura do Projeto

```
reserva-salas/
│-- microsala/       # Microserviço de Sala
|   ├──demo/
│     ├── Dockerfile
│     ├── src/
│-- microuser/       # Microserviço de Usuário
|   ├──demo/
│     ├── Dockerfile
│     ├── src/
│-- microreserva/    # Microserviço de Reserva
|   ├──demo/
│     ├── Dockerfile
│     ├── src/
│-- gateway/    # Microserviço de api gateway
|   ├──demo/
│     ├── Dockerfile
│     ├── src/
│-- docker-compose.yml # Gerencia todos os serviços
│-- README.md          # Documentação do projeto
```

---

## Como Rodar o Projeto 🚀

### 1️⃣ **Rodar todos os serviços com Docker Compose**
```sh
docker-compose up -d --build
```
Isso irá subir:
- **3 microserviços** (`microuser`, `microsala`, `microreserva`)
- **3 bancos de dados PostgreSQL**
- **Adminer** para gestão do banco
- **RabbitMQ** para comunicação assincrona
- **API Gateway** para centralização das chamadas http

### 3️⃣ **Acessar o Adminer** (Gerenciador de Banco)
- URL: `http://localhost:8080`
- Sistema: **PostgreSQL**
- Servidor: **localhost**
- Usuário: **postgres**
- Senha: **admin**
- Base de Dados: **usersdb**, **salasdb**, **reservadb**

---

## Testando as APIs no Postman

### **📌 microuser (`8081`)**
#### **Criar um Usuário**
**POST** `http://localhost:8081/users`
```json
{
  "nome": "Arthur Ritzel",
  "email": "ritzelarthur@email.com",
  "senha": "123456",
  "telefone": "11987654321",
  "rua": "Rua das Resenhas",
  "numero": "123",
  "cidade": "Toledo",
  "cep": "01010-010",
  "cpf": "12345678901",
  "dataNascimento": "2005-02-18",
  "dataCadastro": "2025-02-25"
}
```

#### **Listar Usuários**
**GET** `http://localhost:8081/users`

---

### **📌 microsala (`8082`)**
#### **Criar uma Sala**
**POST** `http://localhost:8082/salas`
```json
{
  "nome": "Sala de Reunião",
  "capacidade": 5
}
```

#### **Listar Salas**
**GET** `http://localhost:8082/salas`

---

### **📌 microreserva (`8083`)**
#### **Criar uma Reserva**
**POST** `http://localhost:8083/reservas/salvar
```json
{
  "dataHora": "2025-08-01T14:00:00",
  "sala_id": 1,
  "usuario_id": 1
}
```

#### **Listar Reservas**
**GET** `http://localhost:8083/reservas`

---


