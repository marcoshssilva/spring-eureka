# spring-boot-eureka

Eureka service discovery to Spring Boot Apps and supported as NodeJS, Quarkus, Micrometer or FastAPI

[![Quality gate](https://qsonar.starlord443.dev/api/project_badges/quality_gate?project=marcoshssilva-spring-eureka)](https://qsonar.starlord443.dev/dashboard?id=marcoshssilva-spring-eureka)

Link to SonarQube project [spring-eureka](https://qsonar.starlord443.dev/project/information?id=marcoshssilva-spring-eureka)

## Roles

| Role    | Description                                                   |
|---------|---------------------------------------------------------------|
| READER  | User enabled to open Eureka Dashboard only                    |
| METRICS | User enabled to requests actuator endpoints only              |
| ADMIN   | User enabled to admin user and make request to any endpoint   |
| CLIENT  | User enabled to register and fetch data from Eureka as client |

## Initial Username and Password

You can log using initial user **admin** with credentials **admin**

## Change own password

Endpoint: `/api/user/change-password`
```
 curl -X 'POST' http://localhost:8761/api/user/change-password \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "oldPassword": "YOUR_PASSWORD", "newPassword": "YOUR_NEW_PASSWORD" }' \
   -v

```

## Reset password using REST API as ROLE ADMIN

Endpoint: `/api/admin/reset-password`
```
 curl -X 'POST' http://localhost:8761/api/admin/reset-password \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "admin", "newPassword": "PUT_YOUR_NEW_PASSWORD_HERE" }' \
   -v
```

## Create new user using REST API as ROLE ADMIN

Endpoint: `/api/admin/create-user`
```
curl -X 'POST' http://localhost:8761/api/admin/create-user \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "YOUR_USERNAME", "password": "YOUR_PASSWORD", "enabled": "true", "roles": ["ADMIN", "READER"] }' \
   -v
```

## Update user using REST API as ROLE ADMIN

Endpoint: `/api/admin/update-user`
```
curl -X 'POST' http://localhost:8761/api/admin/update-user \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "YOUR_USERNAME", "roles": ["ROLE1", "ROLE2"] }' \
   -v
```

## Delete user using REST API as ROLE ADMIN

Endpoint: `/api/admin/delete-user/{username}`

```
curl -X 'DELETE' http://localhost:8761/api/admin/delete-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```
## Enable user using REST API as ROLE ADMIN

Endpoint: `/api/admin/enable-user/{username}`
```
curl -X 'PUT' http://localhost:8761/api/admin/enable-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```

## Disable user using REST API as ROLE ADMIN

Endpoint: `/api/admin/disable-user/{username}`
```
curl -X 'PUT' http://localhost:8761/api/admin/disable-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```
