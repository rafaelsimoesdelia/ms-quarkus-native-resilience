# Quarkus Resilience Project

Este projeto utiliza **Quarkus** para criar uma API REST resiliente e otimizada para **GraalVM** e execução nativa.

## 🚀 Tecnologias Utilizadas

- **Quarkus**: 3.18.1
- **Java**: 21
- **Hibernate ORM com Panache**
- **PostgreSQL**
- **Docker**: Para execução do PostgreSQL e da aplicação em um ambiente isolado
- **Resiliência**: Utilização de *SmallRye Fault Tolerance* para tolerância a falhas
- **MapStruct**: Para mapeamento entre DTOs e Entidades
- **Lombok**: Para reduzir o código boilerplate

## 🛠️ Configuração do Banco de Dados

```properties
quarkus.package.main-class=com.ignis.api.MainApplication

quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.url=jdbc:postgresql://host.docker.internal:5432/postgres
quarkus.datasource.username=yyyy
quarkus.datasource.password=XXXXXXXX
quarkus.datasource.jdbc.driver=org.postgresql.Driver

# Configuração do Hibernate ORM
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.format-sql=true
```

## 🔨 Construção e Execução

### 🏗️ **Compilar para Native com Docker**

```sh
mvn clean package -Pnative "-Dquarkus.native.container-build=true"
```

### 🐳 **Construir a imagem Docker**

```sh
docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/ms-quarkus-native-resilience .
```

### 🚀 **Executar o container da aplicação**

```sh
docker run -i --rm -p 8080:8080 quarkus/ms-quarkus-native-resilience
```

### 🛢️ **Executar o PostgreSQL no Docker**

```sh
docker run --name postgres -e POSTGRES_PASSWORD=XXXXXXXX -p 5432:5432 -d postgres
```

## 📜 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/com/ignis/api/
 │   │   ├── controller/    # Controladores REST
 │   │   ├── service/       # Regras de negócio
 │   │   ├── repository/    # Interface com o banco de dados
 │   │   ├── mapper/        # MapStruct para conversão DTO ↔ Entidade
 │   │   ├── model/         # Entidades do banco
 │   │   ├── dto/           # Data Transfer Objects
 │   │   ├── interceptor/   # Implementação de Resiliência
 │   ├── resources/         # Arquivos de configuração (application.properties)
 ├── test/                  # Testes automatizados
 ├── Dockerfile.native-micro # Dockerfile para build nativo
 ├── pom.xml                 # Configuração do Maven
```

## 🔄 API REST

### 📌 **Endpoints Disponíveis**

| Método  | Rota            | Descrição                        |
|---------|----------------|--------------------------------|
| GET     | `/roles`       | Lista todas as roles         |
| GET     | `/roles/{id}`  | Obtém uma role pelo ID       |
| POST    | `/roles`       | Cria uma nova role          |
| PUT     | `/roles/{id}`  | Atualiza uma role existente |
| DELETE  | `/roles/{id}`  | Remove uma role             |

## 📌 Exemplo de Requisição

### **Criar uma nova role**

```json
POST /roles
{
  "roleId": 1,
  "name": "Admin"
}
```

### **Resposta**

```json
{
  "roleId": 1,
  "name": "Admin"
}
```

---

💡 **Autor**: `rsdelia`  
📅 **Última Atualização**: 2025-02-04

