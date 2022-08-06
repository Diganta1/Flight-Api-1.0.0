CREATE TABLE flight (
    flight_number VARCHAR NOT NULL,
    origin VARCHAR NOT NULL,
    destination VARCHAR NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    price INTEGER NOT NULL,
    CONSTRAINT pk_flight PRIMARY KEY (flight_number)
);