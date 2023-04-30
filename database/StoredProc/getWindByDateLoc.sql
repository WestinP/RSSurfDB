/*****************************
* Get Data by Date and Location
******************************/
USE RSSurfDB;
DELIMITER //
CREATE procedure getWindByDateLoc(IN date DATE, IN location VARCHAR(50), OUT direction FLOAT, OUT speed FLOAT)
BEGIN
    SELECT Direction, Speed INTO direction, speed FROM WindTable WHERE Name = location AND TS = date;
END //
DELIMITER ;

