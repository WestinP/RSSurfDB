/****************
-- Description: Insert a new rating into the database by date and location
-- Input: date, location, rating
-- Output: none
-- Example Call: CALL insertRating('2016-01-01', 'Huntington Beach', 5);
****************/

USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE insertRating(IN date DATE, IN location VARCHAR(50), IN rating INT)
BEGIN
    INSERT INTO RatingTable (TS, Name, Rating) VALUES (date, location, rating);
END //
DELIMITER ;

