package RSSurfDB.API.Modals;

import java.sql.Date;

public class WindModal {
    /*******************************************
     * Create_WindTable.sql
     * Scheme:
     * PK TS: datetime
     * Speed: Double
     * Direction: Double
     * FK Name: varchar(50)
     *******************************************/
    public Date TS;
    public Double Speed;
    public Double Direction;
    public String Name;

    public WindModal(Date TS, Double Speed, Double Direction, String Name) {
        this.TS = TS;
        this.Speed = Speed;
        this.Direction = Direction;
        this.Name = Name;
    }
}
