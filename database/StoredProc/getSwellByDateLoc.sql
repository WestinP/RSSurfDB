/*************************
*  get swell by date and location
*************************/

USE RSSurfDB;

DELIMITER //

CREATE PROCEDURE getSwellByDateLoc(IN date DATE, IN location VARCHAR(50), OUT height FLOAT, OUT direction FLOAT, OUT period FLOAT)
BEGIN
    SELECT Height, Direction, Period INTO height, direction, period FROM SwellTable WHERE Name = location AND TS = date;
END //

DELIMITER ;