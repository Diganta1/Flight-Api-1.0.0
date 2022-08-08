# Flight-APi

Please develop a Flight-Search API (only backend) that suites the following  use cases:

Flight Number | Origin |Destination |Departure Time |Arrival Time |Price
A101|AMS|DEL|11:00|17:00|850 EURO
B101|AMS|BOM|12:00|19:30|750 EURO
C101|AMS|BLR|13:00|18:30|800 EURO
D101|AMS|MAA|09:00|15:00|600 EURO
E101|MAA|BOM|16:00|17:30|100 EURO
F101|BOM|DEL|20:30|21:30|80 EURO
G101|BOM|DEL|18:00|19:30|100 EURO
A201|LHR|DEL|11:30|17:00|600 EURO
B201|LHR|BOM|12:35|19:30|750 EURO
C201|LHR|BLR|13:45|18:30|800 EURO
D201|LHR|MAA|09:00|15:00|600 EURO
E201|DEL|BOM|18:45|20:15|100 EURO
F201|BOM|DEL|21:15|22:30|80 EURO
G01|BOM|DEL|20:20|22:30|100 EURO


Use case - 1: User can be able to find list of flights from Origin - Destination
Use Case - 2: User can be able to short list flights based on departure time, arrival time , price , travel time etc

Tips:
- Use at least Java 8 in combination with Spring framework
- Pay attention to code construction and programming paradigm. We will look at minor detail

---------------------------

### Docker setup script :

- Added the Dockerfile in project
- create an image (sudo docker build -t spring-boot:2.1.4 .)
- Run the container (sudo docker run -d -p 8080:8080 -t spring-boot:2.1.4)

### Swagger link :

- Load with below URL :
  http://localhost:8080/swagger-ui/index.html#/
- Open api profile : /flight-openapi

### Embedded H2 database

During application startup an embedded h2 database is started and loaded with location and translation data (schema and example data provided below).   
You can view the database through the h2 webconsole.

Url: [http://localhost:8080/h2-console]   
Database url: `jdbc:h2:mem:travel-api`   
Username: `sa` (no password required)   

### End point for Flights routes : 

http://localhost:8080/flight/routes?sortBy=price,desc&sortBy=departureTime,desc&origin=bom&destination=del

### end point for Actuators :

http://localhost:8080/actuator/metricsInfo
- Fetch below details :
- `{
  "totalRequest": 5,
  "totalOkRequest": 3,
  "total4xxRequest": 1,
  "total5xxRequest": 1,
  "avgResponseTime": 0.101045646,
  "totalResponseTime": 1.2125478
  }`



