# Garage Project

In this problem, you have a garage that can be parked up to 10 slots (you can consider each slot is 1 unit range) at any
given point in time. You should create an automated ticketing system that allows your customers to use your garage
without human intervention. When a car enters your garage, you give a unique ticket issued to the driver. The ticket
issuing process includes us documenting the plate and the colour of the car and allocating an available slots to the car
before actually handing over a ticket to the driver. When a vehicle holds number of slots with its own width, you have to
leave 1 unit slot to next one. The customer should be allocated slot(s) which is nearest to the entry. At the exit the
customer returns the ticket which then marks slot(s) they were using as being available.
Create a spring boot project and then, publish a rest controller. Your controller methods include park, leave and status. 

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
