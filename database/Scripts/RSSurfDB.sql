/*************************************************************
-- Create DataBase RSSurfDB only if it does not exist
*************************************************************/
drop database surf;
CREATE DATABASE IF NOT EXISTS surf;
 use surf;

/********************************************************
-- Create_LocationTable
-- -- Main Table all other tables will be weak relations to this table
Scheme: 
    PK Name: varchar(50)
    PK TS: datetime
    CityName: varchar(50)
********************************************************/
-- IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='LocationTable' AND xtype='U')
CREATE TABLE if not exists LocationTable(
    Name varchar(50),
    TS datetime,
    CityName varchar(50),
    PRIMARY KEY (Name, TS)
);
-- Insert Data

/**********************************************
* Create_SwellTable.sql
* Scheme:
*     PK TS: datetime
*     Height: double
*     Direction: double
*     Period: double
*     FK Name: varchar(50)
**********************************************/
CREATE TABLE if not exists SwellTable(
    TS datetime,
    Name varchar(50),  -- FK to LocationTable
    Height double,
    Direction double,
    Period double,
    PRIMARY KEY (TS),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data

/*******************************************
* Create_WindTable.sql
* Scheme:
*     PK TS: datetime
*     Speed: double
*     Direction: double
*     FK Name: varchar(50)
*******************************************/
CREATE TABLE if not exists WindTable(
    TS datetime,
    Speed double,
    Direction double,
    Name varchar(50),  -- FK to LocationTable
    PRIMARY KEY (TS),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data


/*****************************************
* Create_TideTable.sql
* Scheme:
*     PK TS: datetime
*     DayLow: datetime
*     DayHigh: datetime
*     Going: BIT
*     Direction: double
*     FK Name: varchar(50)
*****************************************/
CREATE TABLE if not exists TideTable(
    TS datetime,
    DayLow datetime,
    DayHigh datetime,
    Going BIT,
    Direction double,
    Name varchar(50),  -- FK to LocationTable
    PRIMARY KEY (TS),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data

/*****************************************
* Create_ReviewsTable.sql
* Scheme: 
*   PK Ts: datetime
*   UserRating: int
*   PK Name: varchar(50)
*****************************************/
CREATE TABLE if not exists ReviewsTable(
    TS datetime,
    UserRating int,
    Name varchar(50),  -- FK to LocationTable
    PRIMARY KEY (TS, Name),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data


/*****************************************
* Create_RatingTable.sql
* Scheme:
*   PK Ts: datetime
*   Rating: double
*   PK Name: varchar(50)
*****************************************/
CREATE TABLE if not exists RatingTable(
    TS datetime,
    Rating double,
    Name varchar(50),  -- FK to LocationTable
    PRIMARY KEY (TS, Name),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data


commit;


