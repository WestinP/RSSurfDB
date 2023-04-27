package RSSurfDB.API.Modals;

public class SwellModal {
    /**********************************************
     * Create_SwellTable.sql
     * Scheme:
     * PK TS: datetime
     * Height: float
     * Direction: float
     * Period: float
     * FK Name: varchar(50)
     **********************************************/
    public String TS;
    public float Height;
    public float Direction;
    public float Period;
    public String Name;

    public SwellModal(String TS, float Height, float Direction, float Period, String Name) {
        this.TS = TS;
        this.Height = Height;
        this.Direction = Direction;
        this.Period = Period;
        this.Name = Name;
    }
}
