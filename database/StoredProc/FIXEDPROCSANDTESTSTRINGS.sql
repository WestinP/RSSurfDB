use surf

select * from surf.swelltable s ;

select * from surf.windtable w ;

select * from surf.locationtable l ;

select * from surf.ratingtable r;

call surf.getSwellByDateLoc('2023-04-29 12:12:00', 'Flagler Beach') 

INSERT INTO surf.reviewstable (TS, UserRating , Name) VALUES ('2023-04-30', 9, 'Flagler Beach');

SELECT TS, UserRating, Name
FROM surf.reviewstable;


call surf.insertRating('2023-04-29 09:33:00', 'Flagler Beach', 8);

call insertSwell('2023-04-29 09:30:00', 'Flagler Beach',12,3,3);

call surf.getRatingByDateLoc('2023-04-30', 'Flagler Beach');

call surf.getReviewsByDateLoc('2023-04-30', 'Flagler Beach');

SELECT * FROM surf.SwellTable WHERE Name = 'Flagler Beach' AND TS >= '2023-04-29 09:30:00' limit 1;

DELIMITER //

CREATE PROCEDURE surf.insertLocation(IN date DATE, IN location VARCHAR(50), IN city VARCHAR(50))
BEGIN
    INSERT INTO LocationTable (Name, TS, CityName) VALUES (location, date, city);
end//

DELIMITER ;

call surf.insertLocation('2023-04-29 09:30:00', 'Flagler Beach', 'flager');

call surf.insertSwell('2023-04-29 09:30:00', 'Flagler Beach', 12.3, 11.2, 1);

SELECT Name, TS, CityName
FROM surf.locationtable;

use surf;
DELIMITER //
CREATE OR REPLACE PROCEDURE surf.insertLocation(IN date DATETIME, IN location VARCHAR(50), IN city VARCHAR(50))
BEGIN
    INSERT INTO LocationTable (Name, TS, CityName) VALUES (location, date, city);
END //
DELIMITER ;

USE surf;
DELIMITER //
CREATE or replace procedure surf.insertSwell(IN date DATETIME, IN location VARCHAR(50), IN height INT, IN direction INT, IN period INT)
BEGIN
    INSERT INTO SwellTable (TS, Name, Height, Direction, Period) VALUES (date, location, height, direction, period);
END //
DELIMITER ;


USE surf;
DELIMITER //
CREATE or replace PROCEDURE surf.insertWind(IN date DATETIME, IN location VARCHAR(50), IN direction INT, IN speed INT)
BEGIN
    INSERT INTO WindTable (TS, Name, Direction, Speed) VALUES (date, location, direction, speed);
END //
DELIMITER ;

USE surf;
DELIMITER //
CREATE or replace PROCEDURE surf.insertRating(IN date DATETIME, IN location VARCHAR(50), IN rating INT)
BEGIN
    INSERT INTO ratingtable (TS, Rating, Name) VALUES (date, rating, location);
END //
DELIMITER ;

USE surf;
DELIMITER //
create or replace PROCEDURE surf.insertReview(IN date DATETIME, IN location VARCHAR(50), IN review INT)
BEGIN
    INSERT INTO reviewstable (TS, UserRating, Name) VALUES (date, review, location);
END //
DELIMITER ;


USE surf;
DELIMITER //
create or replace PROCEDURE surf.getReviewsByDateLoc(IN date DATETIME, IN location VARCHAR(50))
BEGIN
    SELECT AVG(UserRating) as UserRating FROM surf.reviewstable WHERE Name = location AND TS <= date limit 3;
END //
DELIMITER ;

DELIMITER //

create or replace PROCEDURE surf.getSwellByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    select TS, Name, Height, Direction, Period FROM SwellTable WHERE Name = location AND TS >= date limit 1;
END //
DELIMITER ;

DELIMITER //
create or replace procedure surf.getRatingByDateLoc(IN date DATETIME, IN location VARCHAR(50))
BEGIN
    SELECT AVG(Rating) as Rating FROM ratingtable WHERE Name = location AND TS <= date limit 3;
END //
DELIMITER ;

DELIMITER //
CREATE or replace PROCEDURE surf.getTideByDateLoc(IN date DATE, IN location VARCHAR(50), OUT going VARCHAR(50), OUT dayHigh TIME, OUT dayLow TIME, OUT direction VARCHAR(50)
BEGIN
    SELECT Going, DayHigh, DayLow, Direction INTO going, dayHigh, dayLow, direction FROM TideTable WHERE Name = location AND TS >= NOW() limit 1;
END //
DELIMITER ;

DELIMITER //
create or replace procedure surf.getWindByDateLoc(IN date DATE, IN location VARCHAR(50))
BEGIN
    select TS, Name, Direction, Speed from windtable WHERE Name = location AND TS >= date limit 1;
END //
DELIMITER ;
