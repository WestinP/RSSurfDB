-- Insert Wind Data

USE surf;
DELIMITER //
CREATE or replace PROCEDURE insertWind(IN date DATETIME, IN location VARCHAR(50), IN direction INT, IN speed INT)
BEGIN
    INSERT INTO WindTable (TS, Name, Direction, Speed) VALUES (date, location, direction, speed);
END //
DELIMITER ;
