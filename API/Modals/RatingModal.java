package RSSurfDB.API.Modals;

public class RatingModal {
    /*****************************************
     * Create_RatingTable.sql
     * Scheme:
     * PK Ts: datetime
     * Rating: float
     * PK Name: varchar(50)
     *****************************************/
    public String Ts;
    public float Rating;
    public String Name;

    public RatingModal(String Ts, float Rating, String Name) {
        this.Ts = Ts;
        this.Rating = Rating;
        this.Name = Name;
    }
}
