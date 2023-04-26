-- Update Location Rating

USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE updateRating(IN date DATE, IN location VARCHAR(50), IN rating INT)
BEGIN 
    UPDATE RatingTable SET Rating = rating WHERE TS = date AND Name = location;
END //
    