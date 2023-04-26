/**************************
* Get tide data by date and location
**************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getTideByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    SELECT Going, DayHigh, DayLow, Direction FROM TideTable WHERE TS = date AND Name = location;
END //
