package Data;


public class SportsModel {

    private int sportId;
    private String sportName;

    public SportsModel() { }

    public SportsModel(String sportName) {
        this.sportName = sportName;
    }

    public SportsModel(int id, String sportName) {
        this.sportId = id;
        this.sportName = sportName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public String setSportName(String sportName) {
       return this.sportName = sportName;
    }
}
