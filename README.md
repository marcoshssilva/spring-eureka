# spring-boot-eureka

Eureka service discovery to Spring Boot Apps and supported as NodeJS, Quarkus, Micrometer or FastAPI

## Initial Username and Password

You can log using initial user **admin** with credentials **admin**

## Reset password using REST API

Endpoint: `/api/admin/reset-password`
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

## Update user using REST API

Endpoint: `/api/admin/update-user`
```
curl -X 'POST' http://localhost:8761/api/admin/update-user \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -d '{ "username": "YOUR_USERNAME", "roles": ["ROLE1", "ROLE2"] }' \
   -v
```

## Delete user using REST API

Endpoint: `/api/admin/delete-user/{username}`

```
curl -X 'DELETE' http://localhost:8761/api/admin/delete-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```
## Enable user using REST API

Endpoint: `/api/admin/enable-user/{username}`
```
curl -X 'PUT' http://localhost:8761/api/admin/enable-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```

## Disable user using REST API

Endpoint: `/api/admin/disable-user/{username}`
```
curl -X 'PUT' http://localhost:8761/api/admin/disable-user/{username} \
   -H 'Content-type: application/json' \
   -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
   -v
```