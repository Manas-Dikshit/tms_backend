-- Transport Management System (TMS) PostgreSQL Schema

CREATE TABLE transporter (
    transporter_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    truck_capacity INT NOT NULL
);

CREATE TABLE load (
    load_id UUID PRIMARY KEY,
    shipper_id VARCHAR(100) NOT NULL,
    loading_city VARCHAR(100) NOT NULL,
    unloading_city VARCHAR(100) NOT NULL,
    loading_date DATE NOT NULL,
    product_type VARCHAR(100) NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    weight_unit VARCHAR(10) NOT NULL,
    truck_type VARCHAR(50) NOT NULL,
    no_of_trucks INT NOT NULL,
    status VARCHAR(20) NOT NULL, -- Enum: POSTED, BOOKED, CANCELLED
    date_posted TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bid (
    bid_id UUID PRIMARY KEY,
    load_id UUID NOT NULL,
    transporter_id UUID NOT NULL,
    proposed_rate DOUBLE PRECISION NOT NULL,
    trucks_offered INT NOT NULL,
    status VARCHAR(20) NOT NULL, -- Enum: PENDING, ACCEPTED, REJECTED
    submitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bid_load FOREIGN KEY (load_id) REFERENCES load(load_id),
    CONSTRAINT fk_bid_transporter FOREIGN KEY (transporter_id) REFERENCES transporter(transporter_id),
    CONSTRAINT unique_bid UNIQUE (load_id, transporter_id)
);

CREATE TABLE booking (
    booking_id UUID PRIMARY KEY,
    bid_id UUID NOT NULL,
    load_id UUID NOT NULL,
    transporter_id UUID NOT NULL,
    status VARCHAR(20) NOT NULL, -- Enum: ACTIVE, CANCELLED, COMPLETED
    booked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booking_bid FOREIGN KEY (bid_id) REFERENCES bid(bid_id),
    CONSTRAINT fk_booking_load FOREIGN KEY (load_id) REFERENCES load(load_id),
    CONSTRAINT fk_booking_transporter FOREIGN KEY (transporter_id) REFERENCES transporter(transporter_id)
);

-- Enums can be created in PostgreSQL for better type safety (optional)
-- CREATE TYPE load_status AS ENUM ('POSTED', 'BOOKED', 'CANCELLED');
-- CREATE TYPE bid_status AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');
-- CREATE TYPE booking_status AS ENUM ('ACTIVE', 'CANCELLED', 'COMPLETED');
