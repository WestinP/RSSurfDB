-- Insert Tide Data

USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE insertTide(IN date DATE, IN location VARCHAR(50), IN height INT, IN direction INT)
BEGIN
    INSERT INTO TideTable (TS, Name, Height, Direction) VALUES (date, location, height, direction);
END //
DELIMITER ;
