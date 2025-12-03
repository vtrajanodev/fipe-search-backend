
# FIPE Search API 🚗💰

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.x-green)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)
![License](https://img.shields.io/badge/License-MIT-blue)

API para consulta de preços de veículos usando a **tabela FIPE**.

---

## 🌐 Links importantes

[![API](https://img.shields.io/badge/API-FIPE%20Search-brightgreen)](https://fipe-search-backend-production.up.railway.app/)  
[![Swagger UI](https://img.shields.io/badge/Swagger-UI-blue)](https://fipe-search-backend-production.up.railway.app/swagger-ui.html)  
[![OpenAPI JSON](https://img.shields.io/badge/OpenAPI-JSON-orange)](https://fipe-search-backend-production.up.railway.app/v3/api-docs)

---

## 🚀 Tecnologias

- **Java 17**  
- **Spring Boot 3.4.x**  
- **Maven**  
- **Swagger / OpenAPI**  
- **Deploy:** Railway

---

## 📦 Funcionalidades

- Listar marcas de veículos (`cars`, `motorcycles`, `trucks`)  
- Listar modelos por marca  
- Consultar preço de modelo específico e ano  
- Histórico de preços por ano  
- Validação de tipos de veículos via `Enum`

---

## 🛠 Como rodar localmente

```bash
git clone git@github.com:vtrajanodev/fipe-search.git
cd fipe-search
mvn clean install
mvn spring-boot:run
```

Acesse a API: `http://localhost:8080/fipe`  
Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## 🧪 Testes

```bash
mvn test
```

Testes cobrem:  
- Services  
- Controllers
  
---

## ⚡ Exemplos de endpoints

- **Marcas de carros:**  
`GET /fipe/cars/brands`

- **Modelos por marca:**  
`GET /fipe/cars/brands/{brandId}/models`

- **Preço por modelo/ano:**  
`GET /fipe/cars/brands/{brandId}/models/{modelId}/years/{yearId}`

- **Histórico de preços:**  
`GET /fipe/cars/brands/{brandId}/models/{modelId}/history`

---

## 📄 Licença

MIT License © Victor Trajano

---

🔥 **FIPE Search API** – Backend pronto para integração com front-end, mobile ou qualquer cliente HTTP.
