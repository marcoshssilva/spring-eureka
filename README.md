# spring-boot-eureka

Eureka service discovery to Spring Boot Apps and supported as NodeJS, Quarkus, Micrometer or FastAPI

## Initial Username and Password

You can log using initial user **admin** with credentials **admin**

## Change admin password using REST API

You can call endpont `/api/admin/reset-password` to change default password from any user

Example:
```
 curl -X 'POST' http://localhost:8761/api/admin/reset-password \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "admin", "newPassword": "PUT_YOUR_NEW_PASSWORD_HERE" }' \
   -v
```

## Create new user using REST API

Endpoint: `/api/admin/create-user`

```
curl -X 'POST' http://localhost:8761/api/admin/create-user \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "YOUR_USERNAME", "password": "YOUR_PASSWORD", "enabled": "true", "roles": ["ADMIN", "READER"] }' \
   -v
```