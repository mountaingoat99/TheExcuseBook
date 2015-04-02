package Data;

public class ExcuseModel {

    private int excuseId;
    private int sportId;
    private String excuseName;

    public ExcuseModel(){}

    public ExcuseModel(int sportId, String excuseName) {
        this.sportId = sportId;
        this.excuseName = excuseName;
    }

    public ExcuseModel(int id, int sportId, String excuseName) {
        this.excuseId = id;
        this.sportId = sportId;
        this.excuseName = excuseName;
    }

    public int getExcuseId() {
        return excuseId;
    }

    public void setExcuseId(int excuseId) {
        this.excuseId = excuseId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getExcuseName() {
        return excuseName;
    }

    public void setExcuseName(String excuseName) {
        this.excuseName = excuseName;
    }
}
