package Data;


public class SportsModel {

    private int sportId;
    private String sportName;

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

    public String getSportName() {
        return sportName;
    }

}
