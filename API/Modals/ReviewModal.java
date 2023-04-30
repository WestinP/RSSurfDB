package RSSurfDB.API.Modals;

import java.time.LocalDateTime;

public class ReviewModal {
    /*****************************************
     * Create_ReviewsTable.sql
     * Scheme:
     * PK TS: datetime
     * UserRating: int
     * PK Name: varchar(50)
     *****************************************/
    public LocalDateTime TS;
    public int UserRating;
    public String Name;

    public ReviewModal(LocalDateTime TS, Integer UserRating, String Name) {
        this.TS = TS;
        this.UserRating = UserRating;
        this.Name = Name;
    }
}
