package RSSurfDB.API.Modals;

import java.time.LocalDateTime;

public class SwellModal {
    /**********************************************
     * Create_SwellTable.sql
     * Scheme:
     * PK TS: datetime
     * Height: double
     * Direction: double
     * Period: double
     * FK Name: varchar(50)
     **********************************************/
    public LocalDateTime TS;
    public double Height;
    public double Direction;
    public double Period;
    public String Name;

    public SwellModal(LocalDateTime TS, double Height, double Direction, double Period, String Name) {
        this.TS = TS;
        this.Height = Height;
        this.Direction = Direction;
        this.Period = Period;
        this.Name = Name;
    }
}
