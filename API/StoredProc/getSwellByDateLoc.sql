/*************************
*  get swell by date and location
*************************/

USE RSSurfDB;

DELIMITER //

CREATE PROCEDURE getSwellByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    SELECT Height, Direction, Period FROM SwellTable WHERE TS = date AND Name = location;
END //
