package RSSurfDB.API.Modals;

import java.time.LocalDateTime;

public class RatingModal {
    /*****************************************
     * Create_RatingTable.sql
     * Scheme:
     * PK Ts: datetime
     * Rating: Integer
     * PK Name: varchar(50)
     *****************************************/
    public LocalDateTime TS;
    public Integer Rating;
    public String Name;

    public RatingModal(LocalDateTime TS, Integer Rating, String Name) {
        this.TS = TS;
        this.Rating = Rating;
        this.Name = Name;
    }
}
