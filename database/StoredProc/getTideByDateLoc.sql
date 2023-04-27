/**************************
* Get tide data by date and location
**************************/
USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE getTideByDateLoc(IN date DATE, IN location VARCHAR(50), OUT going VARCHAR(50), OUT dayHigh TIME, OUT dayLow TIME, OUT direction VARCHAR(50)
BEGIN
    SELECT Going, DayHigh, DayLow, Direction INTO going, dayHigh, dayLow, direction FROM TideTable WHERE Name = location AND TS = date;
END //
DELIMITER ;