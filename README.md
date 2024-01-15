# Complaint System

## Technologies:
I have used several technologies for this project:
* Spring Boot
* Spring Security
* Spring Data
* Lombok
* JWT
* PostgreSQL
* Map Struct
* Spring Bool Validation

## APIs:
User API:   
* POST /api/v1/user/register
* GET /api/v1/user/get-by-role-id/
* GET /api/v1/user/current-user/
* GET /api/v1/user/logout/
* POST /api/v1/user/authenticate

Roles API:
* POST /api/v1/role/{userId}
* POST /api/v1/role/
* GET /api/v1/role/get-by-role-id/{userId}

Complaint API:
* POST /api/v1/complaint
* GET /api/v1/complaint/{complaintId}
* GET /api/v1/complaint/list
* POST /api/v1/complaint/{complaintId}/update-status
* POST /api/v1/complaint/{complaintId}/comments
