CREATE TABLE IF NOT EXISTS bodytypes(
    id SERIAL PRIMARY KEY,
    bodytypename varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS brands(
    id SERIAL PRIMARY KEY,
	brandname varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS engines(
    id SERIAL PRIMARY KEY,
    capacity double precision NOT NULL,
    fuel varchar(255) NOT NULL,
    hp int NOT NULL
);

CREATE TABLE IF NOT EXISTS transmissions(
    id SERIAL PRIMARY KEY,
    transmissionname varchar(255)
);

CREATE TABLE IF NOT EXISTS cars(
    id SERIAL PRIMARY KEY,
    carmodel varchar(255) NOT NULL,
    color varchar(255) NOT NULL,
    drive varchar(255) NOT NULL,
    mileage int NOT NULL,
    year int NOT NULL,
	bodytype_id int,
	brand_id int,
	engine_id int,
	transmission_id int,
	FOREIGN KEY (bodytype_id) REFERENCES bodytypes (id),
	FOREIGN KEY (brand_id) REFERENCES brands (id),
	FOREIGN KEY (engine_id) REFERENCES engines (id),
	FOREIGN KEY (transmission_id) REFERENCES transmissions (id)
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    email varchar(255) UNIQUE,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    phonenumber varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS adverts(
    id SERIAL PRIMARY KEY,
    active boolean NOT NULL,
    created timestamp NOT NULL,
    title varchar(255) NOT NULL,
    filename varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    price int NOT NULL,
    car_id int UNIQUE,
    users_id int,
	FOREIGN KEY (car_id) REFERENCES cars (id),
	FOREIGN KEY (users_id) REFERENCES users (id)
);


