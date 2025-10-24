# 🎬 Cinema Management Backend (Work in Progress)

![Java](https://img.shields.io/badge/Java-21-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-2.7.0-orange?logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-Collection-lightgrey?logo=postman&logoColor=white)

## Descrizione
Questo progetto è un gestionale backend per un cinema, sviluppato con Spring Boot. L’obiettivo è fornire una piattaforma per gestire film, sale, orari e prenotazioni. Il progetto è attualmente in fase di sviluppo.

## Tecnologie
- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **MySQL**
- **Maven** per la gestione delle dipendenze
- **Lombok**
- **MapStruct 1.5.5.Final**
- **Spring Security**
- **Springdoc OpenAPI 2.7.0**

## Funzionalità previste
- Gestione film e programmazione
- Gestione sale e posti disponibili
- Prenotazioni utenti
- Autenticazione e autorizzazione

## Credenziali per test
- **Admin:**
  ```json
  {
    "email": "admin@admin.com",
    "password": "admin123"
  }
  ```
- **Utenti già presenti:** la password di default è `password`

> Nota: quando un utente viene inserito dall'admin, la password sarà impostata di default a `password`. L'utente potrà accedere solo all'endpoint per modificare la propria password. Se un utente si registra autonomamente, può scegliere la propria password al momento della registrazione.

## Porta e accesso alle API
L'applicativo risponde di default sulla porta **8080**.
- **Swagger/OpenAPI UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Come eseguire il progetto
1. Clonare il repository:
   ```bash
   git clone https://github.com/LuigiAlbino/gestionale-cinema-springboot.git
   ```
2. Creare un database MySQL vuoto con il nome `cinema_db`
3. Configurare `application.properties` con le credenziali corrette (username, password).
4. Avviare l’applicazione:
   ```bash
   mvn spring-boot:run
   ```
5. Una volta avviata l’applicazione, verranno inseriti dati di esempio testabili tramite le API esposte.

## Documentazione
- È inclusa una **Postman Collection** per facilitare l’interazione con le API e testare le funzionalità.

