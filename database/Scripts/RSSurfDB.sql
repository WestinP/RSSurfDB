/*************************************************************
-- Create DataBase RSSurfDB only if it does not exist
*************************************************************/

CREATE DATABASE IF NOT EXISTS RSSurfDB;
-- use RSSurfDB;

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
*     Height: float
*     Direction: float
*     Period: float
*     FK Name: varchar(50)
**********************************************/
CREATE TABLE if not exists SwellTable(
    TS datetime,
    Name varchar(50),  -- FK to LocationTable
    Height float,
    Direction float,
    Period float,
    PRIMARY KEY (TS),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data

/*******************************************
* Create_WindTable.sql
* Scheme:
*     PK TS: datetime
*     Speed: float
*     Direction: float
*     FK Name: varchar(50)
*******************************************/
CREATE TABLE if not exists WindTable(
    TS datetime,
    Speed float,
    Direction float,
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
*     Direction: float
*     FK Name: varchar(50)
*****************************************/
CREATE TABLE if not exists TideTable(
    TS datetime,
    DayLow datetime,
    DayHigh datetime,
    Going BIT,
    Direction float,
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
*   Rating: float
*   PK Name: varchar(50)
*****************************************/
CREATE TABLE if not exists RatingTable(
    TS datetime,
    Rating float,
    Name varchar(50),  -- FK to LocationTable
    PRIMARY KEY (TS, Name),
    FOREIGN KEY (Name) REFERENCES LocationTable(Name)
);
-- Insert Data


commit;


