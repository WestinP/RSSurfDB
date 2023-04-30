/***************************
* GetRating by Date and Location
***************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getRating(IN date DATE, IN location VARCHAR(50), OUT rating INT)
BEGIN
    SELECT Rating INTO rating FROM RatingTable WHERE TS = date AND Name = location;
END //
DELIMITER ;