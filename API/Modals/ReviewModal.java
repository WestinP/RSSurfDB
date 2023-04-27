package RSSurfDB.API.Modals;

public class ReviewModal {
    /*****************************************
     * Create_ReviewsTable.sql
     * Scheme:
     * PK Ts: datetime
     * UserRating: int
     * PK Name: varchar(50)
     *****************************************/
    public String Ts;
    public int UserRating;
    public String Name;

    public ReviewModal(String Ts, int UserRating, String Name) {
        this.Ts = Ts;
        this.UserRating = UserRating;
        this.Name = Name;
    }
}
