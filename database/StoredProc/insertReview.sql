/*****************
-- Description: Inserts a new review into the database
-- Input: date, location, review
-- Output: none
-- Example Call: CALL insertReview('2016-01-01', 'Huntington Beach', 25);
*****************/

USE RSSurfDB;
DELIMITER //
CREATE PROCEDURE insertReview(IN date DATE, IN location VARCHAR(50), IN review INT)
BEGIN
    INSERT INTO ReviewTable (TS, Name, Review) VALUES (date, location, review);
END //
DELIMITER ;