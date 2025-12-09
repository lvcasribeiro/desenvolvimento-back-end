### Projeto back-end - Restaurante

---

#### 1. Descrição do projeto

Projeto apresentado à disciplina de Desenvolvimento Back-end, do Programa de Residência em Tecnologia da Informação da Universidade Federal de Goiás, como parte dos requisitos necessários para aprovação, sob a orientação da professora doutora Sofia Larissa da Costa Paiva.

---

#### 2. Tecnologias utilizadas

- **Spring boot** - Framework para desenvolvimento de aplicações Java;
- **Spring data JPA** - Persistência de dados;
- **Spring web** - Desenvolvimento de APIs REST;
- **Swagger** - Documentação da API;
- **Maven** - Gerenciamento de dependências; e
- **Banco de dados** - PostgreSQL.

---

#### 3. Pré-requisitos

- Java 25;
- Maven 3.6+;
- Docker.

---

#### 4. Configurações

a) Clone do repositório:
```bash
git clone https://github.com/lvcasribeiro/desenvolvimento-back-end.git
cd demo
```

b) Compilação do projeto:
```bash
mvn clean install
mvn compile
```

c) Execução da aplicação:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

#### 5. Documentação da API (swagger)

A documentação completa da API estará disponível através do Swagger UI em `http://localhost:8080/swagger-ui/index.html#/`.

---

#### 6. Principais endpoints

##### **Usuários** (`/api/v1/usuario`)
Gerenciamento de usuários do sistema (Gerente, Garçom, Cozinha, Caixa):

- `GET /api/v1/usuario` - Listar usuários (paginado)
- `POST /api/v1/usuario` - Criar novo usuário
- `GET /api/v1/usuario/{id}` - Buscar por ID
- `PUT /api/v1/usuario/{id}` - Atualizar usuário

**Tipos de usuário:** `GERENTE`, `GARCOM`, `COZINHA`, `CAIXA`.

---

##### **Mesas** (`/api/mesas`)
Controle de mesas e atendimento:

- `GET /api/mesas` - Listar mesas (com filtros de disponibilidade e garçom)
- `POST /api/mesas` - Criar nova mesa
- `GET /api/mesas/{id}` - Buscar mesa por ID
- `PUT /api/mesas/{id}` - Atualizar mesa
- `DELETE /api/mesas/{id}` - Remover mesa
- `POST /api/mesas/ocupar` - Ocupar mesa
- `POST /api/mesas/{id}/iniciar-atendimento` - Iniciar atendimento
- `POST /api/mesas/{id}/liberar` - Liberar mesa

---

##### **Cardápio e Itens** (`/api/item-cardapio` e `/cardapios`)
Gerenciamento do cardápio do restaurante:

- `GET /cardapios` - Listar cardápios (paginado)
- `POST /api/item-cardapio` - Criar item no cardápio
- `GET /api/item-cardapio/{id}` - Buscar item por ID
- `PUT /api/item-cardapio/{id}` - Atualizar item
- `PUT /api/item-cardapio/{id}/ativar` - Ativar item
- `PUT /api/item-cardapio/{id}/desativar` - Desativar item
- `GET /api/item-cardapio/disponiveis` - Listar apenas disponíveis
- `GET /api/item-cardapio/categoria/{categoriaId}` - Filtrar por categoria

---

##### **Pedidos** (`/api/v1/pedido`)
Gestão completa de pedidos:

- `GET /api/v1/pedido` - Listar pedidos (filtrar por status)
- `POST /api/v1/pedido` - Criar novo pedido
- `GET /api/v1/pedido/{id}` - Buscar pedido por ID
- `POST /api/v1/pedido/{id}/entregar` - Marcar como entregue

**Status do pedido:** `RECEBIDO`, `EM_PREPARO`, `PRONTO`, `ENTREGUE`.

---

##### **Cozinha** (`/api/v1/cozinha`)
Controle do fluxo de preparo na cozinha:

- `GET /api/v1/cozinha/pendentes` - Pedidos aguardando preparo
- `GET /api/v1/cozinha/em-preparo` - Pedidos sendo preparados
- `GET /api/v1/cozinha/prontos` - Pedidos finalizados
- `POST /api/v1/cozinha/{pedidoId}/iniciar` - Iniciar preparo
- `POST /api/v1/cozinha/{pedidoId}/finalizar` - Finalizar preparo

---

##### **Contas** (`/api/v1/conta`)
Gestão de contas dos clientes:

- `GET /api/v1/conta` - Listar contas (paginado)
- `GET /api/v1/conta/{id}` - Buscar conta por ID
- `PUT /api/v1/conta/{id}` - Atualizar conta
- `POST /api/v1/conta/{id}/pagar` - Adicionar pagamento
- `POST /api/v1/conta/finalizar` - Finalizar conta

---

##### **Pagamentos** (`/api/v1/pagamento`)
Controle de pagamentos:

- `GET /api/v1/pagamento` - Listar pagamentos (paginado)
- `POST /api/v1/pagamento` - Registrar pagamento
- `GET /api/v1/pagamento/{id}` - Buscar por ID
- `PUT /api/v1/pagamento/{id}` - Atualizar pagamento
- `DELETE /api/v1/pagamento/{id}` - Remover pagamento

**Tipos de pagamento:** `CARTAO`, `DINHEIRO`, `CHEQUE`.

---

#### 7. Estrutura do projeto

```
src/
├── main/
│   ├── java/
│   │   └── projeto_garcom.com.demo/
│   │       └── package/     
│   │           ├── dto/        
│   │           │   ├── PackageDTO.java
│   │           │   ├── PackageShowDTO.java
│   │           │   ├── PackageRequestDTO.java
│   │           │   └── PackageUpdateDTO.java
│   │           ├── PackageController.java
│   │           ├── PackageEntity.java
│   │           ├── PackageMapper.java
│   │           ├── PackageRepository.java
│   │           ├── PackageService.java
│   │           └── PackageSpecification.java
│   └── resources/
│       ├── application.properties
│       └── [outros recursos]
└── test/
```

---

#### 8. Equipe

- Antonio Leoncio Vieira Neto;
- Lucas Anderson Ribeiro;
- Victor Furtado.