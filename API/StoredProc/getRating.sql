/***************************
* GetRating by Date and Location
***************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getRating(IN date DATE, IN location VARCHAR(50))
BEGIN
    SELECT Rating FROM RatingTable WHERE TS = date AND Name = location;
END //