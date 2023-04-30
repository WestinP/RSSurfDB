/*
 * Creating a repository for the database RSSurfDB MySQL
 * Will set up the connection to the database for each Method
 * that will call the database's stored procedures accordingly
 * to the method's purpose.
 */

package RSSurfDB.API;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.ArrayList;

import RSSurfDB.API.Modals.RatingModal;
import RSSurfDB.API.Modals.ReviewModal;
import RSSurfDB.API.Modals.SwellModal;
import RSSurfDB.API.Modals.WindModal;

public class DataRepositiory {
    // ! Database Credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/surf";
    private static final String USER = "root";
    private static final String PASS = "password";

    Connection conn;

    // ! all the methods will be called from here for database access
    public DataRepositiory() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertion Functions
    public void insertWind(WindModal wind) {
        System.out.println(wind.TS.toString());
        try {
            CallableStatement cs = conn.prepareCall("{call surf.insertWind(?,?,?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(wind.TS));
            cs.setString(2, wind.Name);
            cs.setDouble(3, wind.Speed);
            cs.setDouble(4, wind.Direction);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSwell(SwellModal swell) {
        try {
            CallableStatement cs = conn.prepareCall("{call surf.insertSwell(?,?,?,?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(swell.TS));
            cs.setString(2, swell.Name);
            cs.setDouble(3, swell.Height);
            cs.setDouble(4, swell.Direction);
            cs.setDouble(5, swell.Period);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public void insertTide(TideModal tide) {
    // try {
    // CallableStatement cs = conn.prepareCall("{call insertTide(?,?,?,?,?,?}");
    // cs.setString(1, tide.TS);
    // cs.setString(2, tide.DayLow);
    // cs.setString(3, tide.DayHigh);
    // cs.setBoolean(4, tide.Going);
    // cs.setDouble(5, tide.Direction);
    // cs.setString(6, tide.Name);
    // cs.execute();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public void insertRating(RatingModal rating) {
        try {
            CallableStatement cs = conn.prepareCall("{call surf.insertRating(?,?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(rating.TS));
            cs.setString(2, rating.Name);
            cs.setInt(3, rating.Rating);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertReview(ReviewModal review) {
        try {
            CallableStatement cs = conn.prepareCall("{call surf.insertReview(?,?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(review.TS));
            cs.setString(2, review.Name);
            cs.setInt(3, review.UserRating);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Functions
    public ArrayList<WindModal> getWindByDate(LocalDateTime dayTime, String locationName) {
        ArrayList<WindModal> wind = new ArrayList<WindModal>();
        try {
            CallableStatement cs = conn.prepareCall("{call surf.getWindByDateLoc(?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(dayTime));
            cs.setString(2, locationName);
            // cs.registerOutParameter(3, Types.DOUBLE);
            // cs.registerOutParameter(4, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                wind.add(new WindModal(rs.getTimestamp("TS").toLocalDateTime(),
                        rs.getDouble("Speed"), rs.getDouble("Direction"), locationName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wind;
    }

    public ArrayList<SwellModal> getSwellByDate(LocalDateTime dayTime, String locationName) {
        ArrayList<SwellModal> swell = new ArrayList<SwellModal>();
        try {
            CallableStatement cs = conn.prepareCall("{call surf.getSwellByDateLoc(?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(dayTime));
            cs.setString(2, locationName);
            // cs.registerOutParameter(3, Types.DOUBLE);
            // cs.registerOutParameter(4, Types.DOUBLE);
            // cs.registerOutParameter(5, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                swell.add(new SwellModal(rs.getTimestamp("TS").toLocalDateTime(),
                        rs.getDouble("Height"), rs.getDouble("Direction"), rs.getDouble("Period"), locationName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return swell;
    }

    public Double getReview(LocalDateTime day, String locationName) {
        Double review;
        try {
            CallableStatement cs = conn.prepareCall("{call surf.getReviewsByDateLoc(?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(day));
            cs.setString(2, locationName);
            // cs.registerOutParameter(3, Types.INTEGER);
            ResultSet rs = cs.executeQuery();
            rs.next();
            review = rs.getDouble("UserRating");
        } catch (SQLException e) {
            e.printStackTrace();
            review = null;
        }
        return review;
    }

    public Double getRating(LocalDateTime day, String locationName) {
        Double rating;
        try {
            CallableStatement cs = conn.prepareCall("{call surf.getRatingByDateLoc(?,?)}");
            cs.setTimestamp(1, Timestamp.valueOf(day));
            cs.setString(2, locationName);
            // cs.registerOutParameter(3, Types.INTEGER);
            ResultSet rs = cs.executeQuery();
            rs.next();
            rating = rs.getDouble("Rating");
        } catch (SQLException e) {
            e.printStackTrace();
            rating = null;
        }
        return rating;
    }
}