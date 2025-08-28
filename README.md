# 🔐 Spring Cloud Gateway – JWT + API Key Injection

This project demonstrates how to use **Spring Cloud Gateway** as a security and routing layer for microservices, combining:

* **JWT Authentication with Keycloak**
* **API Key protection for backend services**
* **Automatic API Key injection by the Gateway**

---

## 🏗️ Architecture

* **Gateway (8080)**

    * Verifies **JWT tokens** issued by Keycloak.
    * Forwards valid requests to backend services.
    * Automatically injects `x-api-key` header into requests.

* **Service A (8081)**

    * Protected by **API Key validation**.

* **Service B (8082)**

    * Protected by **API Key validation**.

* **Keycloak (9090)**

    * Identity provider for **authentication**.
    * Issues JWT tokens for clients.

---

## ⚙️ Core Functions

1. **Authentication (Keycloak)**

    * Clients authenticate with Keycloak and receive a JWT token.

2. **Authorization (Gateway)**

    * Gateway validates JWT before allowing access to routes.

3. **API Key Injection**

    * Clients **do not need** to send API keys.
    * Gateway enriches requests with `x-api-key` automatically.

4. **Backend Security**

    * Services (A & B) only accept requests with the correct API key.
    * This ensures **separation of concerns**:

        * Clients handle JWTs.
        * Services handle API key validation.

---

## 📖 Why This Design?

* ✅ **Decoupling** → Services don’t need to handle JWT parsing.
* ✅ **Security** → JWT is validated at the gateway, services stay isolated.
* ✅ **Flexibility** → Easy to add new services by configuring routes.
* ✅ **Centralized Control** → API keys are managed only at the gateway.

---

## ✅ Summary

This project shows how to:

* Use **Keycloak** for JWT-based authentication.
* Use **Spring Cloud Gateway** to enforce security policies.
* Inject `x-api-key` automatically, removing that burden from clients.
* Keep backend services simple by validating only API keys.

---
