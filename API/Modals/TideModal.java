package RSSurfDB.API.Modals;

public class TideModal {
    /*****************************************
     * Create_TideTable.sql
     * Scheme:
     * PK TS: datetime
     * DayLow: datetime
     * DayHigh: datetime
     * Going: BIT
     * Direction: Double
     * FK Name: varchar(50)
     *****************************************/
    public String TS;
    public String DayLow;
    public String DayHigh;
    public boolean Going;
    public Double Direction;
    public String Name;

    public TideModal(String TS, String DayLow, String DayHigh, boolean Going, Double Direction, String Name) {
        this.TS = TS;
        this.DayLow = DayLow;
        this.DayHigh = DayHigh;
        this.Going = Going;
        this.Direction = Direction;
        this.Name = Name;
    }
}
