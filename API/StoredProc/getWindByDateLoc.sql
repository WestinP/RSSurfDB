/*****************************
* Get Data by Date and Location
******************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getWindByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    SELECT Direction, Speed FROM WindTable WHERE TS = date AND Name = location;
END //

