# Garage Project

## Requirements

For building and running the application you need:

- [JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/)
- [Maven](https://maven.apache.org)

# API

_You can use the postman collection inside the project._

| Endpoints  | Request Body | Description |
| ------------- |:-------------:|:-------------:|
| __POST__ /api/v1/garage/park  | ```JSON {  "type" : "CAR","color": "Red","licencePlate": "34-CAR-556" }```     | Park the vehicle |
| __GET__ /api/v1/garage/status  |    -     | Show garage status |
| __POST__ /api/v1/garage/leave  | ```JSON {  "ticketNo" : "1252c0e3-b280-43ac-805d-e7a28edd66f4" }```     | Leave the garage  |
