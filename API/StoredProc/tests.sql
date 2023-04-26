/****
3 separate queries to gets the data from the 3 tables (WindTable, TideTable, and SwellTable)
using the date as the key.
****/
-- Gets
SELECT * FROM WindTable WHERE Date = '2022-09-15';
SELECT * FROM TideTable WHERE Date = '2022-09-15 00:00:00.000';
SELECT * FROM SwellTable WHERE Date = '2022-09-15 00:00:00.000';
SELECT * FROM LocationTable WHERE Location = Bali AND Date = '2022-09-15 00:00:00.000';

-- Update


-- Insert
INSERT INTO WindTable VALUES ('2023-03-22', 10, 180, 'Bali');
INSERT INTO TideTable VALUES ('2023-03-22', '2023-03-22 10:00:00', '2023-03-22 16:00:00', 1, 180, 'Bali');
INSERT INTO SwellTable VALUES ('2023-03-22', 1.5, 180, 10, 'Bali');