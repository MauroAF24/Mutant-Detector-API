# 🧬 Mutant Detector API

Este proyecto es una API desarrollada en **Java 17 con Spring Boot 3** para detectar si una cadena de ADN pertenece a un mutante.  
El sistema analiza una matriz NxN de letras válidas (`A`, `T`, `C`, `G`) y determina si contiene **al menos dos secuencias** de cuatro letras iguales consecutivas en alguna dirección:

- Horizontal
- Vertical
- Diagonal ↘
- Diagonal ↗

El trabajo está basado en el desafío de Mercado Libre.

---

## 📌 ¿Cómo funciona?

La API recibe una matriz de ADN como un arreglo de strings. Ejemplo:

```json
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}
```
El algoritmo recorre toda la matriz y busca secuencias de 4 letras iguales.
Cuando encuentra dos o más, la persona se considera mutante.

Además, cada ADN analizado se guarda (mediante hash SHA-256) en la base de datos H2 en memoria, para evitar procesar ADN duplicado y poder generar estadísticas.

---
## 📂 Estructura del proyecto

```json
src/main/java/com.example.MutantDetectorApplication
│
├── Config
│ └── OpenAPIConfig.java
│
├── controller
│ └── MutantController.java
│
├── dto
│ ├── DnaRequest.java
│ └── StatsResponse.java
│
├── entity
│ └── DnaRecord.java
│
├── exception
│ ├── DnaHashCalculationException.java
│ └── GlobalExceptionHandler.java
│
├── repository
│ └── DnaRecordRepository.java
│
├── service
│ ├── MutantDetector.java
│ ├── MutantService.java
│ └── StatsService.java
│
└── validation
├── ValidDnaSequence.java
└── ValidDnaSequenceValidator.java
```
---
## 🌐 Endpoints

### 🔹 **POST /mutant**

Analiza el ADN recibido.

#### Request:
```json
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
Respuestas:
200 OK → Es mutante

403 Forbidden → NO es mutante

400 Bad Request → ADN inválido

### 🔹GET /stats

#### Devuelve estadísticas generales:
```json
{
"count_mutant_dna": 10,
"count_human_dna": 20,
"ratio": 0.5
}
```
---
## 🛢 Base de datos H2

La aplicación utiliza una base de datos **H2 en memoria**, lo que permite almacenar temporalmente los ADN analizados sin necesidad de configurar un motor externo.

Podés acceder a la consola de H2 desde el navegador:
http://localhost:8081/h2-console

**Datos de conexión:**

- **JDBC URL:** `jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- **Usuario:** `adn`
- **Contraseña:** *(vacío)*

La tabla almacena:

- Hash del ADN analizado
- Si corresponde a un mutante o humano
- Fecha del análisis

---

## 📚 Swagger / API Docs

El proyecto incluye documentación automática con **SpringDoc OpenAPI**, lo que permite visualizar y probar los endpoints de forma sencilla.

- **Swagger UI:** http://localhost:8081/swagger-ui.html
- **OpenAPI Specification (JSON):** *
  http://localhost:8081/api-docs

---

## ▶ Cómo ejecutar el proyecto

### 1. Compilar el proyecto
Ejecutar desde consola:
```json
./gradlew clean build
```
### 2. Levantar la aplicación
```json
./gradlew bootRun
```
### 3. Probar el endpoint `/mutant` con curl
curl -X POST http://localhost:8081/mutant
-H "Content-Type: application/json"
-d "{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}"

---

## 🧪 Testing

El proyecto cuenta con:

- Tests unitarios usando **JUnit 5**
- Mocks con **Mockito**
- Reporte de cobertura de código usando **Jacoco**

### Ejecutar todos los tests:
```json
./gradlew test
```

### Ver el reporte de Jacoco:
build/reports/jacoco/test/html/index.html

---

## ✔ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Swagger / SpringDoc OpenAPI**
- **JUnit 5**
- **Mockito**
- **Jacoco**  

## 📌 Link para acceder al protecto
https://mutant-detector-api-8.onrender.com/swagger-ui/index.html#/
