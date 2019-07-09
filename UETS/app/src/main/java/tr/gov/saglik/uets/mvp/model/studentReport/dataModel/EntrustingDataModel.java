package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class EntrustingDataModel {

    private String entrustingType;
    private String entrustingReason;
    private String startDate;
    private String endDate;
    private String programName;
    private String companyName;
    private String status;

    public EntrustingDataModel(String entrustingType, String entrustingReason, String startDate, String endDate, String programName, String companyName, String status) {
        this.entrustingType = entrustingType;
        this.entrustingReason = entrustingReason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programName = programName;
        this.companyName = companyName;
        this.status = status;
    }

    public String getEntrustingType() {
        return entrustingType;
    }

    public void setEntrustingType(String entrustingType) {
        this.entrustingType = entrustingType;
    }

    public String getEntrustingReason() {
        return entrustingReason;
    }

    public void setEntrustingReason(String entrustingReason) {
        this.entrustingReason = entrustingReason;
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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
