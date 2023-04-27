package RSSurfDB.API.Modals;

public class LocationModal {
    /********************************************************
     * -- Create_LocationTable
     * -- -- Main Table all other tables will be weak relations to this table
     * Scheme:
     * PK Name: varchar(50)
     * PK TS: datetime
     * CityName: varchar(50)
     ********************************************************/
    public String Name;
    public String TS;
    public String CityName;

    public LocationModal(String Name, String TS, String CityName) {
        this.Name = Name;
        this.TS = TS;
        this.CityName = CityName;
    }
}
