-- Insert Swell Data

USE RSSurfDB;
DELIMITER //
CREATE or replace procedure insertSwell(IN date DATE, IN location VARCHAR(50), IN height INT, IN direction INT, IN period INT)
BEGIN
    INSERT INTO SwellTable (TS, Name, Height, Direction, Period) VALUES (date, location, height, direction, period);
END //
DELIMITER ;
