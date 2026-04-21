-- Countries
INSERT INTO country (id, name, code) VALUES
(1, 'Ukraine', 'UKR'),
(2, 'Russia', 'RUS'),
(3, 'Israel', 'ISR'),
(4, 'Palestine', 'PSE'),
(5, 'United States', 'USA'),
(6, 'Iran', 'IRN'),
(7, 'United Kingdom', 'GBR'),
(8, 'France', 'FRA'),
(9, 'Germany', 'DEU'),
(10, 'China', 'CHN');

-- Conflicts
INSERT INTO conflict (id, name, start_date, status, description) VALUES
(1, 'Russo-Ukrainian War', '2014-02-20', 'ACTIVE', 'Ongoing conflict between Russia and Ukraine that began with the annexation of Crimea in 2014 and escalated into a full-scale invasion in 2022.'),
(2, 'Israel-Palestine Conflict', '1948-05-15', 'ACTIVE', 'Long-standing political and territorial conflict between Israel and Palestine, involving numerous wars and ongoing tensions.'),
(3, 'Syrian Civil War', '2011-03-15', 'ACTIVE', 'Multi-sided civil war in Syria fought between the Syrian Arab Republic and various domestic and foreign forces.'),
(4, 'Korean War', '1950-06-25', 'FROZEN', 'War between North Korea and South Korea that ended with an armistice but no peace treaty.');

-- Conflict-Country relationships
INSERT INTO conflict_country (conflict_id, country_id) VALUES
(1, 1), (1, 2), (1, 5), (1, 7),
(2, 3), (2, 4), (2, 5), (2, 6),
(3, 5), (3, 6), (3, 8), (3, 9),
(4, 10);

-- Factions
INSERT INTO faction (id, name, conflict_id) VALUES
(1, 'Ukrainian Armed Forces', 1),
(2, 'Russian Armed Forces', 1),
(3, 'Israel Defense Forces', 2),
(4, 'Hamas', 2),
(5, 'Free Syrian Army', 3),
(6, 'Syrian Arab Army', 3);

-- Faction-Country support
INSERT INTO faction_country_support (faction_id, country_id) VALUES
(1, 5), (1, 7), (1, 8), (1, 9),
(2, 2), (2, 10),
(3, 5), (3, 7),
(4, 6),
(5, 5), (5, 8),
(6, 6), (6, 10);

-- Events
INSERT INTO event (id, event_date, location, description, conflict_id) VALUES
(1, '2014-02-20', 'Crimea', 'Beginning of the Russo-Ukrainian War with the annexation of Crimea by Russia', 1),
(2, '2022-02-24', 'Kyiv', 'Full-scale Russian invasion of Ukraine begins', 1),
(3, '2023-06-06', 'Kherson', 'Kakhovka dam explosion causing massive flooding', 1),
(4, '1948-05-15', 'Middle East', '1948 Arab-Israeli War begins following the declaration of the State of Israel', 2),
(5, '2023-10-07', 'Gaza Strip', 'Hamas launches surprise attack on Israel, escalating the conflict', 2),
(6, '2011-03-15', 'Damascus', 'Start of Syrian Civil War with anti-government protests', 3),
(7, '1950-06-25', '38th Parallel', 'North Korean forces cross the 38th parallel, starting the Korean War', 4);