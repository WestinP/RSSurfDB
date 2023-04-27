package RSSurfDB.API.Modals;

import java.sql.Date;

public class WindModal {
    /*******************************************
     * Create_WindTable.sql
     * Scheme:
     * PK TS: datetime
     * Speed: float
     * Direction: float
     * FK Name: varchar(50)
     *******************************************/
    public Date TS;
    public float Speed;
    public float Direction;
    public String Name;

    public WindModal(Date TS, float Speed, float Direction, String Name) {
        this.TS = TS;
        this.Speed = Speed;
        this.Direction = Direction;
        this.Name = Name;
    }
}
