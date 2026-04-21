-- Create Country table
CREATE TABLE IF NOT EXISTS country (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) NOT NULL
);

-- Create Conflict table
CREATE TABLE IF NOT EXISTS conflict (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE,
    status VARCHAR(50),
    description VARCHAR(1000)
);

-- Create Faction table
CREATE TABLE IF NOT EXISTS faction (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    conflict_id BIGINT,
    FOREIGN KEY (conflict_id) REFERENCES conflict(id)
);

-- Create Event table
CREATE TABLE IF NOT EXISTS event (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    event_date DATE,
    location VARCHAR(255),
    description VARCHAR(1000),
    conflict_id BIGINT,
    FOREIGN KEY (conflict_id) REFERENCES conflict(id)
);

-- Create Conflict-Country relationship table
CREATE TABLE IF NOT EXISTS conflict_country (
    conflict_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    PRIMARY KEY (conflict_id, country_id),
    FOREIGN KEY (conflict_id) REFERENCES conflict(id),
    FOREIGN KEY (country_id) REFERENCES country(id)
);

-- Create Faction-Country support table
CREATE TABLE IF NOT EXISTS faction_country_support (
    faction_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    PRIMARY KEY (faction_id, country_id),
    FOREIGN KEY (faction_id) REFERENCES faction(id),
    FOREIGN KEY (country_id) REFERENCES country(id)
);
