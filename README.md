# CarStore - Sistema Simples de Gerenciamento de Carros

Sistema básico para gerenciamento de carros com operações CRUD usando Spring Boot.

## 🚀 Como Executar

1. **Clone/baixe o projeto**
2. **Execute no IntelliJ IDEA** ou via terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Acesse:** http://localhost:8080

## 🛠️ Funcionalidades

### Interface Web (http://localhost:8080)
- **Criar** novo carro
- **Listar** todos os carros
- **Editar** carro existente
- **Excluir** carro

### API REST (http://localhost:8080/api/cars)
- **GET** `/api/cars` - Listar todos os carros
- **GET** `/api/cars/{id}` - Buscar carro por ID
- **POST** `/api/cars` - Criar novo carro
- **PUT** `/api/cars/{id}` - Atualizar carro
- **DELETE** `/api/cars/{id}` - Excluir carro

## 📝 Modelo de Dados

```json
{
    "id": 1,
    "brand": "Toyota",
    "model": "Corolla", 
    "year": 2022,
    "price": 85000.00,
    "color": "Prata"
}
```

## 🔧 Tecnologias

- Spring Boot 3.2.0
- Spring Web MVC
- Thymeleaf
- Bean Validation
- Bootstrap 5 (frontend simples)

## 📁 Estrutura

```
carstore/
├── src/main/java/com/example/carstore/
│   ├── CarstoreApplication.java
│   ├── controller/
│   │   ├── WebController.java
│   │   └── CarRestController.java
│   ├── model/
│   │   └── Car.java
│   └── service/
│       ├── GenericService.java
│       └── impl/CarServiceImpl.java
├── src/main/resources/
│   ├── templates/index.html
│   └── application.properties
└── pom.xml
```

## 💾 Dados

- **Armazenamento:** Em memória (HashMap)
- **Dados iniciais:** 5 carros de exemplo
- **Persistência:** Apenas durante execução da aplicação

## 🧪 Testando

### Via Interface Web:
1. Acesse http://localhost:8080
2. Use o formulário para adicionar carros
3. Use botões "Editar" e "Excluir" na tabela

### Via API (Postman/cURL):
```bash
# Listar carros
curl -X GET http://localhost:8080/api/cars

# Criar carro
curl -X POST http://localhost:8080/api/cars \
  -H "Content-Type: application/json" \
  -d '{"brand":"BMW","model":"X3","year":2023,"price":120000.00,"color":"Azul"}'
```