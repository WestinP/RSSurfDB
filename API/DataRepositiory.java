/*
 * Creating a repository for the database RSSurfDB MySQL
 * Will set up the connection to the database for each Method
 * that will call the database's stored procedures accordingly
 * to the method's purpose.
 */

package RSSurfDB.API;

import java.sql.*;
import java.util.ArrayList;

import RSSurfDB.API.Modals.SwellModal;
import RSSurfDB.API.Modals.TideModal;
import RSSurfDB.API.Modals.WindModal;

public class DataRepositiory {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/RSSurfDB";
    private static final String USER = "root";
    private static final String PASS = "root";

    Connection conn;

    public DataRepositiory() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertion Functions
    public void insertWind(WindModal wind) {
        try {
            CallableStatement cs = conn.prepareCall("{call insertWind(?,?,?,?}");
            cs.setDate(1, wind.TS);
            cs.setDouble(2, wind.Speed);
            cs.setDouble(3, wind.Direction);
            cs.setString(4, wind.Name);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSwell(SwellModal swell) {
        try {
            CallableStatement cs = conn.prepareCall("{call insertSwell(?,?,?,?,?}");
            cs.setDate(1, swell.TS);
            cs.setDouble(2, swell.Height);
            cs.setDouble(3, swell.Direction);
            cs.setDouble(4, swell.Period);
            cs.setString(5, swell.Name);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTide(TideModal tide) {
        try {
            CallableStatement cs = conn.prepareCall("{call insertTide(?,?,?,?,?,?}");
            cs.setString(1, tide.TS);
            cs.setString(2, tide.DayLow);
            cs.setString(3, tide.DayHigh);
            cs.setBoolean(4, tide.Going);
            cs.setDouble(5, tide.Direction);
            cs.setString(6, tide.Name);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRating(Date day, String locationName, int rating) {
        try {
            CallableStatement cs = conn.prepareCall("{call insertRating(?,?,?}");
            cs.setDate(1, day);
            cs.setString(1, locationName);
            cs.setInt(2, rating);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertReview(Date day, String locationName, int review) {
        try {
            CallableStatement cs = conn.prepareCall("{call insertReview(?,?,?}");
            cs.setDate(1, day);
            cs.setString(1, locationName);
            cs.setInt(2, review);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Functions
    public ArrayList<WindModal> getWind(Date day, String locationName) {
        ArrayList<WindModal> wind = new ArrayList<WindModal>();
        try {
            CallableStatement cs = conn.prepareCall("{call getWind(?,?,?,?)}");
            cs.setDate(1, day);
            cs.setString(2, locationName);
            cs.registerOutParameter(3, Types.DOUBLE);
            cs.registerOutParameter(4, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                wind.add(new WindModal(rs.getDate("TS"), rs.getDouble("Speed"), rs.getDouble("Direction"),
                        rs.getString("Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wind;
    }

    public ArrayList<SwellModal> getSwell(Date day, String locationName) {
        ArrayList<SwellModal> swell = new ArrayList<SwellModal>();
        try {
            CallableStatement cs = conn.prepareCall("{call getSwell(?,?,?,?,?)}");
            cs.setDate(1, day);
            cs.setString(2, locationName);
            cs.registerOutParameter(3, Types.DOUBLE);
            cs.registerOutParameter(4, Types.DOUBLE);
            cs.registerOutParameter(5, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                swell.add(new SwellModal(rs.getDate("TS"), rs.getDouble("Height"), rs.getDouble("Direction"),
                        rs.getDouble("Period"), rs.getString("Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return swell;
    }

    // public ArrayList<TideModal> getTide(Date day, String locationName) {
    // ArrayList<TideModal> tide = new ArrayList<TideModal>();
    // try {
    // CallableStatement cs = conn.prepareCall("{call getTide(?,?,?,?,?,?)}");
    // cs.setDate(1, day);
    // cs.setString(2, locationName);
    // cs.registerOutParameter(3, Types.VARCHAR);
    // cs.registerOutParameter(4, Types.VARCHAR);
    // cs.registerOutParameter(5, Types.BOOLEAN);
    // cs.registerOutParameter(6, Types.DOUBLE);
    // ResultSet rs = cs.executeQuery();
    // while (rs.next()) {
    // tide.add(new TideModal(rs.getString("TS"), rs.getString("DayLow"),
    // rs.getString("DayHigh"),
    // rs.getBoolean("Going"), rs.getDouble("Direction"), rs.getString("Name")));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return tide;
    // }

    public Double getReview(Date day, String locationName) {
        Double review;
        try {
            CallableStatement cs = conn.prepareCall("{call getReview(?,?,?)}");
            cs.setDate(1, day);
            cs.setString(2, locationName);
            cs.registerOutParameter(3, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            review = rs.getDouble("Review");
        } catch (SQLException e) {
            e.printStackTrace();
            review = null;
        }
        return review;
    }

    public Double getRating(Date day, String locationName) {
        Double rating;
        try {
            CallableStatement cs = conn.prepareCall("{call getRating(?,?,?)}");
            cs.setDate(1, day);
            cs.setString(2, locationName);
            cs.registerOutParameter(3, Types.DOUBLE);
            ResultSet rs = cs.executeQuery();
            rating = rs.getDouble("Rating");
        } catch (SQLException e) {
            e.printStackTrace();
            rating = null;
        }
        return rating;
    }
}