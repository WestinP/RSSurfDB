-- Insert Wind Data

USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE insertWind(IN date DATE, IN location VARCHAR(50), IN direction INT, IN speed INT)
BEGIN
    INSERT INTO WindTable (TS, Name, Direction, Speed) VALUES (date, location, direction, speed);
END //
