package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class RotationDataModel {

    private String rotationDepartmant;
    private String rotationDate;
    private String rotationStatus;

    public RotationDataModel(String rotationDepartmant, String rotationDate, String rotationStatus) {
        this.rotationDepartmant = rotationDepartmant;
        this.rotationDate = rotationDate;
        this.rotationStatus = rotationStatus;
    }

    public String getRotationDepartmant() {
        return rotationDepartmant;
    }

    public void setRotationDepartmant(String rotationDepartmant) {
        this.rotationDepartmant = rotationDepartmant;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }

    public String getRotationStatus() {
        return rotationStatus;
    }

    public void setRotationStatus(String rotationStatus) {
        this.rotationStatus = rotationStatus;
    }
}
