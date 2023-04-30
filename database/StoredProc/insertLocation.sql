/*
-- Create_LocationTable
-- -- Main Table all other tables will be weak relations to this table
Scheme: 
    PK Name: varchar(50)
    PK TS: datetime
    CityName: varchar(50)
********************************************************/

use RSSurfDB;
DELIMITER //
CREATE OR REPLACE PROCEDURE insertLocation(IN date DATE, IN location VARCHAR(50), IN city VARCHAR(50))
BEGIN
    INSERT INTO LocationTable (Name, TS, CityName) VALUES (location, date, city);
END //
DELIMITER ;