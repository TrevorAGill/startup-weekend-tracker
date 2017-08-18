SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS events (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  description VARCHAR,
  attendeeCount INTEGER
);

CREATE TABLE IF NOT EXISTS attendees (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 company VARCHAR,
 email VARCHAR,
 age INTEGER,
 eventid INTEGER
);