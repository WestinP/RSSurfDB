/*****************************
* Get The Review average by Date and Location
******************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getReviewsByDateLoc(IN date DATE, IN location VARCHAR(50), OUT UR FLOAT)
BEGIN
    SELECT AVG(UR) INTO UR FROM ReviewTable WHERE Name = location AND TS = date;
END //
DELIMITER ;