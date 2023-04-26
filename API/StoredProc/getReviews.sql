/*****************************
* Get The Review average by Date and Location
******************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getReviewsByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    SELECT AVG(UserRating) AS UR FROM ReviewTable WHERE TS = date AND Name = location;
END //