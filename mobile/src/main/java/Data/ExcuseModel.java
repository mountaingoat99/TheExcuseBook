package Data;

public class ExcuseModel {

    private int sportId;
    private String excuseName;

    public ExcuseModel(){}

    public ExcuseModel(int sportId, String excuseName) {
        this.sportId = sportId;
        this.excuseName = excuseName;
    }

    public int getSportId() {
        return sportId;
    }

    public String getExcuseName() {
        return excuseName;
    }

    public String setExcuseName(String excuseName) {
        return this.excuseName = excuseName;
    }
}
