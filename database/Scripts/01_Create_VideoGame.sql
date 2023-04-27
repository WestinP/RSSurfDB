

-- ------------------------------------------------
-- Resets database if it already exists
DROP DATABASE IF EXISTS videogames;
CREATE DATABASE IF NOT EXISTS videogames;
use videogames;


-- ------------------------------------------------
-- Create the Tables

-- VideoGame(GameID, Title, Year, Publisher, Genre
CREATE TABLE VideoGames(
	title VARCHAR(60),
	year YEAR,
	publisher VARCHAR(30),
	genre VARCHAR(30),
	PRIMARY KEY(title, year)
);


-- Platform(Platform, PlatformName)
CREATE TABLE Platforms(
	platform VARCHAR(5) PRIMARY KEY,
	platformName VARCHAR(50),
	manf VARCHAR(30));


-- GameRelease(title, year, Platform, Edition, Date)
CREATE TABLE GameReleases(
	title VARCHAR(60),
	year YEAR,
	platform VARCHAR(5),
	date DATE,
    PRIMARY KEY (title, year, platform));



-- ------------------------------------------------
-- Insert Data
INSERT INTO VideoGames VALUES 
	('Destiny 2',2017,'Activision','First-Person Shooter'),
	('Portal 2', 2011, 'Valve','Puzzle-platform'),
	('Minecraft', 2009,'Microsoft','Sandbox, Survival'),
	('Goat Simulator',2014,'Coffee Stain Studios','Action'),
	('Grand Theft Auto V',2014,'Coffee Stain Studios','Action'),
	('Team Fortress 2',2007,'Valve','First-person shooter');

-- ------------------------------------------------
INSERT INTO Platforms VALUES 
	('iOS','iOS','Apple'),
	('MAC','macOS','Apple'),
	('DROID','Android','Google'),
	('WIN','Windows','Microsoft'),
	('XBO','Xbox One','Microsoft'),
	('NS','Switch','Nintendo'),
	('3DS','3DS','Nintendo'),
	('WiiU','WiiU','Nintendo'),
	('PS3','Playstation 3','Sony'),
	('PS4','Playstation 4','Sony');


-- ------------------------------------------------
INSERT INTO GameReleases VALUES 

	('Minecraft', 2009, 'WIN', '2009-05-17'),
	('Minecraft', 2009, 'MAC', '2009-05-17'),
	('Minecraft', 2009, 'iOS', '2016-12-19'),
	('Minecraft', 2009, 'DROID', '2016-12-19'),
	('Minecraft', 2009, 'PS4', '2014-09-14'),	
	('Minecraft', 2009, '3DS', '2017-09-13'),
	('Minecraft', 2009, 'WiiU', '2017-12-17'),
	('Minecraft', 2009, 'XBO', '2017-09-05'),
	('Minecraft', 2009, 'NS', '2018-01-01'),

	('Team Fortress 2', 2007, 'WIN', '2007-10-10'),
	('Team Fortress 2', 2007, 'MAC', '2007-10-10'),

	('Goat Simulator', 2014, 'WIN', '2014-04-01'),
	('Goat Simulator', 2014, 'MAC', '2014-06-27'),
	('Goat Simulator', 2014, 'iOS', '2014-08-16'),
	('Goat Simulator', 2014, 'DROID', '2014-08-16'),
	('Goat Simulator', 2014, 'PS4', '2015-08-11'),	
	('Goat Simulator', 2014, 'XBO', '2015-04-17'),

	('Portal 2', 2011, 'WIN', '2012-04-19'), 
	('Portal 2', 2011, 'MAC', '2012-04-19'), 
    
	('South Park: The Stick of Truth', 2014, 'PS3', '2014-03-14'), 

	('Destiny 2',2017,'XBO','2017-10-24'),
	('Destiny 2',2017,'PS4','2017-09-06');


