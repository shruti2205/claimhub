# ClaimHub

ClaimHub is a Spring Boot 3 claim-management microservice. It uses PostgreSQL, Spring Data JPA, Bean Validation, Actuator, and springdoc OpenAPI.

## Run locally

Create a PostgreSQL database named `claimhub`, then run:

```powershell
./mvnw.cmd spring-boot:run
```

The default `dev` profile uses `jdbc:postgresql://localhost:5432/claimhub` with `claimhub` / `claimhub`; override these with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

Useful endpoints: `/swagger-ui.html`, `/actuator/health`, `/actuator/info`.

For production, activate the `prod` profile and set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` explicitly.
