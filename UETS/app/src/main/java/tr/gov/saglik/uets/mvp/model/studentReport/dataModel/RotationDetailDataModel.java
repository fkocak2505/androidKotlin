package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class RotationDetailDataModel {

    private String ratationDepartment;
    private String rotationDate;
    private String startDate;
    private String endDate;
    private String programAdmin;
    private String companyName;
    private String status;

    public RotationDetailDataModel(String ratationDepartment, String rotationDate, String startDate, String endDate, String programAdmin, String companyName, String status) {
        this.ratationDepartment = ratationDepartment;
        this.rotationDate = rotationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programAdmin = programAdmin;
        this.companyName = companyName;
        this.status = status;
    }

    public String getRatationDepartment() {
        return ratationDepartment;
    }

    public void setRatationDepartment(String ratationDepartment) {
        this.ratationDepartment = ratationDepartment;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProgramAdmin() {
        return programAdmin;
    }

    public void setProgramAdmin(String programAdmin) {
        this.programAdmin = programAdmin;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
