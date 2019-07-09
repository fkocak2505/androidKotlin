package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class TransportInfoDataModel {

    private String transportType;
    private String transportReason;
    private String beforeProgram;
    private String transprotProgram;
    private String endDate;
    private String startDate;
    private String status;

    public TransportInfoDataModel(String transportType, String transportReason, String beforeProgram, String transprotProgram, String endDate, String startDate, String status) {
        this.transportType = transportType;
        this.transportReason = transportReason;
        this.beforeProgram = beforeProgram;
        this.transprotProgram = transprotProgram;
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportReason() {
        return transportReason;
    }

    public void setTransportReason(String transportReason) {
        this.transportReason = transportReason;
    }

    public String getBeforeProgram() {
        return beforeProgram;
    }

    public void setBeforeProgram(String beforeProgram) {
        this.beforeProgram = beforeProgram;
    }

    public String getTransprotProgram() {
        return transprotProgram;
    }

    public void setTransprotProgram(String transprotProgram) {
        this.transprotProgram = transprotProgram;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
